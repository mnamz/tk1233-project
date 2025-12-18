#!/bin/bash
# Run script for Course Management System

echo "Starting Course Management System..."

# Check if compiled
if [ ! -d "bin" ]; then
    echo "Project not compiled. Running compilation first..."
    ./compile.sh
fi

# Run the application
java -cp bin gui.MainFrame

