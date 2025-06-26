package com.spring.spring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(nullable = true, unique = true, length = 100)
    private String email;

    private Integer age;

    @Column(name = "enrolled_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime enrolledDate;

    public Student() {}

    public Student(String userName, String email, Integer age) {
        this.userName = userName;
        this.email = email;
        this.age = age;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getEnrolledDate() {
        return enrolledDate;
    }

    public void setEnrolledDate(LocalDateTime enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    @PrePersist
    protected void onCreate() {
        if (enrolledDate == null) {
            enrolledDate = LocalDateTime.now();
        }
    }
}