# Quick Start Guide

## For Team Members

### Getting Started (5 minutes)

1. **Understand the Project Structure**
   ```
   jawa/
   ├── src/models/           ✅ All model classes ready
   ├── src/services/         ✅ DataService ready
   ├── src/gui/              
   │   ├── MainFrame.java                    ✅ Complete
   │   └── StudentManagementPanel.java       ✅ Complete (Reference)
   ├── README.md             ✅ Full documentation
   └── IMPLEMENTATION_GUIDE.md  ✅ Your implementation guide
   ```

2. **Run the Application**
   ```bash
   # On macOS/Linux:
   ./run.sh
   
   # On Windows:
   run.bat
   ```

3. **Test Student Management** (Already Working!)
   - Click "Student Management" tab
   - Add a test student
   - Try edit, delete, and search features
   - Notice how data persists after restart

### Your Assignment

**Find your assigned functionality below:**

#### 👤 Member 2: Course Management
**File to create:** `src/gui/CourseManagementPanel.java`
**Reference:** Look at `StudentManagementPanel.java`
**Page in guide:** IMPLEMENTATION_GUIDE.md - Functionality 2

#### 👤 Member 3: Enrollment Management
**File to create:** `src/gui/EnrollmentManagementPanel.java`
**Reference:** Look at `StudentManagementPanel.java`
**Page in guide:** IMPLEMENTATION_GUIDE.md - Functionality 3

#### 👤 Member 4: Grade Management
**File to create:** `src/gui/GradeManagementPanel.java`
**Reference:** Look at `StudentManagementPanel.java`
**Page in guide:** IMPLEMENTATION_GUIDE.md - Functionality 4

### Implementation Workflow

1. **Study the Reference** (30 minutes)
   - Open `src/gui/StudentManagementPanel.java`
   - Understand the structure:
     - Constructor: Initialize components
     - createTopPanel(): Search panel
     - createTablePanel(): Data table
     - createFormPanel(): Input form
     - CRUD methods: add, update, delete
     - Data methods: load, save, refresh

2. **Copy and Modify** (2-3 hours)
   - Copy `StudentManagementPanel.java` to your new file
   - Rename the class
   - Change "Student" to your entity (Course/Enrollment/Grade)
   - Modify form fields for your entity
   - Update table columns
   - Adjust validation rules
   - Test each feature

3. **Integrate** (30 minutes)
   - In `MainFrame.java`, add your panel:
   ```java
   // Add import
   import gui.YourPanel;
   
   // In initComponents() method
   YourPanel yourPanel = new YourPanel();
   tabbedPane.setComponentAt(INDEX, yourPanel); // INDEX: 1=Course, 2=Enrollment, 3=Grade
   ```

4. **Test** (1 hour)
   - Recompile: `./compile.sh`
   - Run: `./run.sh`
   - Test all features
   - Test data persistence
   - Fix any bugs

### Quick Reference

#### DataService Methods Available
```java
// For courses
dataService.saveCourses(List<Course>)
dataService.loadCourses()

// For enrollments
dataService.saveEnrollments(List<Enrollment>)
dataService.loadEnrollments()

// For grades
dataService.saveGrades(List<Grade>)
dataService.loadGrades()
```

#### Model Classes Available
```java
Student(studentId, firstName, lastName, email, phone, program, yearLevel)
Course(courseCode, name, description, credits, instructor, capacity, schedule)
Enrollment(enrollmentId, studentId, courseCode, enrollmentDate)
Grade(gradeId, enrollmentId)
```

#### Common Swing Components
```java
JTextField txtField = new JTextField(20);
JComboBox<String> combo = new JComboBox<>();
JButton btn = new JButton("Click Me");
JTable table = new JTable(tableModel);
JLabel label = new JLabel("Text:");
```

### Tips for Success

✅ **DO:**
- Copy from StudentManagementPanel as a template
- Test frequently as you develop
- Ask team members for help
- Commit your code regularly
- Read error messages carefully

❌ **DON'T:**
- Start from scratch (use the template!)
- Skip validation
- Forget to save data after changes
- Modify model classes (they're complete)
- Ignore the implementation guide

### Testing Checklist

Before submitting, verify:
- [ ] Compiles without errors: `./compile.sh`
- [ ] Runs without crashes: `./run.sh`
- [ ] All CRUD operations work (Create, Read, Update, Delete)
- [ ] Validation prevents bad data
- [ ] Data persists after application restart
- [ ] Search/filter works correctly
- [ ] Integrated into MainFrame successfully
- [ ] No console errors during use

### Getting Help

1. **Implementation Guide:** `IMPLEMENTATION_GUIDE.md` - Detailed requirements
2. **README:** `README.md` - General project info
3. **Reference Code:** `StudentManagementPanel.java` - Working example
4. **Models:** `src/models/` - See what data is available
5. **Team:** Ask your teammates!

### Common Issues & Solutions

**Issue:** Code doesn't compile
- **Solution:** Check for typos in class names and imports

**Issue:** Data doesn't save
- **Solution:** Make sure you call `saveXXX()` after modifications

**Issue:** NullPointerException
- **Solution:** Initialize all variables, check for null before using

**Issue:** Table not updating
- **Solution:** Call `refreshTable()` after data changes

**Issue:** Can't find other entities (for Enrollment/Grade)
- **Solution:** Load all related data in constructor
  ```java
  students = dataService.loadStudents();
  courses = dataService.loadCourses();
  ```

### Time Estimate

- Understanding reference: 30 min
- Implementation: 2-3 hours
- Testing: 1 hour
- Bug fixes: 30 min
- **Total: 4-5 hours per functionality**

### Division of Work is Fair

Each functionality is equally challenging:
- **Student Management:** Basic CRUD (Template provided ✅)
- **Course Management:** Similar to students (Similar difficulty)
- **Enrollment Management:** Requires working with 2 entities (More complex)
- **Grade Management:** Calculations and reports (More complex)

The complexity balances out! 🎯

---

**You've got this! The hardest work is already done. Just follow the template and customize for your entity. Good luck! 🚀**

