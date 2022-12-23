package com.spring.hibernate.services;

import com.spring.hibernate.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);

    StudentDto findStudentById(Long id);

    List<StudentDto> findStudentByEmail(String email);

    StudentDto updateStudent(StudentDto studentDto);

    List<StudentDto> getAllStudents();

    void deleteStudentById(Long id);

    List<StudentDto> getStudentsByLastName(String lastName);

}
