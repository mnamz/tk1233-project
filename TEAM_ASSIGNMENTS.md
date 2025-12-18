# Team Member Assignments

## 👥 Quick Reference for Who Does What

---

## ✅ Team Member 1: Student Management (COMPLETED)
**Status:** ✅ **DONE - This is your reference implementation!**

**What was implemented:**
- File: `src/gui/StudentManagementPanel.java` (450+ lines)
- Full CRUD operations for students
- Search and filter functionality
- Data validation and persistence
- Professional GUI with tables and forms

**Your role now:**
- Help other team members understand the code
- Test other features as they're implemented
- Review and integrate final system

---

## 📚 Team Member 2: Course Management

### Your Assignment
**Status:** 🚧 **TO BE IMPLEMENTED**

**File to create:** `src/gui/CourseManagementPanel.java`

### What You Need to Do

#### 1. Features Required
- ✅ Add new courses
- ✅ Edit existing courses
- ✅ Delete courses
- ✅ View all courses in table
- ✅ Search courses by code or name
- ✅ Validate course data

#### 2. Form Fields
```
- Course Code (e.g., "CS101")
- Course Name (e.g., "Introduction to Programming")
- Description
- Credits (1-6)
- Instructor Name
- Capacity (max students, e.g., 30)
- Schedule (e.g., "MWF 10:00-11:00")
```

#### 3. Table Columns
```
| Code | Name | Credits | Instructor | Capacity | Schedule |
```

#### 4. How to Start
```bash
# 1. Copy the reference file
cp src/gui/StudentManagementPanel.java src/gui/CourseManagementPanel.java

# 2. Open CourseManagementPanel.java

# 3. Find and replace:
#    - "StudentManagementPanel" → "CourseManagementPanel"
#    - "Student" → "Course"
#    - "students" → "courses"

# 4. Update form fields to match Course model

# 5. Update table columns

# 6. Update validation rules

# 7. Test it!
./compile.sh
./run.sh
```

#### 5. Integration with MainFrame
```java
// In MainFrame.java, find this line (around line 32):
// tabbedPane.addTab("Course Management", createIcon("📚"), createPlaceholderPanel("Course Management"), "Manage Courses (To be implemented)");

// Replace with:
coursePanel = new CourseManagementPanel();
tabbedPane.addTab("Course Management", createIcon("📚"), coursePanel, "Manage Courses");
```

#### 6. Resources
- **Reference Code:** `src/gui/StudentManagementPanel.java`
- **Model to use:** `models/Course.java`
- **Methods to use:** `dataService.loadCourses()` and `dataService.saveCourses()`
- **Detailed Guide:** See IMPLEMENTATION_GUIDE.md - Functionality 2

**Estimated Time:** 4-5 hours

---

## ✍️ Team Member 3: Enrollment Management

### Your Assignment
**Status:** 🚧 **TO BE IMPLEMENTED**

**File to create:** `src/gui/EnrollmentManagementPanel.java`

### What You Need to Do

#### 1. Features Required
- ✅ Enroll student in course
- ✅ Drop/remove enrollment
- ✅ View all enrollments
- ✅ View student's courses
- ✅ View course's students
- ✅ Check course capacity (prevent over-enrollment)
- ✅ Prevent duplicate enrollments

#### 2. Form Components
```
- Student Selector (JComboBox with all students)
- Course Selector (JComboBox with all courses)
- Enrollment Date (default to today)
- Status (ACTIVE, DROPPED, COMPLETED)
```

#### 3. Table Columns
```
| Enrollment ID | Student ID | Student Name | Course Code | Course Name | Date | Status |
```

