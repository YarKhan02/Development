package com.spring.spring.controller;

import com.spring.spring.entity.Course;
import com.spring.spring.entity.Student;

import com.spring.spring.repository.CourseRepository;
import com.spring.spring.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    // --- GET student by ID ---
    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        return studentRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body("Student with ID " + id + " not found."));
    }

    // --- POST create student ---
    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(201).body(saved);
    }

    // --- PUT update student ---
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            if (studentDetails.getUserName() != null) {
                student.setUserName(studentDetails.getUserName());
            }
            if (studentDetails.getEmail() != null) {
                student.setEmail(studentDetails.getEmail());
            }
            if (studentDetails.getAge() != null) {
                student.setAge(studentDetails.getAge());
            }
            Student updated = studentRepository.save(student);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity
                    .status(404)
                    .body("Student with ID " + id + " not found.");
        }
    }

    // --- DELETE student ---
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.deleteById(id);
                    return ResponseEntity.noContent().build(); // 204 No Content
                })
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body("Student with ID " + id + " not found."));
    }

    // --- Course CRUD ---

    // --- GET all courses ---
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return ResponseEntity.ok(courses);
    }

    // --- GET course by ID ---
    @GetMapping("/courses/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id) {
        return courseRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body("Course with ID " + id + " not found."));
    }

    // --- POST create course ---
    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@Valid @RequestBody Course course) {
        Course saved = courseRepository.save(course);
        return ResponseEntity.status(201).body(saved); // 201 Created
    }

    // --- PUT update course ---
    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @Valid @RequestBody Course courseDetails) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setName(courseDetails.getName());
            Course updated = courseRepository.save(course);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity
                    .status(404)
                    .body("Course with ID " + id + " not found.");
        }
    }

    // --- DELETE course ---
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            courseRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity
                    .status(404)
                    .body("Course with ID " + id + " not found.");
        }
    }
}
