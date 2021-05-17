package springsecurity.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springsecurity.demo.business.abstracts.CourseService;
import springsecurity.demo.entitites.concretes.Course;
import springsecurity.demo.entitites.concretes.Student;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CoursesRestController {

    private CourseService courseService;

    @Autowired
    public CoursesRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/get-by-id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Course getById(@RequestParam("id") Long id){
        return courseService.getById(id);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Course> getAll(){
        return courseService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('course:buy')")
    public Course add(@RequestParam("studentId") Long studentId, @RequestBody Course course){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof Student){
            Long id = ((Student) authentication.getPrincipal()).getId();
            if(id == studentId){
                return courseService.add(studentId, course);
            } else return null;
        }
        return null;
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Course update(@RequestParam("id") Long id, @RequestBody Course course){
        return courseService.update(id, course);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('course:delete')")
    public void delete(@RequestParam("id") Long id){
        courseService.delete(id);
    }

    @GetMapping("/get-by-student-id")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMINTRAINEE', 'ROLE_ADMIN')")
    public List<Course> findAllByStudentId(@RequestParam("studentId") Long studentId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication.getPrincipal() instanceof Student)){
            return courseService.findAllByStudentId(studentId);

        } else{
            Long id = ((Student) authentication.getPrincipal()).getId();
            if(id == studentId){
                return courseService.findAllByStudentId(studentId);
            } else return Collections.emptyList();
        }
    }
}