#### 4. Special Considerations
```java
// You'll need to load BOTH students and courses
private List<Student> students;
private List<Course> courses;
private List<Enrollment> enrollments;

// In constructor:
students = dataService.loadStudents();
courses = dataService.loadCourses();
enrollments = dataService.loadEnrollments();

// Populate combo boxes
for (Student s : students) {
    studentCombo.addItem(s);
}
for (Course c : courses) {
    courseCombo.addItem(c);
}

// Check capacity before enrolling
int currentEnrollments = countActiveEnrollments(courseCode);
if (currentEnrollments >= course.getCapacity()) {
    JOptionPane.showMessageDialog(this, "Course is full!");
    return;
}

// Check duplicate
boolean exists = enrollments.stream()
    .anyMatch(e -> e.getStudentId().equals(studentId) && 
                   e.getCourseCode().equals(courseCode) &&
                   e.getStatus().equals("ACTIVE"));
```

#### 5. How to Start
```bash
# 1. Start with the reference
cp src/gui/StudentManagementPanel.java src/gui/EnrollmentManagementPanel.java

# 2. Replace names similar to Member 2

# 3. Add dropdown selectors instead of text fields for Student and Course

# 4. Implement capacity checking

# 5. Implement duplicate prevention

# 6. Test!
```

#### 6. Integration with MainFrame
```java
// Replace placeholder at index 2
enrollmentPanel = new EnrollmentManagementPanel();
tabbedPane.setComponentAt(2, enrollmentPanel);
```

#### 7. Resources
- **Reference Code:** `src/gui/StudentManagementPanel.java`
- **Models to use:** `models/Enrollment.java`, `models/Student.java`, `models/Course.java`
- **Methods to use:** 
  - `dataService.loadEnrollments()` / `saveEnrollments()`
  - `dataService.loadStudents()`
  - `dataService.loadCourses()`
- **Detailed Guide:** See IMPLEMENTATION_GUIDE.md - Functionality 3

**Estimated Time:** 5-6 hours (slightly more complex due to multiple entities)

---

## 📊 Team Member 4: Grade Management

### Your Assignment
**Status:** 🚧 **TO BE IMPLEMENTED**

**File to create:** `src/gui/GradeManagementPanel.java`

### What You Need to Do

