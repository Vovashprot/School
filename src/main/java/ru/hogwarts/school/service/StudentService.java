package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent (Student newStudent){
        return studentRepository.save(newStudent);
    }

    public Student getStudent (Long counterLocal){
        return studentRepository.getById(counterLocal);
    }

    public Student editStudent (Student newStudent){
        return studentRepository.save(newStudent);
    }
    public void removeStudent (Long id){
        studentRepository.deleteById(id);
    }
    public Collection<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public List<Student> findStudentByAgeBetween(int minAge, int maxAge){
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public List<Student> findStudentByFaculty(String name){
        return studentRepository.findByFacultyContaining(name);
    }
}
