package springsecurity.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springsecurity.demo.business.abstracts.CourseService;
import springsecurity.demo.entitites.concretes.Course;

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
    public Course getById(@RequestParam("id") Long id){
        return courseService.getById(id);
    }

    @GetMapping("/get-all")
    public List<Course> getAll(){
        return courseService.getAll();
    }

    @PostMapping
    public Course add(@RequestBody Course course){
        return courseService.add(course);
    }

    @PutMapping
    public Course update(@RequestParam("id") Long id, @RequestBody Course course){
        return courseService.update(id, course);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") Long id){
        courseService.delete(id);
    }
}
