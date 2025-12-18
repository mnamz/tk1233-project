# Course Management System

A comprehensive Course Management System built with Java Swing for university course administration. This system allows managing students, courses, enrollments, and grades through an intuitive graphical user interface.

## Features

### Implemented Features
- **✅ Student Management (Core)**
  - Add, edit, delete students
  - View all students
  - Search functionality
  - Data persistence

### Features Under Development
- **📚 Course Management**
  - Manage course catalog
  - Track course capacity and schedules

- **✍️ Enrollment Management**
  - Enroll students in courses
  - Track enrollment status
  - Manage course capacity

- **📊 Grade Management**
  - Record and calculate grades
  - Generate transcripts
  - Course reports and statistics



## Project Structure

```
jawa/
├── src/
│   ├── models/
│   │   ├── Student.java          # Student data model
│   │   ├── Course.java           # Course data model
│   │   ├── Enrollment.java       # Enrollment data model
│   │   └── Grade.java            # Grade data model
│   ├── services/
│   │   └── DataService.java      # Data persistence service
│   └── gui/
│       ├── MainFrame.java        # Main application window
│       └── StudentManagementPanel.java  # Student management UI
├── data/                         # Data storage (auto-created)
├── README.md                     # This file
└── IMPLEMENTATION_GUIDE.md       # Developer guide for team members
```

## Installation & Setup

### Method 1: Using Command Line

1. **Clone or download the project**
   ```bash
   cd /Users/aliifz/projects/jawa
   ```

2. **Compile the project**
   ```bash
   javac -d bin -sourcepath src src/gui/MainFrame.java
   ```

3. **Run the application**
   ```bash
   java -cp bin gui.MainFrame
   ```

### Method 2: Using IDE (Eclipse/IntelliJ/NetBeans)

1. **Import Project**
   - Open your IDE
   - Select "Import Existing Project" or "Open Project"
   - Navigate to the `jawa` folder
   - Select the `src` folder as the source directory

2. **Configure Build Path**
   - Ensure JDK 8+ is set as the project SDK
   - Set source folder to `src`

3. **Run the Application**
   - Locate `MainFrame.java` in `src/gui/`
   - Right-click and select "Run as Java Application"

### Method 3: Creating JAR File

1. **Compile all classes**
   ```bash
   javac -d bin -sourcepath src src/**/*.java
   ```

2. **Create manifest file** (`manifest.txt`)
   ```
   Main-Class: gui.MainFrame
   ```

3. **Create JAR**
   ```bash
   jar cfm CourseManagement.jar manifest.txt -C bin .
   ```

4. **Run JAR**
   ```bash
   java -jar CourseManagement.jar
   ```

## Running the Application

1. **Launch the application** using one of the methods above
2. **Main window** will open with 4 tabs:
   - Student Management (Fully Functional)
   - Course Management (Under Development)
   - Enrollment (Under Development)
   - Grades (Under Development)

3. **Student Management Features:**
   - **Add Student:** Fill in the form at the bottom and click "Add Student"
   - **Edit Student:** Click on a row in the table, modify fields, and click "Update Student"
   - **Delete Student:** Select a row and click "Delete Student"
   - **Search:** Enter search term and click "Search"
   - **Refresh:** Click "Refresh" to reload all data

## Functionality Overview

### 1. Student Management ✅
**Status:** Fully Implemented

**Features:**
- Add new students with complete information
- Edit existing student details
- Delete students with confirmation
- Search by name or student ID
- View all students in a table format
- Data validation for required fields
- Persistent data storage

**Fields:**
- Student ID (Unique)
- First Name
- Last Name
- Email
- Phone Number
- Program
- Year Level (1-4)

### 2. Course Management 🚧

**Planned Features:**
- Add, edit, delete courses
- View course catalog
- Track instructor and schedule
- Manage course capacity
- Search courses


### 3. Enrollment Management 🚧

**Planned Features:**
- Enroll students in courses
- Drop/remove enrollments
- View student's courses
- View course's students
- Check course capacity
- Enrollment status tracking


### 4. Grade Management 🚧

**Planned Features:**
- Record midterm, final, and assignment scores
- Automatic grade calculation
- Letter grade assignment
- Student transcripts
- Course grade reports
- Grade statistics

## 💻 Development Guide

### For Team Members

3. **Implement Your Feature**
   - Create your panel class
   - Implement required features
   - Test thoroughly
   - Integrate with MainFrame

4. **Testing**
   - Test independently first
   - Test data persistence
   - Test integration with other features
   - Handle edge cases

### Data Storage

The application uses Java serialization for data persistence:
- Data files are stored in the `data/` directory
- Files are automatically created on first run
- Data persists between application sessions

**Data Files:**
- `datasource/students.csv` - Student records
- `data/courses.dat` - Course records
- `data/enrollments.dat` - Enrollment records
- `data/grades.dat` - Grade records


### Code Organization

```java
// Model classes (already implemented)
models.Student
models.Course
models.Enrollment
models.Grade

// Data service (already implemented)
services.DataService

// GUI components
gui.MainFrame              // Main window (already implemented)
gui.StudentManagementPanel // Student UI (already implemented)
gui.CourseManagementPanel  // To be implemented
gui.EnrollmentManagementPanel // To be implemented
gui.GradeManagementPanel   // To be implemented
```


## Notes

- All model classes implement `Serializable` for data persistence
- The system uses file-based storage (no database required)
- GUI follows Java Swing best practices
- Data validation is implemented at the UI layer


