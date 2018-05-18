package proven.mypizzadao.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class StoreDBConnect {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String BD_URL = "jdbc:mysql://localhost/mypizza";
    private static final String USUARI = "administrator";
    private static final String PASSWORD = "adminpsw";

    public StoreDBConnect(){
        
        try{
        Class.forName(this.DRIVER);
        }catch(ClassNotFoundException ex){
            System.out.println("Error falta driver.");
        }
    }
    
    /**
     * 
     * @return a connection
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
