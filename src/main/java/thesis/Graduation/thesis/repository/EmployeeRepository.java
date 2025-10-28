package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
