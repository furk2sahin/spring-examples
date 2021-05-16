package springsecurity.demo.business.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;
import springsecurity.demo.entitites.concretes.Student;

import java.util.List;

public interface StudentService extends UserDetailsService {
    Student add(Student add);
    void delete(Long id);
    Student update(Long id, Student update);
    List<Student> saveAll(List<Student> saveList);
    List<Student> getAll();
}
