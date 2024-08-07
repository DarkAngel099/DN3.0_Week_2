import java.io.*;
import java.util.ArrayList;
import java.util.List;

// LargeFileReader class
class LargeFileReader {
    public static List<String> readLargeFile(String inputFileName) {
        List<String> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line (e.g., parse and print sales records)
                // For demonstration, just add the line to the list
                records.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return records;
    }
}

// LargeFileWriter class
class LargeFileWriter {
    public static void writeProcessedData(String outputFileName, List<String> processedData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String data : processedData) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

// Main application class
public class LargeFileProcessor {
    public static void main(String[] args) {
        String inputFileName = "sales_data.txt";
        String outputFileName = "processed_sales_data.txt";

        // Read the large sales data file
        List<String> data = LargeFileReader.readLargeFile(inputFileName);

        // Process the data (for demo, just using the same data)
        List<String> processedData = new ArrayList<>();
        for (String record : data) {
            // Example processing (could include filtering, aggregation, etc.)
            processedData.add("Processed: " + record);
        }

        // Write the processed data to a new file
        LargeFileWriter.writeProcessedData(outputFileName, processedData);

        // Demonstrate error handling by attempting to read and write to non-existent or restricted files
        System.out.println("Attempting to read from a non-existent file:");
        LargeFileReader.readLargeFile("non_existent_file.txt");

        System.out.println("Attempting to write to a restricted directory:");
        LargeFileWriter.writeProcessedData("/restricted_directory/processed_sales_data.txt", processedData);
    }
}

