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
import proven.modelo.Factura;
import proven.modelo.Pedido;
import proven.modelo.PedidoInfo;
import proven.modelo.Producto;

/**
 *
 * @author alumne
 */
public class OrderDao {

    private StoreDBConnect dbConnection;

    public OrderDao() {

        dbConnection = new StoreDBConnect();
    }

    public int createOrder(PedidoInfo pi, List<Pedido> pList, Factura f) {
        int i = 0;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            PreparedStatement pst;
            try {
                long id_info_pedido = receiveOrder(pi, conn);
                if (id_info_pedido > 0) {
                    i += setProductsToOrder(id_info_pedido, pList, conn);
                    i += generateBill(id_info_pedido, f, conn);
                }
            } catch (SQLException ex) {
                i = -1;
            }
        } else {
            i = -1;
        }
        return i;
    }

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

    private int setProductsToOrder(long id_info_pedido, List<Pedido> pList, Connection conn) throws SQLException {
        int i = 0;
        if (conn != null) {
            PreparedStatement pst;
            for (Pedido p : pList) {
                pst = conn.prepareStatement("INSERT INTO tb_pedido (id_pedido_info, id_producto, observaciones, cantidad, precio) VALUES (?,?,?,?,?)");
                pst.setLong(1, id_info_pedido);
                pst.setLong(2, p.getProducto().getIdProducto());
                pst.setString(3, p.getObservaciones());
                pst.setInt(4, p.getCantidad());
                pst.setDouble(5, p.getPrecio());
                i = pst.executeUpdate();
                pst.close();
            }
        }else {
            i = -1;
        }
        return i;
    }

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
        }else {
            i = -1;
        }
        return i;
    }

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
        }else {
            i = -1;
        }
        return i;
    }

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
        }else {
            i = -1;
        }
        return i;
    }

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
        }else {
            i = -1;
        }
        return i;
    }

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

    private PedidoInfo pedidoInfoToResultSet(ResultSet rs) throws SQLException {
        return new PedidoInfo(rs.getLong("id_pedido_info"), rs.getLong("id_estado"), rs.getString("direccion"));
    }

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
}
