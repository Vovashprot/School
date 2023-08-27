package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty (Faculty newFaculty){
        return facultyRepository.save(newFaculty);
    }

    public Faculty getFaculty (Long counterLocal){
        return facultyRepository.getById(counterLocal);
    }

    public Faculty editFaculty (Faculty newFaculty){
        return facultyRepository.save(newFaculty);
    }
    public void removeFaculty (Long id){
        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }
}
