package com.example.WebDiary.model;

import com.example.WebDiary.model.enums.AttendanceStatus;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class Attendance {
    private String name;
    private UUID studentId;
    private UUID eventId;
    private AttendanceStatus status;
}
