package JDBCHomework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb?serverTimezone=Europe/Kiev";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "12345";

    public static void main(String[] args) {
        Flat flatOne = new Flat(1, "Borshchaga", "asd,22", 45,2, 45000);
        Flat flatTwo = new Flat(2, "Borshchaga", "dagns,45", 25,1, 30000);
        Flat flatThree = new Flat(3, "Troya", "dordor,4", 98,4, 567000);

        Client clientOne = new Client(1,"Vasya", 34, 5466788);
        Client clientTwo = new Client(2,"Petya", 23, 8834677);

        Product productOne = new Product(1, "Khren", 1, 15);
        Product productTwo = new Product(2, "Figa", 2, 190);
        Product productThree = new Product(3, "Cucumber", 1, 40);

        try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            try (Statement st = conn.createStatement()){
                st.execute("DROP TABLE IF EXISTS " + "flats");
                st.execute("CREATE TABLE " + "flats" +" (id INT NOT NULL PRIMARY KEY, district VARCHAR(20) NOT NULL, address VARCHAR(20), square INT, rooms INT, price INT)");

                st.execute("DROP TABLE IF EXISTS " + "clients");
                st.execute("CREATE TABLE " + "clients" +" (id INT NOT NULL PRIMARY KEY, name VARCHAR(10), age INT, phone INT)");

                st.execute("DROP TABLE IF EXISTS " + "products");
                st.execute("CREATE TABLE " + "products" +" (id INT NOT NULL PRIMARY KEY, name VARCHAR(10), weight INT, price INT)");

                st.execute("DROP TABLE IF EXISTS " + "orders");
                st.execute("CREATE TABLE " + "orders" +" (id INT NOT NULL PRIMARY KEY, date LONG, productId INT, clientId INT)");
            }

            FlatDAO flatDAO = new FlatDAO(conn, "flats");
            ClientDAO clientDAO = new ClientDAO(conn, "clients");
            ProductDAO productDAO = new ProductDAO(conn, "products");
            OrderDAO orderDAO = new OrderDAO(conn, "orders");

            flatDAO.add(flatOne);
            flatDAO.add(flatThree);
            flatDAO.add(flatTwo);

            List flats = flatDAO.getAll(Flat.class);
            flatDAO.getByParametr(Flat.class, "district", "Borshchaga");

            flatOne.setPrice(500000);
            flatDAO.update(flatOne);
            flatDAO.getAll(Flat.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
