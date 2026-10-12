package com.example.WebDiary.service;

import com.example.WebDiary.model.Student;
import com.example.WebDiary.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService studentService;

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
    }

    @Test
    void getStudents() {
    }

    @Test
    void getStudentById() {
        when(repository.findById(uuid)).thenReturn(Optional.of(student));

        String result = studentService.getStudentById(uuid).name();

        assertThat(result).isEqualTo("Nikolay");
        verify(repository).findById(uuid);
    }

    @Test
    void createStudent() {
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