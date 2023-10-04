package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.service.AvatarService;

@RequestMapping("/avatar")
@RestController
public class AvatarController {
    private final AvatarService service;

    public AvatarController(AvatarService service) {
        this.service = service;
    }
    @PostMapping
    public void unload(@PathVariable long studentId, MultipartFile file){
        service.upload(studentId, file);
    }
}
