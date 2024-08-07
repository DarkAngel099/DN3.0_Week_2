import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LogManagementSystem {

    // Method to write log messages to a file
    public static void writeLog(String fileName, String logMessage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to the log file: " + e.getMessage());
        }
    }

    // Method to read log messages from a file
    public static void readLogs(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Log file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading the log file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String fileName = "log.txt";

        // Writing log messages
        writeLog(fileName, "Log Message 1: Application started.");
        writeLog(fileName, "Log Message 2: User logged in.");
        writeLog(fileName, "Log Message 3: User performed an action.");

        // Reading log messages
        System.out.println("Reading logs from the file:");
        readLogs(fileName);

        // Demonstrating error handling by attempting to read from a non-existent file
        System.out.println("Attempting to read logs from a non-existent file:");
        readLogs("non_existent_log.txt");
    }
}

