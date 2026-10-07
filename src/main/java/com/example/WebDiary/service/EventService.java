package com.example.WebDiary.service;

import com.example.WebDiary.dto.EventResponse;
import com.example.WebDiary.dto.StudentRequest;
import com.example.WebDiary.exception.EventNotFoundException;
import com.example.WebDiary.mapper.EventMapper;
import com.example.WebDiary.mapper.StudentMapper;
import com.example.WebDiary.model.Event;
import com.example.WebDiary.model.Student;
import com.example.WebDiary.repository.EventRepository;
import com.example.WebDiary.dto.EventRequest;
import com.example.WebDiary.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final StudentRepository studentRepository;

    public EventService(EventRepository eventRepository, EventMapper eventMapper, StudentRepository studentRepository) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.studentRepository = studentRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public EventResponse getEventById(UUID id) {
        return eventRepository.findById(id).stream().map(eventMapper::toResponse).findFirst().orElseThrow(EventNotFoundException::new);
    }

    public EventResponse createEventById(EventRequest eventRequest) {
        Event event = eventMapper.toEntity(eventRequest);
        eventRepository.save(event);
        return eventMapper.toResponse(event);
    }

    public EventResponse updateEventById(EventRequest eventRequest, UUID id) {
        Event event = eventMapper.toEntity(eventRequest);
        event.setId(id);
        eventRepository.save(event);
        return eventMapper.toResponse(event);
    }

    public boolean deleteEventById(UUID id) {
        eventRepository.deleteById(id);
        return true;
    }

    public void addStudentToEvent(UUID eventId, UUID studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(EventNotFoundException::new);
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        student.addEvent(event);
        eventRepository.save(event);
        studentRepository.save(student);
    }

    public void deleteStudentFromEvent(UUID eventId, UUID studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(EventNotFoundException::new);
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        student.removeEvent(event);
        eventRepository.save(event);
        studentRepository.save(student);
    }

}
