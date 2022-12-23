package com.spring.hibernate.apis;

import com.spring.hibernate.dtos.StudentDto;
import com.spring.hibernate.services.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentsApi {

    private final StudentService studentService;

    public StudentsApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody @NotNull @Valid StudentDto studentDto) {
        return new ResponseEntity<>(studentService.saveStudent(studentDto), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody @NotNull @Valid StudentDto studentDto) {
        return new ResponseEntity<>(studentService.updateStudent(studentDto), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<StudentDto>> findStudentsByLastName(@RequestParam("lastName") String lastName) {
        return new ResponseEntity<>(studentService.getStudentsByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.findStudentById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/by-email")
    public ResponseEntity<List<StudentDto>> findStudentByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(studentService.findStudentByEmail(email), HttpStatus.OK);
    }
}
