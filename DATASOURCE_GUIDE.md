# Data Source Configuration Guide

## Overview
This Course Management System uses a **datasource-based approach** for student data, meaning students are loaded from a CSV file rather than being manually entered through a form.

## Why Use Datasource Instead of Forms?

### Traditional Approach (Forms)
- ❌ Manually enter each student one by one
- ❌ Time-consuming for large classes
- ❌ Prone to data entry errors
- ❌ Difficult to import existing data

### Datasource Approach (CSV Import) ✅
- ✅ Import hundreds of students instantly
- ✅ Data can come from registration system
- ✅ Easy to maintain in spreadsheet
- ✅ Bulk operations supported
- ✅ Version control friendly

## How It Works

```
┌─────────────────────────────────────────────────────────────┐
│ 1. Application Starts                                        │
└────────────────────┬────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────┐
│ 2. Check if data/students.dat exists                        │
└────────────────────┬────────────────────────────────────────┘
                     │
         ┌───────────┴───────────┐
         │                       │
         ▼ NO                    ▼ YES
┌──────────────────┐    ┌──────────────────┐
│ Load from        │    │ Use existing     │
│ datasource/      │    │ students.dat     │
│ students.csv     │    │ (already loaded) │
└────────┬─────────┘    └──────────────────┘
         │
         ▼
┌──────────────────┐
│ Parse CSV        │
│ Create Student   │
│ objects          │
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│ Save to          │
│ data/students.dat│
│ for persistence  │
└──────────────────┘
```

## Student Data Flow

### Initial Setup (First Run)
1. Place `students.csv` in `datasource/` folder
2. Run application
3. System loads students from CSV
4. System saves to `data/students.dat`
5. Students appear in Student Directory

### Subsequent Runs
1. Run application
2. System loads from `data/students.dat` (fast!)
3. Students appear in Student Directory

### Updating Students
1. Delete `data/students.dat`
2. Update `datasource/students.csv`
3. Run application
4. Fresh import from CSV

## File Structure

```
jawa/
├── datasource/              ← Data source files
│   ├── students.csv        ← Student records (you edit this)
│   └── README.md           ← Datasource documentation
│
├── data/                    ← Persistent storage (auto-generated)
│   ├── students.dat        ← Serialized student data
│   ├── courses.dat         ← Course data
│   ├── enrollments.dat     ← Enrollment data
│   └── grades.dat          ← Grade data
│
└── src/
    └── services/
        ├── DataService.java          ← Data persistence
        └── StudentDataLoader.java    ← CSV import logic
```

## Managing Student Data

### Option 1: Excel/Spreadsheet
1. Open `datasource/students.csv` in Excel
2. Edit student records
3. Save as CSV (Comma delimited)
4. Delete `data/students.dat`
5. Run application

### Option 2: Text Editor
1. Open `datasource/students.csv` in any text editor
2. Add/modify rows following the format
3. Save file
4. Delete `data/students.dat`
5. Run application

### Option 3: Database Export
```sql
-- Export from your student database
SELECT student_id, first_name, last_name, email, 
       phone, program, year_level
FROM students
INTO OUTFILE 'students.csv'
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n';
```

## CSV Format Specification

### Structure
```csv
StudentID,FirstName,LastName,Email,PhoneNumber,Program,YearLevel
S001,John,Doe,john.doe@university.edu,555-0101,Computer Science,2
```

### Rules
- **Header:** First line (will be skipped)
- **Separator:** Comma (,)
- **No spaces** around commas
- **No quotes** unless data contains commas
- **One student** per line
- **Empty lines** are ignored

### Field Requirements

| Field | Required | Format | Example |
|-------|----------|--------|---------|
| StudentID | ✅ Yes | Alphanumeric | S001 |
| FirstName | ✅ Yes | Text | John |
| LastName | ✅ Yes | Text | Doe |
| Email | ✅ Yes | user@domain | john.doe@university.edu |
| PhoneNumber | ✅ Yes | Text | 555-0101 |
| Program | ✅ Yes | Text | Computer Science |
| YearLevel | ✅ Yes | 1-4 | 2 |

## Examples

### Example 1: Small Class (5 students)
```csv
StudentID,FirstName,LastName,Email,PhoneNumber,Program,YearLevel
S001,Alice,Anderson,alice.a@uni.edu,555-0001,Computer Science,1
S002,Bob,Brown,bob.b@uni.edu,555-0002,Computer Science,1
S003,Carol,Chen,carol.c@uni.edu,555-0003,IT,2
S004,David,Davis,david.d@uni.edu,555-0004,Computer Science,3
S005,Eve,Evans,eve.e@uni.edu,555-0005,Software Engineering,4
```

