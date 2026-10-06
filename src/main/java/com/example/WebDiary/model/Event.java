package com.example.WebDiary.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Date date;

    @ManyToMany(mappedBy = "events")
    private Set<Student> students = new HashSet<>();

    public Event() {}

    public Event(String name, Date date, Set<Student> students) {
        this.name = name;
        this.date = date;
        this.students = students;
    }

    public Event(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void deleteStudent(Student student) {
        this.students.remove(student);
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public void changeDate(Date date) {
        this.date = date;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Student> getStudents() {
        return students;
    }

}
