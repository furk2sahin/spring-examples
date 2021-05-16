package springsecurity.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springsecurity.demo.entitites.concretes.Employee;

public interface EmployeeRepository extends JpaRepository<springsecurity.demo.entitites.concretes.Employee, Long> {
    Employee findByUsername(String username);
}
