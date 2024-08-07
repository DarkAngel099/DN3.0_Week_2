import java.util.ArrayList;
import java.util.List;

// Define the OrderFilter functional interface
interface OrderFilter {
    boolean filter(Order order);
}

// Define the OrderProcessor functional interface
interface OrderProcessor {
    void process(Order order);
}

// Define the Order class with attributes orderId, customerName, orderAmount, and status
class Order {
    int orderId;
    String customerName;
    double orderAmount;
    String status;

    public Order(int orderId, String customerName, double orderAmount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderAmount = orderAmount;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", orderAmount=" + orderAmount +
                ", status='" + status + '\'' +
                '}';
    }
}

// Main class to process the orders
public class OrderProcessing {
    private List<Order> orders = new ArrayList<>();

    // Constructor to initialize the list with some sample orders
    public OrderProcessing() {
        orders.add(new Order(1, "Alice", 100, "Pending"));
        orders.add(new Order(2, "Bob", 200, "Pending"));
        orders.add(new Order(3, "Charlie", 300, "Pending"));
    }

    // Method to filter and process orders based on the provided filter and processor
    public void filterAndProcess(OrderFilter filter, OrderProcessor processor) {
        for (Order order : orders) {
            if (filter.filter(order)) {
                processor.process(order);
            }
        }
    }

    // Method to process all orders with the provided processor
    public void processAll(OrderProcessor processor) {
        for (Order order : orders) {
            processor.process(order);
        }
    }

    public static void main(String[] args) {
        OrderProcessing orderProcessing = new OrderProcessing();

        // Filter orders with an amount greater than 150
        OrderFilter highAmountFilter = order -> order.orderAmount > 150;
        // Filter orders with an amount less than or equal to 150
        OrderFilter lowAmountFilter = order -> order.orderAmount <= 150;

        // Process orders with amount greater than 150 by changing status to "Shipped"
        OrderProcessor shipProcessor = order -> order.status = "Shipped";
        // Process orders with amount less than or equal to 150 by changing status to "Processed"
        OrderProcessor processProcessor = order -> order.status = "Processed";

        // Apply the appropriate processor to the orders based on their amount
        orderProcessing.filterAndProcess(highAmountFilter, shipProcessor);
        orderProcessing.filterAndProcess(lowAmountFilter, processProcessor);

        // Print all orders to verify the changes
        for (Order order : orderProcessing.orders) {
            System.out.println(order);
        }
    }
}

