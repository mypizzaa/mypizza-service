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
import java.util.logging.Level;
import java.util.logging.Logger;
import proven.modelo.MetodoPago;

/**
 *
 * @author alumne
 */
public class PayMethodDao {

    private StoreDBConnect dbConnection;

    public PayMethodDao() {
        dbConnection = new StoreDBConnect();
    }

    public List<MetodoPago> listAllMethods() {
        List<MetodoPago> mpList = null;
        Connection conn = dbConnection.getConnection();

        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_metodoPago");
                ResultSet rs = pst.executeQuery();
                mpList = new ArrayList<MetodoPago>();
                while (rs.next()) {
                    mpList.add(resultSetToPayMethod(rs));
                }

            } catch (SQLException ex) {
            }
        }
        return mpList;
    }

    private MetodoPago resultSetToPayMethod(ResultSet rs) throws SQLException {
        return new MetodoPago(rs.getLong("id_MetodoPago"), rs.getString("nombre"), rs.getString("otros_detalles"));
    }

    public int addPayMethod(MetodoPago mp) {
        int i = 0;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_metodoPago (nombre, otros_detalles) VALUES (?,?)");
                pst.setString(1, mp.getNombre());
                pst.setString(2, mp.getDetalles());
                i = pst.executeUpdate();
            } catch (SQLException ex) {
                i = -1;
            }
        } else {
            i = -1;
        }

        return i;
    }

    public int updatePayMethod(MetodoPago mp) {
        int i = 0;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("UPDATE tb_metodopago SET nombre=?, otros_detalles=? WHERE id_metodoPago=?");
                pst.setString(1, mp.getNombre());
                pst.setString(2, mp.getDetalles());
                pst.setLong(3, mp.getIdMetodoPago());
                i = pst.executeUpdate();
            } catch (SQLException ex) {
                i = -1;
            }
        } else {
            i = -1;
        }

        return i;
    }

    public int removePayMethod(MetodoPago mp) {
        int i = 0;

        Connection conn = dbConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM tb_metodopago WHERE id_metodoPago=?");
                pst.setLong(1, mp.getIdMetodoPago());
                i = pst.executeUpdate();
            } catch (SQLException ex) {
                i = -1;
            }
        } else {
            i = -1;
        }

        return i;
    }

}
