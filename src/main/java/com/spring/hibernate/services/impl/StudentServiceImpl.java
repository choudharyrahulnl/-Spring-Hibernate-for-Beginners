package com.spring.hibernate.services.impl;

import com.spring.hibernate.dtos.StudentDto;
import com.spring.hibernate.entities.Student;
import com.spring.hibernate.mappers.StudentMapper;
import com.spring.hibernate.repositories.StudentRepository;
import com.spring.hibernate.services.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDto findStudentById(Long id) {
        Student studentEntity = studentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return studentMapper.toDto(studentEntity);
    }

    @Override
    public List<StudentDto> findStudentByEmail(String email) {
        List<Student> studentList = studentRepository.findByEmail(email);
        return studentList.stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        if(studentDto.getId() == null) {
            throw new IllegalArgumentException("Student ID is missing");
        }
        Student studentEntity = studentRepository.findById(studentDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        studentMapper.partialUpdate(studentDto, studentEntity);
        return studentMapper.toDto(studentEntity);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStudentById(Long id) {
        Student studentEntity = studentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if(studentEntity.getId() != null) {
            studentRepository.deleteById(id);
        }
    }

    @Override
    public List<StudentDto> getStudentsByLastName(String lastName) {
        List<Student> studentList = studentRepository.findByLastName(lastName);
        return studentList.stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }
}
