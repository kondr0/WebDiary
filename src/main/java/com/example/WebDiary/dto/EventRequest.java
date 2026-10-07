package com.example.WebDiary.dto;

import java.util.Date;


public record EventRequest (
        String name,
        Date date
){}
