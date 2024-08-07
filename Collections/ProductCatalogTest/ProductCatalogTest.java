import java.util.HashSet;

class ProductCatalog {
    private HashSet<String> productNames;

    public ProductCatalog() {
        productNames = new HashSet<>();
    }

    // Add Product
    public boolean addProduct(String productName) {
        if (productNames.contains(productName)) {
            System.out.println("Product already exists: " + productName);
            return false;
        } else {
            productNames.add(productName);
            System.out.println("Product added: " + productName);
            return true;
        }
    }

    // Remove Product
    public boolean removeProduct(String productName) {
        if (productNames.contains(productName)) {
            productNames.remove(productName);
            System.out.println("Product removed: " + productName);
            return true;
        } else {
            System.out.println("Product not found: " + productName);
            return false;
        }
    }

    // Search Product
    public boolean searchProduct(String productName) {
        return productNames.contains(productName);
    }

    // Display Products
    public void displayProducts() {
        System.out.println("Product Catalog:");
        for (String productName : productNames) {
            System.out.println(productName);
        }
    }
}
public class ProductCatalogTest {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();

        // Add Products
        catalog.addProduct("Laptop");
        catalog.addProduct("Smartphone");
        catalog.addProduct("Tablet");

        // Attempt to add a duplicate product
        catalog.addProduct("Laptop");

        // Display Products
        catalog.displayProducts();

        // Search Products
        System.out.println("Searching for 'Smartphone': " + catalog.searchProduct("Smartphone"));
        System.out.println("Searching for 'Camera': " + catalog.searchProduct("Camera"));

        // Remove Products
        catalog.removeProduct("Smartphone");
        catalog.removeProduct("Camera");

        // Display Products again to verify removal
        catalog.displayProducts();
    }
}
