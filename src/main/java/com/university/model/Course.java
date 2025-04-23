package com.university.model;

public class Course {
    private int id;
    private String code;
    private String name;
    private String description;
    private int credits;

    public Course() {

    }

    public Course(int id, String code, String name, String description, int credits) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.credits = credits;
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getCredits() {
        return credits;
    }
}
