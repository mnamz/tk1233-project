package models;

import java.io.Serializable;

/**
 * Course model class
 * Represents a course in the course management system
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String courseCode;
    private String courseName;
    private String description;
    private int credits;
    private String instructor;
    private int capacity;
    private String schedule;
    
    public Course(String courseCode, String courseName, String description, 
                  int credits, String instructor, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        this.instructor = instructor;
        this.capacity = capacity;
        this.schedule = schedule;
    }
    
    // Getters and Setters
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getCredits() {
        return credits;
    }
    
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    public String getInstructor() {
        return instructor;
    }
    
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getSchedule() {
        return schedule;
    }
    
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    
    @Override
    public String toString() {
        return courseCode + " - " + courseName;
    }
}

