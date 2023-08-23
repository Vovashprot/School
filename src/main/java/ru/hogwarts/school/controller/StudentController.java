package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("/Student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student newStudent){
        studentService.createStudent(newStudent);
        return newStudent;
    }
    @GetMapping
    public Student getStudent(@RequestBody Long getStudentNumber){
        studentService.getStudent(getStudentNumber);
        return studentService.getStudent(getStudentNumber);
    }
    @PutMapping
    public Student putStudent(@RequestBody Long counter,@RequestBody Student putStudent){
        studentService.editStudent(counter,putStudent);
        return putStudent;
    }
    @DeleteMapping
    public Student removeStudent(@RequestBody Long removeStudentNumber){
        studentService.removeStudent(removeStudentNumber);
        return studentService.getStudent(removeStudentNumber);
    }
    @GetMapping("/all")
    public Collection<Student> getStudent(){
        return studentService.getAllStudents();
    }
}
