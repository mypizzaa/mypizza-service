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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public int generateToken(Usuario u) {
        int i = 0;
        String token = "";
        Connection conn = dbConnect.getConnection();
        if (conn != null) {
            try {
                SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
                for (int j = 0; j < 20; j++) {
                    token += String.valueOf(number.nextInt(21));
                }
                PreparedStatement pst = conn.prepareStatement("INSERT INTO tb_token(id_usuario, token, time_date) VALUES(?,?,?)");
                pst.setLong(1, u.getIdUsuario());
                pst.setString(2, token);
                pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                i = pst.executeUpdate();

            } catch (NoSuchAlgorithmException | SQLException ex) {
                i = -1;
            }
        } else {
            i = -1;
        }
        return i;
    }
    
    public boolean validateUser(String token){
    
    }

}
