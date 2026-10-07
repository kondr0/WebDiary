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

        final StudentResponse response = new StudentResponse(
                student.getId(),
                student.getName(),
                student.getBirthday(),
                student.getPresence(),
                student.getStudyDirection(),
                student.getCourse(),
                student.getEndOfStudying(),
                eventMapper.toResponseSet(student.getEvents())
                );

        return response;
    }

    public Student toEntity(StudentRequest studentRequest) {
        Student student = new Student();

        student.setName(studentRequest.name());
        student.setBirthday(studentRequest.birthday());
        student.setPresence(studentRequest.presence());
        student.setStudyDirection(studentRequest.studyDirection());
        student.setCourse(studentRequest.course());
        student.setEndOfStudying(studentRequest.endOfStudying());

        return student;
    }

}
