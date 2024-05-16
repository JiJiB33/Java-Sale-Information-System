package ProductDataSystem;

import java.util.List;

public interface ProductInterface {
    void showProducts();
    void searchProductByName(String name);
    void addProduct(Product product);
    List<Product> getAllProducts();
    boolean productExists(int productId);  
}
