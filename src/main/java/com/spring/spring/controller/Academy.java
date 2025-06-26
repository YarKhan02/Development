package com.spring.spring.controller;

import com.spring.spring.entity.Course;
import com.spring.spring.entity.Student;
import com.spring.spring.repository.CourseRepository;
import com.spring.spring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow();
        if (studentDetails.getUserName() != null) {
            student.setUserName(studentDetails.getUserName());
        }
        if (studentDetails.getEmail() != null) {
            student.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getAge() != null) {
            student.setAge(studentDetails.getAge());
        }
        return studentRepository.save(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
    }

    // --- Course CRUD ---

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/courses/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course courseDetails) {
        Course course = courseRepository.findById(id).orElseThrow();
        course.setName(courseDetails.getName());
        return courseRepository.save(course);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseRepository.deleteById(id);
    }
}
