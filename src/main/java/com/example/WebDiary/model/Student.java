package com.example.WebDiary.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private Date birthday;
    private int presence;
    private String studyDirection;
    private int course;
    private String endOfStudying;

    @ManyToMany
    @JoinTable(
            name = "student_event",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private final Set<Event> events = new HashSet<>();

    public Student() {}

    public Student(String name, Date birthday, int presence, String studyDirection,
                   int course, String endOfStudying) {
        this.name = name;
        this.birthday = birthday;
        this.presence = presence;
        this.studyDirection = studyDirection;
        this.course = course;
        this.endOfStudying = endOfStudying;
    }

    public Set<Event> getEvents() {
        return events;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getPresence() {
        return presence;
    }

    public void setPresence(int presence) {
        this.presence = presence;
    }

    public String getStudyDirection() {
        return studyDirection;
    }

    public void setStudyDirection(String studyDirection) {
        this.studyDirection = studyDirection;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getEndOfStudying() {
        return endOfStudying;
    }

    public void setEndOfStudying(String endOfStudying) {
        this.endOfStudying = endOfStudying;
    }

    public void addEvent(Event event) {
        events.add(event);
        event.getStudents().add(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.getStudents().remove(this);
    }
}
