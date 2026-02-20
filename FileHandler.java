import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    // 1. Create and Write to a File
    public static void createFile(String fileName, String content) {
        try {
            Path path = Paths.get(fileName);
            Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("✅ File created successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error creating file: " + e.getMessage());
        }
    }

    // 2. Read from a File
    public static void readFile(String fileName) {
        try {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path);
            System.out.println("\n--- File Content ---");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("--------------------\n");
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }

    // 3. Append to a File
    public static void appendToFile(String fileName, String content) {
        try {
            Path path = Paths.get(fileName);
            Files.writeString(path, "\n" + content, StandardOpenOption.APPEND);
            System.out.println("✅ Data appended successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error appending to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "InternshipTask.txt";

        System.out.println("--- Java File Handling Utility ---");
        
        // Step A: Create
        System.out.print("Enter initial text for the file: ");
        String initialText = sc.nextLine();
        createFile(fileName, initialText);

        // Step B: Read
        readFile(fileName);

        // Step C: Append
        System.out.print("Enter text to append: ");
        String appendText = sc.nextLine();
        appendToFile(fileName, appendText);

        // Step D: Read again to show changes
        System.out.println("Updated File Content:");
        readFile(fileName);
        
        sc.close();
    }
}