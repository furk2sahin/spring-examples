package springsecurity.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springsecurity.demo.business.concretes.StudentManager;
import springsecurity.demo.entitites.concretes.Student;
import springsecurity.demo.util.RoleParser;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentsRestController {

    private StudentManager studentManager;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public StudentsRestController(StudentManager studentManager, PasswordEncoder passwordEncoder) {
        this.studentManager = studentManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add")
    public Student add(@RequestBody Student student){
        String[] roles = student.getRole().split(",");
        student.setGrantedAuthorities(RoleParser.parse(roles));
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentManager.add(student);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void delete(@RequestParam("id") Long id){
        studentManager.delete(id);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Student update(@RequestParam("id") Long id, @RequestBody Student student){
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentManager.update(id, student);
    }

    @PostMapping("/add-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Student> addAll(@RequestBody List<Student> students){
        for(Student student : students){
            String[] roles = student.getRole().split(",");
            student.setGrantedAuthorities(RoleParser.parse(roles));
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }
        return studentManager.saveAll(students);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student> getAll(){
        return studentManager.getAll();
    }
}
