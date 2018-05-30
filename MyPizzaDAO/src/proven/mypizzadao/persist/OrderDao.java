/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao.persist;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proven.modelo.Factura;
import proven.modelo.Pedido;
import proven.modelo.PedidoInfo;
import proven.modelo.Producto;

/**
 *
 * @author MyPizza
 * @version 1.0
 */
public class OrderDao {

    private StoreDBConnect dbConnection;

    /**
     * Constructor
     */
    public OrderDao() {

        dbConnection = new StoreDBConnect();
    }

    /**
     * Create and order in data source
     *
     * @param pi info of the order
     * @param pList products in the order
     * @param f bill to create
     * @return rows affected or -1 if error
     */
    public long createOrder(PedidoInfo pi, Factura f) {
        long id_info_pedido = 0;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                id_info_pedido = receiveOrder(pi, conn);
                if (id_info_pedido > 0) {
                    generateBill(id_info_pedido, f, conn);
                }
            } catch (SQLException ex) {
                id_info_pedido = -1;
            }
        } else {
            id_info_pedido = -1;
        }
        return id_info_pedido;
    }

    /**
     * Create info order in data source
     *
     * @param pi information of the order
     * @param conn connection with data source
     * @return the id of the generated statement
     * @throws SQLException if error ocurrs
     */
    private int receiveOrder(PedidoInfo pi, Connection conn) throws SQLException {
        int id_pedido = 0;
        if (conn != null) {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_pedido_info (id_estado, direccion) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, 1);
            pst.setString(2, pi.getDireccion());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id_pedido = rs.getInt(1);
            }
            pst.close();
            rs.close();
        }
        return id_pedido;
    }

    /**
     * Insert in data source the products of the order
     *
     * @param id_info_pedido id of the info order
     * @param pList list of products of the order
     * @param conn connection with data source
     * @return rows affected or -1 if error
     * @throws SQLException
     */
    public int setProductsToOrder(List<Pedido> pList) {
        int i = 0;
        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            PreparedStatement pst;
            for (Pedido p : pList) {
                try {
                    pst = conn.prepareStatement("INSERT INTO tb_pedido (id_pedido_info, id_producto, observaciones, cantidad, precio) VALUES (?,?,?,?,?)");
                    pst.setLong(1, p.getId_pedido_info());
                    pst.setLong(2, p.getProducto().getIdProducto());
                    pst.setString(3, p.getObservaciones());
                    pst.setInt(4, p.getCantidad());
                    pst.setDouble(5, p.getPrecio());
                    i += pst.executeUpdate();
                    pst.close();
                } catch (SQLException ex) {
                   i = -1;
                }
            }
        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Create the bill in data source
     *
     * @param id_info_pedido if of the info order
     * @param f bill to create
     * @param conn connection with data source
     * @return rows affected or -1 if error
     * @throws SQLException if error ocurrs
     */
    private int generateBill(long id_info_pedido, Factura f, Connection conn) throws SQLException {
        int i = 0;
        if (conn != null) {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_factura(id_cliente, id_metodoPago, id_pedido_info, precio_total, dia_hora, cobrado) VALUES (?,?,?,?,?,?)");
            pst.setLong(1, f.getId_cliente());
            pst.setLong(2, f.getId_metodoPago());
            pst.setLong(3, id_info_pedido);
            pst.setDouble(4, f.getPrecio_total());
            pst.setString(5, f.getFecha());
            pst.setInt(6, 0);
            i += pst.executeUpdate();
        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Set order to coock
     *
     * @param pi order to set to coock
     * @return rows affected or -1 if null
     */
    public int setOrderToCoock(PedidoInfo pi) {
        int i = 0;
        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            PreparedStatement pst;
            try {
                pst = conn.prepareStatement("UPDATE tb_pedido_info SET id_estado=? WHERE id_pedido_info=?");
                pst.setInt(1, 2);
                pst.setLong(2, pi.getId_pedido_info());
                i = pst.executeUpdate();
            } catch (SQLException ex) {
            }
        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Set order to ready
     *
     * @param pi order to set to ready
     * @return rows affected or -1 if null
     */
    public int setOrderToReady(PedidoInfo pi) {
        int i = 0;
        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            PreparedStatement pst;
            try {
                pst = conn.prepareStatement("UPDATE tb_pedido_info SET id_estado=? WHERE id_pedido_info=?");
                pst.setInt(1, 3);
                pst.setLong(2, pi.getId_pedido_info());
                i = pst.executeUpdate();
            } catch (SQLException ex) {
            }
        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Set order to delivery
     *
     * @param pi order to set to delivery
     * @return rows affected or -1 if null
     */
    public int setOrderToDelivery(PedidoInfo pi) {
        int i = 0;
        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            PreparedStatement pst;
            try {
                pst = conn.prepareStatement("UPDATE tb_pedido_info SET id_estado=? WHERE id_pedido_info=?");
                pst.setInt(1, 4);
                pst.setLong(2, pi.getId_pedido_info());
                i = pst.executeUpdate();
            } catch (SQLException ex) {
            }
        } else {
            i = -1;
        }
        return i;
    }

    /**
     * Set bill to paid
     *
     * @param pi order to set to paid
     * @return rows affected or -1 if null
     */
    public int setBillToPaid(PedidoInfo pi) {
        int i = 0;
        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            PreparedStatement pst;
            try {
                pst = conn.prepareStatement("UPDATE tb_factura SET cobrado=? WHERE id_pedido_info=?");
                pst.setInt(1, 1);
                pst.setLong(2, pi.getId_pedido_info());
                i = pst.executeUpdate();
            } catch (SQLException ex) {
            }
        }
        return i;
    }

    /**
     * List all orders
     *
     * @return a list of orders or null if error
     */
    public List<PedidoInfo> getAllInfoOrder() {
        List<PedidoInfo> piList = null;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_pedido_info");
                ResultSet rs = pst.executeQuery();
                piList = new ArrayList<PedidoInfo>();
                while (rs.next()) {
                    piList.add(pedidoInfoToResultSet(rs));
                }
            } catch (SQLException ex) {
            }
        }

        return piList;
    }

    /**
     * List all products from oder
     *
     * @param pi order of the products
     * @return a list of products or null if error
     */
    public List<Pedido> getOrderFromProductInfo(PedidoInfo pi) {
        List<Pedido> peList = null;
        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM `tb_pedido` INNER JOIN tb_producto ON tb_producto.id_producto = tb_pedido.id_producto WHERE tb_pedido.id_pedido_info = ?");
                pst.setLong(1, pi.getId_pedido_info());
                ResultSet rs = pst.executeQuery();
                peList = new ArrayList<Pedido>();
                while (rs.next()) {
                    peList.add(resultSetToPedido(rs));
                }

            } catch (SQLException ex) {
            }

        }
        return peList;
    }

    /**
     * Convert ResultSet to order
     *
     * @param rs ResultSet
     * @return order
     * @throws SQLException if error ocurrs
     */
    private PedidoInfo pedidoInfoToResultSet(ResultSet rs) throws SQLException {
        return new PedidoInfo(rs.getLong("id_pedido_info"), rs.getLong("id_estado"), rs.getString("direccion"));
    }

    /**
     * Convert ResultSet to product order
     *
     * @param rs ResultSet
     * @return product order
     * @throws SQLException if error ocurrs
     */
    private Pedido resultSetToPedido(ResultSet rs) throws SQLException {
        Pedido pe = null;

        long id_producto = rs.getLong("id_producto");

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            PreparedStatement pst1 = conn.prepareStatement("SELECT * FROM tb_producto WHERE id_producto = ?");
            pst1.setLong(1, id_producto);
            ResultSet rs1 = pst1.executeQuery();
            if (rs1.next()) {
                Producto p = new Producto(id_producto, rs.getString("nombre"), rs.getDouble("precio"), rs.getString("imagen"), rs.getInt("id_tipo"));
                pe = new Pedido(rs.getLong("id_pedido"), rs.getLong("id_pedido_info"), p, rs.getString("observaciones"), rs.getInt("cantidad"), rs.getDouble("precio"));
            }
        }

        return pe;
    }

    /**
     * List all orders that are in received state
     *
     * @return a list of orders or null if error
     */
    public List<PedidoInfo> getAllReceivedOrders() {
        List<PedidoInfo> piList = null;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_pedido_info WHERE id_estado=?");
                pst.setInt(1, 1);
                ResultSet rs = pst.executeQuery();
                piList = new ArrayList<PedidoInfo>();
                while (rs.next()) {
                    piList.add(pedidoInfoToResultSet(rs));
                }
            } catch (SQLException ex) {
            }
        }

        return piList;
    }

    /**
     * List all orders that are in coocking state
     *
     * @return a list of orders or null if error
     */
    public List<PedidoInfo> getAllCookingOrders() {
        List<PedidoInfo> piList = null;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_pedido_info WHERE id_estado=?");
                pst.setInt(1, 2);
                ResultSet rs = pst.executeQuery();
                piList = new ArrayList<PedidoInfo>();
                while (rs.next()) {
                    piList.add(pedidoInfoToResultSet(rs));
                }
            } catch (SQLException ex) {
            }
        }

        return piList;
    }

    /**
     * List all orders that are in received state
     *
     * @return a list of orders or null if error
     */
    public List<PedidoInfo> getAllReadyOrders() {
        List<PedidoInfo> piList = null;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_pedido_info WHERE id_estado=?");
                pst.setInt(1, 3);
                ResultSet rs = pst.executeQuery();
                piList = new ArrayList<PedidoInfo>();
                while (rs.next()) {
                    piList.add(pedidoInfoToResultSet(rs));
                }
            } catch (SQLException ex) {
            }
        }

        return piList;
    }

    public List<PedidoInfo> getAllDeliveryOrders() {
        List<PedidoInfo> piList = null;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_pedido_info INNER JOIN tb_factura ON tb_factura.id_pedido_info = tb_pedido_info.id_pedido_info WHERE tb_pedido_info.id_estado=? AND tb_factura.cobrado = ?");
                pst.setInt(1, 4);
                pst.setInt(2, 0);
                ResultSet rs = pst.executeQuery();
                piList = new ArrayList<PedidoInfo>();
                while (rs.next()) {
                    piList.add(pedidoInfoToResultSet(rs));
                }
            } catch (SQLException ex) {
            }
        }

        return piList;
    }

    public List<PedidoInfo> getAllPaidOrders() {
        List<PedidoInfo> piList = null;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_pedido_info INNER JOIN tb_factura ON tb_factura.id_pedido_info = tb_pedido_info.id_pedido_info WHERE tb_pedido_info.id_estado=? AND tb_factura.cobrado = ?");
                pst.setInt(1, 4);
                pst.setInt(2, 1);
                ResultSet rs = pst.executeQuery();
                piList = new ArrayList<PedidoInfo>();
                while (rs.next()) {
                    piList.add(pedidoInfoToResultSet(rs));
                }
            } catch (SQLException ex) {
            }
        }

        return piList;
    }
}
