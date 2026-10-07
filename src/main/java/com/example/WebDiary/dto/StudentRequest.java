package com.example.WebDiary.dto;

import java.util.Date;

public record StudentRequest (

    String name,
    Date birthday,
    int presence,
    String studyDirection,
    int course,
    String endOfStudying
){}