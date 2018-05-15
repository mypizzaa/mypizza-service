/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao.persist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import proven.modelo.Ingrediente;
import proven.modelo.Pizza;
import proven.modelo.Refresco;
import proven.modelo.Usuario;

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
                PreparedStatement st = conn.prepareStatement("SELECT * FROM tb_producto INNER JOIN tb_pizza ON tb_producto.id_producto = tb_pizza.id_producto ");
                ResultSet rs = st.executeQuery();
                pizzaList = new ArrayList<Pizza>();
                while(rs.next()){
                    pizzaList.add(resultsetToPizza(rs));     
                }
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return pizzaList;
    }
    
    private Pizza resultsetToPizza(ResultSet rs) throws SQLException {
        long id_producto = rs.getLong("id_producto");
        String nombre = rs.getString("nombre");
        double precio = rs.getDouble("precio");
        String imagen = rs.getString("imagen");
        long id_tipo = rs.getLong("id_tipo");
        long id_pizza = rs.getLong("id_pizza");
        return new Pizza(id_producto, nombre, precio, imagen, id_tipo, id_pizza);
    }  

    public List<Ingrediente> getIngredientsFromPizzaId(long id) {
         List<Ingrediente> iList = null;
        
        Connection conn = dbConnect.getConnection();
        try {
        if (conn != null) {
                PreparedStatement st = conn.prepareStatement("SELECT tb_producto.id_producto, tb_producto.nombre, tb_producto.precio, tb_producto.imagen, tb_producto.id_tipo, tb_ingredientes.id_ingrediente FROM tb_pizza INNER JOIN tb_pizzadetalle ON tb_pizzadetalle.id_pizza = tb_pizza.id_pizza INNER JOIN tb_ingredientes ON tb_ingredientes.id_ingrediente = tb_pizzadetalle.id_ingrediente INNER JOIN tb_producto ON tb_producto.id_producto = tb_ingredientes.id_producto WHERE tb_pizza.id_pizza=? ");
                st.setLong(1, id);
                ResultSet rs = st.executeQuery();
                iList = new ArrayList<Ingrediente>();
                while(rs.next()){
                    iList.add(resultsetToIngrediente(rs));     
                }
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return iList;
    }

    private Ingrediente resultsetToIngrediente(ResultSet rs) throws SQLException {
        long id_producto = rs.getLong("id_producto");
        String nombre = rs.getString("nombre");
        double precio = rs.getDouble("precio");
        String imagen = rs.getString("imagen");
        long id_tipo = rs.getLong("id_tipo");
        long id_ingrediente = rs.getLong("id_ingrediente");
        return new Ingrediente(id_producto, nombre, precio, imagen, id_tipo, id_ingrediente);
    }

    public List<Ingrediente> getAllIngredients() {
        List<Ingrediente> iList = null;
        
        Connection conn = dbConnect.getConnection();
        try {
        if (conn != null) {
                PreparedStatement st = conn.prepareStatement("SELECT * FROM tb_producto INNER JOIN tb_ingredientes ON tb_producto.id_producto = tb_ingredientes.id_producto");
                ResultSet rs = st.executeQuery();
                iList = new ArrayList<Ingrediente>();
                while(rs.next()){
                    iList.add(resultsetToIngrediente(rs));     
                }
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return iList;
    }

    public List<Refresco> getAllDrinks() {
        List<Refresco> rList = null;
        
        Connection conn = dbConnect.getConnection();
        try {
        if (conn != null) {
                PreparedStatement st = conn.prepareStatement("SELECT * FROM tb_producto INNER JOIN tb_refresco ON tb_producto.id_producto = tb_refresco.id_producto");
                ResultSet rs = st.executeQuery();
                rList = new ArrayList<Refresco>();
                while(rs.next()){
                    rList.add(resultsetToRefresco(rs));     
                }
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rList;
    }

    private Refresco resultsetToRefresco(ResultSet rs) throws SQLException {
        long id_producto = rs.getLong("id_producto");
        String nombre = rs.getString("nombre");
        double precio = rs.getDouble("precio");
        String imagen = rs.getString("imagen");
        long id_tipo = rs.getLong("id_tipo");
        long id_refresco = rs.getLong("id_refresco");
        return new Refresco(id_producto, nombre, precio, imagen, id_tipo, id_refresco);
    }
}
