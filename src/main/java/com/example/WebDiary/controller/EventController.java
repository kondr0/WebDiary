package com.example.WebDiary.controller;

import com.example.WebDiary.dto.EventRequest;
import com.example.WebDiary.dto.EventResponse;
import com.example.WebDiary.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getEvents() {
        return ResponseEntity.ok(eventService.getEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable UUID id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest eventRequest) {
        EventResponse created = eventService.createEvent(eventRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEventById(@Valid @RequestBody EventRequest eventRequest,
                                                         @PathVariable UUID id) {
        return ResponseEntity.ok(eventService.updateEventById(eventRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable UUID id) {
        eventService.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{eventId}/students/{studentId}")
    public ResponseEntity<Void> addStudentToEvent(@PathVariable UUID eventId,
                                                  @PathVariable UUID studentId) {
        eventService.addStudentToEvent(eventId, studentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{eventId}/students/{studentId}")
    public ResponseEntity<Void> deleteStudentFromEvent(@PathVariable UUID eventId,
                                                       @PathVariable UUID studentId) {
        eventService.deleteStudentFromEvent(eventId, studentId);
        return ResponseEntity.noContent().build();
    }
}