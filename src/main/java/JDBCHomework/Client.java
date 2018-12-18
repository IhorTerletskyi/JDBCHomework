package JDBCHomework;

public class Client {
    @Id
    private int id;
    private String name;
    private int age;
    private int phone;

    public Client(int id, String name, int age, int phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Client() {
    }
}
