package springsecurity.demo.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springsecurity.demo.business.abstracts.EmployeeService;
import springsecurity.demo.entitites.concretes.Employee;
import springsecurity.demo.repository.EmployeeRepository;
import springsecurity.demo.util.RoleParser;

import java.util.List;

@Service
public class EmployeeManager implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public Employee add(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        Employee employeeToUpdate = this.employeeRepository.getOne(id);
        employeeToUpdate.setAccountNonExpired(employee.isAccountNonExpired());
        employeeToUpdate.setAccountNonLocked(employee.isAccountNonLocked());
        employeeToUpdate.setCredentialsNonExpired(employee.isCredentialsNonExpired());
        employeeToUpdate.setEnabled(employee.isEnabled());
        employeeToUpdate.setUsername(employee.getUsername());
        employeeToUpdate.setPassword(employee.getPassword());
        employeeToUpdate.setFullname(employee.getFullname());
        employeeToUpdate.setEmail(employee.getEmail());
        employeeToUpdate.setRole(employee.getRole());
        String[] role = employeeToUpdate.getRole().split(",");
        employeeToUpdate.setGrantedAuthorities(RoleParser.parse(role));
        return employee;
    }

    @Override
    public List<Employee> saveAll(List<Employee> employees) {
        return employeeRepository.saveAll(employees);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
