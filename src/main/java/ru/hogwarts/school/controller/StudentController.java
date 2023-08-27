package ru.hogwarts.school.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
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
    public ResponseEntity<Student> addStudent(@RequestBody Student newStudent){
        studentService.createStudent(newStudent);
        return ResponseEntity.ok(newStudent);
    }
    @GetMapping
    public ResponseEntity<Student> getStudent(@RequestBody Long getStudentNumber){
        return ResponseEntity.ok(studentService.getStudent(getStudentNumber));
    }
    @PutMapping
    public ResponseEntity<Student> putStudent(@RequestBody Long counter,@RequestBody Student putStudent){
        studentService.editStudent(putStudent);
        return ResponseEntity.ok(putStudent);
    }
    @DeleteMapping
    public ResponseEntity<Student> removeStudent(@RequestBody Long removeStudentNumber){
        studentService.removeStudent(removeStudentNumber);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/all")
    public ResponseEntity<Collection<Student>> getStudent(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}
