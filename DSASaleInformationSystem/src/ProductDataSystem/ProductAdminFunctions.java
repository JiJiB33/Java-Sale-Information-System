package ProductDataSystem;

import java.util.List;
import java.util.Scanner;
import SaleInfoMainSystem.Documentation;

public class ProductAdminFunctions implements ProductInterface {
    private List<Product> products;
    private ProductSearch productSearch = new ProductSearch();
    private ProductSort productSort = new ProductSort();
    private ProductPurchase productPurchase;
    private SaleReport saleReport = new SaleReport();
    private ProductAdder productAdder = new ProductAdder();
    private Documentation documentation;

    public ProductAdminFunctions(Documentation documentation, ProductPurchase productPurchase, List<Product> sharedProducts) {
        this.products = sharedProducts;
        this.documentation = documentation;
        this.productPurchase = productPurchase;
    }

    public void startAdminPanel() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Admin Panel ===");
            System.out.println("1. Show Products List");
            System.out.println("2. Search Products");
            System.out.println("3. Add New Product");
            System.out.println("4. Sort Products");
            System.out.println("5. Sales Report");
            System.out.println("6. Main Menu");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showProducts();
                case 2 -> {
                    System.out.print("Enter product name to search: ");
                    String name = scanner.nextLine();
                    searchProductByName(name);
                }
                case 3 -> productAdder.addProductFromInput(scanner, this);
                case 4 -> sortProducts(scanner);
                case 5 -> saleReport.generateSalesReport(productPurchase.getOrderHistory());
                case 6 -> {
                    System.out.println("Returning to main menu...");
                    documentation.startSession(); // Return to the documentation session
                    exit = true;
                }
                case 7 -> {
                    exit = true;
                    System.out.println("Exiting the system... Goodbye!");
                }
                default -> System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }

    private void sortProducts(Scanner scanner) {
        System.out.println("Choose sorting option:");
        System.out.println("1. By Name");
        System.out.println("2. By Price");
        System.out.println("3. By Category");

        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> productSort.sortByName(products);
            case 2 -> productSort.sortByPrice(products);
            case 3 -> productSort.sortByCategory(products);
            default -> System.out.println("Invalid choice, sorting not performed.");
        }

        showProducts();
    }

    @Override
    public void showProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            printProductList();
        }
    }

    private void printProductList() {
        System.out.printf("%-5s %-30s %-15s %-15s %-10s\n", "ID", "Name", "Category", "Price", "Quantity");
        System.out.println("-------------------------------------------------------------------------------");
        for (Product product : products) {
            String quantityDisplay = product.getQty() > 0 ? String.valueOf(product.getQty()) : "Out of Stock";
            System.out.printf("%-5d %-30s %-15s %-15s %-10s\n",
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice() + " MMK",
                quantityDisplay);
        }
    }

    @Override
    public void searchProductByName(String name) {
        productSearch.searchProductByName(name, products);
    }

    
    
    @Override
    public void addProduct(Product product) {
        if (!productExists(product.getId())) {
            products.add(product);
//            System.out.println("Product added successfully!");
        } else {
            System.out.println("A product with this ID already exists. Addition aborted.");
        }
    }

    @Override
    public boolean productExists(int productId) {
        return products.stream().anyMatch(p -> p.getId() == productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }
}
