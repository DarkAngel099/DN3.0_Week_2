import java.util.ArrayList;
import java.util.List;

interface OrderFilter {
    boolean filter(Order order);
}

interface OrderProcessor {
    void process(Order order);
}

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

public class OrderProcessing {
    private List<Order> orders = new ArrayList<>();

    public OrderProcessing() {
        orders.add(new Order(1, "Alice", 100, "Pending"));
        orders.add(new Order(2, "Bob", 200, "Pending"));
        orders.add(new Order(3, "Charlie", 300, "Pending"));
    }

    public void filterAndProcess(OrderFilter filter, OrderProcessor processor) {
        for (Order order : orders) {
            if (filter.filter(order)) {
                processor.process(order);
            }
        }
    }

    public void processAll(OrderProcessor processor) {
        for (Order order : orders) {
            processor.process(order);
        }
    }

    public static void main(String[] args) {
        OrderProcessing orderProcessing = new OrderProcessing();

        // Filter orders with amount greater than 150
        OrderFilter filter = order -> order.orderAmount > 150;

        // Process orders by changing status to "Processed"
        OrderProcessor processor = order -> order.status = "Processed";

        orderProcessing.filterAndProcess(filter, processor);

        // Process all orders by changing status to "Shipped"
        orderProcessing.processAll(order -> order.status = "Shipped");

        for (Order order : orderProcessing.orders) {
            System.out.println(order);
        }
    }
}