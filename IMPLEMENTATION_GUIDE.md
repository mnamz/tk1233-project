# Course Management System - Implementation Guide

## Project Overview
This is a Java Swing-based Course Management System for university course administration. The system is divided into 4 main functionalities, with each team member implementing one feature.

## Project Structure
```
jawa/
├── src/
│   ├── models/           # Data models (Student, Course, Enrollment, Grade)
│   ├── services/         # Data service layer (DataService)
│   └── gui/             # GUI components
│       ├── MainFrame.java
│       ├── StudentManagementPanel.java
│       └── [Other panels to be implemented]
├── data/                # Data storage directory (auto-created)
└── README.md           # Setup instructions
```

## Team Member Responsibilities

### ✅ Functionality 1: Student Management (COMPLETED)
**Status:** Fully Implemented
**File:** `src/gui/StudentManagementPanel.java`
**Team Member:** [Member 1]

This functionality is complete and serves as a reference for implementing other features.

**Features Implemented:**
- Add new students with validation
- Edit existing student information
- Delete students with confirmation
- View all students in a table
- Search students by name or ID
- Data persistence using file serialization

---

### 📚 Functionality 2: Course Management
**Status:** To Be Implemented
**File to Create:** `src/gui/CourseManagementPanel.java`
**Team Member:** [Member 2]

#### Requirements
Implement a panel for managing courses in the system.

#### Features to Implement
1. **Add New Course**
   - Course Code (unique identifier)
   - Course Name
   - Description
   - Credits (integer)
   - Instructor name
   - Capacity (max students)
   - Schedule (e.g., "MWF 10:00-11:00")
   - Validate all required fields

2. **View Courses**
   - Display all courses in a JTable
   - Columns: Course Code, Name, Credits, Instructor, Capacity, Schedule

3. **Update Course**
   - Select course from table
   - Populate form with course details
   - Modify fields and save
   - Course code should not be editable after creation

4. **Delete Course**
   - Select course and delete
   - Show confirmation dialog
   - Check if course has enrollments (optional enhancement)

5. **Search Courses**
   - Search by course code or name
   - Real-time filtering of table

#### Implementation Steps
1. Create `CourseManagementPanel.java` extending `JPanel`
2. Use `StudentManagementPanel.java` as a template
3. Create similar layout with:
   - Top search panel
   - Center table panel
   - Bottom form panel with fields
4. Implement CRUD operations using `DataService`
   - `dataService.loadCourses()`
   - `dataService.saveCourses(List<Course>)`
5. Add validation for:
   - Unique course codes
   - Valid credit values (1-6)
   - Required fields not empty
6. Update `MainFrame.java`:
   ```java
   coursePanel = new CourseManagementPanel();
   // Replace placeholder tab
   tabbedPane.setComponentAt(1, coursePanel);
   ```

#### Testing Checklist
- [ ] Can add new courses
- [ ] Can view all courses in table
- [ ] Can update course details
- [ ] Can delete courses
- [ ] Search functionality works
- [ ] Data persists after restart
- [ ] Validation prevents invalid data

---

### ✍️ Functionality 3: Enrollment Management
**Status:** To Be Implemented
**File to Create:** `src/gui/EnrollmentManagementPanel.java`
**Team Member:** [Member 3]

#### Requirements
Implement a panel for managing student enrollments in courses.

#### Features to Implement
1. **Enroll Student in Course**
   - Select student from dropdown/list
   - Select course from dropdown/list
   - Set enrollment date (default to current date)
   - Status: ACTIVE (default)
   - Generate unique enrollment ID
   - Check course capacity before enrolling
   - Prevent duplicate enrollments

2. **View Enrollments**
   - Display all enrollments in a JTable
   - Columns: Enrollment ID, Student ID, Student Name, Course Code, Course Name, Date, Status
   - Join data from Student and Course lists for display

