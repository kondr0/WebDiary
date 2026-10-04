package com.example.WebDiary.mapper;

import com.example.WebDiary.dto.StudentRequest;
import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private final EventMapper eventMapper;

    public StudentMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public StudentResponse toResponse(Student student) {
        final StudentResponse response = new StudentResponse();

        response.setId(student.getId());
        response.setName(student.getName());
        response.setBirthday(student.getBirthday());
        response.setPresence(student.getPresence());
        response.setStudyDirection(student.getStudyDirection());
        response.setCourse(student.getCourse());
        response.setEndOfStudying(student.getEndOfStudying());

        response.setEvents(
                eventMapper.toResponseSet(student.getEvents())
        );

        return response;
    }

    public Student toEntity(StudentRequest studentRequest) {
        Student student = new Student();

        student.setName(studentRequest.getName());
        student.setBirthday(studentRequest.getBirthday());
        student.setPresence(studentRequest.getPresence());
        student.setStudyDirection(studentRequest.getStudyDirection());
        student.setCourse(studentRequest.getCourse());
        student.setEndOfStudying(studentRequest.getEndOfStudying());

        return student;
    }

}
