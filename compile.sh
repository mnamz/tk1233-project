#!/bin/bash
# Compilation script for Course Management System

echo "Compiling Course Management System..."

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
javac -d bin -sourcepath src src/gui/MainFrame.java src/gui/StudentManagementPanel.java src/models/*.java src/services/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilation successful!"
    echo ""
    echo "To run the application, use:"
    echo "  java -cp bin gui.MainFrame"
    echo ""
    echo "Or run: ./run.sh"
else
    echo "❌ Compilation failed. Please check for errors."
    exit 1
fi

