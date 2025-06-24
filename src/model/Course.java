package model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
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