### Example 2: With Special Characters
```csv
StudentID,FirstName,LastName,Email,PhoneNumber,Program,YearLevel
S001,María,González,maria.g@uni.edu,555-0001,Computer Science,2
S002,François,Dubois,francois.d@uni.edu,555-0002,IT,1
S003,李,明,li.ming@uni.edu,555-0003,Computer Science,3
```

### Example 3: Different Programs
```csv
StudentID,FirstName,LastName,Email,PhoneNumber,Program,YearLevel
S001,John,Doe,john@uni.edu,555-0001,Computer Science,2
S002,Jane,Smith,jane@uni.edu,555-0002,Information Technology,3
S003,Bob,Jones,bob@uni.edu,555-0003,Software Engineering,1
S004,Alice,Brown,alice@uni.edu,555-0004,Data Science,4
S005,Mike,Wilson,mike@uni.edu,555-0005,Cybersecurity,2
```

## Common Use Cases

### Use Case 1: New Semester
1. Receive student roster from registrar
2. Export to CSV format
3. Replace `datasource/students.csv`
4. Delete `data/students.dat`
5. Start application

### Use Case 2: Adding New Students Mid-Semester
1. Add rows to `datasource/students.csv`
2. Delete `data/students.dat`
3. Restart application
4. All students (old + new) are loaded

### Use Case 3: Testing
1. Create `test_students.csv` with sample data
2. Copy to `datasource/students.csv`
3. Delete `data/students.dat`
4. Test your features

## Advantages for Your Project

### For Development
- ✅ **Quick testing:** Create test data easily
- ✅ **Reset anytime:** Just delete data file
- ✅ **No UI needed:** Focus on other features first
- ✅ **Team friendly:** Share CSV files easily

### For Other Features
- ✅ **Course Management:** Team member 2 can use same approach
- ✅ **Enrollments:** Can reference existing students
- ✅ **Grades:** Can reference existing students

### For Grading
- ✅ **Easy to demo:** Pre-load realistic data
- ✅ **Professional:** Real-world approach
- ✅ **Scalable:** Works with any number of students

## Extending to Other Entities

You can use the same approach for courses!

### Create courses.csv
```csv
CourseCode,CourseName,Description,Credits,Instructor,Capacity,Schedule
CS101,Intro to Programming,Learn Java basics,3,Dr. Smith,30,MWF 10:00-11:00
CS201,Data Structures,Arrays and trees,4,Prof. Johnson,25,TTh 14:00-15:30
```

### Implement CourseDataLoader.java
```java
public class CourseDataLoader {
    public static List<Course> loadCoursesFromDatasource() {
        // Similar to StudentDataLoader
    }
}
```

## Troubleshooting

### Problem: Students not appearing
**Solution:**
1. Check `datasource/students.csv` exists
2. Check console for error messages
3. Verify CSV format is correct
4. Try deleting `data/students.dat` and restarting

### Problem: Some students missing
**Solution:**
1. Check console for parsing errors
2. Look for invalid rows in CSV
3. Ensure all required fields are present
4. Check for correct number of columns

### Problem: Wrong data displayed
**Solution:**
1. Delete `data/students.dat`
2. Update `datasource/students.csv`
3. Restart application

### Problem: Can't update students
**Solution:**
- This is by design! Students are read-only
- To update: modify CSV and reimport
- For production: implement admin tools

## For Team Members

### Member 2 (Course Management)
You can use the same approach:
1. Create `datasource/courses.csv`
2. Create `CourseDataLoader.java`
3. Load courses on startup
4. Focus on enrollment and search features

### Member 3 (Enrollment Management)
Benefits for you:
- Students already loaded ✅
- Courses already loaded ✅
- Just focus on linking them!

### Member 4 (Grade Management)
Benefits for you:
- All data already available ✅
- Focus on grade calculation ✅
- Focus on reports ✅

## Best Practices

### DO ✅
- Keep CSV files in version control
- Use consistent ID format (S001, S002, etc.)
- Validate data before importing
- Back up data files regularly
- Document any custom fields

### DON'T ❌
- Don't edit `data/*.dat` files manually
- Don't commit real student data to Git
- Don't mix data formats
- Don't skip validation

## Real-World Application

This approach mirrors how real systems work:

1. **University Systems:** Student data from registration database
2. **Corporate Training:** Employee data from HR system
3. **Online Courses:** User data from authentication system

Your project demonstrates **data integration** skills! 🎯

## Summary

✅ Students loaded from `datasource/students.csv`
✅ No manual form entry needed
✅ Easy to import bulk data
✅ Professional approach
✅ Scalable and maintainable
✅ Easy for team collaboration

**This is how real course management systems work!** 🚀

