package com.spring.spring.controller.external;

import com.spring.spring.dto.external.StudentArtistDTO;
import com.spring.spring.dto.external.StudentDTO;
import com.spring.spring.service.external.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    // Method to handle the request for fetching a student's artist by ID
    @GetMapping("/artist/{id}")
    public Mono<StudentArtistDTO> getStudentsArtist(@PathVariable int id) {
        return studentService.getStudentsArtists(id)
                .doOnError(error -> System.err.println("Error fetching students: " + error.getMessage()));
    }

    // Method to handle the request for creating a new student
    @PostMapping
    public Mono<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(studentDTO)
                .doOnNext(student -> System.out.println("Creating student: " + student.getName()))
                .doOnError(error -> System.err.println("Error creating student: " + error.getMessage()));
    }
}
