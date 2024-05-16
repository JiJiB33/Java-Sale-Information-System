package ProductDataSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductSearch {

    public void searchProductByName(String name, List<Product> products) {
        try {
            // Sort by name before searching
            Collections.sort(products, Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER));

            int index = binarySearchStartingPoint(products, name, 0, products.size() - 1);
            if (index != -1) {
                List<Product> matchingProducts = findPartialMatches(products, name, index);
                if (!matchingProducts.isEmpty()) {
                    printProductList(matchingProducts);
                } 
                else {
                    System.out.println("Product not found.");
                }
            } 
            else {
                System.out.println("Product not found.");
            }
        } 
        catch (Exception e) {
            System.out.println("An error occurred during the product search: " + e.getMessage());
        }
    }

    // Finds the starting point for partial matches using binary search
    private int binarySearchStartingPoint(List<Product> products, String name, int low, int high) {
        if (low <= high) {
            int mid = low + (high - low) / 2;
            Product product = products.get(mid);
            int comparison = product.getName().compareToIgnoreCase(name);

            if (product.getName().toLowerCase().startsWith(name.toLowerCase())) {
                return mid;
            } 
            else if (comparison < 0) {
                return binarySearchStartingPoint(products, name, mid + 1, high);
            } 
            else {
                return binarySearchStartingPoint(products, name, low, mid - 1);
            }
        }
        return -1;
    }

    // Finds all products that partially match the given name starting from an initial index
    private List<Product> findPartialMatches(List<Product> products, String name, int startIndex) {
        List<Product> matchingProducts = new ArrayList<>();
        name = name.toLowerCase();

        // Scan backward from startIndex to find earlier matches
        for (int i = startIndex; i >= 0; i--) {
            if (products.get(i).getName().toLowerCase().startsWith(name)) {
                matchingProducts.add(products.get(i));
            } 
            else {
                break;
            }
        }

        // Scan forward from startIndex to find later matches
        for (int i = startIndex + 1; i < products.size(); i++) {
            if (products.get(i).getName().toLowerCase().startsWith(name)) {
                matchingProducts.add(products.get(i));
            } 
            else {
                break;
            }
        }

        // Sort matching products list by name
        Collections.sort(matchingProducts, Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER));
        return matchingProducts;
    }

    private void printProductList(List<Product> products) {
        System.out.printf("%-5s %-30s %-15s %-15s %-10s\n", "ID", "Name", "Category", "Price", "Quantity");
        System.out.println("-------------------------------------------------------------------------------");
        for (Product product : products) {
            System.out.printf("%-5d %-30s %-15s %-15s %-10d\n",
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice() + " MMK",
                product.getQty());
        }
    }
}
