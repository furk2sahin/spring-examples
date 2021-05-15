package springsecurity.demo.business.abstracts;

import springsecurity.demo.entitites.Student;

import java.util.List;

public interface StudentService {
    Student add(Student add);
    void delete(Long id);
    Student update(Long id, Student update);
    List<Student> saveAll(List<Student> saveList);
    List<Student> getAll();
}
