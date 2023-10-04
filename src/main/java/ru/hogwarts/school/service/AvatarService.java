package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repositories.AvatarRepository;
import ru.hogwarts.school.repositories.StudentRepository;

import javax.validation.Path;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
        var student = studentRepository.findById(studentId);
        if(student.isEmpty()){
            throw new IllegalStateException();
        }
        var dir = Path.of(avatarDir).toFile();
        if(!dir.exists()){
            Files.createDirectories(Path.of(avatarDir));
        }
        var path = avatarDir + "/" + student.get().getId()+ "_" + file.getName();
        try(var in = file.getInputStream();
        var out = new FileOutputStream(path);){
        in.transferTo(out);
        }


        var avatar = avatarRepository.findByStudentId(studentId).orElse(new Avatar());
        avatar.setFilePath();
        avatar.setData(file.getBytes());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setStudent(student.get());
        avatarRepository.save(avatar);
    }
}
