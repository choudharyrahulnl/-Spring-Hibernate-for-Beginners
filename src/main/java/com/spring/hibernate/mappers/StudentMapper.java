package com.spring.hibernate.mappers;

import com.spring.hibernate.dtos.StudentDto;
import com.spring.hibernate.entities.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentMapper {
    Student toEntity(StudentDto studentDto);

    StudentDto toDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(StudentDto studentDto, @MappingTarget Student student);
}