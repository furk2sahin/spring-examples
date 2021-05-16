package springsecurity.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springsecurity.demo.business.concretes.EmployeeManager;
import springsecurity.demo.business.concretes.RoleManager;
import springsecurity.demo.entitites.concretes.Employee;
import springsecurity.demo.util.RoleParser;

import java.util.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeesRestController {

    private EmployeeManager employeeManager;
    private RoleManager roleManager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeesRestController(EmployeeManager employeeManager, RoleManager roleManager, PasswordEncoder passwordEncoder) {
        this.employeeManager = employeeManager;
        this.roleManager = roleManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/get-by-username")
    public UserDetails getEmployeeByUsername(@RequestParam("username") String username){
        return employeeManager.loadUserByUsername(username);
    }

    @PostMapping("/add")
    public Employee add(@RequestBody Employee employee){
        String[] roles = employee.getRole().split(",");
        employee.setGrantedAuthorities(RoleParser.parse(roles));
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
       return employeeManager.add(employee);
    }

    @DeleteMapping
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRANIEE')")
    public void delete(@RequestParam("id") Long id){
        employeeManager.delete(id);
    }

    @PutMapping
    public Employee update(@RequestParam("id") Long id, @RequestBody Employee employee){
        roleManager.deleteRolesByUserId(id);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeManager.update(id, employee);
    }

    @PostMapping("/add-all")
    public List<Employee> addAll(@RequestBody List<Employee> employees){
        for(Employee employee : employees){
            String[] roles = employee.getRole().split(",");
            employee.setGrantedAuthorities(RoleParser.parse(roles));
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        }
        return employeeManager.saveAll(employees);
    }

    @GetMapping("/get-all")
    public List<Employee> getAll(){
        return employeeManager.getAll();
    }


}
