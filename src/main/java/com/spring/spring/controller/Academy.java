package com.spring.spring.controller;

import com.spring.spring.dto.CourseDTO;
import com.spring.spring.dto.StudentDTO;
import com.spring.spring.entity.Course;
import com.spring.spring.entity.Student;

import com.spring.spring.mapper.CourseMapper;
import com.spring.spring.mapper.StudentMapper;
import com.spring.spring.repository.CourseRepository;
import com.spring.spring.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Academy {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public Academy(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Spring Academy!";
    }

    // --- Student CRUD ---

    // --- GET all students ---
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentDTOs = studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }

    // --- GET student by ID ---
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id) {
        return studentRepository.findById(id)
                .map(student -> ResponseEntity.ok(StudentMapper.toDTO(student)))
                .orElse(ResponseEntity.notFound().build());
    }

    // --- POST create student ---
    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        Student student = StudentMapper.toEntity(studentDTO);
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(201).body(StudentMapper.toDTO(saved));
    }

    // --- PUT update student ---
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @Valid @RequestBody StudentDTO studentDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            StudentMapper.updateEntity(student, studentDTO);
            Student updated = studentRepository.save(student);
            return ResponseEntity.ok(StudentMapper.toDTO(updated));
        } else {
            return ResponseEntity.status(404).body("Student with ID " + id + " not found.");
        }
    }

    // --- DELETE student ---
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.status(404).body("Student with ID " + id + " not found.");
        }
    }

    // --- Course CRUD ---

    // --- GET all courses ---
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseRepository.findAll()
                .stream()
                .map(CourseMapper::toDTO)
                .toList();
        return ResponseEntity.ok(courses);
    }

    // --- GET course by ID ---
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable int id) {
        return courseRepository.findById(id)
                .map(course -> ResponseEntity.ok(CourseMapper.toDTO(course)))
                .orElse(ResponseEntity.notFound().build());
    }

    // --- POST create course ---
    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        Course saved = courseRepository.save(CourseMapper.toEntity(courseDTO));
        return ResponseEntity
                .status(201)
                .body(CourseMapper.toDTO(saved));
    }

    // --- PUT update course ---
    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @Valid @RequestBody CourseDTO courseDTO) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            CourseMapper.updateEntity(course, courseDTO);
            Course updated = courseRepository.save(course);
            return ResponseEntity.ok(CourseMapper.toDTO(updated));
        } else {
            return ResponseEntity.status(404).body("Course with ID " + id + " not found.");
        }
    }

    // --- DELETE course ---
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            courseRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.status(404).body("Course with ID " + id + " not found.");
        }
    }
}
