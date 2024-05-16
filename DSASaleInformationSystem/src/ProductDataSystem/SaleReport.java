package ProductDataSystem;

import java.util.List;
import java.util.stream.Collectors;

public class SaleReport {
    public void generateSalesReport(List<String> orderHistory) {
        System.out.println("\n=== Sales Report ===");

        if (orderHistory.isEmpty()) {
            System.out.println("No sales data available.");
        } else {
            for (String receipt : orderHistory) {
                System.out.println(receipt);
            }

            int totalRevenue = orderHistory.stream()
                .map(receipt -> Integer.parseInt(receipt.split("Total Cost: ")[1].split(" MMK")[0]))
                .collect(Collectors.summingInt(Integer::intValue));

            System.out.printf("\nTotal Revenue: %d MMK\n", totalRevenue);
        }
    }
}
