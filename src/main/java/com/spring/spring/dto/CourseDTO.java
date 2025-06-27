package com.spring.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseDTO {
    private int id;
    private String courseName;

    public CourseDTO() {
        // No-args constructor
    }

    public CourseDTO(int id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

}
