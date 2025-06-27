package com.spring.spring.service;

import com.spring.spring.dto.CourseDTO;
import com.spring.spring.entity.Course;
import com.spring.spring.mapper.CourseMapper;
import com.spring.spring.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Get all courses as DTOs
    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toDTO)
                .toList();
    }

    // Get a course by ID as DTO
    public Optional<CourseDTO> getCourseById(int id) {
        return courseRepository.findById(id)
                .map(CourseMapper::toDTO);
    }

    // Create a new course and return as DTO
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course course = CourseMapper.toEntity(courseDTO);
        Course saved = courseRepository.save(course);
        return CourseMapper.toDTO(saved);
    }

    // Update an existing course and return as DTO if found
    public Optional<CourseDTO> updateCourse(int id, CourseDTO courseDTO) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    CourseMapper.updateEntity(existingCourse, courseDTO);
                    Course saved = courseRepository.save(existingCourse);
                    return CourseMapper.toDTO(saved);
                });
    }

    // Delete a course by ID, return true if deleted
    public boolean deleteCourse(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
