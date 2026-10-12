package com.example.WebDiary.dto;

import java.time.LocalDate;


public record EventRequest (
        String name,
        LocalDate date
){}
