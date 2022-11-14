package eight.one.controllers;

import eight.one.models.Operators;
import eight.one.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import eight.one.models.ApplicationRequest;
import eight.one.models.Courses;
import eight.one.services.ApplicationService;
import eight.one.services.CourseService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OperatorService operatorService;

    @GetMapping(value = "/")
    public String getAllApplications(Model model) {
        List<ApplicationRequest> applications = applicationService.getAllApplications();
        model.addAttribute("applications", applications);
        return "allapplications";
    }

    @GetMapping(value = "/addform")
    public String getApplicationForm(Model model) {
        List<Courses> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "applicationsform";
    }

    @PostMapping(value = "/addapp")
    public String addApplication(@RequestParam(name = "userName") String userName,
                                 @RequestParam(name = "courseId") Long id,
                                 @RequestParam(name = "commentary") String commentary,
                                 @RequestParam(name = "phone") String phone) {

        Courses course = courseService.getCourse(id);
        if (course != null) {

            ApplicationRequest application = new ApplicationRequest();
            application.setCommentary(commentary);
            application.setHandled(false);
            application.setUserName(userName);
            application.setPhone(phone);
            application.setCourse(course);

            applicationService.addApplication(application);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/details/{id}")
    public String getDetails(Model model,
                             @PathVariable(name = "id") Long id) {

        ApplicationRequest application = applicationService.getApplication(id);
        model.addAttribute("app", application);

        List<Operators> operators = operatorService.getAllOperators();
        operators.removeAll(application.getOperators());
        model.addAttribute("operators", operators);

        return "details";

    }

    @PostMapping(value = "/handleapp")
    public String handleApplication(@RequestParam(name = "id") Long id) {
        ApplicationRequest application = applicationService.getApplication(id);
        if (application != null) {
            application.setHandled(true);
            applicationService.saveApplication(application);
            return "redirect:/details/" + id;
        }
        return "redirect:/";
    }

    @PostMapping(value = "/deleteapp")
    public String deleteApplication(@RequestParam(name = "id") Long id) {
        ApplicationRequest application = applicationService.getApplication(id);
        if (application != null) {
            applicationService.deleteApplication(application);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/newapplications")
    public String getNewApplications(Model model) {
        List<ApplicationRequest> newApplications = applicationService.getNewApplications();
        model.addAttribute("newApplications", newApplications);
        return "newapplications";
    }

    @GetMapping(value = "/oldapplications")
    public String getOldApplications(Model model) {
        List<ApplicationRequest> oldApplications = applicationService.getOldApplications();
        model.addAttribute("oldApplications", oldApplications);
        return "oldapplications";
    }

    @PostMapping(value = "/assignoperator")
    public String assignOperator(@RequestParam(name = "applicationId") Long applicationId,
                                 @RequestParam(name = "operatorId") Long operatorId) {

        Operators oper = operatorService.getOperator(operatorId);
        if (oper != null) {

            ApplicationRequest application = applicationService.getApplication(applicationId);
            if (application != null) {

                List<Operators> operators = application.getOperators();
                if (operators == null) {
                    operators = new ArrayList<>();
                }
                operators.add(oper);
                application.setOperators(operators);
                applicationService.saveApplication(application);

                return "redirect:/details/" + applicationId;
            }

        }

        return "redirect:/";
    }

    @PostMapping(value = "/unassignoperator")
    public String unassignOperator(@RequestParam(name = "applicationId") Long applicationId,
                                 @RequestParam(name = "operatorId") Long operatorId) {

        Operators oper = operatorService.getOperator(operatorId);
        if (oper != null) {

            ApplicationRequest application = applicationService.getApplication(applicationId);
            if (application != null) {

                List<Operators> operators = application.getOperators();
                if (operators == null) {
                    operators = new ArrayList<>();
                }
                operators.remove(oper);
                application.setOperators(operators);
                applicationService.saveApplication(application);

                return "redirect:/details/" + applicationId;
            }

        }

        return "redirect:/";
    }
}
