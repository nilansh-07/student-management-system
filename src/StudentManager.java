import java.util.*;
import java.util.stream.Collectors;

public class StudentManager {
    private List<Student> students;
    
    public StudentManager() {
        this.students = new ArrayList<>();
    }
    
    public void addStudent(Student student) {
        if (findStudentById(student.getId()) == null) {
            students.add(student);
        }
    }
    
    public void removeStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }
    
    public Student findStudentById(String id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    public List<Student> findStudentsByName(String name) {
        return students.stream()
                .filter(student -> student.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<Student> sortStudentsByGrade(String courseId) {
        return students.stream()
                .filter(student -> student.getCourses().stream()
                        .anyMatch(course -> course.getCourseId().equals(courseId)))
                .sorted(Comparator.comparingDouble(student -> student.getGrade(courseId)))
                .collect(Collectors.toList());
    }
    
    public void updateStudentGrade(String studentId, String courseId, double grade) {
        Student student = findStudentById(studentId);
        if (student != null) {
            student.setGrade(courseId, grade);
        }
    }
    
    public void updateStudentAttendance(String studentId, String courseId, int attendance) {
        Student student = findStudentById(studentId);
        if (student != null) {
            student.setAttendance(courseId, attendance);
        }
    }
    
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    public void loadStudents() {
        try {
            students = FileHandler.loadStudents();
        } catch (Exception e) {
            System.err.println("Error loading students: " + e.getMessage());
            students = new ArrayList<>();
        }
    }
    
    public void saveStudents() {
        try {
            FileHandler.saveStudents(students);
        } catch (Exception e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }
} 