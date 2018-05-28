/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao.persist;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import proven.modelo.Token;
import proven.modelo.Usuario;

/**
 *
 * @author alumne
 */
public class TokenDao {

    private final StoreDBConnect dbConnect;

    public TokenDao() {
        dbConnect = new StoreDBConnect();
    }

    public Token generateToken(Usuario u) {
        Token t = null;
        
        String token = "";
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
                for (int j = 0; j < 20; j++) {
                    token += String.valueOf(number.nextInt(21));
                }
                PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_token(id_usuario, token, time_date) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
                pst.setLong(1, u.getIdUsuario());
                pst.setString(2, token);
                Timestamp time = new Timestamp(System.currentTimeMillis());
                pst.setTimestamp(3, time);
                if (pst.executeUpdate()>0){
                    t = new Token(u, token, time);
                }
            } catch (NoSuchAlgorithmException | SQLException ex) {
            }
        } else {
        }
        return t;
    }
    
    public Usuario validateUser(String token){
        Usuario u = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null && token !=null){        
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT tb_usuario.id_usuario, tb_usuario.tipo_usuario, tb_token.time_date FROM tb_token INNER JOIN tb_usuario ON tb_usuario.id_usuario = tb_token.id_usuario WHERE token=?");                
                pst.setString(1, token);
                ResultSet rs  = pst.executeQuery();
                if (rs.next()){
                    long id_usuario = rs.getLong("id_usuario");
                    String tipoUsuario = rs.getString("tipo_usuario");
                    long time = rs.getTimestamp("time_date").getTime();
                    long now = new Timestamp(System.currentTimeMillis()).getTime();
                    double hours = (now - time)/3600000;
                    pst.close();
                    if(hours>23){
                        pst = conn.prepareStatement("DELETE FROM tb_token WHERE token=?");
                        pst.setString(1, token);
                        pst.executeUpdate();
                    } else {
                        u = new Usuario(id_usuario, tipoUsuario);
                    }
                }
                                
            } catch (SQLException ex) {     
              
            }
        }                
        return u;
    }

}
