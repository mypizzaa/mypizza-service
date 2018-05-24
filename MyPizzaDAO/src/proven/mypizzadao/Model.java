package proven.mypizzadao;

import java.util.List;
import proven.modelo.Cliente;
import proven.modelo.Ingrediente;
import proven.modelo.Pizza;
import proven.modelo.Producto;
import proven.modelo.Refresco;
import proven.modelo.Usuario;
import proven.mypizzadao.persist.ClientDao;
import proven.mypizzadao.persist.EmployeeDao;
import proven.mypizzadao.persist.LoginDao;
import proven.mypizzadao.persist.OrderDao;
import proven.mypizzadao.persist.PayMethodDao;
import proven.mypizzadao.persist.ProductDao;

public class Model {

    private final LoginDao loginDao;
    private final ProductDao productDao;
    private final ClientDao clientDao;
    private final EmployeeDao employeeDao;
    private final OrderDao orderDao;
    private final PayMethodDao pmDao;

    private static Model instance;

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private Model() {
        loginDao = new LoginDao();
        productDao = new ProductDao();
        clientDao = new ClientDao();
        employeeDao = new EmployeeDao();
        orderDao = new OrderDao();
        pmDao = new PayMethodDao();
    }

    // <editor-fold defaultstate="collapsed" desc="Login">
    public Usuario login(String correo, String pass) {
        return loginDao.login(correo, pass);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Products ">
    public List<Pizza> getAllPizzas() {
        return productDao.getAllPizzas();
    }

    public List<Ingrediente> getAllIngredients() {
        return productDao.getAllIngredients();
    }

    public List<Refresco> getAllDrinks() {
        return productDao.getAllDrinks();
    }

    public List<Ingrediente> getIngredientsFromPizzaId(long id) {
        return productDao.getIngredientsFromPizzaId(id);
    }

    public int addPizza(Pizza p, List<Ingrediente> iList) {
        return productDao.addPizza(p, iList);
    }

    public int addIngredient(Ingrediente ing) {
        return productDao.addIngredient(ing);
    }

    public int addDrink(Refresco r) {
        return productDao.addDrink(r);
    }

    public int modifyProductInfo(Producto p) {
        return productDao.modifyProductInfo(p);
    }

    public int addIgredientsToPizza(Pizza p, List<Ingrediente> iList) {
        return productDao.addIngredientsToPizza(p, iList);
    }

    public int removeIngredientFromPizza(Pizza p, List<Ingrediente> iList) {
        return productDao.removeIngredientsFromPizza(p, iList);
    }

    public int removePizza(Pizza p) {
        return productDao.removePizza(p);
    }

    public int removeIngrediente(Ingrediente ing) {
        return productDao.removeIngredient(ing);
    }

    public int removeDrink(Refresco r) {
        return productDao.removeDrink(r);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Client ">
        public List<Cliente> listAllClients(){
            return clientDao.listAllClients();
        }
        
        public Cliente findClientByDni(Cliente c){
            return clientDao.findClientByDni(c);
        }
        
        public int addClient(Cliente c){
            return clientDao.addClient(c);
        }
        
        public int checkIfExist(Cliente c){
            return clientDao.checkIfExist(c);
        }
        
        public int modifyPassword(Cliente c){
            return clientDao.modifyPassword(c);
        }
        
        public int modifyClient(Cliente c){
            return clientDao.modifyClient(c);
        }
        
        public int inactivateClient(Cliente c){
            return clientDao.inactivateClient(c);
        }
                
    // </editor-fold>
}
