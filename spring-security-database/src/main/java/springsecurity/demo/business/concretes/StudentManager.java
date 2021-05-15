package springsecurity.demo.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springsecurity.demo.business.abstracts.StudentService;
import springsecurity.demo.entitites.Student;
import springsecurity.demo.repository.StudentRepository;

import java.util.List;

@Service
public class StudentManager implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentManager(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student update(Long id, Student student) {
        Student studentToUpdate = studentRepository.getOne(id);
        studentToUpdate.setStudentName(student.getStudentName());
        studentToUpdate.setStudentNumber(student.getStudentNumber());
        return studentRepository.save(studentToUpdate);
    }

    @Override
    public List<Student> saveAll(List<Student> students) {
        return studentRepository.saveAll(students);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
