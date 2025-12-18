package models;

import java.io.Serializable;
import java.util.Date;

/**
 * Enrollment model class
 * Represents a student's enrollment in a course
 */
public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String enrollmentId;
    private String studentId;
    private String courseCode;
    private Date enrollmentDate;
    private String status; // ACTIVE, DROPPED, COMPLETED
    
    public Enrollment(String enrollmentId, String studentId, String courseCode, Date enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.enrollmentDate = enrollmentDate;
        this.status = "ACTIVE";
    }
    
    // Getters and Setters
    public String getEnrollmentId() {
        return enrollmentId;
    }
    
    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return enrollmentId + " - Student: " + studentId + " | Course: " + courseCode;
    }
}

