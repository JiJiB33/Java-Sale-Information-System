package SaleInfoMainSystem;

import java.util.List;
import java.util.Scanner;
import ProductDataSystem.*;

public class Documentation {
    private List<Product> products;
    private ProductPurchase productPurchase;
    private ProductAdminFunctions adminFunctions;
    private ProductBuyerFunctions buyerFunctions;

    public Documentation() {
        this.products = SampleProductInitializer.initializeSampleProducts();
        this.productPurchase = new ProductPurchase();
        this.adminFunctions = new ProductAdminFunctions(this, productPurchase, products);
        this.buyerFunctions = new ProductBuyerFunctions(this, productPurchase, products);
    }

    public void startSession() {
        	Scanner scanner = new Scanner(System.in);
            boolean validChoice = false;

            System.out.println("Welcome to Thiri Pyae Sone's Sale Information System!");

            while (!validChoice) {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. Admin Panel");
                System.out.println("2. Buyer Panel");
                System.out.println("3. Or Exit");

                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> {
                            adminFunctions.startAdminPanel();
                            validChoice = true;
                        }
                        case 2 -> {
                            buyerFunctions.startBuyerPanel();
                            validChoice = true;
                        }
                        case 3 -> {
                            System.out.println("Exiting the system... Goodbye!");
                            validChoice = true;
                        }
                        default -> System.out.println("Invalid choice. Please choose either 1 for Admin, 2 for Buyer, or 3 to Exit.");
                    }
                } 
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    scanner.nextLine(); 
                }
            }
         
    }
    
    

    private void displayAdminDocumentation() {
        System.out.println("\n==================== Admin Documentation =======================");
        System.out.println("As an admin, you have access to various functionalities to manage products within the system.");
        String[] adminFunctionalities = {
            "1. You can display the products list.",
            "2. You can search for specific products.",
            "3. You can add new products to the system.",
            "4. You can sort products based on different parameters.",
            "5. You can review the order history.",
            "6. You can generate and review the sales report.",
        };

        System.out.printf("\n%-20s%-50s\n", "No.", "Available Options");
        System.out.println("--------------------------------------------------------------------------");
        for (String functionality : adminFunctionalities) {
            String[] parts = functionality.split("\\. ", 2);
            System.out.printf("%-10s%-10s\n", parts[0], parts[1]);
        }

        System.out.println("--------------------------------------------------------------------------");
    }

    private void displayBuyerDocumentation() {
        System.out.println("\n==================== Buyer Documentation =======================");
        System.out.println("As a buyer, you have access to various functionalities that allow you to browse, search, and filter products within the system.");
        String[] buyerFunctionalities = {
            "1. You can browse through a wide variety of products.",
            "2. You can search for specific products using different criteria.",
            "3. You can sort the products based on different parameters.",
            "4. You can purchase products directly through the system.",
            "5. You can review your order history.",
        };

        System.out.printf("\n%-20s%-50s\n", "No.", "Available Options");
        System.out.println("--------------------------------------------------------------------------");

        for (String functionality : buyerFunctionalities) {
            String[] parts = functionality.split("\\. ", 2);
            System.out.printf("%-10s%-10s\n", parts[0], parts[1]);
        }
        System.out.println("---------------------------------------------------------------------------");
    }

}
