package proven.mypizzadao.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class StoreDBConnect {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String BD_URL = "jdbc:mysql://"+"127.0.0.1/mypizza";
    private static final String USUARI = "administrator";
    private static final String PASSWORD = "adminpsw";

    public StoreDBConnect() throws ClassNotFoundException {
        Class.forName(this.DRIVER);       
    }
    
    /**
     * 
     * @return a connection 
     * @throws SQLException if a connection error occurs
     */
    public Connection getConnection(){
        try{
            Connection conn=DriverManager.getConnection(BD_URL, USUARI, PASSWORD);
        return conn;
        }catch(SQLException ex){
            System.out.println("There is no connection with the database ");
            return null;
        }
    }
}
