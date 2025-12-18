package gui;

import models.Student;
import services.DataService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Student Management Panel
 * Simplified view-only panel for displaying students
 * Students are loaded from datasource file (datasource/students.csv)
 */
public class StudentManagementPanel extends JPanel {
    private DataService dataService;
    private List<Student> students;
    
    // GUI Components
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private JButton btnSearch, btnRefresh;
    
    public StudentManagementPanel() {
        dataService = new DataService();
        students = new ArrayList<>();
        loadStudents();
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create components
        add(createTopPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createInfoPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        // Title
        JLabel titleLabel = new JLabel("Student Directory (Loaded from datasource/students.csv)");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearch = new JTextField(25);
        btnSearch = new JButton("Search");
        btnRefresh = new JButton("Refresh");
        
        searchPanel.add(new JLabel("Search by Name or ID:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(btnRefresh);
        
        // Event handlers
        btnSearch.addActionListener(e -> searchStudents());
        btnRefresh.addActionListener(e -> refreshTable());
        txtSearch.addActionListener(e -> searchStudents()); // Search on Enter key
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(searchPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Student List (" + students.size() + " students)"));
        
        // Create table
        String[] columns = {"Student ID", "First Name", "Last Name", "Email", "Phone", "Program", "Year"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setRowHeight(25);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        // Auto-resize columns
        studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setPreferredSize(new Dimension(900, 400));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        refreshTable();
        
        return panel;
    }
    
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        // Info message
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(new Color(230, 244, 255));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 150, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel infoIcon = new JLabel("ℹ️");
        infoIcon.setFont(new Font("Arial", Font.PLAIN, 20));
        
        JLabel infoLabel = new JLabel("<html><b>Read-Only View</b><br>" +
            "Students are automatically loaded from <code>datasource/students.csv</code> on every startup. " +
            "To add/modify students, edit the CSV file and restart the application.</html>");
        
        infoPanel.add(infoIcon);
        infoPanel.add(infoLabel);
        
        panel.add(infoPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadStudents() {
        try {
            students = dataService.loadStudents();
        } catch (IOException | ClassNotFoundException e) {
            students = new ArrayList<>();
            System.out.println("No existing student data found. Starting fresh.");
        }
    }
    
    private void saveStudents() {
        try {
            dataService.saveStudents(students);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error saving students: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void refreshTable() {
        loadStudents();
        tableModel.setRowCount(0);
        for (Student student : students) {
            Object[] row = {
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getProgram(),
                student.getYearLevel()
            };
            tableModel.addRow(row);
        }
        // Update border with count
        ((javax.swing.border.TitledBorder)((JPanel)studentTable.getParent().getParent().getParent()).getBorder())
            .setTitle("Student List (" + students.size() + " students)");
        repaint();
    }
    
    private void searchStudents() {
        String searchTerm = txtSearch.getText().trim().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            refreshTable();
            return;
        }
        
        tableModel.setRowCount(0);
        int matchCount = 0;
        
        for (Student student : students) {
            if (student.getStudentId().toLowerCase().contains(searchTerm) ||
                student.getFirstName().toLowerCase().contains(searchTerm) ||
                student.getLastName().toLowerCase().contains(searchTerm) ||
                student.getEmail().toLowerCase().contains(searchTerm) ||
                student.getProgram().toLowerCase().contains(searchTerm)) {
                
                Object[] row = {
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPhoneNumber(),
                    student.getProgram(),
                    student.getYearLevel()
                };
                tableModel.addRow(row);
                matchCount++;
            }
        }
        
        // Update count in status
        System.out.println("Search found " + matchCount + " students matching '" + searchTerm + "'");
    }
}

