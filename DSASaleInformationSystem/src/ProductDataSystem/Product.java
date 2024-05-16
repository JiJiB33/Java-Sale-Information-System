package ProductDataSystem;

public class Product {
    private int id;
    private String name;
    private String category;
    private int price;
    private int qty;

    public Product(int id, String name, String category, int price, int qty) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
