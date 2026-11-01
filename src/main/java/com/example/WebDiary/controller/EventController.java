package com.example.WebDiary.controller;
//TODO: add unitTests
import com.example.WebDiary.dto.EventRequest;
import com.example.WebDiary.dto.EventResponse;
import com.example.WebDiary.dto.StudentRequest;
import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.model.Event;
import com.example.WebDiary.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {
    EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateStudentById(@Valid @RequestBody EventRequest eventRequest, @PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.updateEventById(eventRequest, id));
    }

    @PostMapping
    public ResponseEntity<EventResponse> createStudent(@Valid @RequestBody EventRequest eventRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(eventRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventResponse> deleteStudentById(@PathVariable UUID id){
        eventService.deleteEventById(id);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/{eventId}/students/{studentId}")
    public ResponseEntity<Void> addStudentToEvent(@PathVariable UUID eventId, @PathVariable UUID studentId){
        eventService.addStudentToEvent(eventId, studentId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{eventId}/students/{studentId}")
    public ResponseEntity<Void> deleteStudentFromEvent(@PathVariable UUID eventId, @PathVariable UUID studentId){
        eventService.deleteStudentFromEvent(eventId, studentId);
        return ResponseEntity.ok().build();
    }
}
