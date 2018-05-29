package proven.mypizzadao.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author MyPizza
 * @version 1.0
 */
public class StoreDBConnect {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String BD_URL = "jdbc:mysql://127.0.0.1/dam1804";
    private static final String USUARI = "dam1804";
    private static final String PASSWORD = "Ew5kaer6";
    
    /**
     * Constructor
     */
    public StoreDBConnect() {
        try {
            Class.forName(this.DRIVER);
        } catch (ClassNotFoundException ex) {

        }
    }

    /**
     * Get connection from date source
     * @return a connection with data source
     */
    public Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(BD_URL, USUARI, PASSWORD);
            return conn;
        } catch (SQLException ex) {
            return null;
        }
    }
}
