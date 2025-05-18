import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_NAME = "students.dat";
    
    public static void saveStudents(List<Student> students) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(FILE_NAME)))) {
            oos.writeObject(students);
        } catch (IOException e) {
            throw new IOException("Error saving students to file: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public static List<Student> loadStudents() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(FILE_NAME)))) {
            return (List<Student>) ois.readObject();
        } catch (IOException e) {
            throw new IOException("Error loading students from file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Error deserializing students: " + e.getMessage());
        }
    }
} 