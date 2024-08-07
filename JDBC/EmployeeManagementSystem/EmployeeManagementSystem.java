import java.util.Scanner;

public class EmployeeManagementSystem {
1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display the menu
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Execute the chosen operation
            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    updateEmployee(scanner);
                    break;
                case 3:
                    deleteEmployee(scanner);
                    break;
                case 4:
                    viewEmployees();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to add a new employee
    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter employee name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        System.out.print("Enter employee position: ");
        String position = scanner.nextLine();
        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();

        EmployeeDataRetrieval.addEmployee(name, position, salary);
    }

    // Method to update an existing employee
    private static void updateEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        System.out.print("Enter new position: ");
        String position = scanner.nextLine();
        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();

        EmployeeDataRetrieval.updateEmployee(id, name, position, salary);
    }

    // Method to delete an employee
    private static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter employee ID to delete: ");
        int id = scanner.nextInt();
        EmployeeDataRetrieval.deleteEmployee(id);
    }

    // Method to view all employees
    private static void viewEmployees() {
        EmployeeDataRetrieval.getEmployees();
    }
}
