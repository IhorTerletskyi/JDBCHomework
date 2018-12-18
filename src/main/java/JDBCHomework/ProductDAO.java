package JDBCHomework;

import java.sql.Connection;

public class ProductDAO extends DAO<Integer, Product> {
    public ProductDAO(Connection conn, String table) {
        super(conn, table);
    }
}
