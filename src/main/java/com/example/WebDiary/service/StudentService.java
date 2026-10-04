package com.example.WebDiary.service;

import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.exception.StudentNotFoundException;
import com.example.WebDiary.mapper.StudentMapper;
import com.example.WebDiary.model.Student;
import com.example.WebDiary.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    public Page<StudentResponse> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable).map(studentMapper::toResponse);
    }

    public Student getStudentByName(String name){
        return studentRepository.findByName(name).orElseThrow(() -> new StudentNotFoundException(name));
    }

    public Student getStudentById(UUID id){
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id.toString()));
    }



}
