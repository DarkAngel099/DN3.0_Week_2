import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

// FileCopy class
class FileCopy {
    public static void copyFile(Path source, Path target) {
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied from " + source + " to " + target);
        } catch (IOException e) {
            System.err.println("Error copying file from " + source + " to " + target + ": " + e.getMessage());
        }
    }
}

// DirectoryCopy class
class DirectoryCopy {
    public static void copyDirectory(Path sourceDir, Path targetDir) {
        try {
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetPath = targetDir.resolve(sourceDir.relativize(dir));
                    if (!Files.exists(targetPath)) {
                        Files.createDirectory(targetPath);
                        System.out.println("Directory created: " + targetPath);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetFile = targetDir.resolve(sourceDir.relativize(file));
                    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File copied: " + file + " to " + targetFile);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    System.err.println("Error visiting file: " + file + ": " + exc.getMessage());
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println("Error copying directory from " + sourceDir + " to " + targetDir + ": " + e.getMessage());
        }
    }
}

// Main application class
public class BackupSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for source and target directories
        System.out.print("Enter source directory path: ");
        String sourceDirPath = scanner.nextLine();
        System.out.print("Enter target directory path: ");
        String targetDirPath = scanner.nextLine();

        Path sourceDir = Paths.get(sourceDirPath);
        Path targetDir = Paths.get(targetDirPath);

        // Copy the directory
        DirectoryCopy.copyDirectory(sourceDir, targetDir);

        // Demonstrate error handling
        System.out.println("Attempting to copy to a read-only directory:");
        DirectoryCopy.copyDirectory(sourceDir, Paths.get("/root/backup"));  // This should fail if running without proper permissions

        scanner.close();
    }
}

