package com.university.model;

import java.time.LocalDateTime;

public class Student {

    private int id;
    private String name;
    private String email;
    private Course course;
    private LocalDateTime registrationDate;

   
    public Student() {
        this.registrationDate = this.registrationDate = LocalDateTime.now();
    }

    
    public Student(int id, String name, String email, Course course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Student{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", course=" + course
                + ", registrationDate=" + registrationDate
                + '}';
    }
}
