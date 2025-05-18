import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager studentManager = new StudentManager();
    
    public static void main(String[] args) {
        studentManager.loadStudents();
        
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> removeStudent();
                    case 3 -> searchStudent();
                    case 4 -> updateStudent();
                    case 5 -> displayAllStudents();
                    case 6 -> sortStudentsByGrade();
                    case 7 -> {
                        studentManager.saveStudents();
                        System.out.println("Data saved successfully!");
                    }
                    case 8 -> {
                        System.out.println("Exiting...");
                        studentManager.saveStudents();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Display All Students");
        System.out.println("6. Sort Students by Grade");
        System.out.println("7. Save Data");
        System.out.println("8. Exit");
    }
    
    private static void addStudent() {
        String id = getStringInput("Enter student ID: ");
        String name = getStringInput("Enter student name: ");
        
        Student student = new Student(id, name);
        studentManager.addStudent(student);
        System.out.println("Student added successfully!");
    }
    
    private static void removeStudent() {
        String id = getStringInput("Enter student ID to remove: ");
        studentManager.removeStudent(id);
        System.out.println("Student removed successfully!");
    }
    
    private static void searchStudent() {
        System.out.println("\nSearch by:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        int choice = getIntInput("Enter your choice: ");
        
        if (choice == 1) {
            String id = getStringInput("Enter student ID: ");
            Student student = studentManager.findStudentById(id);
            if (student != null) {
                System.out.println(student);
            } else {
                System.out.println("Student not found!");
            }
        } else if (choice == 2) {
            String name = getStringInput("Enter student name: ");
            List<Student> students = studentManager.findStudentsByName(name);
            if (!students.isEmpty()) {
                students.forEach(System.out::println);
            } else {
                System.out.println("No students found!");
            }
        }
    }
    
    private static void updateStudent() {
        String id = getStringInput("Enter student ID: ");
        Student student = studentManager.findStudentById(id);
        
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }
        
        System.out.println("\nUpdate:");
        System.out.println("1. Grade");
        System.out.println("2. Attendance");
        int choice = getIntInput("Enter your choice: ");
        
        String courseId = getStringInput("Enter course ID: ");
        
        if (choice == 1) {
            double grade = getDoubleInput("Enter new grade: ");
            studentManager.updateStudentGrade(id, courseId, grade);
            System.out.println("Grade updated successfully!");
        } else if (choice == 2) {
            int attendance = getIntInput("Enter new attendance count: ");
            studentManager.updateStudentAttendance(id, courseId, attendance);
            System.out.println("Attendance updated successfully!");
        }
    }
    
    private static void displayAllStudents() {
        List<Student> students = studentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            students.forEach(System.out::println);
        }
    }
    
    private static void sortStudentsByGrade() {
        String courseId = getStringInput("Enter course ID: ");
        List<Student> sortedStudents = studentManager.sortStudentsByGrade(courseId);
        if (sortedStudents.isEmpty()) {
            System.out.println("No students found for this course!");
        } else {
            sortedStudents.forEach(System.out::println);
        }
    }
    
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
} 