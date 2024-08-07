import java.util.HashMap;

class InventoryManagement {
    private HashMap<Integer, Product> products;

    public InventoryManagement() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            System.out.println("Product ID already exists: " + product.getId());
        } else {
            products.put(product.getId(), product);
            System.out.println("Product added: " + product);
        }
    }

    public void removeProduct(int productId) {
        if (products.containsKey(productId)) {
            Product removedProduct = products.remove(productId);
            System.out.println("Product removed: " + removedProduct);
        } else {
            System.out.println("Product not found with ID: " + productId);
        }
    }

    public void updateProductQuantity(int productId, int newQuantity) {
        Product product = products.get(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            System.out.println("Product updated: " + product);
        } else {
            System.out.println("Product not found with ID: " + productId);
        }
    }

    public void displayProducts() {
        System.out.println("Product Inventory:");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }
}
class Product {
    private int id;
    private String name;
    private int quantity;

    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Quantity: " + quantity;
    }
}
public class InventoryManagementTest {
    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();

        Product prod1 = new Product(1, "Laptop", 10);
        Product prod2 = new Product(2, "Smartphone", 20);
        Product prod3 = new Product(3, "Tablet", 15);

        inventory.addProduct(prod1);
        inventory.addProduct(prod2);
        inventory.addProduct(prod3);
        inventory.displayProducts();
        inventory.updateProductQuantity(2, 25);
        inventory.removeProduct(1);
        inventory.displayProducts();
    }
}

