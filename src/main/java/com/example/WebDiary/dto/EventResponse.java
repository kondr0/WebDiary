package com.example.WebDiary.dto;

import java.time.LocalDate;
import java.util.UUID;

public record EventResponse(
        UUID id,
        String name,
        LocalDate date
) {
}
