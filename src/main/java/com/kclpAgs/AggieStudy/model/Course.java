package com.kclpAgs.AggieStudy.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Courses")
public class Course {

    @Id
    private String id;

    private String name;
    private String description;
    private String subject;

    public Course(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
