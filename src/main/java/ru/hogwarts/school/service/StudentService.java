package ru.hogwarts.school.service;



import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(Long id) {return studentRepository.getById(id);}

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsAccordingAge(int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> findStudentByAgeBetween(int minAge, int maxAge){
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public List<Student> findStudentByFaculty(Faculty faculty){
        return studentRepository.findByFacultyContaining(faculty);
    }

    public int getStudentAverageAge(){
        return studentRepository.getStudentAverageAge();
    }
    public int getStudentCount(){
        return studentRepository.getStudentCount();
    }
    public List<Student> getLastStudents(){
        return studentRepository.getLastStudents();
    }
}