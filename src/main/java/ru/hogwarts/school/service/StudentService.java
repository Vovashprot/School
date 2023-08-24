package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
@Service
public class StudentService {
    HashMap<Long, Student> studentMap;
    Long counter = 0L;

    public Student createStudent (Student newStudent){
        newStudent.setId(counter++);
        studentMap.put(newStudent.getId(),newStudent);
        return newStudent;
    }

    public Student getStudent (Long counterLocal){
        if (counterLocal>counter){
            throw new RuntimeException();
        }
        return studentMap.get(counter);
    }

    public Student editStudent (Long counterLocal,Student newStudent){
        if (!studentMap.containsKey(newStudent.getId())) {
            return null;
        }
        studentMap.put(counter,newStudent);
        return studentMap.get(counter);
    }
    public Student removeStudent (Long id){
        return studentMap.get(id);
    }
    public Collection<Student> getAllStudents(){
        return studentMap.values();
    }
}
