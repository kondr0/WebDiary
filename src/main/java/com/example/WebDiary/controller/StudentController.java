package com.example.WebDiary.controller;

import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.model.Student;
import com.example.WebDiary.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class StudentController {
    static StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public Student getStudentById(@RequestParam UUID id){
        return studentService.getStudentById(id);
    }

    @GetMapping
    public Page<StudentResponse> getStudents(Pageable pageable) {
        return studentService.getStudents(pageable);
    }
}
