// Database Schema
CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE faculty (
    faculty_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    department VARCHAR(50),
    email VARCHAR(100)
);

CREATE TABLE leave_applications (
    application_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    start_date DATE,
    end_date DATE,
    reason TEXT,
    status ENUM('PENDING', 'APPROVED', 'REJECTED'),
    faculty_id INT,
    comments TEXT,
    submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (faculty_id) REFERENCES faculty(faculty_id)
);

// Basic GUI Structure
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LeaveApplicationSystem extends JFrame {
    // Main components
    private JTabbedPane tabbedPane;
    private JPanel studentPanel;
    private JPanel facultyPanel;
    
    // Student components
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextArea reasonArea;
    private JButton submitButton;
    
    // Database connection
    private Connection conn;
    
    public LeaveApplicationSystem() {
        setTitle("Leave Application System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        setupDatabase();
        setSize(800, 600);
    }
    
    private void initializeComponents() {
        // Initialize main container
        tabbedPane = new JTabbedPane();
        
        // Setup student panel
        studentPanel = new JPanel(new BorderLayout());
        setupStudentPanel();
        
        // Setup faculty panel
        facultyPanel = new JPanel(new BorderLayout());
        setupFacultyPanel();
        
        // Add panels to tabbed pane
        tabbedPane.addTab("Student", studentPanel);
        tabbedPane.addTab("Faculty", facultyPanel);
        
        add(tabbedPane);
    }
    
    private void setupStudentPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        // Add components for leave application
        inputPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDateField = new JTextField();
        inputPanel.add(startDateField);
        
        inputPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField();
        inputPanel.add(endDateField);
        
        inputPanel.add(new JLabel("Reason:"));
        reasonArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(reasonArea);
        inputPanel.add(scrollPane);
        
        submitButton = new JButton("Submit Application");
        submitButton.addActionListener(e -> submitApplication());
        
        studentPanel.add(inputPanel, BorderLayout.CENTER);
        studentPanel.add(submitButton, BorderLayout.SOUTH);
    }
    
    private void setupDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/leave_system",
                "username",
                "password"
            );
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed!");
        }
    }
    
    private void submitApplication() {
        try {
            String sql = "INSERT INTO leave_applications (student_id, start_date, end_date, reason, status) VALUES (?, ?, ?, ?, 'PENDING')";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Add proper validation and error handling here
            pstmt.setInt(1, 1); // Replace with actual student ID
            pstmt.setString(2, startDateField.getText());
            pstmt.setString(3, endDateField.getText());
            pstmt.setString(4, reasonArea.getText());
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Application submitted successfully!");
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error submitting application!");
        }
    }
    
    private void clearForm() {
        startDateField.setText("");
        endDateField.getText();
        reasonArea.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LeaveApplicationSystem().setVisible(true);
        });
    }
}
