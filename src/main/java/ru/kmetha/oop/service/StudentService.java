package ru.kmetha.oop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kmetha.oop.entity.Student;
import ru.kmetha.oop.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    StudentRepository studentRepository;

    public Student show(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> index() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student save(Student student) {
        if (student.getId() != null) {
            Optional<Student> studentFromDBOptional = studentRepository.findById(student.getId());
            if (studentFromDBOptional.isPresent()) {
                Student studentFromDB = studentFromDBOptional.get();
                studentFromDB.setName(student.getName());
                studentFromDB.setAge(student.getAge());
                return studentRepository.save(studentFromDB);
            }
        }
        return studentRepository.save(student);
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }
}
