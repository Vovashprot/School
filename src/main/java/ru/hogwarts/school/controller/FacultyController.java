package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/Faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty newFaculty){
        facultyService.createFaculty(newFaculty);
        return facultyService.createFaculty(newFaculty);
    }
    @GetMapping
    public ResponseEntity<Faculty> getFaculty(@RequestBody Long getFacultyNumber){
        Faculty faculty = facultyService.getFaculty(getFacultyNumber);
        if (faculty ==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }
    @PutMapping
    public ResponseEntity<Faculty> putFaculty(@RequestBody Faculty putFaculty){
        Faculty faculty = facultyService.editFaculty(putFaculty);
        if (faculty==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(faculty);
    }
    @DeleteMapping
    public ResponseEntity<Faculty> removeFaculty(@RequestBody Long removeFacultyNumber){
        facultyService.removeFaculty(removeFacultyNumber);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/all")
    public ResponseEntity<Collection<Faculty>> getFaculty(){
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }
}