package com.spring.spring.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class StudentDTO {

    private int id;
    private String userName;
    private String email;
    private Integer age;
    private LocalDateTime enrolledDate;

    public StudentDTO() {
        // No-args constructor
    }

    public StudentDTO(int id, String userName, String email, Integer age, LocalDateTime enrolledDate) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.age = age;
        this.enrolledDate = enrolledDate;
    }

}