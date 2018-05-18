
package proven.mypizzadao;


import java.util.List;
import proven.modelo.Cliente;
import proven.modelo.Ingrediente;
import proven.modelo.Pizza;
import proven.modelo.Refresco;
import proven.modelo.Usuario;
import proven.mypizzadao.persist.ClientDao;
import proven.mypizzadao.persist.LoginDao;
import proven.mypizzadao.persist.ProductDao;

public class Model {
    private final LoginDao loginDao;
    private final ProductDao productDao;
    private final ClientDao clientDao;
    
    public Model(){
        loginDao = new LoginDao();
        productDao = new ProductDao();
        clientDao = new ClientDao();
    }

    public Usuario login(String correo, String pass) {
        return loginDao.login(correo, pass);
    }
    
    public int addClient(Cliente c){
        return clientDao.addClient(c);
    }
    
    public int checkIfExist(Cliente c){
        return clientDao.checkIfExist(c);
    }
    
    public int inactivateClient(Cliente c){
        return clientDao.inactivateClient(c);
    }
    
    public int changePassword(Cliente c){
        return clientDao.modifyPassword(c);
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
