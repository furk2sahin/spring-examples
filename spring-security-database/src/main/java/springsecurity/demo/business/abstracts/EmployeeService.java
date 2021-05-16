package springsecurity.demo.business.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;
import springsecurity.demo.entitites.concretes.Employee;

import java.util.List;

public interface EmployeeService extends UserDetailsService{
    Employee add(Employee add);
    void delete(Long id);
    Employee update(Long id, Employee update);
    List<Employee> saveAll(List<Employee> saveList);
    List<Employee> getAll();
}
