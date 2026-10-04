package com.example.WebDiary.dto;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class StudentResponse {

    private UUID id;
    private String name;
    private Date birthday;
    private boolean showBirthday;
    private int presence;
    private String studyDirection;
    private int course;
    private String endOfStudying;
    private Set<EventResponse> events;
}
