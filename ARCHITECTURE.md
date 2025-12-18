# System Architecture

## Overview
This document explains the architecture of the Course Management System.

## Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                         GUI Layer                            │
│                       (javax.swing)                          │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              MainFrame (JFrame)                       │  │
│  │  ┌────────────────────────────────────────────────┐  │  │
│  │  │         JTabbedPane                             │  │  │
│  │  │  ┌─────────────────────────────────────────┐  │  │  │
│  │  │  │  StudentManagementPanel ✅              │  │  │  │
│  │  │  │  - Add/Edit/Delete Students              │  │  │  │
│  │  │  │  - Search Students                        │  │  │  │
│  │  │  └─────────────────────────────────────────┘  │  │  │
│  │  │  ┌─────────────────────────────────────────┐  │  │  │
│  │  │  │  CourseManagementPanel 🚧               │  │  │  │
│  │  │  │  (To be implemented by Member 2)         │  │  │  │
│  │  │  └─────────────────────────────────────────┘  │  │  │
│  │  │  ┌─────────────────────────────────────────┐  │  │  │
│  │  │  │  EnrollmentManagementPanel 🚧           │  │  │  │
│  │  │  │  (To be implemented by Member 3)         │  │  │  │
│  │  │  └─────────────────────────────────────────┘  │  │  │
│  │  │  ┌─────────────────────────────────────────┐  │  │  │
│  │  │  │  GradeManagementPanel 🚧                │  │  │  │
│  │  │  │  (To be implemented by Member 4)         │  │  │  │
│  │  │  └─────────────────────────────────────────┘  │  │  │
│  │  └────────────────────────────────────────────┘  │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                            ↓↑
┌─────────────────────────────────────────────────────────────┐
│                      Service Layer                           │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌──────────────────────────────────────────────────────┐  │
│  │              DataService                              │  │
│  │  - saveStudents() / loadStudents()                    │  │
│  │  - saveCourses() / loadCourses()                      │  │
│  │  - saveEnrollments() / loadEnrollments()              │  │
│  │  - saveGrades() / loadGrades()                        │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                            ↓↑
┌─────────────────────────────────────────────────────────────┐
│                      Model Layer                             │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │   Student    │  │    Course    │  │  Enrollment  │     │
│  │  - id        │  │  - code      │  │  - id        │     │
│  │  - name      │  │  - name      │  │  - studentId │     │
│  │  - email     │  │  - credits   │  │  - courseCode│     │
│  │  - program   │  │  - instructor│  │  - date      │     │
│  │  - year      │  │  - capacity  │  │  - status    │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
│                                                               │
│  ┌──────────────┐                                           │
│  │    Grade     │                                           │
│  │  - id        │                                           │
│  │  - enrollId  │                                           │
│  │  - midterm   │                                           │
│  │  - final     │                                           │
│  │  - assignment│                                           │
│  │  - total     │                                           │
│  │  - letter    │                                           │
│  └──────────────┘                                           │
└─────────────────────────────────────────────────────────────┘
                            ↓↑
┌─────────────────────────────────────────────────────────────┐
│                   Persistence Layer                          │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  File System (data/ directory)                               │
│  - students.dat (serialized Student objects)                │
│  - courses.dat (serialized Course objects)                  │
│  - enrollments.dat (serialized Enrollment objects)          │
│  - grades.dat (serialized Grade objects)                    │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

## Layer Responsibilities

### 1. GUI Layer (User Interface)
**Location:** `src/gui/`
**Purpose:** Handle user interactions and display data
**Components:**
- `MainFrame`: Main application window with tabbed navigation
- Management Panels: Individual panels for each functionality

**Responsibilities:**
- Display data in tables and forms
- Capture user input
- Validate form data
- Show confirmation dialogs
- Handle button clicks and events
- Call service layer for data operations

### 2. Service Layer (Business Logic)
**Location:** `src/services/`
**Purpose:** Manage data operations and business rules
**Components:**
- `DataService`: Handles all data persistence operations

**Responsibilities:**
- Save data to files
- Load data from files
- Handle file I/O exceptions
- Provide abstraction between GUI and file system
- Ensure data integrity

### 3. Model Layer (Data Structures)
**Location:** `src/models/`
**Purpose:** Define data structures and entity relationships
**Components:**
- `Student`: Represents a student entity
- `Course`: Represents a course entity
- `Enrollment`: Represents student-course relationship
- `Grade`: Represents grades for an enrollment

**Responsibilities:**
- Define entity attributes
- Implement getters and setters
- Implement Serializable for persistence
- Provide utility methods (e.g., calculateGrade)
- Define toString() for display

### 4. Persistence Layer (Data Storage)
**Location:** `data/` directory
**Purpose:** Store application data persistently
**Format:** Java Object Serialization (.dat files)

**Responsibilities:**
- Persist data between application sessions
- Store data in binary format
- Auto-create on first use

## Data Flow

### Example: Adding a Student

