package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int minAge, int maxAge);
    List<Student> findByFacultyContaining(Faculty faculty);
    @Query(value = "select COUNT(*) from student",nativeQuery = true)
    int getStudentCount();
    @Query(value = "select AVG(age) from student",nativeQuery = true)
    int getStudentAverageAge();
    @Query(value = "select * from student order by id desc limit 5",nativeQuery = true)
    List<Student> getLastStudents();

}