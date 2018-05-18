/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao.persist;

;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import proven.modelo.Cliente;

/**
 *
 * @author alumne
 */


public class ClientDao {

    private StoreDBConnect dbConnect;

    public ClientDao() {
        dbConnect = new StoreDBConnect();
    }

    public List<Cliente> listAllClients() {
        List<Cliente> cList = null;
        //TODO
        return cList;
    }

    public int addClient(Cliente c) {
        int i = 0;

        Connection conn = dbConnect.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_usuario (dni, nombre, apellidos, password, imagen, tipo_usuario, correo) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, c.getDni());
            pst.setString(2, c.getNombre());
            pst.setString(3, c.getApellidos());
            pst.setString(4, c.getPassword());
            pst.setString(5, c.getImagen());
            pst.setString(6, c.getTipoUsuario());
            pst.setString(7, c.getCorreo());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                long id = rs.getInt(1);
                PreparedStatement pst1 = conn.prepareStatement("INSERT INTO tb_cliente (id_usuario, telefono, direccion1, direccion2, poblacion, codigo_postal) VALUES(?, ?, ?, ?, ?, ?)");
                pst1.setLong(1, id);
                pst1.setString(2, c.getTelefono());
                pst1.setString(3, c.getPrimeraDireccion());
                pst1.setString(4, c.getSegundaDireccion());
                pst1.setString(5, c.getPoblacion());
                pst1.setInt(6, c.getCodigo_postal());

                i = pst1.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }

    public int checkIfExist(Cliente c) {
        int i = 0;

        Connection conn = dbConnect.getConnection();

        if (conn != null && c != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_usuario WHERE dni=?");
                pst.setString(1, c.getDni());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    i = 1;
                }
            } catch (SQLException ex) {
            }

        }
        return i;
    }

    public int modifyPassword(Cliente c) {
        int i = 0;
        if (c.getCorreo() != null && c.getPassword() != null) {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                try {
                    PreparedStatement pst = conn.prepareStatement("UPDATE tb_usuario SET password=? WHERE dni=?");
                    pst.setString(1, c.getPassword());
                    pst.setString(2, c.getDni());
                    i = pst.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return i;
    }

    public int modifyClient(Cliente c) {
        int i = 0;
        if (c != null) {
            Connection conn = dbConnect.getConnection();
            if (conn != null) {
                try {
                    PreparedStatement pst = conn.prepareStatement("UPDATE tb_usuario SET nombre=?, apellidos=?, password=?, imagen=?, correo=? WHERE id_usuario=?");
                    pst.setString(1, c.getNombre());
                    pst.setString(2, c.getApellidos());
                    pst.setString(3, c.getPassword());
                    pst.setString(4, c.getImagen());
                    pst.setString(5, c.getCorreo());
                    pst.setLong(6, c.getIdUsuario());

                    i = pst.executeUpdate();

                    if (i > 0) {
                        PreparedStatement pst1 = conn.prepareStatement("UPDATE tb_cliente SET telefono=?, direccion1=?, direccion2=?, poblacion=?, codigo_postal=? WHERE id_usuario=?");
                        pst1.setString(1, c.getTelefono());
                        pst1.setString(2, c.getPrimeraDireccion());
                        pst1.setString(3, c.getSegundaDireccion());
                        pst1.setString(4, c.getPoblacion());
                        pst1.setInt(5, c.getCodigo_postal());
                        pst1.setLong(6, c.getIdUsuario());                        
                        i = pst1.executeUpdate();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return i;
    }

    public int inactivateClient(Cliente c) {
        int i = 0;

        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("UPDATE tb_usuario SET activo=? WHERE dni=?");
                pst.setInt(1, 0);
                pst.setString(2, c.getDni());
                i = pst.executeUpdate();
            } catch (SQLException ex) {

            }

        }
        return i;
    }

}