3. **Drop Enrollment**
   - Select enrollment and change status to "DROPPED"
   - Or remove enrollment completely
   - Show confirmation dialog

4. **Filter Enrollments**
   - Filter by student ID
   - Filter by course code
   - Filter by status (ACTIVE, DROPPED, COMPLETED)

5. **View Student's Courses**
   - Select a student and see all their enrolled courses
   - Show course details

6. **View Course's Students**
   - Select a course and see all enrolled students
   - Show enrollment count vs capacity

#### Implementation Steps
1. Create `EnrollmentManagementPanel.java` extending `JPanel`
2. Design layout with:
   - Top filter panel (student/course/status filters)
   - Center table for enrollments
   - Bottom panel with enrollment form
3. Use `JComboBox` for student and course selection:
   ```java
   JComboBox<Student> studentCombo = new JComboBox<>();
   JComboBox<Course> courseCombo = new JComboBox<>();
   // Populate from loaded students and courses
   ```
4. Implement enrollment logic:
   ```java
   // Check capacity
   int currentEnrollments = countEnrollments(courseCode);
   if (currentEnrollments >= course.getCapacity()) {
       // Show error: Course is full
   }
   
   // Check duplicate
   boolean exists = enrollments.stream()
       .anyMatch(e -> e.getStudentId().equals(studentId) && 
                      e.getCourseCode().equals(courseCode) &&
                      e.getStatus().equals("ACTIVE"));
   ```
5. Generate enrollment ID:
   ```java
   String enrollmentId = "ENR" + System.currentTimeMillis();
   ```
6. Update `MainFrame.java` to add the panel

#### Testing Checklist
- [ ] Can enroll students in courses
- [ ] Cannot enroll in full courses
- [ ] Cannot duplicate enrollments
- [ ] Can view all enrollments
- [ ] Can drop/remove enrollments
- [ ] Filtering works correctly
- [ ] Student and course data displayed correctly
- [ ] Data persists after restart

---

### 📊 Functionality 4: Grade Management
**Status:** To Be Implemented
**File to Create:** `src/gui/GradeManagementPanel.java`
**Team Member:** [Member 4]

#### Requirements
Implement a panel for managing student grades for their course enrollments.

#### Features to Implement
1. **Assign Grades**
   - Select an enrollment (student + course combination)
   - Enter scores:
     - Midterm Score (0-100)
     - Final Exam Score (0-100)
     - Assignment Score (0-100)
   - System automatically calculates:
     - Total Score (weighted: Midterm 30%, Final 40%, Assignment 30%)
     - Letter Grade (A, A-, B+, B, B-, C+, C, C-, D, F)
   - Add remarks/comments (optional)

2. **View Grades**
   - Display all grades in a JTable
   - Columns: Student ID, Student Name, Course Code, Midterm, Final, Assignment, Total, Letter Grade
   - Join data from Enrollment, Student, and Course

3. **Update Grades**
   - Select grade entry
   - Modify scores
   - Recalculate automatically

4. **Filter Grades**
   - Filter by student ID
   - Filter by course code
   - Filter by letter grade

5. **Generate Reports**
   - Show student transcript (all grades for a student)
   - Show course grade distribution (all students in a course)
   - Calculate GPA for a student
   - Export to text file (optional enhancement)

6. **Grade Statistics**
   - Show average, highest, lowest for a course
   - Grade distribution chart (optional)

#### Implementation Steps
1. Create `GradeManagementPanel.java` extending `JPanel`
2. Design layout with:
   - Top: Enrollment selection panel
   - Center: Grades table
   - Bottom: Grade entry form
3. Load enrollments and create grade selection:
   ```java
   // Create display string for combo box
   for (Enrollment e : enrollments) {
       Student s = findStudent(e.getStudentId());
       Course c = findCourse(e.getCourseCode());
       String display = s.getFullName() + " - " + c.getCourseName();
       enrollmentCombo.addItem(display);
   }
   ```
