//This program to define Course Entity class
package com.tns.course.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Course {
    //Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    // Title of the course
    @Column(name="title")
    private String title;
    
    // Description of the course
    @Column(name="description")
    private String description;
    
    
    // Start date of the course
    @Temporal(TemporalType.DATE)
    @Column(name="startdate")
    private Date startDate;
    
    // End date of the course
    @Temporal(TemporalType.DATE)
    @Column(name="enddate")
    private Date endDate;
    
    // Instructor of the course
    @Column(name="instructor")
    private String instructor;

    // Constructors

    // Default constructor
    public Course() {
    }

    // Parameterized constructor
    public Course(String title, String description, Date startDate, Date endDate, String instructor) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
    }

    // Getters and setters

    // Getter for the ID
    public Long getId() {
        return id;
    }

    // Setter for the ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for the title
    public String getTitle() {
        return title;
    }

    // Setter for the title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for the description
    public String getDescription() {
        return description;
    }

    // Setter for the description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for the start date
    public Date getStartDate() {
        return startDate;
    }

    // Setter for the start date
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // Getter for the end date
    public Date getEndDate() {
        return endDate;
    }

    // Setter for the end date
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Getter for the instructor
    public String getInstructor() {
        return instructor;
    }

    // Setter for the instructor
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
