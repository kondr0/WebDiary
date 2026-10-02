package com.example.WebDiary.service;

import com.example.WebDiary.exception.StudentNotFoundException;
import com.example.WebDiary.model.Student;
import com.example.WebDiary.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {
    private static StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(UUID id){
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id.toString()));
    }
}
