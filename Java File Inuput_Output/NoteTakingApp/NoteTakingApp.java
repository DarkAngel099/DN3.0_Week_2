import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class NoteTakingApp {

    // Method to save a note to a file
    public static void saveNoteToFile(String fileName, String note) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(note);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read a note from a file
    public static void readNoteFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your note: ");
        String note = scanner.nextLine();

        String fileName = "note.txt";

        // Save the note to a file
        saveNoteToFile(fileName, note);

        // Read the note back from the file
        System.out.println("Your note from the file: ");
        readNoteFromFile(fileName);

        scanner.close();
    }
}

