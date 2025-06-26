package com.spring.spring.mapper;

import com.spring.spring.dto.CourseDTO;
import com.spring.spring.entity.Course;

public class CourseMapper {

    public static CourseDTO toDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getName()
        );
    }

    public static Course toEntity(CourseDTO dto) {
        Course course = new Course();
        course.setName(dto.getCourseName());
        return course;
    }

    public static void updateEntity(Course course, CourseDTO dto) {
        if (dto.getCourseName() != null) {
            course.setName(dto.getCourseName());
        }
    }
}