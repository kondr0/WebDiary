package com.example.WebDiary.controller;

import com.example.WebDiary.dto.StudentRequest;
import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.model.Student;
import com.example.WebDiary.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.UUID;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/{id}")
    public ResponseEntity getStudentById(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStudentById(@RequestBody StudentRequest studentRequest, @PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.updateStudentById(studentRequest, id));
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody StudentRequest studentRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudentById(@PathVariable UUID id){
        if (studentService.deleteStudentById(id)){
            return  ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping
    public Page<StudentResponse> getStudents(Pageable pageable) {
        return studentService.getStudents(pageable);
    }

    @GetMapping("/search")
    public ResponseEntity getStudentByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentByName(name));
    }
}
