@echo off
REM Run script for Course Management System (Windows)

echo Starting Course Management System...

REM Check if compiled
if not exist "bin" (
    echo Project not compiled. Running compilation first...
    call compile.bat
)

REM Run the application
java -cp bin gui.MainFrame

