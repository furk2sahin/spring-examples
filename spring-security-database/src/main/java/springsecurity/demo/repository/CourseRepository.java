package springsecurity.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springsecurity.demo.entitites.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
