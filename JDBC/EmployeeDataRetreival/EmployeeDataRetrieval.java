import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDataRetrieval {

    /**
     * Adds a new employee to the employees table.
     */
    public static void addEmployee(String name, String position, double salary) {
        String query = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, position);
            preparedStatement.setDouble(3, salary);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new employee was added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing employee record based on the id.
     */
    public static void updateEmployee(int id, String name, String position, double salary) {
        String query = "UPDATE employees SET name = ?, position = ?, salary = ? WHERE id = ?";
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, position);
            preparedStatement.setDouble(3, salary);
            preparedStatement.setInt(4, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing employee was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an employee record based on the id.
     */
    public static void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("An employee was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all employees from the employees table and displays their details.
     */
    public static void getEmployees() {
        String query = "SELECT id, name, position, salary FROM employees";
        try (Connection connection = JDBCConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Employee Details:");
            System.out.println("ID | Name | Position | Salary");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                double salary = resultSet.getDouble("salary");
                System.out.println(id + " | " + name + " | " + position + " | " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Demonstrate CRUD operations
    	addEmployee("Joemama", "Developer", 75000);
        addEmployee("Afsha", "Manager", 85000);
        addEmployee("Swathi", "Designer", 65000);
        addEmployee("Samba","CEO",100000);

        // Retrieve and display all employees
        getEmployees();

        // Update an employee
        updateEmployee(1, "Teja", "Senior Developer", 90000);

        // Retrieve and display all employees
        getEmployees();

        // Delete an employee
        deleteEmployee(0);

        // Retrieve and display all employees
        getEmployees();
    }
}
