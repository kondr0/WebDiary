package com.example.WebDiary.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record StudentRequest(
        @NotBlank String name,
        @NotNull @PastOrPresent LocalDate birthday,
        @NotNull @Min(0) Integer presence,
        @NotBlank String studyDirection,
        @NotNull @Min(1) Integer course,
        @NotBlank String endOfStudying
) {}