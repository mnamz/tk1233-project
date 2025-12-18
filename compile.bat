@echo off
REM Compilation script for Course Management System (Windows)

echo Compiling Course Management System...

REM Create bin directory if it doesn't exist
if not exist "bin" mkdir bin

REM Compile all Java files
javac -d bin -sourcepath src src/gui/MainFrame.java src/gui/StudentManagementPanel.java src/models/*.java src/services/*.java

if %ERRORLEVEL% EQU 0 (
    echo.
    echo Compilation successful!
    echo.
    echo To run the application, use:
    echo   java -cp bin gui.MainFrame
    echo.
    echo Or run: run.bat
) else (
    echo.
    echo Compilation failed. Please check for errors.
    exit /b 1
)

