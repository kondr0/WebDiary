package com.example.WebDiary.controller;

import com.example.WebDiary.model.Student;
import com.example.WebDiary.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    static StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public Student getStudentById(@RequestParam String id){
        return
    }
}
