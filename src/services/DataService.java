package services;

import models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DataService class
 * Handles data persistence using file serialization
 */
public class DataService {
    private static final String DATA_DIR = "data/";
    private static final String STUDENTS_FILE = DATA_DIR + "students.dat";
    private static final String COURSES_FILE = DATA_DIR + "courses.dat";
    private static final String ENROLLMENTS_FILE = DATA_DIR + "enrollments.dat";
    private static final String GRADES_FILE = DATA_DIR + "grades.dat";
    
    public DataService() {
        // Create data directory if it doesn't exist
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    
    // Generic save method
    private <T> void saveData(String filename, List<T> data) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        }
    }
    
    // Generic load method
    @SuppressWarnings("unchecked")
    private <T> List<T> loadData(String filename) throws IOException, ClassNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<T>) ois.readObject();
        }
    }
    
    // Student operations
    public void saveStudents(List<Student> students) throws IOException {
        saveData(STUDENTS_FILE, students);
    }
    
    public List<Student> loadStudents() throws IOException, ClassNotFoundException {
        return loadData(STUDENTS_FILE);
    }
    
    // Course operations
    public void saveCourses(List<Course> courses) throws IOException {
        saveData(COURSES_FILE, courses);
    }
    
    public List<Course> loadCourses() throws IOException, ClassNotFoundException {
        return loadData(COURSES_FILE);
    }
    
    // Enrollment operations
    public void saveEnrollments(List<Enrollment> enrollments) throws IOException {
        saveData(ENROLLMENTS_FILE, enrollments);
    }
    
    public List<Enrollment> loadEnrollments() throws IOException, ClassNotFoundException {
        return loadData(ENROLLMENTS_FILE);
    }
    
    // Grade operations
    public void saveGrades(List<Grade> grades) throws IOException {
        saveData(GRADES_FILE, grades);
    }
    
    public List<Grade> loadGrades() throws IOException, ClassNotFoundException {
        return loadData(GRADES_FILE);
    }
}

