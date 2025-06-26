package com.spring.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name can't exceed 100 characters")
    private String course_name;

    public Course() {}

    public Course(String name) {
        this.course_name = name;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getName() { return course_name; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.course_name = name; }
}
