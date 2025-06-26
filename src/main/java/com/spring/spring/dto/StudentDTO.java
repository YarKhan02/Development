package com.spring.spring.dto;

import java.time.LocalDateTime;

public class StudentDTO {

    private int id;
    private String userName;
    private String email;
    private Integer age;
    private LocalDateTime enrolledDate;

    public StudentDTO() {}

    public StudentDTO(int id, String userName, String email, Integer age, LocalDateTime enrolledDate) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.age = age;
        this.enrolledDate = enrolledDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public LocalDateTime getEnrolledDate() {
        return enrolledDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEnrolledDate(LocalDateTime enrolledDate) {
        this.enrolledDate = enrolledDate;
    }
}