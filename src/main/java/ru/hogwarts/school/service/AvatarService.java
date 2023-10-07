package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final String avatarDir;

    public AvatarService(AvatarRepository avatarRepository,
                         StudentRepository studentRepository,
                         @Value("${avatars.dir}")String avatarDir) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
        this.avatarDir = avatarDir;
    }

    public void upload(long studentId, MultipartFile file) throws IOException {
        var student = studentRepository.findById(studentId).orElseThrow(
                () -> new StudentNotFoundException()
        );
        File dir = Path.of(avatarDir).toFile();
        if(!dir.exists()){
            Files.createDirectories(Path.of(avatarDir));
        }
        var dotIndex = file.getName().lastIndexOf(',');
        var ext = file.getName().substring(dotIndex + 1);
        var path = avatarDir + "/" + student.getId()+ "_" + file.getName() + "." + ext;

        try(var in = file.getInputStream();
            var out = new BufferedOutputStream(new FileOutputStream(path))){
            in.transferTo(out);
        }


        var avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setFilePath(path);
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student);
        avatarRepository.save(avatar);
    }
    public Avatar find(long studentId){
        return avatarRepository.findByStudentId(studentId).orElse(null);
    }
}
