package com.example.WebDiary.mapper;

import com.example.WebDiary.dto.EventResponse;
import com.example.WebDiary.model.Event;
import com.example.WebDiary.dto.EventRequest;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    public EventResponse toResponse(Event event)
    {
        EventResponse response = new EventResponse();

        response.setId(event.getId());
        response.setDate(event.getDate());
        response.setName(event.getName());

        return  response;
    }

    public Event toEntity(EventRequest eventRequest)
    {
        Event event = new Event();

        event.setName(eventRequest.getName());
        event.setDate(eventRequest.getDate());

        return event;
    }

    public Set<EventResponse> toResponseSet(Set<Event> events) {
        return events.stream()
                .map(this::toResponse)
                .collect(Collectors.toSet());
    }
}
