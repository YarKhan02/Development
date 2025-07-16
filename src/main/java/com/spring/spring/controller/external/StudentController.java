package com.spring.spring.controller.external;

import com.spring.spring.dto.external.StudentDTO;
import com.spring.spring.service.external.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Method to handle the request for fetching all students
    @GetMapping
    public Flux<StudentDTO> getAllStudents() {
        return studentService.getAllStudents()
                .doOnError(error -> System.err.println("Error fetching students: " + error.getMessage()));
    }
}
