package eight.one.services;

import eight.one.models.ApplicationRequest;

import java.util.List;

public interface ApplicationService {

    ApplicationRequest addApplication(ApplicationRequest application);
    List<ApplicationRequest> getAllApplications();
    ApplicationRequest getApplication(Long id);
    void deleteApplication(ApplicationRequest application);
    ApplicationRequest saveApplication(ApplicationRequest application);
    List<ApplicationRequest> getNewApplications();
    List<ApplicationRequest> getOldApplications();

}
