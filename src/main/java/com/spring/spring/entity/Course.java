package com.spring.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Course name is required")
    @Size(max = 100, message = "Course name can't exceed 100 characters")
    private String courseName;

    public Course() {}

    public Course(int id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

}