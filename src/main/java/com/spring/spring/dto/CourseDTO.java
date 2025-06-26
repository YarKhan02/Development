package com.spring.spring.dto;

public class CourseDTO {
    private int id;
    private String courseName;

    public CourseDTO() {}

    public CourseDTO(int id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
