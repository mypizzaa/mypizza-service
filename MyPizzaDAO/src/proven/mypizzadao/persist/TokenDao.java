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
                if (pst.executeUpdate() > 0) {
                    t = new Token(u, token, time);
                }
            } catch (NoSuchAlgorithmException | SQLException ex) {
            }
        } else {
        }
        return t;
    }

    public Usuario validateUserToken(String token) {
        Usuario u = null;
        Connection conn = dbConnect.getConnection();
        if (conn != null && token != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("SELECT tb_token.time_date, tb_usuario.id_usuario, tb_usuario.tipo_usuario FROM tb_token INNER JOIN tb_usuario ON tb_usuario.id_usuario = tb_token.id_usuario WHERE tb_token.token=?");
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    Timestamp time_date = rs.getTimestamp(1);
                    Timestamp ts = new Timestamp(System.currentTimeMillis());
                    long time = ts.getTime() - time_date.getTime();
                    if (time / 3600000 > 23) {
                        pst.close();
                        pst = conn.prepareStatement("DELETE FROM tb_token WHERE token=?");
                        pst.setString(1, token);
                        pst.executeUpdate();
                    } else {
                        u = new Usuario(rs.getLong("id_usuario"), rs.getString("tipo_usuario"));
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return u;
    }

}
