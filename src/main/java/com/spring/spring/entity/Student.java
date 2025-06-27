package com.spring.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name", nullable = false, length = 100)
    @NotBlank(message = "Username is required")
    @Size(max = 100, message = "Username can't exceed 100 characters")
    private String userName;

    @Column(unique = true, length = 100)
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email can't exceed 100 characters")
    private String email;

    @Min(value = 15, message = "Age must be at least 15")
    private Integer age;

    @Column(name = "enrolled_date", updatable = false)
    private LocalDateTime enrolledDate;

    public Student() {
        // No-args constructor
    }

    public Student(int id, String userName, String email, Integer age, LocalDateTime enrolledDate) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.age = age;
        this.enrolledDate = enrolledDate;
    }

    @PrePersist
    protected void onCreate() {
        if (enrolledDate == null) {
            enrolledDate = LocalDateTime.now();
        }
    }
}