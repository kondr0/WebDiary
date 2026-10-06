package com.example.WebDiary.service;

import com.example.WebDiary.dto.StudentRequest;
import com.example.WebDiary.dto.StudentResponse;
import com.example.WebDiary.exception.StudentNotFoundException;
import com.example.WebDiary.mapper.StudentMapper;
import com.example.WebDiary.model.Student;
import com.example.WebDiary.repository.StudentRepository;
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

    public StudentResponse getStudentById(UUID id){
        return studentRepository.findById(id).stream().map(studentMapper::toResponse).findFirst().orElseThrow(StudentNotFoundException::new);

    }

    public StudentResponse createStudent(StudentRequest studentRequest){

        Student student = studentMapper.toEntity(studentRequest);
        return studentMapper.toResponse(student);
    }

    public StudentResponse updateStudentById(StudentRequest studentRequest, UUID id){
        Student student = studentMapper.toEntity(studentRequest);
        student.setId(id);
        studentRepository.save(student);
        return studentMapper.toResponse(studentRepository.save(student));
    }

    public boolean deleteStudentById(UUID id){
        try{
            studentRepository.deleteById(id);
            return true;
        }
            catch (StudentNotFoundException e){
            }
        return false;
    }

    public StudentResponse getStudentByName(String name){
        return studentRepository.findByName(name).stream().map(studentMapper::toResponse).findFirst().orElseThrow(StudentNotFoundException::new);
    }



}
