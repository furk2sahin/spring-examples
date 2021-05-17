package springsecurity.demo.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springsecurity.demo.business.abstracts.CourseService;
import springsecurity.demo.entitites.concretes.Course;
import springsecurity.demo.entitites.concretes.Role;
import springsecurity.demo.entitites.concretes.Student;
import springsecurity.demo.repository.CourseRepository;
import springsecurity.demo.repository.StudentRepository;

import java.util.Iterator;
import java.util.List;

@Service
public class CourseManager implements CourseService {

    private CourseRepository courseRepository;
    private JdbcTemplate jdbcTemplate;
    private StudentManager studentManager;
    private StudentRepository studentRepository;

    @Autowired
    public CourseManager(CourseRepository courseRepository,
                         JdbcTemplate jdbcTemplate,
                         StudentManager studentManager,
                         StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.studentManager = studentManager;
        this.studentRepository = studentRepository;
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
    public Course add(Long studentId, Course course) {
        Student student = studentRepository.getOne(studentId);
        student.getCourses().add(course);
        Student updatedStudent = studentManager.update(studentId, student);
        Course courseReturn = null;
        for(Iterator<Course> iterator = updatedStudent.getCourses().iterator(); iterator.hasNext();){
            courseReturn = iterator.next();
        }
        return courseReturn;
    }

    @Override
    public List<Course> findAllByStudentId(Long id) {
        String sql = "select * from courses where student_id=?";
        return jdbcTemplate.query(
                sql, new Object[]{id},
                (rs, rowNum) ->  new Course(rs.getLong("id"), rs.getString("course_name")));
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
