package springsecurity.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<springsecurity.demo.entitites.concretes.Employee, Long> {
}
