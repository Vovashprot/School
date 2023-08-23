package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
@Service
public class FacultyService {
    HashMap<Long,Faculty> FacultyMap;
    Long counter = 0L;
}
