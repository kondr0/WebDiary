package com.example.WebDiary.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Date date;
    private List<String> students = new ArrayList<>();

    public Event(String name, Date date, List<String> students) {
        this.name = name;
        this.date = date;
        this.students = students;
    }

    public Event(String name) {
        this.name = name;
    }

    public void addStudent(String student) {
        this.students.add(student);
    }

    public void deleteStudent(String student) {
        this.students.remove(student);
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public void changeDate(Date date) {
        this.date = date;
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

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }
}
