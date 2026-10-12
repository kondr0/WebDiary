package com.example.WebDiary.dto;

import java.time.LocalDate;

public record StudentRequest (

    String name,
    LocalDate birthday,
    int presence,
    String studyDirection,
    int course,
    String endOfStudying
){}