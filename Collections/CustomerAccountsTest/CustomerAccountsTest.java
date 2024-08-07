import java.util.TreeMap;
class CustomerAccounts {
    private TreeMap<Integer, Customer> customers;

    public CustomerAccounts() {
        customers = new TreeMap<>();
    }

    public void addCustomer(Customer customer) {
        if (customers.containsKey(customer.getId())) {
            System.out.println("Customer ID already exists: " + customer.getId());
        } else {
            customers.put(customer.getId(), customer);
            System.out.println("Customer added: " + customer);
        }
    }

    public void removeCustomer(int customerId) {
        if (customers.containsKey(customerId)) {
            Customer removedCustomer = customers.remove(customerId);
            System.out.println("Customer removed: " + removedCustomer);
        } else {
            System.out.println("Customer not found with ID: " + customerId);
        }
    }

    public void displayCustomers() {
        System.out.println("Customer List:");
        for (Customer customer : customers.values()) {
            System.out.println(customer);
        }
    }
}
class Customer {
    private int id;
    private String name;
    private String email;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}
public class CustomerAccountsTest {
    public static void main(String[] args) {
        CustomerAccounts accounts = new CustomerAccounts();

        // Add Customers
        Customer cust1 = new Customer(1, "Allen", "allen@example.com");
        Customer cust2 = new Customer(2, "Bob", "bob@example.com");
        Customer cust3 = new Customer(3, "Carl", "carl@example.com");

        accounts.addCustomer(cust1);
        accounts.addCustomer(cust2);
        accounts.addCustomer(cust3);
        accounts.displayCustomers();
        accounts.removeCustomer(2);
        accounts.displayCustomers();
    }
}

