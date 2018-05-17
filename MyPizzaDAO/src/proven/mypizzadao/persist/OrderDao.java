/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao.persist;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import proven.modelo.Empleado;
import proven.modelo.Factura;
import proven.modelo.MetodoPago;

/**
 *
 * @author alumne
 */
public class OrderDao {
    
    private StoreDBConnect dbConnection;

    public OrderDao() {
        
        dbConnection = new StoreDBConnect();
    }
    
    public int createBill(Factura f){
        int i = 0;
        
        Connection conn = dbConnection.getConnection();
        
        if (conn != null){
            try {
                PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_factura(id_cliente, id_metodoPago, fecha, cobrado) VALUES (?,?,?,?)");
                pst.setLong(1, f.getIdCliente());
                pst.setLong(2, f.getIdMetodoPago());
                pst.setDate(3, (Date) f.getFecha());
                pst.setInt(4, 0);
                
                i = pst.executeUpdate();
            } catch (SQLException ex) {
            }
            
        }
        
       return i;         
    }
    
    
}
