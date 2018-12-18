package JDBCHomework;

public class Order {
    @Id
    private int id;
    private long time;
    private int productId;
    private int clientId;

    public Order(int id, int productId, int clientId) {
        this.id = id;
        this.productId = productId;
        this.clientId = clientId;
        this.time = System.currentTimeMillis();
    }

    public Order() {
    }
}
