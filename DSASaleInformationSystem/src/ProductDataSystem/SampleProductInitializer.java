package ProductDataSystem;

import java.util.ArrayList;

public class SampleProductInitializer {
    public static ArrayList<Product> initializeSampleProducts() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1, "Apple Magic Pen", "Electronics", 125000, 10));
        products.add(new Product(2, "Samsung SmartWatch 6", "Electronics", 450000, 15));
        products.add(new Product(3, "PlayStation 5 Gaming Console", "Electronics", 1360000, 20));
        products.add(new Product(4, "Sony WH-1000XM4 Headphones", "Accessories", 350000, 25));
        products.add(new Product(5, "Logitech MX Master 3 Mouse", "Accessories", 167000, 30));
        products.add(new Product(6, "Nike Air Max 270", "Footwear", 262000, 50));
        products.add(new Product(7, "Adidas Ultraboost 21", "Footwear", 330000, 50));
        products.add(new Product(8, "Levi's 501 Original Jeans", "Clothing", 90000, 100));
        products.add(new Product(9, "Prada Sweater Jacket", "Clothing", 345600, 60));
        products.add(new Product(10, "Capcom Portable Deck", "Electronics", 865000, 5));
        return products;
    }
}
