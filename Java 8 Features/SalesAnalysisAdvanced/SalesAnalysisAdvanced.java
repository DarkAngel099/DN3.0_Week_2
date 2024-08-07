import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SalesAnalysisAdvanced {

    // Define SalesRecord class
    static class SalesRecord {
        private int recordId;
        private String salesPerson;
        private String region;
        private double amount;
        private String date;
        private String productCategory;
        private int quantity;

        public SalesRecord(int recordId, String salesPerson, String region, double amount, String date, String productCategory, int quantity) {
            this.recordId = recordId;
            this.salesPerson = salesPerson;
            this.region = region;
            this.amount = amount;
            this.date = date;
            this.productCategory = productCategory;
            this.quantity = quantity;
        }

        public int getRecordId() { return recordId; }
        public String getSalesPerson() { return salesPerson; }
        public String getRegion() { return region; }
        public double getAmount() { return amount; }
        public String getDate() { return date; }
        public String getProductCategory() { return productCategory; }
        public int getQuantity() { return quantity; }

        @Override
        public String toString() {
            return "SalesRecord{" +
                    "recordId=" + recordId +
                    ", salesPerson='" + salesPerson + '\'' +
                    ", region='" + region + '\'' +
                    ", amount=" + amount +
                    ", date='" + date + '\'' +
                    ", productCategory='" + productCategory + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    private List<SalesRecord> salesRecords = new ArrayList<>();

    public SalesAnalysisAdvanced() {
        // Create sample data
        salesRecords.add(new SalesRecord(1, "Alice", "North", 1200.0, "2023-08-01", "Electronics", 5));
        salesRecords.add(new SalesRecord(2, "Bob", "South", 800.0, "2023-08-02", "Clothing", 10));
        salesRecords.add(new SalesRecord(3, "Charlie", "North", 1500.0, "2023-08-03", "Electronics", 3));
        salesRecords.add(new SalesRecord(4, "Alice", "East", 1100.0, "2023-08-04", "Furniture", 2));
        salesRecords.add(new SalesRecord(5, "Bob", "North", 900.0, "2023-08-05", "Clothing", 7));
    }

    public void filterAndSortRecords(String productCategory) {
        long startTime = System.nanoTime();
        
        List<SalesRecord> sortedRecords = salesRecords.stream()
                .filter(record -> record.getProductCategory().equalsIgnoreCase(productCategory))
                .sorted(Comparator.comparing(SalesRecord::getDate))
                .collect(Collectors.toList());

        long endTime = System.nanoTime();
        long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        
        System.out.println("Sorted Sales Records for product category " + productCategory + ":");
        sortedRecords.forEach(System.out::println);
        System.out.println("Time taken for sequential stream: " + duration + " ms");
        
        // Parallel stream for performance comparison
        startTime = System.nanoTime();
        
        sortedRecords = salesRecords.parallelStream()
                .filter(record -> record.getProductCategory().equalsIgnoreCase(productCategory))
                .sorted(Comparator.comparing(SalesRecord::getDate))
                .collect(Collectors.toList());
                
        endTime = System.nanoTime();
        duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        
        System.out.println("Time taken for parallel stream: " + duration + " ms");
    }

    public void calculateAverageSales(String region) {
        double averageSales = salesRecords.parallelStream()
                .filter(record -> record.getRegion().equalsIgnoreCase(region))
                .mapToDouble(SalesRecord::getAmount)
                .average()
                .orElse(0.0);

        System.out.println("Average Sales Amount for region " + region + ": " + averageSales);
    }

    public void findTopSalesRecord() {
        SalesRecord topRecord = salesRecords.parallelStream()
                .max(Comparator.comparing(SalesRecord::getAmount))
                .orElse(null);

        System.out.println("Top Sales Record:");
        System.out.println(topRecord);
    }

    public static void main(String[] args) {
        SalesAnalysisAdvanced analysis = new SalesAnalysisAdvanced();

        // Filter and sort sales records for a specific product category
        analysis.filterAndSortRecords("Electronics");

        // Calculate and print average sales amount for a specific region
        analysis.calculateAverageSales("North");

        // Find and print the sales record with the highest amount
        analysis.findTopSalesRecord();
    }
}

