package com.spring.spring.mapper;

import com.spring.spring.dto.StudentDTO;
import com.spring.spring.entity.Student;

public class StudentMapper {

    public static StudentDTO toDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getUserName(),
                student.getEmail(),
                student.getAge(),
                student.getEnrolledDate()
        );
    }

    public static Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setUserName(dto.getUserName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        return student;
    }

    public static void updateEntity(Student student, StudentDTO dto) {
        if (dto.getUserName() != null) student.setUserName(dto.getUserName());
        if (dto.getEmail() != null) student.setEmail(dto.getEmail());
        if (dto.getAge() != null) student.setAge(dto.getAge());
    }
}