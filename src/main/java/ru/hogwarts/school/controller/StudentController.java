package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;
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
    public Student getStudent(@RequestBody Long getStudent){
        studentService.getStudent(getStudent);
        return studentService.getStudent(getStudent);
    }
    @PostMapping
    public Student addStudent(@RequestBody Long counter,@RequestBody Student putStudent){
        studentService.editStudent(counter,putStudent);
        return putStudent;
    }
    @GetMapping
    public Student removeStudent(@RequestBody Long removeStudent){
        studentService.removeStudent(removeStudent);
        return studentService.getStudent(removeStudent);
    }
}
