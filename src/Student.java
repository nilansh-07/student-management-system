import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private List<Course> courses;
    private Map<String, Double> grades;
    private Map<String, Integer> attendance;
    
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
        this.grades = new HashMap<>();
        this.attendance = new HashMap<>();
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }
    
    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            grades.put(course.getCourseId(), 0.0);
            attendance.put(course.getCourseId(), 0);
        }
    }
    
    public void removeCourse(String courseId) {
        courses.removeIf(course -> course.getCourseId().equals(courseId));
        grades.remove(courseId);
        attendance.remove(courseId);
    }
    
    public double getGrade(String courseId) {
        return grades.getOrDefault(courseId, 0.0);
    }
    
    public void setGrade(String courseId, double grade) {
        if (grades.containsKey(courseId)) {
            grades.put(courseId, grade);
        }
    }
    
    public int getAttendance(String courseId) {
        return attendance.getOrDefault(courseId, 0);
    }
    
    public void setAttendance(String courseId, int attendanceCount) {
        if (attendance.containsKey(courseId)) {
            attendance.put(courseId, attendanceCount);
        }
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", courses=" + courses +
                ", grades=" + grades +
                ", attendance=" + attendance +
                '}';
    }
} 