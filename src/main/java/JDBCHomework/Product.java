package JDBCHomework;

public class Product {
    @Id
    private int id;
    private String name;
    private int weight;
    private int price;

    public Product(int id, String name, int weight, int price) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public Product() {
    }
}
