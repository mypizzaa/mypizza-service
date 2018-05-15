/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizzadao;


import java.util.List;
import proven.modelo.Ingrediente;
import proven.modelo.Pizza;
import proven.modelo.Refresco;
import proven.modelo.Usuario;
import proven.mypizzadao.persist.LoginDao;
import proven.mypizzadao.persist.ProductDao;

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
    
    public List<Ingrediente> getAllIngredients(){
        return productDao.getAllIngredients();
    }
    
    public List<Refresco> getAllDrinks(){
        return productDao.getAllDrinks();
    }
    
    public List<Ingrediente> getIngredientsFromPizzaId(long id){
        return productDao.getIngredientsFromPizzaId(id); 
    }
    
}
