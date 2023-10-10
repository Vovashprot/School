package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> findFaculty(Long id) {
        return facultyRepository.findById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public List<Faculty> getFacultyAccordingName(String name) {
        return facultyRepository.findFacultyByNameContainingIgnoreCase(name);
    }
    public List<Faculty> getFacultyAccordingColor(String color) {
        return facultyRepository.findFacultyByColorContainsIgnoreCase(color);
    }

    public Faculty findFacultyByStudent(Student student) {
        return facultyRepository.findFacultyByStudent(student);
    }
}