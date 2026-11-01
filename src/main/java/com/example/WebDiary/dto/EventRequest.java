package com.example.WebDiary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;


public record EventRequest (
        @NotBlank String name,
        @NotNull @PastOrPresent LocalDate date
){}
