package eight.one.repositories;

import eight.one.models.ApplicationRequest;
import eight.one.models.Operators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface OperatorRepository extends JpaRepository<Operators, Long> {
}
