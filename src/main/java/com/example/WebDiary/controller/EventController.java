package com.example.WebDiary.controller;
//TODO: add student adding to event
//TODO: add unitTests
import com.example.WebDiary.dto.EventRequest;
import com.example.WebDiary.dto.EventResponse;
import com.example.WebDiary.dto.StudentRequest;
import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.model.Event;
import com.example.WebDiary.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public ResponseEntity<EventResponse> updateStudentById(@RequestBody EventRequest eventRequest, @PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.updateEventById(eventRequest, id));
    }

    @PostMapping
    public ResponseEntity<EventResponse> createStudent(@RequestBody EventRequest eventRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEventById(eventRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventResponse> deleteStudentById(@PathVariable UUID id){
        eventService.deleteEventById(id);
        return  ResponseEntity.ok().build();
    }
}
