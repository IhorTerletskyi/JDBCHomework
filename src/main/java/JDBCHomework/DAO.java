package JDBCHomework;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<K, T> {
    private final Connection conn;
    private final String table;

    public DAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    public void add(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();

            StringBuilder names = new StringBuilder();
            StringBuilder values = new StringBuilder();

            for (Field f : fields) {
                f.setAccessible(true);

                names.append(f.getName()).append(',');
                values.append('"').append(f.get(t)).append("\",");
            }
            names.deleteCharAt(names.length() - 1); // last ','
            values.deleteCharAt(values.length() - 1); // last ','

            String sql = "INSERT INTO " + table + "(" + names.toString() +
                    ") VALUES(" + values.toString() + ")";

            try (Statement st = conn.createStatement()) {
                st.execute(sql.toString());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = null;

            for (Field f : fields) {
                if (f.isAnnotationPresent(Id.class)) {
                    id = f;
                    id.setAccessible(true);
                    break;
                }
            }
            if (id == null)
                throw new RuntimeException("No JDBCHomework.Id field");

            String sql = "DELETE FROM " + table + " WHERE " + id.getName() +
                    " = \"" + id.get(t) + "\"";

            try (Statement st = conn.createStatement()) {
                st.execute(sql.toString());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<T> getAll(Class<T> cls) {
        List<T> res = new ArrayList<>();
        System.out.println();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM " + table)) {
                    ResultSetMetaData md = rs.getMetaData();

                    for (int i = 1; i <= md.getColumnCount(); i++)
                        System.out.printf("%20s", md.getColumnName(i));
                    System.out.println();

                    while (rs.next()) {
                        T newObject = (T) cls.newInstance();

                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);

                            Field field = cls.getDeclaredField(columnName);
                            field.setAccessible(true);

                            field.set(newObject, rs.getObject(columnName));
                            System.out.printf("%20s", rs.getObject(columnName));
                        }

                        res.add(newObject);
                        System.out.println();
                    }
                }
            }

            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<T> getFullOrder(Class<T> cls) {
        List<T> res = new ArrayList<>();
        System.out.println();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT orders.*,products.name,products.weight,products.price,clients.clientName,clients.phone FROM orders INNER JOIN products ON orders.productId = products.Id INNER JOIN clients ON orders.clientId = clients.Id")) {
                    ResultSetMetaData md = rs.getMetaData();

                    for (int i = 1; i <= md.getColumnCount(); i++)
                        System.out.printf("%20s", md.getColumnName(i));
                    System.out.println();

                    while (rs.next()) {
                        T newObject = (T) cls.newInstance();

                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);
                            System.out.printf("%20s", rs.getObject(columnName));

                            try {
                                Field field = cls.getDeclaredField(columnName);
                                field.setAccessible(true);

                                field.set(newObject, rs.getObject(columnName));
                            } catch (NoSuchFieldException e) {
                            }
//                            System.out.printf("%20s", rs.getObject(columnName));
                        }

                        res.add(newObject);
                        System.out.println();
                    }
                }
            }

            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<T> getByParametr(Class<T> cls, String parametrName, String parametrValue) {
        List<T> res = new ArrayList<>();
        System.out.println();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM " + table + " WHERE " + parametrName + " = \"" + parametrValue + "\"")) {
                    ResultSetMetaData md = rs.getMetaData();

                    for (int i = 1; i <= md.getColumnCount(); i++)
                        System.out.printf("%20s", md.getColumnName(i));
                    System.out.println();

                    while (rs.next()) {
                        T newObject = (T) cls.newInstance();

                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);

                            Field field = cls.getDeclaredField(columnName);
                            field.setAccessible(true);

                            field.set(newObject, rs.getObject(columnName));
                            System.out.printf("%20s", rs.getObject(columnName));
                        }

                        System.out.println();
                        res.add(newObject);
                    }
                }
            }

            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void update(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = null;

            StringBuilder requestToSQL = new StringBuilder();
            requestToSQL.append("UPDATE ").append(table).append(" SET ");

            for (Field f : fields) {
                if (f.isAnnotationPresent(Id.class)) {
                    id = f;
                    id.setAccessible(true);
                    continue;
                }
                f.setAccessible(true);
                requestToSQL.append(f.getName()).append("=\"").append(f.get(t)).append("\",");
            }

            if (id == null){
                throw new RuntimeException("No Id field");
            }

            requestToSQL.deleteCharAt(requestToSQL.length() - 1);
            requestToSQL.append(" WHERE ").append(id.getName()).append(" = ").append('"').append(id.get(t)).append('"');

            try (Statement st = conn.createStatement()) {
                st.execute(requestToSQL.toString());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
