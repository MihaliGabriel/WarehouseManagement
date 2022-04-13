package Connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Clasa ConnectionWarehouse se ocupa de crearea conexiunii cu baza de date. Are o metoda getter care returneaza conexiunea, de tip DriverManager.
 */
public class ConnectionWarehouse {
    private static final Logger LOGGER = Logger.getLogger(ConnectionWarehouse.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/homework3tp";
    private static final String user = "root";
    private static final String password = "1234";

    private static ConnectionWarehouse singleInstance = new ConnectionWarehouse();

    private ConnectionWarehouse() {
        try {
            Class.forName(DRIVER);
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBURL, user, password);
    }

}
