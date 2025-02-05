# Leave Application System
## **Overview**
The Leave Application System is a **Java-based** application that streamlines the process of leave requests and approvals within an institution. It features a **Graphical User Interface (GUI)** built with **Swing**, enabling students to easily submit leave applications while faculty members can manage and review these requests. The system is powered by a **MySQL database** that stores details about students, faculty, and their leave applications.

## **Features**

- **Student Portal**  
  - Apply for leave by specifying the start date, end date, and reason.  
  - View the status of previous leave applications.

- **Faculty Portal**  
  - Review pending leave applications.  
  - Approve or reject leave requests with comments.

- **Database Management**  
  - Stores student and faculty details.  
  - Maintains leave application records with timestamps.

## **Technologies Used**

- **Backend**: Java (Swing for GUI)  
- **Database**: MySQL  
- **Libraries**: JDBC for database connectivity  

## **Installation and Setup**

### 1. Clone the Repository
```sh
git clone <repository-url>
cd leave-application-system
```

### 2. Set Up the Database
- Install MySQL and create a database.  
- Execute the provided SQL script to create the necessary tables.

### 3. Configure Database Connection
Update the database credentials in the Java application:

```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/leave_system",
    "your-username",
    "your-password"
);
```

### 4. Compile and Run the Application
```sh
javac LeaveApplicationSystem.java
java LeaveApplicationSystem
```

## **File Structure**

```
LeaveApplicationSystem/
│── src/
│   ├── LeaveApplicationSystem.java    # Main application with GUI
│   ├── DatabaseConnection.java        # Manages database connectivity
│   ├── StudentPanel.java              # GUI panel for students
│   ├── FacultyPanel.java              # GUI panel for faculty
│── sql/
│   ├── schema.sql                     # Database schema script
│── README.md
```

## **Usage**

- **Students**: Log in and submit leave applications with the required details.  
- **Faculty members**: Review leave applications, approve or reject them, and leave comments.  
- The system updates the application status and stores comments from faculty.

## **Contributing**

Contributions are welcome! Feel free to submit issues or create pull requests.

---
