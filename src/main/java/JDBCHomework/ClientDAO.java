package JDBCHomework;

import java.sql.Connection;

public class ClientDAO extends DAO<Integer, Client> {
    public ClientDAO(Connection conn, String table) {
        super(conn, table);
    }
}
