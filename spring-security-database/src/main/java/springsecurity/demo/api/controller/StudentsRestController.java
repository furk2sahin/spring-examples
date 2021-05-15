package springsecurity.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springsecurity.demo.business.concretes.StudentManager;
import springsecurity.demo.entitites.Student;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentsRestController {

    private StudentManager studentManager;

    @Autowired
    public StudentsRestController(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    @PostMapping("/add")
    public Student add(@RequestBody Student student){
        return studentManager.add(student);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") Long id){
        studentManager.delete(id);
    }

    @PutMapping
    public Student update(@RequestParam("id") Long id, @RequestBody Student student){
        return studentManager.update(id, student);
    }

    @PostMapping("/add-all")
    public List<Student> addAll(@RequestBody List<Student> students){
        return studentManager.saveAll(students);
    }

    @GetMapping
    public List<Student> getAll(){
        return studentManager.getAll();
    }
}
