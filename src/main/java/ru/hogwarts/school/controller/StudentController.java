package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;
import java.util.Collection;
import java.util.List;

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
        return studentService.createStudent(newStudent);
    }
    @GetMapping
    public ResponseEntity<Student> getStudent(@RequestBody Long getStudentNumber){
        Student student = studentService.getStudent(getStudentNumber);
        if (student ==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @GetMapping("/byFaculty")
    public List<Student> getStudentByFaculty(@RequestParam String faculty){

        return studentService.findStudentByFaculty(faculty);
    }
    @GetMapping("byAge")
    public List<Student> getStudentByAge(@RequestParam int min, @RequestParam int max){

        return studentService.findStudentByAgeBetween( min,  max);
    }

    @PutMapping
    public ResponseEntity<Student> putStudent(@RequestBody Student putStudent){
        Student student = studentService.editStudent(putStudent);
        if (student==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
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
