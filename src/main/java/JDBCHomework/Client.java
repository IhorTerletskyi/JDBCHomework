package JDBCHomework;

public class Client {
    @Id
    private int id;
    private String clientName;
    private int age;
    private int phone;

    public Client(int id, String clientName, int age, int phone) {
        this.id = id;
        this.clientName = clientName;
        this.age = age;
        this.phone = phone;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return clientName;
    }

    public void setName(String name) {
        this.clientName = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
