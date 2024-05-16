package ProductDataSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductSort {

    public void sortByName(List<Product> products) {
        List<Product> sortedProducts = mergeSort(products, Comparator.comparing(Product::getName));
        products.clear();
        products.addAll(sortedProducts);
    }

    public void sortByPrice(List<Product> products) {
        List<Product> sortedProducts = mergeSort(products, Comparator.comparingInt(Product::getPrice));
        products.clear();
        products.addAll(sortedProducts);
    }

    public void sortByCategory(List<Product> products) {
        List<Product> sortedProducts = mergeSort(products, Comparator.comparing(Product::getCategory));
        products.clear();
        products.addAll(sortedProducts);
    }

    private List<Product> mergeSort(List<Product> products, Comparator<Product> comparator) {
        if (products.size() <= 1) {
            return products;
        }

        int mid = products.size() / 2;
        List<Product> left = new ArrayList<>(products.subList(0, mid));
        List<Product> right = new ArrayList<>(products.subList(mid, products.size()));

        left = mergeSort(left, comparator);
        right = mergeSort(right, comparator);

        return merge(left, right, comparator);
    }

    private List<Product> merge(List<Product> left, List<Product> right, Comparator<Product> comparator) {
        List<Product> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i++));
        }

        while (j < right.size()) {
            merged.add(right.get(j++));
        }

        return merged;
    }
}
