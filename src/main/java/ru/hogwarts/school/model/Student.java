package ru.hogwarts.school.model;

import javax.persistence.*;
import java.util.Objects;
@Entity

public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int age;
    @ManyToOne
    @JoinColumn (name = "Faculty_id")
    private Faculty faculty;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", faculty=" + faculty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getId() == student.getId() && getAge() == student.getAge() && getName().equals(student.getName()) && getFaculty().equals(student.getFaculty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAge(), getFaculty());
    }
}
