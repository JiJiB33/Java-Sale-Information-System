package ProductDataSystem;

import java.util.Scanner;

public class ProductAdder {
    public void addProductFromInput(Scanner scanner, ProductInterface productInterface) {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product category: ");
        String category = scanner.nextLine();
        System.out.print("Enter product price: ");
        int price = scanner.nextInt();
        System.out.print("Enter product quantity: ");
        int qty = scanner.nextInt();
        scanner.nextLine();
        
     // Check if product already exists
        if (productInterface.productExists(id)) {
            System.out.println("Error: Product with this ID already exists.");
            return;
        }

        Product product = new Product(id, name, category, price, qty);

     // Ask for confirmation before adding the product
        System.out.print("Confirm adding this product? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("yes")) {
            productInterface.addProduct(product);
            System.out.println("---------------------------");
            System.out.println("Product added successfully!");
            System.out.println("---------------------------");
        } 
        else {
            System.out.println("---------------------------");
            System.out.println("Product addition canceled.");
            System.out.println("---------------------------");
        }
        
    }
}
