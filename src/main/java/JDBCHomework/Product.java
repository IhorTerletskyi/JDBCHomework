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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
