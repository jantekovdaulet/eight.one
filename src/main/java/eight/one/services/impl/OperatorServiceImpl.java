package eight.one.services.impl;

import eight.one.models.Courses;
import eight.one.models.Operators;
import eight.one.repositories.CourseRepository;
import eight.one.repositories.OperatorRepository;
import eight.one.services.CourseService;
import eight.one.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    OperatorRepository operatorRepository;


    @Override
    public List<Operators> getAllOperators() {
        return operatorRepository.findAll();
    }

    @Override
    public Operators getOperator(Long id) {
        return operatorRepository.getOne(id);
    }

    @Override
    public Operators addOperator(Operators operator) {
        return operatorRepository.save(operator);
    }

    @Override
    public Operators saveOperator(Operators operator) {
        return operatorRepository.save(operator);
    }

    @Override
    public void deleteOperator(Long id) {
        operatorRepository.deleteById(id);
    }
}
