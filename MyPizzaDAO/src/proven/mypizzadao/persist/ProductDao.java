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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proven.modelo.Ingrediente;
import proven.modelo.Pizza;
import proven.modelo.Producto;
import proven.modelo.Refresco;
import proven.modelo.Usuario;

/**
 *
 * @author MyPizza
 * @version 1.0
 */
public class ProductDao {

    private StoreDBConnect dbConnect;

    /**
     * Constructor
     */
    public ProductDao() {
        dbConnect = new StoreDBConnect();
    }

    /**
     * list all pizzas from data saource
     *
     * @return a list of pizzas or null if error
     */
    public List<Pizza> getAllPizzas() {
        List<Pizza> pizzaList = null;

        Connection conn = dbConnect.getConnection();
        try {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement("SELECT * FROM tb_producto INNER JOIN tb_pizza ON tb_producto.id_producto = tb_pizza.id_producto ");
                ResultSet rs = st.executeQuery();
                pizzaList = new ArrayList<Pizza>();
                while (rs.next()) {
                    pizzaList.add(resultsetToPizza(rs));
                }
            }
        } catch (SQLException ex) {
        }
        return pizzaList;
    }

    /**
     * Find a pizza by name in data source
     *
     * @param pizza to find
     * @return product found or null if not
     */
    public Pizza findPizzaByName(Pizza pizza) {
        Pizza p = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM `tb_producto` INNER JOIN tb_pizza ON tb_pizza.id_producto = tb_producto.id_producto WHERE nombre = ?");
                pst.setString(1, pizza.getNombre());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    p = resultsetToPizza(rs);
                }
            } catch (SQLException ex) {
            }
        }
        return p;
    }

    /**
     * Find a ingredient by name in data source
     *
     * @param ingredient to find
     * @return ingredient found or null if not
     */
    public Ingrediente findIngredientName(Ingrediente ingredient) {
        Ingrediente i = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM `tb_producto` INNER JOIN tb_ingredientes ON tb_ingredientes.id_producto = tb_producto.id_producto WHERE nombre = ?");
                pst.setString(1, ingredient.getNombre());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    i = resultsetToIngrediente(rs);
                }
            } catch (SQLException ex) {
            }
        }
        return i;
    }

    /**
     * Find a ingredient by name in data source
     *
     * @param drink to find
     * @return found found or null if not
     */
    public Refresco findDrinkByName(Refresco drink) {
        Refresco r = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM `tb_producto` INNER JOIN tb_refresco ON tb_refresco.id_producto = tb_producto.id_producto"
                        + " WHERE nombre = ?");
                pst.setString(1, drink.getNombre());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    r = resultsetToRefresco(rs);
                }
            } catch (SQLException ex) {
            }
        }
        return r;
    }

    /**
     * Convert a ResultSet to pizza
     *
     * @param rs ResultSet
     * @return pizza
     * @throws SQLException if error ocurrs
     */
    private Pizza resultsetToPizza(ResultSet rs) throws SQLException {
        long id_producto = rs.getLong("id_producto");
        String nombre = rs.getString("nombre");
        double precio = rs.getDouble("precio");
        String imagen = rs.getString("imagen");
        long id_tipo = rs.getLong("id_tipo");
        long id_pizza = rs.getLong("id_pizza");
        return new Pizza(id_producto, nombre, precio, imagen, id_tipo, id_pizza);
    }

    /**
     * Get ingredients from a pizza
     *
     * @param id of the pizza
     * @return a list of ingredints or null if error
     */
    public List<Ingrediente> getIngredientsFromPizzaId(long id) {
        List<Ingrediente> iList = null;

        Connection conn = dbConnect.getConnection();
        try {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement("SELECT tb_producto.id_producto, tb_producto.nombre, tb_producto.precio, tb_producto.imagen, tb_producto.id_tipo, tb_ingredientes.id_ingrediente FROM tb_pizza INNER JOIN tb_pizzadetalle ON tb_pizzadetalle.id_pizza = tb_pizza.id_pizza INNER JOIN tb_ingredientes ON tb_ingredientes.id_ingrediente = tb_pizzadetalle.id_ingrediente INNER JOIN tb_producto ON tb_producto.id_producto = tb_ingredientes.id_producto WHERE tb_pizza.id_pizza=? ");
                st.setLong(1, id);
                ResultSet rs = st.executeQuery();
                iList = new ArrayList<Ingrediente>();
                while (rs.next()) {
                    iList.add(resultsetToIngrediente(rs));
                }
            }
        } catch (SQLException ex) {
        }
        return iList;
    }

    /**
     * Convert a ResultSet to ingredien
     *
     * @param rs ResultSet
     * @return an ingredient
     * @throws SQLException if error occurs
     */
    private Ingrediente resultsetToIngrediente(ResultSet rs) throws SQLException {
        long id_producto = rs.getLong("id_producto");
        String nombre = rs.getString("nombre");
        double precio = rs.getDouble("precio");
        String imagen = rs.getString("imagen");
        long id_tipo = rs.getLong("id_tipo");
        long id_ingrediente = rs.getLong("id_ingrediente");
        return new Ingrediente(id_producto, nombre, precio, imagen, id_tipo, id_ingrediente);
    }

    /**
     * get a list of all ingredients from data source
     *
     * @return a list of ingredients or null if error
     */
    public List<Ingrediente> getAllIngredients() {
        List<Ingrediente> iList = null;

        Connection conn = dbConnect.getConnection();
        try {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement("SELECT * FROM tb_producto INNER JOIN tb_ingredientes ON tb_producto.id_producto = tb_ingredientes.id_producto");
                ResultSet rs = st.executeQuery();
                iList = new ArrayList<Ingrediente>();
                while (rs.next()) {
                    iList.add(resultsetToIngrediente(rs));
                }
            }
        } catch (SQLException ex) {
        }
        return iList;
    }

    /**
     * get all drinks from data source
     *
     * @return a list of drinks or null if error
     */
    public List<Refresco> getAllDrinks() {
        List<Refresco> rList = null;

        Connection conn = dbConnect.getConnection();
        try {
            if (conn != null) {
                PreparedStatement st = conn.prepareStatement("SELECT * FROM tb_producto INNER JOIN tb_refresco ON tb_producto.id_producto = tb_refresco.id_producto");
                ResultSet rs = st.executeQuery();
                rList = new ArrayList<Refresco>();
                while (rs.next()) {
                    rList.add(resultsetToRefresco(rs));
                }
            }
        } catch (SQLException ex) {
        }
        return rList;
    }

    /**
     * Convert a ResultSet to drink
     *
     * @param rs ResultSet
     * @return a drink
     * @throws SQLException if error ocurrs
     */
    private Refresco resultsetToRefresco(ResultSet rs) throws SQLException {
        long id_producto = rs.getLong("id_producto");
        String nombre = rs.getString("nombre");
        double precio = rs.getDouble("precio");
        String imagen = rs.getString("imagen");
        long id_tipo = rs.getLong("id_tipo");
        long id_refresco = rs.getLong("id_refresco");
        return new Refresco(id_producto, nombre, precio, imagen, id_tipo, id_refresco);
    }

    /**
     * Add a pizza to data source
     *
     * @param p pizza to add
     * @param iList list of ingedients of the pizza
     * @return rows affected or null if error
     */
    public int addPizza(Pizza p, List<Ingrediente> iList) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            PreparedStatement pst;
            try {
                if (p.getImagen() == null || p.getImagen().equals("")) {
                    p.setImagen("pizza.png");
                }
                pst = conn.prepareStatement("INSERT INTO tb_producto (nombre, precio, imagen, id_tipo) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, p.getNombre());
                pst.setDouble(2, p.getPrecio());
                pst.setString(3, p.getImagen());
                pst.setInt(4, 1);
                pst.executeUpdate();
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    int id_product = rs.getInt(1);
                    pst.close();
                    pst = conn.prepareStatement("INSERT INTO tb_pizza (id_producto) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                    pst.setInt(1, id_product);
                    pst.executeUpdate();
                    rs = pst.getGeneratedKeys();
                    if (rs.next()) {
                        Pizza pizz = new Pizza(rs.getInt(1));
                        pst.close();
                        i = addIngredientsToPizza(pizz, iList);
                    }
                }

            } catch (SQLException ex) {
            }
        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Add a drink to data source
     *
     * @param r drink to add
     * @return rows affected or -1 if error
     */
    public int addDrink(Refresco r) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && r != null) {
            try {
                if (r.getImagen() == null || r.getImagen().equals("")) {
                    r.setImagen("bebida.png");
                }
                PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_producto (nombre, precio, imagen, id_tipo) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, r.getNombre());
                pst.setDouble(2, r.getPrecio());
                pst.setString(3, r.getImagen());
                pst.setInt(4, 2);
                i += pst.executeUpdate();
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    long id_producto = rs.getInt(1);
                    pst.close();
                    pst = conn.prepareStatement("INSERT INTO tb_refresco (id_producto) VALUES (?)");
                    pst.setLong(1, id_producto);
                    i += pst.executeUpdate();
                }

            } catch (SQLException ex) {
            }

        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Add an ingredient to data source
     *
     * @param ing ingredient to add
     * @return rows affected or -1 if error
     */
    public int addIngredient(Ingrediente ing) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && ing != null) {
            try {
                if (ing.getImagen() == null || ing.getImagen().equals("")) {
                    ing.setImagen("ingrediente.png");
                }
                PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_producto (nombre, precio, imagen, id_tipo) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, ing.getNombre());
                pst.setDouble(2, ing.getPrecio());
                pst.setString(3, ing.getImagen());
                pst.setInt(4, 3);
                i += pst.executeUpdate();
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    long id_producto = rs.getInt(1);
                    pst.close();
                    pst = conn.prepareStatement("INSERT INTO tb_ingredientes (id_producto) VALUES (?)");
                    pst.setLong(1, id_producto);
                    i += pst.executeUpdate();
                }

            } catch (SQLException ex) {
            }

        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Update product info in data source
     *
     * @param p product to modify
     * @return rows affected or -1 if error
     */
    public int modifyProductInfo(Producto p) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                if (p.getImagen() == null || p.getImagen().equals("")) {
                    int tipo = getProductType(p.getIdProducto(), conn);
                    switch (tipo) {
                        case 1:
                            p.setImagen("pizza.png");
                            break;
                        case 2:
                            p.setImagen("bebida.png");
                            break;
                        case 3:
                            p.setImagen("ingrediente.png");
                            break;
                    }
                }
                PreparedStatement pst = conn.prepareStatement("UPDATE tb_producto SET nombre=?, precio=?, imagen=? WHERE id_producto=?");
                pst.setString(1, p.getNombre());
                pst.setDouble(2, p.getPrecio());
                pst.setString(3, p.getImagen());
                pst.setLong(4, p.getIdProducto());
                i = pst.executeUpdate();
            } catch (SQLException ex) {
            }
        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Add ingredients to a pizza
     *
     * @param p pizza to add the ingredients
     * @param iList list of ingredients to add to the pizza
     * @return rows affected or -1 if null
     */
    public int addIngredientsToPizza(Pizza p, List<Ingrediente> iList) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && p != null && iList != null) {
            PreparedStatement pst;
            ResultSet rs;
            for (Ingrediente ing : iList) {
                try {
                    pst = conn.prepareStatement("SELECT * FROM tb_pizzadetalle WHERE id_pizza=? AND id_ingrediente=?");
                    pst.setLong(1, p.getIdPizza());
                    pst.setLong(2, ing.getIdIngrediente());
                    rs = pst.executeQuery();
                    if (!rs.next()) {
                        pst.close();
                        pst = conn.prepareStatement("INSERT INTO tb_pizzadetalle (id_ingrediente, id_pizza) VALUES (?,?)");
                        pst.setLong(1, ing.getIdIngrediente());
                        pst.setLong(2, p.getIdPizza());
                        i += pst.executeUpdate();
                        pst.close();
                    }
                } catch (SQLException ex) {
                }
            }
        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Remove ingredients from a pizza check if ingredients are in the pizza
     *
     * @param p pizza to remove ingredients
     * @param iList list of the ingredients to remove
     * @return rows affected or -1 if error
     */
    public int removeIngredientsFromPizza(Pizza p, List<Ingrediente> iList) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && p != null && iList != null) {
            PreparedStatement pst;
            for (Ingrediente ing : iList) {
                try {
                    pst = conn.prepareStatement("DELETE FROM tb_pizzadetalle WHERE id_ingrediente=? AND id_pizza=?");
                    pst.setLong(1, ing.getIdIngrediente());
                    pst.setLong(2, p.getIdPizza());
                    i += pst.executeUpdate();
                    pst.close();
                } catch (SQLException ex) {
                }
            }
        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Find product id Delete ingredients to pizza
     *
     * @param p pizza with id to remvoe
     * @return
     */
    public int removePizza(Pizza p) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && p != null) {
            PreparedStatement pst;
            try {

                pst = conn.prepareStatement("SELECT id_producto FROM tb_pizza WHERE id_pizza=?");
                pst.setLong(1, p.getIdPizza());
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    long id_producto = rs.getInt(1);
                    pst.close();

                    pst = conn.prepareStatement("SELECT * FROM tb_pedido INNER JOIN tb_pedido_info ON tb_pedido_info.id_pedido_info = tb_pedido.id_pedido_info INNER JOIN tb_factura ON tb_factura.id_pedido_info = tb_pedido_info.id_pedido_info WHERE tb_pedido.id_producto=? && tb_factura.cobrado = 0");
                    pst.setLong(1, id_producto);
                    rs = pst.executeQuery();
                    if (!rs.next()) {
                        pst = conn.prepareStatement("DELETE FROM tb_pizzadetalle WHERE id_pizza=?");
                        pst.setLong(1, p.getIdPizza());
                        i += pst.executeUpdate();
                        pst.close();

                        pst = conn.prepareStatement("DELETE FROM tb_pizza WHERE id_pizza=?");
                        pst.setLong(1, p.getIdPizza());
                        i += pst.executeUpdate();
                        pst.close();

                        pst = conn.prepareStatement("DELETE FROM tb_producto WHERE id_producto = ?");
                        pst.setLong(1, id_producto);
                        i += pst.executeUpdate();
                        pst.close();
                    } else {
                        i = -2;
                    }
                }
            } catch (SQLException ex) {
                i = -1;
            }

        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Remove an ingredient in data source
     *
     * @param ing ingredient to remove
     * @return rows affected or -1 if null
     */
    public int removeIngredient(Ingrediente ing) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && ing != null) {
            PreparedStatement pst;
            try {

                pst = conn.prepareStatement("SELECT id_producto FROM tb_ingredientes WHERE id_ingrediente=?");
                pst.setLong(1, ing.getIdIngrediente());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    long id_producto = rs.getInt(1);
                    pst.close();
                    pst = conn.prepareStatement("SELECT * FROM tb_pedido INNER JOIN tb_pedido_info ON tb_pedido_info.id_pedido_info = tb_pedido.id_pedido_info INNER JOIN tb_factura ON tb_factura.id_pedido_info = tb_pedido_info.id_pedido_info WHERE tb_pedido.id_producto=? && tb_factura.cobrado = 0");
                    pst.setLong(1, id_producto);
                    rs = pst.executeQuery();
                    if (!rs.next()) {
                        pst = conn.prepareStatement("DELETE FROM tb_pizzadetalle WHERE id_ingrediente=?");
                        pst.setLong(1, ing.getIdIngrediente());
                        i += pst.executeUpdate();
                        pst.close();

                        pst = conn.prepareStatement("DELETE FROM tb_ingredientes WHERE id_ingrediente=?");
                        pst.setLong(1, ing.getIdIngrediente());
                        i += pst.executeUpdate();
                        pst.close();

                        pst = conn.prepareStatement("DELETE FROM tb_producto WHERE id_producto = ?");
                        pst.setLong(1, id_producto);
                        i += pst.executeUpdate();
                        pst.close();
                    }

                }

            } catch (SQLException ex) {
            }

        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Remove a drink in data source
     *
     * @param r drink to remove
     * @return rows affected or -1 if null
     */
    public int removeDrink(Refresco r) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && r != null) {
            PreparedStatement pst;
            try {
                pst = conn.prepareStatement("SELECT id_producto FROM tb_refresco WHERE id_refresco=?");
                pst.setLong(1, r.getIdRefresco());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    long id_producto = rs.getInt(1);
                    pst.close();
                    pst = conn.prepareStatement("SELECT * FROM tb_pedido INNER JOIN tb_pedido_info ON tb_pedido_info.id_pedido_info = tb_pedido.id_pedido_info INNER JOIN tb_factura ON tb_factura.id_pedido_info = tb_pedido_info.id_pedido_info WHERE tb_pedido.id_producto=? && tb_factura.cobrado = 0");
                    pst.setLong(1, id_producto);
                    rs = pst.executeQuery();
                    if (!rs.next()) {
                        pst.close();
                        pst = conn.prepareStatement("DELETE FROM tb_refresco WHERE id_refresco=?");
                        pst.setLong(1, r.getIdRefresco());
                        i += pst.executeUpdate();
                        pst.close();

                        pst = conn.prepareStatement("DELETE FROM tb_producto WHERE id_producto = ?");
                        pst.setLong(1, id_producto);
                        i += pst.executeUpdate();
                        pst.close();
                    }
                }
            } catch (SQLException ex) {
            }

        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Convert ResultSet to product
     *
     * @param rs ResultSet
     * @return product
     * @throws SQLException if error ocurrs
     */
    private Producto resultsetToProduct(ResultSet rs) throws SQLException {
        long id_producto = rs.getLong("id_producto");
        String nombre = rs.getString("nombre");
        double precio = rs.getDouble("precio");
        String imagen = rs.getString("imagen");
        long id_tipo = rs.getLong("id_tipo");
        return new Producto(id_producto, nombre, precio, imagen, id_tipo);
    }

    /**
     * get type of prodcut
     *
     * @param idProducto to find
     * @param conn connection
     * @return id kind
     */
    private int getProductType(long idProducto, Connection conn) throws SQLException {
        int id_tipo = -1;
        PreparedStatement pst = conn.prepareStatement("SELECT id_tipo FROM tb_producto WHERE id_producto=?");
        pst.setLong(1, idProducto);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            id_tipo = rs.getInt(1);
        }
        pst.close();
        return id_tipo;
    }
}