4. Implement grade calculation (already in Grade model):
   ```java
   Grade grade = new Grade(gradeId, enrollmentId);
   grade.setMidtermScore(midterm);
   grade.setFinalScore(finalExam);
   grade.setAssignmentScore(assignment);
   // Total and letter grade calculated automatically
   ```
5. Create report generation methods:
   ```java
   private void generateStudentTranscript(String studentId) {
       // Find all enrollments for student
       // Find grades for each enrollment
       // Display in dialog or new window
   }
   
   private void generateCourseReport(String courseCode) {
       // Find all enrollments for course
       // Find grades for each enrollment
       // Calculate statistics
   }
   ```
6. Add input validation for scores (0-100 range)
7. Update `MainFrame.java` to add the panel

#### Advanced Features (Optional)
- GPA calculation using credit hours
- Grade trend graphs
- Export reports to PDF or Excel
- Send grade notifications (simulated)
- Grade curve adjustment tool

#### Testing Checklist
- [ ] Can assign grades to enrollments
- [ ] Scores validate correctly (0-100)
- [ ] Total score and letter grade calculate correctly
- [ ] Can update existing grades
- [ ] Can view all grades in table
- [ ] Filtering works correctly
- [ ] Student transcript generates correctly
- [ ] Course report shows all students
- [ ] Statistics calculate correctly
- [ ] Data persists after restart

---

## Common Guidelines for All Team Members

### 1. Code Style
- Follow Java naming conventions
- Use meaningful variable names
- Add comments for complex logic
- Keep methods focused and small

### 2. GUI Design Principles
- Use consistent layouts across panels
- Add proper borders and spacing
- Use appropriate colors (green=add, blue=update, red=delete)
- Implement proper validation with user feedback
- Show confirmation dialogs for destructive operations

### 3. Data Management
- Always load data in constructor
- Save data after every modification
- Handle exceptions gracefully
- Use try-catch blocks for file operations

### 4. Testing
- Test all CRUD operations
- Test validation rules
- Test with empty data
- Test with large datasets
- Test data persistence

### 5. Integration
- Test your panel independently first
- Integrate with MainFrame after testing
- Ensure data is shared correctly between panels
- Test interactions between different functionalities

### 6. Common Code Patterns

#### Loading Data
```java
private void loadData() {
    try {
        items = dataService.loadXXX();
    } catch (IOException | ClassNotFoundException e) {
        items = new ArrayList<>();
        System.out.println("No existing data found.");
    }
}
```

#### Saving Data
```java
private void saveData() {
    try {
        dataService.saveXXX(items);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, 
            "Error saving: " + e.getMessage(), 
            "Error", JOptionPane.ERROR_MESSAGE);
    }
}
```

#### Validation Example
```java
private boolean validateForm() {
    if (txtField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Field is required!", 
            "Validation Error", 
            JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
}
```

#### Table Refresh Pattern
```java
private void refreshTable() {
    loadData();
    tableModel.setRowCount(0);
    for (Item item : items) {
        Object[] row = { /* item data */ };
        tableModel.addRow(row);
    }
}
```

## Getting Help

1. **Reference Implementation:** Look at `StudentManagementPanel.java` for guidance
2. **Model Classes:** All models are in `src/models/` package
3. **Data Service:** Use `DataService` class for all data operations
4. **Java Swing Documentation:** https://docs.oracle.com/javase/tutorial/uiswing/

## Submission Checklist

Before submitting your implementation:
- [ ] Code compiles without errors
- [ ] All features work as specified
- [ ] Data persists correctly
- [ ] No runtime exceptions
- [ ] Code is commented
- [ ] Integration with MainFrame completed
- [ ] Tested with sample data
- [ ] Screenshots/demo prepared

## Team Communication

- Share progress regularly
- Ask for help when stuck
- Review each other's code
- Test integrated system together

Good luck with your implementation! 🚀

