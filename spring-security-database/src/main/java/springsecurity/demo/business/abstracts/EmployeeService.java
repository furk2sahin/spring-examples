package springsecurity.demo.business.abstracts;

import springsecurity.demo.entitites.concretes.Employee;

import java.util.List;

public interface EmployeeService{
    Employee add(Employee add);
    void delete(Long id);
    Employee update(Long id, Employee update);
    List<Employee> saveAll(List<Employee> saveList);
    List<Employee> getAll();
}
