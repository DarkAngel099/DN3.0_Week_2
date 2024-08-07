import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class SalesRecord {
    private int recordId;
    private String salesPerson;
    private String region;
    private double amount;
    private String date;

    public SalesRecord(int recordId, String salesPerson, String region, double amount, String date) {
        this.recordId = recordId;
        this.salesPerson = salesPerson;
        this.region = region;
        this.amount = amount;
        this.date = date;
    }

    public int getRecordId() {
        return recordId;
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public String getRegion() {
        return region;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "recordId=" + recordId +
                ", salesPerson='" + salesPerson + '\'' +
                ", region='" + region + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
    }
}

public class SalesAnalysis {
    private List<SalesRecord> salesRecords = new ArrayList<>();

    public SalesAnalysis() {
        // Create sample data
        salesRecords.add(new SalesRecord(1, "Alice", "North", 1200.0, "2023-08-01"));
        salesRecords.add(new SalesRecord(2, "Bob", "South", 800.0, "2023-08-02"));
        salesRecords.add(new SalesRecord(3, "Charlie", "North", 1500.0, "2023-08-03"));
        salesRecords.add(new SalesRecord(4, "Alice", "East", 1100.0, "2023-08-04"));
        salesRecords.add(new SalesRecord(5, "Bob", "North", 900.0, "2023-08-05"));
    }

    public void filterSalesByRegion(String region) {
        List<SalesRecord> filteredRecords = salesRecords.stream()
                .filter(record -> record.getRegion().equalsIgnoreCase(region))
                .collect(Collectors.toList());

        System.out.println("Filtered Sales Records for region " + region + ":");
        filteredRecords.forEach(System.out::println);
    }

    public void extractAndPrintSalesAmounts(String region) {
        List<Double> salesAmounts = salesRecords.stream()
                .filter(record -> record.getRegion().equalsIgnoreCase(region))
                .map(SalesRecord::getAmount)
                .collect(Collectors.toList());

        System.out.println("Sales Amounts for region " + region + ":");
        salesAmounts.forEach(System.out::println);
    }

    public void calculateAndPrintTotalSales(String region) {
        double totalSales = salesRecords.stream()
                .filter(record -> record.getRegion().equalsIgnoreCase(region))
                .mapToDouble(SalesRecord::getAmount)
                .sum();

        System.out.println("Total Sales for region " + region + ": " + totalSales);
    }

    public void groupAndPrintSalesBySalesPerson() {
        Map<String, List<SalesRecord>> salesBySalesPerson = salesRecords.stream()
                .collect(Collectors.groupingBy(SalesRecord::getSalesPerson));

        System.out.println("Sales Records grouped by SalesPerson:");
        salesBySalesPerson.forEach((salesPerson, records) -> {
            System.out.println("SalesPerson: " + salesPerson);
            records.forEach(System.out::println);
        });
    }

    public void generateAndPrintSalesReport() {
        Map<String, Double> salesReport = salesRecords.stream()
                .collect(Collectors.groupingBy(SalesRecord::getSalesPerson, Collectors.summingDouble(SalesRecord::getAmount)));

        System.out.println("Sales Report:");
        salesReport.forEach((salesPerson, totalAmount) -> {
            System.out.println("SalesPerson: " + salesPerson + ", Total Sales Amount: " + totalAmount);
        });
    }

    public static void main(String[] args) {
        SalesAnalysis analysis = new SalesAnalysis();

        // Filter and print sales records for region "North"
        analysis.filterSalesByRegion("North");

        // Extract and print sales amounts for region "North"
        analysis.extractAndPrintSalesAmounts("North");

        // Calculate and print total sales for region "North"
        analysis.calculateAndPrintTotalSales("North");

        // Group and print sales records by salesPerson
        analysis.groupAndPrintSalesBySalesPerson();

        // Generate and print sales report
        analysis.generateAndPrintSalesReport();
    }
}

