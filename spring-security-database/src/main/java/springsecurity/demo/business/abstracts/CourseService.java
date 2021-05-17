package springsecurity.demo.business.abstracts;

import springsecurity.demo.entitites.concretes.Course;

import java.util.List;

public interface CourseService {
    Course getById(Long id);
    List<Course> getAll();
    Course add(Long studentId, Course course);
    Course update(Long id, Course course);
    void delete(Long id);
    List<Course> findAllByStudentId(Long id);
}
