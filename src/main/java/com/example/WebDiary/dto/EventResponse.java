package com.example.WebDiary.dto;

import java.util.Date;
import java.util.UUID;

public record EventResponse(
        UUID id,
        String name,
        Date date
) {
}
