package com.example.WebDiary.service;

import com.example.WebDiary.dto.EventResponse;
import com.example.WebDiary.dto.StudentRequest;
import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.mapper.StudentMapper;
import com.example.WebDiary.model.Event;
import com.example.WebDiary.model.Student;
import com.example.WebDiary.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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
        Pageable pageable = Pageable.unpaged();
        Page<Student> page = new PageImpl<>(List.of(student));

        when(repository.findAll(pageable))
                .thenReturn(page);
        when(studentMapper.toResponse(student))
                .thenReturn(response);

        Page<StudentResponse> result = studentService.getStudents(pageable);

        assertThat(result.getContent()).containsExactly(response);
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

        when(repository.save(student))
                .thenReturn(student);

        when(studentMapper.toResponse(student))
                .thenReturn(response);

        when(studentMapper.toEntity(request))
                .thenReturn(student);

        StudentResponse result = studentService.createStudent(request);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void updateStudentById() {
        when(repository.findById(uuid))
                .thenReturn(Optional.of(student));
        when(studentMapper.toEntity(request))
                .thenReturn(student);
        when(repository.save(student))
                .thenReturn(student);
        when(studentMapper.toResponse(student))
                .thenReturn(response);

        StudentResponse result = studentService.updateStudentById(request, uuid);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void deleteStudentById() {
        studentService.deleteStudentById(uuid);

        verify(repository).deleteById(uuid);
    }

    @Test
    void getStudentByName() {
        when(repository.findByName("Nikolay"))
                .thenReturn(Optional.of(student));
        when(studentMapper.toResponse(student))
                .thenReturn(response);

        StudentResponse result = studentService.getStudentByName("Nikolay");

        assertThat(result).isEqualTo(response);
    }
}