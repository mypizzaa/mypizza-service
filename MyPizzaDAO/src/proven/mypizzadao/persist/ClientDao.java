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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import proven.modelo.Cliente;

/**
 *
 * @author alumne
 */
public class ClientDao {

    private StoreDBConnect dbConnect;

    public ClientDao() {
        try {
            dbConnect = new StoreDBConnect();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Cliente> listAllClients() {
        List<Cliente> cList = null;

        return cList;
    }

    public int addClient(Cliente c) {
        int i = 0;
        System.out.println(c.toString());
        Connection conn = dbConnect.getConnection();
        

        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_usuario (dni, nombre, apellidos, password, imagen, tipo_usuario, correo) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, c.getDni());
            pst.setString(2, c.getNombre());
            pst.setString(3, c.getApellidos());
            pst.setString(4, c.getPassword());
            pst.setString(5, c.getImagen());
            pst.setString(6, c.getTipoUsuario());
            pst.setString(7, c.getCorreo());

            ResultSet rs = pst.getGeneratedKeys();

            if (rs.next()) {
                long id = rs.getInt(1);
                System.out.println(id);
                PreparedStatement pst1 = conn.prepareStatement("INSERT INTO tb_cliente (id_usuario, telefono, direccion1, direccion2) VALUES(?, ?, ?, ?)");
                pst1.setLong(1, id);
                pst1.setString(2, c.getTelefono());
                pst1.setString(3, c.getPrimeraDireccion());
                pst1.setString(4, c.getSegundaDireccion());
                
                i = pst.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }

}
