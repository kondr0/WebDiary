package com.example.WebDiary.dto;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public record StudentResponse(
        UUID id,
        String name,
        Date birthday,
        int presence,
        String studyDirection,
        int course,
        String endOfStudying,
        Set<EventResponse> events
) {
}