#### 1. Features Required
- ✅ Assign grades to enrollments
- ✅ Enter midterm, final, and assignment scores
- ✅ Auto-calculate total and letter grade
- ✅ View all grades
- ✅ Update existing grades
- ✅ Generate student transcript (all grades for a student)
- ✅ Generate course report (all students' grades in a course)
- ✅ Calculate statistics (average, min, max)

#### 2. Form Components
```
- Enrollment Selector (Student + Course combination)
- Midterm Score (0-100)
- Final Exam Score (0-100)
- Assignment Score (0-100)
- Total Score (auto-calculated, read-only)
- Letter Grade (auto-calculated, read-only)
- Remarks (optional text)
```

#### 3. Table Columns
```
| Student ID | Student Name | Course Code | Midterm | Final | Assignment | Total | Letter Grade |
```

#### 4. Grade Calculation (Already in Grade model!)
```java
// The Grade model already does this automatically!
Grade grade = new Grade(gradeId, enrollmentId);
grade.setMidtermScore(30);      // Set midterm
grade.setFinalScore(40);         // Set final
grade.setAssignmentScore(30);    // Set assignment
// Total and letter grade calculated automatically!
// Weighted: Midterm 30%, Final 40%, Assignment 30%
```

#### 5. Special Features
```java
// Generate Student Transcript
private void generateStudentTranscript(String studentId) {
    // 1. Find all enrollments for this student
    // 2. Find grades for each enrollment
    // 3. Find course names
    // 4. Display in dialog or new window
    // 5. Calculate GPA (optional)
}

// Generate Course Report
private void generateCourseReport(String courseCode) {
    // 1. Find all enrollments for this course
    // 2. Find grades for each enrollment
    // 3. Find student names
    // 4. Calculate statistics (average, min, max)
    // 5. Show grade distribution
}
```

#### 6. How to Start
```bash
# 1. Start with the reference
cp src/gui/StudentManagementPanel.java src/gui/GradeManagementPanel.java

# 2. Replace names

# 3. Add enrollment selector (combo box with student+course display)

# 4. Add score input fields (JTextField with number validation)

# 5. Add read-only fields for total and letter grade

# 6. Implement report generation methods

# 7. Test!
```

#### 7. Integration with MainFrame
```java
// Replace placeholder at index 3
gradePanel = new GradeManagementPanel();
tabbedPane.setComponentAt(3, gradePanel);
```

#### 8. Resources
- **Reference Code:** `src/gui/StudentManagementPanel.java`
- **Models to use:** `models/Grade.java`, `models/Enrollment.java`, `models/Student.java`, `models/Course.java`
- **Methods to use:** 
  - `dataService.loadGrades()` / `saveGrades()`
  - `dataService.loadEnrollments()`
  - `dataService.loadStudents()`
  - `dataService.loadCourses()`
- **Detailed Guide:** See IMPLEMENTATION_GUIDE.md - Functionality 4

**Estimated Time:** 5-6 hours (includes report generation)

---

## 📅 Suggested Timeline

### Week 1
- **All Members:** Read documentation, understand the system (2 hours)
- **All Members:** Study StudentManagementPanel reference code (1 hour)

### Week 2
- **Member 2:** Implement Course Management (4-5 hours)
- **Member 3:** Start Enrollment Management (2-3 hours)
- **Member 4:** Start Grade Management (2-3 hours)

### Week 3
- **Member 3:** Complete Enrollment Management (3-4 hours)
- **Member 4:** Complete Grade Management (3-4 hours)
- **All Members:** Integration testing (2 hours)

### Week 4
- **All Members:** Bug fixes and refinements (2-3 hours)
- **All Members:** Final testing and demo preparation (2 hours)

**Total Time per Member:** 10-15 hours

---

## ✅ Checklist Before Submission

### Individual Checklist
- [ ] My code compiles without errors
- [ ] All required features are implemented
- [ ] I've tested all CRUD operations
- [ ] Data persists after application restart
- [ ] Validation prevents invalid data
- [ ] No runtime exceptions
- [ ] Code is commented
- [ ] I've integrated my panel into MainFrame

### Team Checklist
- [ ] All 4 functionalities are complete
- [ ] Integration testing done
- [ ] All panels work together
- [ ] Data flows correctly between features
- [ ] No conflicts between team members' code
- [ ] Application runs smoothly
- [ ] Demo prepared
- [ ] Documentation updated (if needed)

---

## 🚨 Important Notes

### Data Dependencies
```
Students & Courses → Can be added independently
           ↓
     Enrollments → Requires existing Students and Courses
           ↓
       Grades → Requires existing Enrollments
```

**Recommendation:** Implement in order (Member 2 → Member 3 → Member 4) OR ensure you create test data for dependencies.

### Test Data Order
1. Add Students (Member 1 - already works)
2. Add Courses (Member 2)
3. Create Enrollments (Member 3)
4. Assign Grades (Member 4)

### Communication
- **Member 2** should notify **Member 3** when Course Management is ready
- **Member 3** should notify **Member 4** when Enrollment Management is ready
- Or, create mock/test data to work independently

---

## 🎯 Success Tips

1. **Start Early** - Don't wait until the last minute
2. **Follow the Template** - StudentManagementPanel is your friend
3. **Test Frequently** - Compile and run after each feature
4. **Ask Questions** - Use your team group chat
5. **Commit Often** - Save your work regularly
6. **Read Error Messages** - They tell you what's wrong
7. **Keep It Simple** - Don't over-complicate
8. **Help Each Other** - You're a team!

---

## 📞 Need Help?

### Order of Resources:
1. **QUICK_START.md** - Fast overview
2. **IMPLEMENTATION_GUIDE.md** - Detailed instructions for your feature
3. **StudentManagementPanel.java** - Working example
4. **README.md** - General project info
5. **ARCHITECTURE.md** - System design
6. **Team Members** - Help each other!
7. **Instructor** - Last resort

---

**Good luck, team! You've got excellent documentation and a solid foundation. Just follow the template and you'll do great! 🚀**

