//package ru.hogwarts.school;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.hogwarts.school.model.Student;
//import ru.hogwarts.school.repositories.StudentRepository;
//import ru.hogwarts.school.service.StudentService;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//public class StudentServiceTest {
//    StudentRepository studentRepository;
//    @BeforeEach
//    public void setUp(){
//        studentService = new StudentService(studentRepository);
//    }
//    StudentService studentService;
//    @Test
//    public void createStudentTest(){
//        Student dude = new Student(0l,"dasda",15);
//        studentService.createStudent(dude);
//        assertEquals(studentService.getAllStudents().size() == 1,true);
//    }
//    @Test
//    public void removeStudentTest(){
//        Student dude = new Student(0l,"dasda",15);
//        studentService.createStudent(dude);
//        assertEquals(studentService.getAllStudents().size() == 1,true);
//        studentService.removeStudent(0l);
//        assertEquals(studentService.getAllStudents().size() == 1,false);
//    }
//
//    @Test
//    public void getStudentTest(){
//        Student dude = new Student(0l,"dasda",15);
//        studentService.createStudent(dude);
//        assertEquals(studentService.getAllStudents().size() == 1,true);
//        studentService.getStudent(0l);
//        assertEquals(studentService.getStudent(0l),dude);
//    }
//    @Test
//    public void editStudentTest(){
//        Student dude = new Student(0l,"dasda",15);
//        Student dude2 = new Student(1l,"dassada",15);
//        studentService.createStudent(dude);
//        assertEquals(studentService.editStudent(dude2),dude2);
//    }
//}
//