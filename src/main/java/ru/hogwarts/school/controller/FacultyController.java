package ru.hogwarts.school.controller;

import org.apache.catalina.connector.Response;
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
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty newFaculty){
        facultyService.createFaculty(newFaculty);
        return ResponseEntity.ok(newFaculty);
    }
    @GetMapping
    public ResponseEntity<Faculty> getFaculty(@RequestBody Long getFacultyNumber){
        return ResponseEntity.ok(facultyService.getFaculty(getFacultyNumber));
    }
    @PutMapping
    public ResponseEntity<Faculty> putFaculty(@RequestBody Long counter,@RequestBody Faculty putFaculty){
        facultyService.editFaculty(counter,putFaculty);
        return ResponseEntity.ok(putFaculty);
    }
    @DeleteMapping
    public ResponseEntity<Faculty> removeFaculty(@RequestBody Long removeFacultyNumber){
        facultyService.removeFaculty(removeFacultyNumber);
        return ResponseEntity.ok(facultyService.getFaculty(removeFacultyNumber));
    }
    @GetMapping("/all")
    public ResponseEntity<Collection<Faculty>> getFaculty(){
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }
}