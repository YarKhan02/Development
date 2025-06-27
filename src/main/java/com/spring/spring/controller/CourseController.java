package com.spring.spring.controller;

import com.spring.spring.dto.CourseDTO;
import com.spring.spring.service.CourseService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // --- Course CRUD ---

    // --- GET all courses ---
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    // --- GET course by ID ---
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- POST create course ---
    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO saved = courseService.createCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // --- PUT update course ---
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @Valid @RequestBody CourseDTO courseDTO) {
        return courseService.updateCourse(id, courseDTO)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Course with ID " + id + " not found."));
    }

    // --- DELETE course ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course with ID " + id + " not found.");
        }
    }
}
