package com.spring.spring.service;

import com.spring.spring.dto.StudentDTO;
import com.spring.spring.entity.Student;
import com.spring.spring.mapper.StudentMapper;
import com.spring.spring.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students as DTOs
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .toList();
    }

    // Get a student by ID as DTO
    public Optional<StudentDTO> getStudentById(int id) {
        return studentRepository.findById(id)
                .map(StudentMapper::toDTO);
    }

    // Create a new student and return as DTO
    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.toEntity(studentDTO);
        Student saved = studentRepository.save(student);
        return StudentMapper.toDTO(saved);
    }

    // Update an existing student and return as DTO if found
    public Optional<StudentDTO> updateStudent(int id, StudentDTO studentDTO) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    StudentMapper.updateEntity(existingStudent, studentDTO);
                    Student saved = studentRepository.save(existingStudent);
                    return StudentMapper.toDTO(saved);
                });
    }

    // Delete a student by ID, return true if deleted
    public boolean deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
