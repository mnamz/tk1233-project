package models;

import java.io.Serializable;

/**
 * Student model class
 * Represents a student in the course management system
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String program;
    private int yearLevel;
    
    public Student(String studentId, String firstName, String lastName, String email, 
                   String phoneNumber, String program, int yearLevel) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.program = program;
        this.yearLevel = yearLevel;
    }
    
    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getProgram() {
        return program;
    }
    
    public void setProgram(String program) {
        this.program = program;
    }
    
    public int getYearLevel() {
        return yearLevel;
    }
    
    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    @Override
    public String toString() {
        return studentId + " - " + getFullName();
    }
}

