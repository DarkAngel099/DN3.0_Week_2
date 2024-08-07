import java.util.LinkedList;
class OrderTracking {
    private LinkedList<Order> orders;

    public OrderTracking() {
        orders = new LinkedList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order added: " + order);
    }

    public Order processOrder() {
        if (!orders.isEmpty()) {
            Order order = orders.removeFirst();
            System.out.println("Order processed: " + order);
            return order;
        } else {
            System.out.println("No orders to process.");
            return null;
        }
    }

    public void displayOrders() {
        System.out.println("Order List:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
class Order {
    private int orderId;
    private String orderDetails;

    public Order(int orderId, String orderDetails) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Details: " + orderDetails;
    }
}
public class OrderTrackingTest {
    public static void main(String[] args) {
        OrderTracking tracking = new OrderTracking();

        Order order1 = new Order(1, "Order details for order 1");
        Order order2 = new Order(2, "Order details for order 2");
        Order order3 = new Order(3, "Order details for order 3");

        tracking.addOrder(order1);
        tracking.addOrder(order2);
        tracking.addOrder(order3);
        tracking.displayOrders();
        tracking.processOrder();
        tracking.processOrder();
        tracking.displayOrders();
    }
}


