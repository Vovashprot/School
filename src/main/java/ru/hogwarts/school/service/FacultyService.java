package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
@Service
public class FacultyService {
    HashMap<Long,Faculty> facultyMap;
    Long counter = 0L;
    public Faculty createFaculty (Faculty newFaculty){
        newFaculty.setId(counter++);
        facultyMap.put(newFaculty.getId(),newFaculty);
        return newFaculty;
    }

    public Faculty getFaculty (Long counter){
        return facultyMap.get(counter);
    }

    public Faculty editFaculty (Long counter,Faculty newFaculty){
        if (!facultyMap.containsKey(newFaculty.getId())) {
            return null;
        }
        facultyMap.put(counter,newFaculty);
        return newFaculty;
    }
    public Faculty removeFaculty (Long id){
        return facultyMap.remove(id);
    }
    public Collection<Faculty> getAllFaculties(){
        return facultyMap.values();
    }
}
