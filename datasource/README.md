# Student Data Source

## Overview
This directory contains the student data source file that is used to populate the Course Management System with student records.

## File Format

### students.csv
CSV file containing student records with the following columns:

```csv
StudentID,FirstName,LastName,Email,PhoneNumber,Program,YearLevel
```

### Column Descriptions

| Column | Type | Description | Example |
|--------|------|-------------|---------|
| StudentID | String | Unique student identifier | S001 |
| FirstName | String | Student's first name | John |
| LastName | String | Student's last name | Doe |
| Email | String | Student's university email | john.doe@university.edu |
| PhoneNumber | String | Contact phone number | 555-0101 |
| Program | String | Academic program/major | Computer Science |
| YearLevel | Integer | Year level (1-4) | 2 |

## Adding New Students

1. Open `students.csv` in a text editor or spreadsheet application
2. Add a new row with the student information
3. Ensure the StudentID is unique
4. Save the file
5. Restart the application to load the new data

### Example Entry
```csv
S021,Alex,Johnson,alex.johnson@university.edu,555-0121,Computer Science,1
```

## Important Notes

### First-Time Loading
- When the application starts for the first time, it automatically loads students from `students.csv`
- Students are saved to `data/students.dat` for persistence
- Subsequent application runs will use the saved data file

### Updating Student Data
- **Method 1:** Delete `data/students.dat` and modify `students.csv`, then restart the app (fresh import)
- **Method 2:** Use the application's student directory to view students (read-only)
- For production systems, implement proper admin tools for student management

### Data Validation
The system validates:
- ✅ StudentID must be unique
- ✅ Email must contain '@'
- ✅ YearLevel must be between 1-4
- ✅ All required fields must be present

### CSV Format Rules
- First line is the header (will be skipped)
- Fields separated by commas
- No spaces around commas
- Empty lines are ignored
- Invalid lines will show error messages in console

## Current Students
The datasource currently contains 20 pre-populated student records (S001 - S020).

## Bulk Import

To import a large number of students:

1. **From Excel:**
   - Create/edit data in Excel
   - Save As → CSV (Comma delimited)
   - Ensure format matches the template

2. **From Database:**
   ```sql
   SELECT student_id, first_name, last_name, email, phone, program, year_level
   FROM students
   INTO OUTFILE 'students.csv'
   FIELDS TERMINATED BY ','
   LINES TERMINATED BY '\n';
   ```

3. **Programmatically:**
   ```java
   // Use StudentDataLoader.loadStudentsFromDatasource()
   List<Student> students = StudentDataLoader.loadStudentsFromDatasource();
   ```

## Troubleshooting

### Students Not Loading
- Check that `datasource/students.csv` exists
- Verify CSV format is correct
- Check console for error messages
- Ensure no special characters in data

### Duplicate Students
- Each StudentID must be unique
- Check for duplicate IDs in the CSV file

### Invalid Data
- Check that YearLevel is a number (1-4)
- Ensure email contains '@'
- Remove any empty rows

## Example: Complete students.csv

```csv
StudentID,FirstName,LastName,Email,PhoneNumber,Program,YearLevel
S001,John,Doe,john.doe@university.edu,555-0101,Computer Science,2
S002,Jane,Smith,jane.smith@university.edu,555-0102,Information Technology,3
S003,Michael,Johnson,michael.j@university.edu,555-0103,Computer Science,1
```

## For Administrators

### Reset Student Data
```bash
# Delete existing data and reimport from CSV
rm data/students.dat
# Run application - it will reload from CSV
./run.sh
```

### Export Current Students
Students are stored in `data/students.dat` (Java serialized format).
To export to CSV, you would need to implement an export feature in the application.

## Security Notes

⚠️ **Important for Production:**
- This file contains student data - handle with care
- In production, use proper database with access controls
- Never commit real student data to version control
- Use `.gitignore` to exclude sensitive data files

## Support

For issues with student data loading:
1. Check console output for error messages
2. Validate CSV format
3. Check file permissions
4. Refer to main project README.md

