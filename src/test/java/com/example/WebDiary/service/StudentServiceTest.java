package com.example.WebDiary.service;

import com.example.WebDiary.dto.EventResponse;
import com.example.WebDiary.dto.StudentRequest;
import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.mapper.StudentMapper;
import com.example.WebDiary.model.Event;
import com.example.WebDiary.model.Student;
import com.example.WebDiary.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;
    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    StudentResponse response;
    StudentRequest request;
    Student student;
    LocalDate birthday;
    UUID uuid;

    @BeforeEach
    void setUp() {

        birthday = LocalDate.of(2004, 3, 9);
        uuid = new UUID(1, 1);
        student = new Student(
                uuid,
                "Nikolay",
                birthday,
                9,
                "Информационные системы и технологии",
                4,
                "2026");


        Set<EventResponse> events = new HashSet<>();
        response = new StudentResponse(
                uuid,
                "Nikolay",
                birthday,
                9,
                "Информационные системы и технологии",
                4,
                "2026",
                events
        );

        request = new StudentRequest(
                "Nikolay",
                birthday,
                9,
                "Информационные системы и технологии",
                4,
                "2026"
        );
    }

    @Test
    void getStudents() {
    }

    @Test
    void getStudentById() {
        when(repository.findById(uuid))
                .thenReturn(Optional.of(student));

        when(studentMapper.toResponse(student))
                .thenReturn(response);

        StudentResponse result = studentService.getStudentById(uuid);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void createStudent() {

        when(repository.findById(uuid))
                .thenReturn(Optional.of(student));

        when(studentMapper.toResponse(student))
                .thenReturn(response);

        when(studentMapper.toEntity(request))
                .thenReturn(student);

        StudentResponse result = studentService.createStudent(request);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void updateStudentById() {

    }

    @Test
    void deleteStudentById() {
    }

    @Test
    void getStudentByName() {
    }
}