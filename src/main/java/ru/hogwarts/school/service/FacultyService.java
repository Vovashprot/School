package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
@Service
public class FacultyService {
    HashMap<Long,Faculty> facultyMap;
    Long counter = 0L;
    public Faculty createFaculty (Faculty newFaculty){
        facultyMap.put(counter,newFaculty);
        counter++;
        return newFaculty;
    }

    public Faculty getFaculty (Long counter){
        return facultyMap.get(counter);
    }

    public Faculty editFaculty (Long counter,Faculty newFaculty){
        facultyMap.put(counter,newFaculty);
        return facultyMap.get(counter);
    }
    public Faculty removeFaculty (Long counter){
        facultyMap.remove(counter);
        return facultyMap.get(counter);
    }
}
