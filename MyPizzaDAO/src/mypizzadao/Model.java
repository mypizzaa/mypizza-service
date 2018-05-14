/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypizzadao;


import java.util.List;
import modelo.Pizza;
import modelo.Usuario;
import mypizzadao.persist.LoginDao; 
import mypizzadao.persist.ProductDao;

/**
 *
 * @author alumne
 */
public class Model {
    private final LoginDao loginDao;
    private final ProductDao productDao;
    
    public Model() {
        loginDao = new LoginDao();
        productDao = new ProductDao();
    }

    public Usuario login(String correo, String pass) {
        return loginDao.login(correo, pass);
    }
    
    public List<Pizza> getAllPizzas(){
        return productDao.getAllPizzas();
    }
    
}
