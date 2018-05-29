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
import proven.modelo.Cliente;

/**
 * 
 * @author MyPizza
 * @version 1.0
 */
public class ClientDao {

    private StoreDBConnect dbConnect;
    
    /**
     * Constructor
     */
    public ClientDao() {
        dbConnect = new StoreDBConnect();
    }
    
    /**
     * List all clients from data source
     * @return a list of clients or null if error
     */
    public List<Cliente> listAllClients() {
        List<Cliente> cList = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_usuario INNER JOIN tb_cliente ON tb_cliente.id_usuario = tb_usuario.id_usuario WHERE tb_usuario.activo=1");
                ResultSet rs = pst.executeQuery();
                cList = new ArrayList<Cliente>();
                while (rs.next()) {
                    cList.add(resultSetToClient(rs));
                }
            } catch (SQLException ex) {
            }

        }
        return cList;
    }
    
    /**
     * Find a client by dni in data source
     * @param c client to find
     * @return client found or null if error
     */
    public Cliente findClientByDni(Cliente c) {
        Cliente cli = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null && c.getDni() != null) {
            PreparedStatement pst;
            try {
                pst = conn.prepareStatement("SELECT * FROM tb_usuario INNER JOIN tb_cliente ON tb_cliente.id_usuario = tb_usuario.id_usuario WHERE tb_usuario.activo=1 AND tb_usuario.dni=?");
                pst.setString(1, c.getDni());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    cli = resultSetToClient(rs);
                }

            } catch (SQLException ex) {
            }

        }

        return cli;
    }
    
    /**
     * Find a client by phone
     * @param phone phone of the client
     * @return client found or null if not
     */
    public Cliente findClientByPhone(String phone) {
        Cliente cli = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null && !"".equals(phone)) {
            PreparedStatement pst;
            try {
                pst = conn.prepareStatement("SELECT * FROM tb_usuario INNER JOIN tb_cliente ON tb_cliente.id_usuario = tb_usuario.id_usuario WHERE tb_usuario.activo=1 AND tb_cliente.telefono=?");
                pst.setString(1, phone);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    cli = resultSetToClient(rs);
                }

            } catch (SQLException ex) {
            }

        }

        return cli;
    }
    
    /**
     * Add a client in data source
     * @param c client to add
     * @return rows affected or -1 if null
     */
    public int addClient(Cliente c) {
        int i = 0;

        Connection conn = dbConnect.getConnection();
        if (conn != null) {
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
                    pst1.setString(6, c.getCodigo_postal());

                    i = pst1.executeUpdate();
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            i = -1;
        }
        return i;
    }
    
    /**
     * Check if client dni or email exist
     * @param c client with email and dni
     * @return rows affected or -1 if error
     */
    public int checkIfExist(Cliente c) {
        int i = 0;

        Connection conn = dbConnect.getConnection();

        if (conn != null && c != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM tb_usuario WHERE dni=? || correo=?");
                pst.setString(1, c.getDni());
                pst.setString(2, c.getCorreo());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    i = 1;
                }
            } catch (SQLException ex) {
            }

        } else {
            i = -1;
        }
        return i;
    }
    
    
    /**
     * Change the password of a client
     * @param c client to change the password
     * @return rows affected or -1 if error
     */
    public int modifyPassword(Cliente c) {
        int i = 0;
        if (c.getDni() != null && c.getPassword() != null) {
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
        } else {
            i = -1;
        }
        return i;
    }
    
    /**
     * Update info of the client
     * @param c cliento to modify
     * @return rows affected or -1 if error
     */
    public int modifyClient(Cliente c) {
        int i = 0;
        Connection conn = dbConnect.getConnection();
        if (conn != null && c != null) {
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
                    pst1.setString(5, c.getCodigo_postal());
                    pst1.setLong(6, c.getIdUsuario());
                    i = pst1.executeUpdate();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            i = -1;
        }
        return i;
    }
    
    /**
     * Inactive a client in data source
     * @param c client to inactive
     * @return rows affected or -1 if error
     */
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

        } else {
            i = -1;
        }
        return i;
    }
    
    /**
     * Convert a ResultSet to Client
     * @param rs ResultSet
     * @return client
     * @throws SQLException if error ocurrs 
     */
    private Cliente resultSetToClient(ResultSet rs) throws SQLException {
        return new Cliente(rs.getLong("id_cliente"), rs.getString("telefono"),
                rs.getString("direccion1"), rs.getString("direccion2"), rs.getString("poblacion"),
                rs.getString("codigo_postal"), rs.getLong("id_usuario"), rs.getString("dni"),
                rs.getString("nombre"), rs.getString("apellidos"), rs.getString("password"),
                rs.getString("imagen"), rs.getString("tipo_usuario"), rs.getString("correo"),
                rs.getInt("activo"));
    }

}
