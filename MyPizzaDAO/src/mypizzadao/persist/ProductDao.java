/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypizzadao.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Pizza;
import modelo.Usuario;

/**
 *
 * @author alumne
 */
public class ProductDao {

    private StoreDBConnect dbConnect;    
   
    public ProductDao() {
        try {
            dbConnect = new StoreDBConnect();
        } catch (ClassNotFoundException ex) {            
        }
    }
    
    public List<Pizza> getAllPizzas(){
        List<Pizza> pizzaList = null;
        
        Connection conn = dbConnect.getConnection();
        try {
        if (conn != null) {
                PreparedStatement st = conn.prepareStatement("SELECT * FROM tb_producto INNER JOIN tb_pizza ON tb_producto.id_producto = tb_pizza.id_producto INNER JOIN tb_tipo ON tb_tipo.id_tipo = tb_producto.id_tipo WHERE tb_tipo.nombre = 'Pizza'");
                ResultSet rs = st.executeQuery();
                pizzaList = new ArrayList<Pizza>();
                while(rs.next()){
                    pizzaList.add(resultsetToPizza(rs));     
                }
        }
        } catch (SQLException ex) {
        }
        return pizzaList;
    }
    
    private Pizza resultsetToPizza(ResultSet rs) throws SQLException {
        Pizza p = null;
        long id_producto = rs.getLong("id_producto");
        String nombre = rs.getString("nombre");
        double precio = rs.getDouble("preio");
        String imagen = rs.getString("imagen");
        long id_tipo = rs.getLong("id_producto");
        long id_pizza = rs.getLong("id_producto");

        p = new Pizza(id_producto, nombre, precio, imagen, id_tipo, id_pizza);
        return p;
    }  
}