```
1. User fills form in StudentManagementPanel
                ↓
2. User clicks "Add Student" button
                ↓
3. Panel validates form data
                ↓
4. Panel creates Student object
                ↓
5. Panel adds to students list
                ↓
6. Panel calls dataService.saveStudents(students)
                ↓
7. DataService serializes list to data/students.dat
                ↓
8. Panel calls refreshTable()
                ↓
9. Table updates to show new student
```

### Example: Loading Students on Startup

```
1. StudentManagementPanel constructor called
                ↓
2. Panel calls loadStudents()
                ↓
3. loadStudents() calls dataService.loadStudents()
                ↓
4. DataService reads data/students.dat
                ↓
5. DataService deserializes to List<Student>
                ↓
6. Panel stores list in students variable
                ↓
7. Panel calls refreshTable()
                ↓
8. Table populated with student data
```

## Entity Relationships

```
Student (1) ──── (M) Enrollment (M) ──── (1) Course
                      │
                      │ (1)
                      │
                      │ (1)
                    Grade

Legend:
(1) = One
(M) = Many
```

**Relationships:**
- One Student can have Many Enrollments
- One Course can have Many Enrollments
- One Enrollment has One Grade (optional)
- Enrollment is the junction table between Student and Course

## Design Patterns Used

### 1. Model-View-Controller (MVC)
- **Model:** Entity classes (Student, Course, etc.)
- **View:** GUI panels and components
- **Controller:** Event handlers in panels + DataService

### 2. Singleton Pattern (Implicit)
- Each panel typically has one DataService instance
- Data files are managed centrally

### 3. Table Model Pattern
- `DefaultTableModel` for displaying data in JTable
- Separates data from presentation

## Key Design Decisions

### Why File-Based Storage?
- ✅ Simple to implement
- ✅ No external dependencies
- ✅ Suitable for small to medium datasets
- ✅ Easy to backup (just copy data folder)
- ❌ Not suitable for concurrent access
- ❌ Not suitable for large datasets

### Why Java Serialization?
- ✅ Built into Java
- ✅ Handles object graphs automatically
- ✅ Type-safe
- ❌ Not human-readable
- ❌ Version-sensitive

### Why Swing GUI?
- ✅ Part of Java standard library
- ✅ Cross-platform
- ✅ Rich component library
- ✅ Good for desktop applications
- ❌ Older technology (but still widely used)

## Extension Points

### For Future Enhancements

1. **Database Integration**
   - Replace DataService with DatabaseService
   - Use JDBC for SQL database
   - Models already support this design

2. **Authentication System**
   - Add User model
   - Add login panel
   - Restrict operations based on user role

3. **Export/Import**
   - Add CSV export in DataService
   - Add PDF report generation
   - Add Excel integration

4. **Search Improvements**
   - Add advanced filters
   - Add sorting capabilities
   - Add date range filters

5. **Web Interface**
   - Keep models and business logic
   - Replace Swing with web framework
   - Create REST API layer

## Code Organization Best Practices

### Package Structure
```
src/
├── models/       # Data entities (no dependencies)
├── services/     # Business logic (depends on models)
└── gui/          # User interface (depends on models + services)
```

### Dependency Flow
```
GUI → Services → Models
```

**Rule:** Higher layers depend on lower layers, never the reverse

### Naming Conventions
- **Classes:** PascalCase (e.g., `StudentManagementPanel`)
- **Methods:** camelCase (e.g., `loadStudents()`)
- **Variables:** camelCase (e.g., `studentId`)
- **Constants:** UPPER_SNAKE_CASE (e.g., `DATA_DIR`)

## Testing Strategy

### Unit Testing (Not implemented, but recommended)
```java
// Test models
testStudentCreation()
testGradeCalculation()

// Test services
testSaveAndLoadStudents()
testDataPersistence()
```

### Integration Testing
- Test each panel independently
- Test data flow between panels
- Test data persistence across restarts

### User Acceptance Testing
- Test all user scenarios
- Verify error handling
- Check edge cases

## Security Considerations

### Current Implementation
- ⚠️ No authentication
- ⚠️ No encryption
- ⚠️ No input sanitization (basic validation only)
- ⚠️ No audit logging

### Recommendations for Production
- Add user authentication
- Encrypt sensitive data (emails, grades)
- Implement role-based access control
- Add audit trail for changes
- Validate and sanitize all inputs

## Performance Considerations

### Current Implementation
- Suitable for: < 10,000 records per entity
- Memory usage: All data loaded at startup
- File I/O: On every modification

### For Large Datasets
- Implement lazy loading
- Add pagination to tables
- Use database instead of files
- Cache frequently accessed data
- Index search fields

## Conclusion

This architecture provides:
- ✅ Clear separation of concerns
- ✅ Easy to understand and extend
- ✅ Suitable for learning and small projects
- ✅ Foundation for future enhancements

The modular design allows each team member to work independently on their assigned functionality while sharing common infrastructure (models and services).

