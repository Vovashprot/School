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
    public Faculty addFaculty(@RequestBody Faculty newFaculty) {
        facultyService.createFaculty(newFaculty);
        return newFaculty;
    }

    @GetMapping
    public Faculty getFaculty(@RequestBody Long getFacultyNumber){
        return facultyService.getFaculty(getFacultyNumber);
    }

    @PutMapping
    public Faculty putFaculty(@RequestBody Long counter, @RequestBody Faculty putFaculty) {
        facultyService.editFaculty(counter, putFaculty);
        return putFaculty;
    }

    @DeleteMapping
    public Faculty removeFaculty(@RequestBody Long removeFacultyNumber) {
        facultyService.removeFaculty(removeFacultyNumber);
        return facultyService.getFaculty(removeFacultyNumber);
    }
}