package ProductDataSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ProductPurchase {
    public List<String> orderHistory = new ArrayList<>();
    public Map<String, Integer> discountCodes = Map.of(
        "DISCOUNT10", 10, // 10% discount
        "DISCOUNT20", 20  // 20% discount
    );

    public List<String> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }

    public void purchaseProduct(Scanner scanner, List<Product> products) {
        System.out.print("Enter product ID to purchase: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Product product : products) {
            if (product.getId() == id) {
                found = true;
                System.out.print("Enter quantity to purchase: ");
                int qty = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter discount code (if any) or press Enter: ");
                String discountCode = scanner.nextLine();

                int discount = discountCodes.getOrDefault(discountCode.toUpperCase(), 0);
                int totalPrice = product.getPrice() * qty;
                int discountedPrice = totalPrice - (totalPrice * discount / 100);

                if (qty <= product.getQty()) {
                    // Show purchase summary and ask for confirmation
                    System.out.printf("\n--- Purchase Summary ---\n");
                    System.out.printf("Product: %s\n", product.getName());
                    System.out.printf("Quantity: %d\n", qty);
                    System.out.printf("Unit Price: %d MMK\n", product.getPrice());
                    System.out.printf("Total Cost: %d MMK\n", totalPrice);
                    System.out.printf("Discount: %d%%\n", discount);
                    System.out.printf("Final Cost: %d MMK\n", discountedPrice);
                    System.out.printf("------------------------\n");

                    System.out.print("Confirm purchase? (yes/no): ");
                    String confirmation = scanner.nextLine().trim().toLowerCase();

                    if (confirmation.equals("yes")) {
                        product.setQty(product.getQty() - qty);

                        System.out.println("--------------------");
                        System.out.println("Purchase successful!");
                        System.out.println("--------------------");

                        String receipt = generateReceipt(product, qty, totalPrice, discountedPrice, discount);
                        orderHistory.add(receipt);
                        System.out.println(receipt);
                    } else {
                        System.out.println("------------------");
                        System.out.println("Purchase canceled.");
                        System.out.println("------------------");

                    }
                } else {
                    System.out.println("Not enough quantity available.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    public String generateReceipt(Product product, int quantity, int totalPrice, int discountedPrice, int discount) {
        String receipt = "\n================= Receipt ================\n";
        receipt += "Product Name: " + product.getName() + "\n";
        receipt += "Quantity: " + quantity + "\n";
        receipt += "Unit Price: " + product.getPrice() + " MMK\n";
        receipt += "Total Cost: " + totalPrice + " MMK\n";
        receipt += "Discount: " + discount + "%\n";
        receipt += "Final Cost: " + discountedPrice + " MMK\n";
        receipt += "Thank you for shopping with us <3\n";
        receipt += "==========================================\n";
        return receipt;
    }

    public void showOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No purchase history available.");
        } else {
            System.out.println("Showing all purchase history:");
            for (String receipt : orderHistory) {
                System.out.println(receipt);
            }
        }
    }
}
