package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

@RestController
@RequestMapping ("/Faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty addStudent(@RequestBody Faculty newStudent) {
        facultyService.createFaculty(newStudent);
        return newStudent;
    }

    @GetMapping
    public Faculty getStudent(@RequestBody Long getStudent) {
        facultyService.getFaculty(getStudent);
        return facultyService.getFaculty(getStudent);
    }

    @PostMapping
    public Faculty addStudent(@RequestBody Long counter, @RequestBody Faculty putFaculty) {
        facultyService.editFaculty(counter, putFaculty);
        return putFaculty;
    }

    @GetMapping
    public Faculty removeStudent(@RequestBody Long removeFaculty) {
        facultyService.removeFaculty(removeFaculty);
        return facultyService.getFaculty(removeFaculty);
    }
}