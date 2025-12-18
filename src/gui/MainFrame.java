package gui;

import services.DataService;
import services.StudentDataLoader;

import javax.swing.*;
import java.awt.*;

/**
 * Main Application Frame
 * Contains navigation and hosts different management panels
 */
public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private StudentManagementPanel studentPanel;
    // TODO: Add other panels when implemented by team members
    // private CourseManagementPanel coursePanel;
    // private EnrollmentManagementPanel enrollmentPanel;
    // private GradeManagementPanel gradePanel;
    
    public MainFrame() {
        setTitle("Course Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Initialize student data from datasource on first run
        initializeData();
        
        initComponents();
    }
    
    private void initializeData() {
        // Load student data from CSV datasource file
        DataService dataService = new DataService();
        StudentDataLoader.initializeStudentData(dataService);
    }
    
    private void initComponents() {
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Add panels
        studentPanel = new StudentManagementPanel();
        tabbedPane.addTab("Student Directory", createIcon("👨‍🎓"), studentPanel, "View Students");
        
        // Placeholder tabs for other team members
        tabbedPane.addTab("Course Management", createIcon("📚"), createPlaceholderPanel("Course Management"), "Manage Courses (To be implemented)");
        tabbedPane.addTab("Enrollment", createIcon("✍️"), createPlaceholderPanel("Enrollment Management"), "Manage Enrollments (To be implemented)");
        tabbedPane.addTab("Grades", createIcon("📊"), createPlaceholderPanel("Grade Management"), "Manage Grades (To be implemented)");
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Add status bar
        add(createStatusBar(), BorderLayout.SOUTH);
    }
    
    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        JLabel statusLabel = new JLabel("Ready");
        statusBar.add(statusLabel);
        return statusBar;
    }
    
    private JPanel createPlaceholderPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel label = new JLabel("<html><center><h1>" + title + "</h1><br>" +
                                  "<p style='font-size:14px;'>This functionality will be implemented by a team member.</p>" +
                                  "<p style='font-size:14px;'>Please refer to IMPLEMENTATION_GUIDE.md for details.</p></center></html>");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
    
    private Icon createIcon(String emoji) {
        return new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2d.setFont(new Font("Arial", Font.PLAIN, 16));
                g2d.drawString(emoji, x, y + 14);
            }
            
            @Override
            public int getIconWidth() {
                return 20;
            }
            
            @Override
            public int getIconHeight() {
                return 20;
            }
        };
    }
    
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this,
            "Course Management System\n" +
            "Version 1.0\n" +
            "Developed by: [Team Name]\n" +
            "University Assignment - Group Project",
            "About",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create and show the frame
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

