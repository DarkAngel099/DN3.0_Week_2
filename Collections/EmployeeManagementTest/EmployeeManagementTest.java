import java.util.ArrayList;

class EmployeeManagement {
    private ArrayList<Employee> employees;

    public EmployeeManagement() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added: " + employee);
    }

    public boolean removeEmployee(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                employees.remove(employee);
                System.out.println("Employee removed: " + employee);
                return true;
            }
        }
        System.out.println("Employee not found with ID: " + employeeId);
        return false;
    }

    public boolean updateEmployee(int employeeId, String newAddress) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                employee.setAddress(newAddress);
                System.out.println("Employee updated: " + employee);
                return true;
            }
        }
        System.out.println("Employee not found with ID: " + employeeId);
        return false;
    }

    public void displayEmployees() {
        System.out.println("Employee List:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}
class Employee {
    private int id;
    private String name;
    private String address;

    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Address: " + address;
    }
}
public class EmployeeManagementTest {
    public static void main(String[] args) {
        EmployeeManagement management = new EmployeeManagement();

        Employee emp1 = new Employee(1, "Allen", "123 Main Street");
        Employee emp2 = new Employee(2, "Bob", "456 Oak Street");
        Employee emp3 = new Employee(3, "Carl", "789 India");

        management.addEmployee(emp1);
        management.addEmployee(emp2);
        management.addEmployee(emp3);
        management.displayEmployees();
        management.updateEmployee(2, "456 E Street");
        management.removeEmployee(1);
        management.displayEmployees();
    }
}


