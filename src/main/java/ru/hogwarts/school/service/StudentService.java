package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
@Service
public class StudentService {
    HashMap<Long, Student> studentMap = new HashMap<>();
    long counter = 0L;

    public Student createStudent (Student newStudent){
        newStudent.setId(counter++);
        studentMap.put(newStudent.getId(),newStudent);
        return newStudent;
    }

    public Student getStudent (Long counterLocal){
        if (counterLocal>counter){
            throw new RuntimeException();
        }
        return studentMap.get(counterLocal);
    }

    public Student editStudent (Long counterLocal,Student newStudent){
        if (!studentMap.containsKey(newStudent.getId())) {
            return null;
        }
        studentMap.put(counterLocal,newStudent);
        return studentMap.get(counterLocal);
    }
    public Student removeStudent (Long id){
        return studentMap.remove(id);
    }
    public Collection<Student> getAllStudents(){
        return studentMap.values();
    }
}
