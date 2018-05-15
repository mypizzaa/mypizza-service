package proven.mypizzadao.persist;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import proven.modelo.Usuario;

/**
 *
 * @author Javi Delgado
 */
public class LoginDao {

    private StoreDBConnect dbConnect;

    private final String QUERY_FIND_USER = "SELECT * FROM TB_usuario WHERE correo =?  AND password =? ";

    public LoginDao() {
        try {
            dbConnect = new StoreDBConnect();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Usuario login(String correo, String password) {
        Usuario u = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                PreparedStatement st = conn.prepareStatement(QUERY_FIND_USER);
                st.setString(1, correo);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    u = resultsetToUser(rs);
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return u;
    }
    

    private Usuario resultsetToUser(ResultSet rs) throws SQLException {
        long id_usuario = rs.getLong("id_usuario");
        String dni = rs.getString("dni");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String imagen = rs.getString("imagen");
        String tipo_usuario = rs.getString("tipo_usuario");
        String correo = rs.getString("correo");
        Usuario u = new Usuario(id_usuario, dni, nombre, apellidos, correo, imagen, tipo_usuario, correo);
        return u;
    }

}
