package models;

import java.io.Serializable;

/**
 * Grade model class
 * Represents a grade for a student's enrollment in a course
 */
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String gradeId;
    private String enrollmentId;
    private double midtermScore;
    private double finalScore;
    private double assignmentScore;
    private double totalScore;
    private String letterGrade;
    private String remarks;
    
    public Grade(String gradeId, String enrollmentId) {
        this.gradeId = gradeId;
        this.enrollmentId = enrollmentId;
        this.midtermScore = 0.0;
        this.finalScore = 0.0;
        this.assignmentScore = 0.0;
        this.totalScore = 0.0;
        this.letterGrade = "N/A";
        this.remarks = "";
    }
    
    // Getters and Setters
    public String getGradeId() {
        return gradeId;
    }
    
    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }
    
    public String getEnrollmentId() {
        return enrollmentId;
    }
    
    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }
    
    public double getMidtermScore() {
        return midtermScore;
    }
    
    public void setMidtermScore(double midtermScore) {
        this.midtermScore = midtermScore;
        calculateTotalScore();
    }
    
    public double getFinalScore() {
        return finalScore;
    }
    
    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
        calculateTotalScore();
    }
    
    public double getAssignmentScore() {
        return assignmentScore;
    }
    
    public void setAssignmentScore(double assignmentScore) {
        this.assignmentScore = assignmentScore;
        calculateTotalScore();
    }
    
    public double getTotalScore() {
        return totalScore;
    }
    
    public String getLetterGrade() {
        return letterGrade;
    }
    
    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }
    
    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    // Calculate total score and letter grade
    private void calculateTotalScore() {
        // Weighted: Midterm 30%, Final 40%, Assignment 30%
        this.totalScore = (midtermScore * 0.3) + (finalScore * 0.4) + (assignmentScore * 0.3);
        this.letterGrade = calculateLetterGrade(totalScore);
    }
    
    private String calculateLetterGrade(double score) {
        if (score >= 90) return "A";
        else if (score >= 85) return "A-";
        else if (score >= 80) return "B+";
        else if (score >= 75) return "B";
        else if (score >= 70) return "B-";
        else if (score >= 65) return "C+";
        else if (score >= 60) return "C";
        else if (score >= 55) return "C-";
        else if (score >= 50) return "D";
        else return "F";
    }
    
    @Override
    public String toString() {
        return "Grade: " + letterGrade + " (" + String.format("%.2f", totalScore) + ")";
    }
}

