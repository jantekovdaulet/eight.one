package eight.one.services;

import eight.one.models.Courses;
import eight.one.models.Operators;

import java.util.List;

public interface OperatorService {

    List<Operators> getAllOperators();
    Operators getOperator(Long id);
    Operators addOperator(Operators operator);
    Operators saveOperator(Operators operator);
    void deleteOperator(Long id);

}
