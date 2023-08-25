package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
@Service
public class FacultyService {
    HashMap<Long,Faculty> facultyMap = new HashMap<>();
    long counter = 0L;
    public Faculty createFaculty (Faculty newFaculty){
        newFaculty.setId(counter++);
        facultyMap.put(newFaculty.getId(),newFaculty);
        return newFaculty;
    }

    public Faculty getFaculty (Long counterLocal){
        if (counterLocal>counter){
            throw new RuntimeException();
        }
        return facultyMap.get(counterLocal);
    }

    public Faculty editFaculty (Long counterLocal,Faculty newFaculty){
        if (!facultyMap.containsKey(newFaculty.getId())) {
            return null;
        }
        facultyMap.put(counterLocal,newFaculty);
        return facultyMap.get(counterLocal);
    }
    public Faculty removeFaculty (Long id){
        return facultyMap.remove(id);
    }
    public Collection<Faculty> getAllFaculties(){
        return facultyMap.values();
    }
}
