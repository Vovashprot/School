package ru.hogwarts.school;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.FacultyRepository;
import ru.hogwarts.school.repositories.StudentRepository;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTestsTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    private StudentController studentController;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void contextLoads() {
        assertNotNull(studentController);
        assertNotNull(facultyController);
    }


    private long findLastStudentId() {
        List<Student> students = studentRepository.findAll();
        Student lastStudent = students.stream()
                .max(Comparator.comparing(Student::getId))
                .orElse(null);
        if (lastStudent == null) {
            throw new NullPointerException("таблица со студентами пуста");
        }
        return lastStudent.getId();
    }


    private long findLastFacultyId() {
        List<Faculty> faculties = facultyRepository.findAll();
        Faculty lastFaculty = faculties.stream()
                .max(Comparator.comparing(Faculty::getId))
                .orElse(null);
        if (lastFaculty == null) {
            throw new NullPointerException("таблица с факультетами пуста");
        }
        return lastFaculty.getId();
    }


    @Test
    void testPostStudent() {
        Student student = new Student(findLastStudentId() + 1, "Василий Теркин", 23);
        assertNotNull(this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/student", student, String.class));
    }


    @Test
    void testPostFaculty() {

        Faculty faculty = new Faculty(findLastFacultyId() + 1, "Пуфенжуй", "красновато-зеленый");

        assertNotNull(this.testRestTemplate.postForObject(
                "http://localhost:" + port + "/faculty", faculty, String.class));
    }


    @Test
    void findStudent() {
        assertNotNull(this.testRestTemplate.getForObject(
                "http://localhost:" + port + "/student/1", String.class));
    }


    @Test
    void findFaculty() {
        assertNotNull(this.testRestTemplate.getForObject(
                "http://localhost:" + port + "/faculty/1", String.class));
    }


    @Test
    public void testEditStudent() {

        Student student = new Student(findLastStudentId(), "Федор Конюхов", 78);


        this.testRestTemplate.put("http://localhost:" + port + "/student", student);


        Optional<Student> optionalStudent = studentRepository.findById(findLastStudentId());


        assertTrue(optionalStudent.isPresent());


        Student actualStudent = optionalStudent.get();
        assertEquals(student, actualStudent);
    }


    @Test
    public void testEditFaculty() { //специально ради интереса двумя разными методами сделал.
        Faculty faculty = new Faculty(findLastFacultyId(), "КотэРван", "серый");

        ResponseEntity<Faculty> response = facultyController.editFaculty(faculty);

        int actualStatusCodeValue = response.getStatusCodeValue();
        int expectedCode = 200;

        Assertions.assertEquals(expectedCode, actualStatusCodeValue, "коды не совпадают");
    }


    @Test
    void deleteStudentTest() {
        //нахожу последнего студента, сохраняю его в переменную, а так же и его id, ...
        Student lastStudent = studentRepository.findById(findLastStudentId()).orElse(null);
        Long lastStudentId = (lastStudent == null) ? null : lastStudent.getId();

        //...после чего я его удалю, используя запрос контроллера, и...
        this.testRestTemplate.delete("http://localhost:" + port + "/student/" + findLastStudentId());

        //...далее проверяю, совпадают ли id последнего студента до и после операции удаления
        assertNotEquals(findLastStudentId(), lastStudentId);
    }


    @Test
    void deleteFacultyTest() {
        Faculty lastFaculty = facultyRepository.findById(findLastFacultyId()).orElse(null);
        Long lastFacultyId = (lastFaculty == null) ? null : lastFaculty.getId();

        this.testRestTemplate.delete("http://localhost:" + port + "/faculty/" + findLastFacultyId());

        assertNotEquals(findLastFacultyId(), lastFacultyId);
    }


    @Test
    void getAllStudentsTest() {
        //создание заголовков
        HttpHeaders headers = new HttpHeaders();
//        headers.set("accept", "application/json");
//        headers.set("Authorization", "Bearer JWT TOKEN HERE");
        HttpEntity requestEntity = new HttpEntity<>(null, headers);
        //создание запроса через метод exchange
        ResponseEntity<List<Student>> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/student", HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<List<Student>>() {
                });
        //получение списка студентов из тела запроса
        List<Student> students = response.getBody();
        System.out.println("students = " + students);
        assertNotNull(students);
    }


    @Test
    void getAllFacultyTest() {
        //создание заголовков
        HttpHeaders headers = new HttpHeaders();
//        headers.set("accept", "application/json");
//        headers.set("Authorization", "Bearer JWT TOKEN HERE");
        HttpEntity requestEntity = new HttpEntity<>(null, headers);
        //создание запроса через метод exchange
        ResponseEntity<List<Faculty>> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/faculty", HttpMethod.GET, requestEntity,
                new ParameterizedTypeReference<List<Faculty>>() {
                });
        //получение списка студентов из тела запроса
        List<Faculty> faculties = response.getBody();
        System.out.println("faculties = " + faculties);
        assertNotNull(faculties);
    }


    @Test
    void getStudentsAccordingAge() {
        //создаю студента
        int studentsAge = 25;
        studentRepository.save(new Student(findLastStudentId() + 1, "Лена Целофанова", studentsAge));
        long studentId = findLastStudentId();

        //в блок try оборачиваю конструкцию, чтобы студент в блоке finally гарантированно удалялся.
        try {
            assertNotNull(this.testRestTemplate.getForObject(
                    "http://localhost:" + port + "/student/filter_by_age/" + studentsAge, String.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            studentRepository.deleteById(studentId);
        }
    }


    @Test
    void getFacultyAccordingColor() {
        //создаю студента
        String facultyColor = "малиновый";
        facultyRepository.save(new Faculty(findLastFacultyId() + 1, "тестовыйФакультет", facultyColor));
        long facultyId = findLastFacultyId();

        //в блок try оборачиваю конструкцию, чтобы факультет в блоке finally гарантированно удалялся.
        try {
            assertNotNull(this.testRestTemplate.getForObject(
                    "http://localhost:" + port + "/faculty/filter_by_color/?color=" + facultyColor, String.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            facultyRepository.deleteById(facultyId);
        }
    }


    @Test
    void findStudentByAgeBetween() {
        int studentsAgeMin = 25;
        int studentsAgeMax = 55;

        studentRepository.save(new Student(findLastStudentId() + 1, "Лена Целофанова", studentsAgeMin));
        long studentIdMin = findLastStudentId();

        studentRepository.save(new Student(findLastStudentId() + 1, "Лена Целофанова", studentsAgeMax));
        long studentIdMax = findLastStudentId();

        try {
            assertNotNull(this.testRestTemplate.getForObject(
                    "http://localhost:" + port + "/student/find_age_between/?minAge=" + studentsAgeMin +
                            "&maxAge=" + studentsAgeMax, String.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("studentIdMin  studentIdMax = " + studentIdMin + " " + studentIdMax);
            studentRepository.deleteById(findLastStudentId());
            studentRepository.deleteById(findLastStudentId());
        }
    }

    @Test
    void findStudentByFaculty() {
        int studentsAge = 25;
        Student student = new Student(findLastStudentId() + 1, "Лена Целофанова", studentsAge);
        studentRepository.save(student);

        try {
            assertNotNull(this.testRestTemplate.
                    getForObject(
                            "http://localhost:" + port + "/student/find_student_by_faculty", String.class, student));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            studentRepository.deleteById(findLastStudentId());
        }
    }

    @Test
    void findFacultyByStudent() {
        Faculty faculty = new Faculty(findLastFacultyId() + 1, "тестовыйФакультет", "красный");
        facultyRepository.save(faculty);

        try {
            assertNotNull(this.testRestTemplate.
                    getForObject(
                            "http://localhost:" + port + "/student/find_faculty_by_student", String.class, faculty));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            facultyRepository.deleteById(findLastFacultyId());
        }
    }
}