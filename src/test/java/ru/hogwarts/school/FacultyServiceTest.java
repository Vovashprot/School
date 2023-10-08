//package ru.hogwarts.school;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ru.hogwarts.school.model.Faculty;
//import ru.hogwarts.school.repositories.FacultyRepository;
//import ru.hogwarts.school.service.FacultyService;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class FacultyServiceTest {
//    FacultyRepository facultyRepository;
//    @BeforeEach
//    public void setUp(){
//        facultyService = new FacultyService(facultyRepository);
//    }
//    FacultyService facultyService;
//    @Test
//    public void createFacultyTest(){
//        Faculty dude = new Faculty(0l,"dasda","red");
//        facultyService.createFaculty(dude);
//        assertEquals(facultyService.getAllFaculties().size() == 1,true);
//    }
//    @Test
//    public void removeFacultyTest(){
//        Faculty dude = new Faculty(0l,"dasda","red");
//        facultyService.createFaculty(dude);
//        assertEquals(facultyService.getAllFaculties().size() == 1,true);
//        facultyService.removeFaculty(0l);
//        assertEquals(facultyService.getAllFaculties().size() == 1,false);
//    }
//
//    @Test
//    public void getFacultyTest(){
//        Faculty dude = new Faculty(0l,"dasda","red");
//        facultyService.createFaculty(dude);
//        assertEquals(facultyService.getAllFaculties().size() == 1,true);
//        facultyService.getFaculty(0l);
//        assertEquals(facultyService.getFaculty(0l),dude);
//    }
//    @Test
//    public void editFacultyTest(){
//        Faculty dude = new Faculty(0l,"dasda","red");
//        Faculty dude2 = new Faculty(1l,"dassada","red");
//        facultyService.createFaculty(dude);
//        assertEquals(facultyService.editFaculty(dude2),dude2);
//    }
//}
//