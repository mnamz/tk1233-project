# 🚀 Quick Reference Card

## Essential Commands

```bash
# Compile the project
./compile.sh        # macOS/Linux
compile.bat         # Windows

# Run the application
./run.sh           # macOS/Linux
run.bat            # Windows
```

## Project Structure (Simplified)

```
jawa/
├── datasource/
│   └── students.csv          ← Edit to add/modify students
├── src/
│   ├── models/               ← All complete ✅
│   ├── services/             ← All complete ✅
│   └── gui/
│       ├── MainFrame.java                    ✅ Done
│       ├── StudentManagementPanel.java       ✅ Done
│       ├── CourseManagementPanel.java        🚧 Member 2
│       ├── EnrollmentManagementPanel.java    🚧 Member 3
│       └── GradeManagementPanel.java         🚧 Member 4
└── Documentation (8 files)   ← All complete ✅
```

## Key Files

| File | Purpose | Status |
|------|---------|--------|
| `datasource/students.csv` | Student data source | ✅ 20 students |
| `src/gui/StudentManagementPanel.java` | Student directory | ✅ Complete |
| `src/services/StudentDataLoader.java` | CSV loader | ✅ Complete |
| `src/gui/MainFrame.java` | Main window | ✅ Complete |

## Documentation Quick Links

| Document | When to Read | Time |
|----------|-------------|------|
| **UPDATED_APPROACH.md** | **First!** | 5 min |
| QUICK_START.md | Getting started | 5 min |
| TEAM_ASSIGNMENTS.md | Your assignment | 10 min |
| DATASOURCE_GUIDE.md | Managing CSV data | 10 min |
| IMPLEMENTATION_GUIDE.md | Implementing features | 20 min |
| README.md | Complete reference | 15 min |
| ARCHITECTURE.md | System design | 15 min |
| CHANGES_SUMMARY.md | What changed | 5 min |

## Student Data Management

### View Students
```bash
./run.sh
# → Click "Student Directory" tab
# → See 20 pre-loaded students
```

### Add Students
```bash
# Edit datasource/students.csv
# Add line: S021,First,Last,email@uni.edu,555-0121,Program,1
# Restart app
./run.sh
```

### CSV Format
```csv
StudentID,FirstName,LastName,Email,PhoneNumber,Program,YearLevel
S001,John,Doe,john.doe@uni.edu,555-0101,Computer Science,2
```

## Team Member Quick Guide

### Member 1 (You) ✅
- **Status:** DONE
- **File:** StudentManagementPanel.java
- **Features:** View, Search students
- **Approach:** Datasource-based (CSV)

### Member 2 (Courses) 🚧
- **File to create:** CourseManagementPanel.java
- **Recommended:** Use datasource approach (create courses.csv)
- **Alternative:** Full CRUD with forms
- **Time:** 2-3 hours (datasource) or 4-5 hours (forms)

### Member 3 (Enrollments) 🚧
- **File to create:** EnrollmentManagementPanel.java
- **Approach:** Forms with CRUD operations
- **Prerequisites:** Students ✅, Courses (Member 2)
- **Time:** 5-6 hours

### Member 4 (Grades) 🚧
- **File to create:** GradeManagementPanel.java
- **Approach:** Forms with CRUD operations
- **Prerequisites:** Students ✅, Courses, Enrollments
- **Time:** 5-6 hours

## Common Tasks

### Compile and Run
```bash
./compile.sh && ./run.sh
```

### Check for Errors
```bash
./compile.sh
# Look for error messages
```

### View Console Output
```bash
# Console shows:
# - "Loading students from datasource..."
# - "✅ Successfully loaded 20 students..."
```

### Reset Student Data
```bash
# Just restart - always loads from CSV
./run.sh
```

## Data Flow

```
CSV File → Loader → DataService → Panel → Display
```

## Key Concepts

### Datasource Approach
- Students loaded from CSV file
- No manual entry forms
- Always fresh data on startup
- Professional, realistic approach

### Why Datasource?
- ✅ Faster development
- ✅ Bulk import capability
- ✅ Realistic scenario
- ✅ Easy testing
- ✅ Professional approach

## Troubleshooting

| Problem | Solution |
|---------|----------|
| Won't compile | Check for typos, missing imports |
| No students showing | Check datasource/students.csv exists |
| Wrong data | Edit CSV and restart app |
| Console errors | Read error message, check CSV format |

## File Locations

```
Project Root: /Users/aliifz/projects/jawa/

Source Code:  src/
Compiled:     bin/
Data Files:   data/
Datasource:   datasource/
Scripts:      *.sh, *.bat
Docs:         *.md
```

## Model Classes (All Complete)

```java
Student(studentId, firstName, lastName, email, phone, program, yearLevel)
Course(courseCode, name, description, credits, instructor, capacity, schedule)
Enrollment(enrollmentId, studentId, courseCode, enrollmentDate)
Grade(gradeId, enrollmentId)
```

## Service Classes (All Complete)

```java
DataService.saveStudents(List<Student>)
DataService.loadStudents()
DataService.saveCourses(List<Course>)
DataService.loadCourses()
DataService.saveEnrollments(List<Enrollment>)
DataService.loadEnrollments()
DataService.saveGrades(List<Grade>)
DataService.loadGrades()

StudentDataLoader.loadStudentsFromDatasource()
StudentDataLoader.initializeStudentData(DataService)
```

## Current Status

```
✅ Foundation:     100% Complete
✅ Students:       100% Complete (20 pre-loaded)
🚧 Courses:          0% (Member 2)
🚧 Enrollments:      0% (Member 3)
🚧 Grades:           0% (Member 4)
✅ Documentation:  100% Complete (8 files)

Overall: 40% Complete
```

## Next Steps

1. **Right Now:**
   - Read UPDATED_APPROACH.md (5 min)
   - Run ./run.sh and test (3 min)
   - Check Student Directory tab (2 min)

2. **Today:**
   - Read your assignment in TEAM_ASSIGNMENTS.md
   - Study the reference implementation
   - Plan your feature

3. **This Week:**
   - Implement your assigned feature
   - Test thoroughly
   - Integrate with MainFrame

## Success Criteria

- [ ] Code compiles without errors
- [ ] Application runs without crashes
- [ ] All features work correctly
- [ ] Data persists after restart
- [ ] Search/filter works
- [ ] Professional UI
- [ ] Code is commented

## Resources

- **Java Swing Tutorial:** https://docs.oracle.com/javase/tutorial/uiswing/
- **CSV Format:** datasource/README.md
- **Reference Code:** src/gui/StudentManagementPanel.java
- **Team Help:** Group chat

## Important Notes

⚠️ **Students are read-only** - Edit CSV to modify
⚠️ **Always loads from CSV** - Fresh data every startup
⚠️ **Don't edit data/*.dat files** - Auto-generated
⚠️ **Follow the template** - Use StudentManagementPanel as reference

## Contact

- **Team Chat:** [Your group chat]
- **Repository:** [If using Git]
- **Instructor:** [Contact info]

---

## Quick Decision Tree

**Need to add students?**
→ Edit datasource/students.csv

**Need to implement courses?**
→ Option 1: Create courses.csv (recommended)
→ Option 2: Full CRUD forms

**Need to test?**
→ ./compile.sh && ./run.sh

**Need help?**
→ Read docs → Check reference code → Ask team

**Ready to start?**
→ Read UPDATED_APPROACH.md → TEAM_ASSIGNMENTS.md → Start coding!

---

**Keep this file handy for quick reference!** 📌

