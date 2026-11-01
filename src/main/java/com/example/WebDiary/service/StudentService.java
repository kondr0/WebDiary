package com.example.WebDiary.service;

import com.example.WebDiary.dto.StudentRequest;
import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.exception.StudentNotFoundException;
import com.example.WebDiary.mapper.StudentMapper;
import com.example.WebDiary.model.Student;
import com.example.WebDiary.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.rmi.StubNotFoundException;
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

    public StudentResponse getStudentById(UUID id) {
        return studentRepository.findById(id).
                map(studentMapper::toResponse).
                orElseThrow(StudentNotFoundException::new);

    }

    public StudentResponse createStudent(StudentRequest request) {

        Student student = studentMapper.toEntity(request);
        Student savedStudent = studentRepository.save(student);

        return studentMapper.toResponse(savedStudent);
    }

    @Transactional
    public StudentResponse updateStudentById(StudentRequest request, UUID id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }

        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);

        studentMapper.updateStudent(student, request);

        return studentMapper.toResponse(student);
    }

    public void deleteStudentById(UUID id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException();
        }
        studentRepository.deleteById(id);
    }

    public StudentResponse getStudentByName(String name) {
        return studentRepository.findByName(name).stream().
                map(studentMapper::toResponse).findFirst().
                orElseThrow(StudentNotFoundException::new);
    }


}
