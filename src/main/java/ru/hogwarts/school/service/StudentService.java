package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
@Service
public class StudentService {
    HashMap<Long, Student> studentMap;
    Long counter = 0L;

    public Student createStudent (Student newStudent){
        studentMap.put(counter,newStudent);
        counter++;
        return newStudent;
    }

    public Student getStudent (Long counter){
        return studentMap.get(counter);
    }

    public Student editStudent (Long counter,Student newStudent){
        studentMap.put(counter,newStudent);
        return studentMap.get(counter);
    }
    public Student removeStudent (Long counter){
        studentMap.remove(counter);
        return studentMap.get(counter);
    }
    public Collection<Student> getAllStudents(){
        return studentMap.values();
    }
}
