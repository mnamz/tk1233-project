package services;

import models.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDataLoader class
 * Loads student data from CSV datasource file
 */
public class StudentDataLoader {
    private static final String DATASOURCE_FILE = "datasource/students.csv";
    
    /**
     * Load students from CSV file
     * @return List of students from the datasource
     */
    public static List<Student> loadStudentsFromDatasource() {
        List<Student> students = new ArrayList<>();
        
        File file = new File(DATASOURCE_FILE);
        if (!file.exists()) {
            System.err.println("Warning: Student datasource file not found: " + DATASOURCE_FILE);
            return students;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;
            
            while ((line = br.readLine()) != null) {
                // Skip header line
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    Student student = parseStudentFromCSV(line);
                    students.add(student);
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    System.err.println("Error: " + e.getMessage());
                }
            }
            
            System.out.println("Successfully loaded " + students.size() + " students from datasource");
            
        } catch (IOException e) {
            System.err.println("Error reading student datasource file: " + e.getMessage());
        }
        
        return students;
    }
    
    /**
     * Parse a CSV line into a Student object
     * Format: StudentID,FirstName,LastName,Email,PhoneNumber,Program,YearLevel
     */
    private static Student parseStudentFromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        
        if (fields.length != 7) {
            throw new IllegalArgumentException("Invalid CSV format. Expected 7 fields, got " + fields.length);
        }
        
        String studentId = fields[0].trim();
        String firstName = fields[1].trim();
        String lastName = fields[2].trim();
        String email = fields[3].trim();
        String phoneNumber = fields[4].trim();
        String program = fields[5].trim();
        int yearLevel = Integer.parseInt(fields[6].trim());
        
        return new Student(studentId, firstName, lastName, email, phoneNumber, program, yearLevel);
    }
    
    /**
     * Initialize student data by always loading from datasource
     * This ensures students are always loaded fresh from the CSV file
     */
    public static void initializeStudentData(DataService dataService) {
        System.out.println("Loading students from datasource (datasource/students.csv)...");
        List<Student> students = loadStudentsFromDatasource();
        
        if (!students.isEmpty()) {
            try {
                dataService.saveStudents(students);
                System.out.println("✅ Successfully loaded " + students.size() + " students from datasource!");
            } catch (IOException ex) {
                System.err.println("Error saving student data: " + ex.getMessage());
            }
        } else {
            System.err.println("⚠️ No students found in datasource file!");
        }
    }
}

