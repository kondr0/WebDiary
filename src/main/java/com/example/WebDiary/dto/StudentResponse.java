package com.example.WebDiary.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record StudentResponse(
        UUID id,
        String name,
        LocalDate birthday,
        int presence,
        String studyDirection,
        int course,
        String endOfStudying,
        Set<EventResponse> events
) {
}
