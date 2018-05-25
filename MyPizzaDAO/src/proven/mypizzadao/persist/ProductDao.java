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
 * @author alumne
 */
public class ProductDao {

    private StoreDBConnect dbConnect;

    public ProductDao() {
        dbConnect = new StoreDBConnect();
    }

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
                while (rs.next()) {
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
                while (rs.next()) {
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
                while (rs.next()) {
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

    public int addPizza(Pizza p, List<Ingrediente> iList) {
        int i = 0;

        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            PreparedStatement pst;
            try {
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
                System.out.println(ex.getMessage());
            }
        } else {
            i = -1;
        }
        return i;
    }

    public int addDrink(Refresco r) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && r != null) {
            try {
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

    public int addIngredient(Ingrediente ing) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && ing != null) {
            try {
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

    public int modifyProductInfo(Producto p) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
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
                        pst = conn.prepareStatement("INSERT INTO tb_pizzaDetalle (id_ingrediente, id_pizza) VALUES (?,?)");
                        pst.setLong(1, ing.getIdIngrediente());
                        pst.setLong(2, p.getIdPizza());
                        i += pst.executeUpdate();
                        pst.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            i = -1;
        }
        return i;
    }

    public int removeIngredientsFromPizza(Pizza p, List<Ingrediente> iList) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && p != null && iList != null) {
            PreparedStatement pst;
            for (Ingrediente ing : iList) {
                try {
                    pst = conn.prepareStatement("DELETE FROM tb_pizzaDetalle WHERE id_ingrediente=? AND id_pizza=?");
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
                        pst = conn.prepareStatement("DELETE FROM tb_pizzaDetalle WHERE id_pizza=?");
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
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            i = -1;
        }
        return i;
    }

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
                        pst = conn.prepareStatement("DELETE FROM tb_pizzaDetalle WHERE id_ingrediente=?");
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
}
