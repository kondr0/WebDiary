package com.example.WebDiary.dto;

import java.util.Date;

public class StudentRequest {

    private String name;
    private Date birthday;
    private int presence;
    private String studyDirection;
    private int course;
    private String endOfStudying;

    public StudentRequest() {}

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
}
