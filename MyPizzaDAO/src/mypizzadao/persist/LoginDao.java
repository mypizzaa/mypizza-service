package mypizzadao.persist;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import mypizzadao.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javi Delgado
 */
public class LoginDao {

    private StoreDBConnect dbConnect;
    
    public LoginDao() {
        try {
            dbConnect = new StoreDBConnect();
        } catch (ClassNotFoundException ex) {
            
        }
    }
    
    public Usuario login(String correo, String password){
        Usuario u = null;
        
        Connection conn = dbConnect.getConnection();
        
        String sql = "SELECT correo FROM tb_usuario WHERE correo = '"+correo+"' AND contraseña = '"+password+"' ;";

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                u= new Usuario(rs.getString("correo"));         
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;
    }

}