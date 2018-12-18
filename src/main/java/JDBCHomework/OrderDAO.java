package JDBCHomework;

import java.sql.Connection;

public class OrderDAO extends DAO<Integer, Order> {
    public OrderDAO(Connection conn, String table) {
        super(conn, table);
    }
}
