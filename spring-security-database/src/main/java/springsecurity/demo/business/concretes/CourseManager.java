package springsecurity.demo.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springsecurity.demo.business.abstracts.CourseService;
import springsecurity.demo.entitites.Course;
import springsecurity.demo.repository.CourseRepository;

import java.util.List;

@Service
public class CourseManager implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseManager(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course add(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course update(Long id, Course course) {
        Course courseToUpdate = courseRepository.getOne(id);
        courseToUpdate.setCourseName(course.getCourseName());
        return courseRepository.save(courseToUpdate);
    }

    @Override
    public void delete(Long id) {
         courseRepository.deleteById(id);
    }
}
