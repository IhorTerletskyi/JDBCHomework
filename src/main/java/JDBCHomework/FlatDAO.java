package JDBCHomework;

import java.sql.Connection;

public class FlatDAO extends DAO<Integer,Flat> {
    public FlatDAO(Connection conn, String table) {
        super(conn, table);
    }
}
