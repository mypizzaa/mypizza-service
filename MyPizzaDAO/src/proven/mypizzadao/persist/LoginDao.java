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
 * @author MyPizza
 * @version 1.0
 */
public class LoginDao {

    private StoreDBConnect dbConnect;

    private final String QUERY_FIND_USER = "SELECT * FROM tb_usuario WHERE correo =? AND password =? AND activo=?";

    /**
     * Constructor
     */
    public LoginDao() {
        dbConnect = new StoreDBConnect();
    }

    /**
     * Check if email and password exist in data source
     *
     * @param correo email of the user
     * @param password of the user
     * @return a user if params are corrects or null if not
     */
    public Usuario login(String correo, String password) {
        Usuario u = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                PreparedStatement st = conn.prepareStatement(QUERY_FIND_USER);
                st.setString(1, correo);
                st.setString(2, password);
                st.setInt(3, 1);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    u = resultsetToUser(rs);
                }
            } catch (SQLException ex) {
            } catch (Exception e) {
            }
        }
        return u;
    }

    /**
     * Convert a result set to user
     *
     * @param rs resultset from query
     * @return user
     * @throws SQLException if error appears
     */
    private Usuario resultsetToUser(ResultSet rs) throws SQLException {
        long id_usuario = rs.getLong("id_usuario");
        String dni = rs.getString("dni");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String imagen = rs.getString("imagen");
        String tipo_usuario = rs.getString("tipo_usuario");
        String correo = rs.getString("correo");
        int activo = rs.getInt("activo");
        Usuario u = new Usuario(id_usuario, dni, nombre, apellidos, correo, imagen, tipo_usuario, correo, activo);
        return u;
    }

}
