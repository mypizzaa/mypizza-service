package proven.mypizzadao;

import java.util.List;
import proven.modelo.*;
import proven.mypizzadao.persist.*;

public class Model {

    private final LoginDao loginDao;
    private final ProductDao productDao;
    private final ClientDao clientDao;
    private final EmployeeDao employeeDao;
    private final OrderDao orderDao;
    private final PayMethodDao pmDao;
    private final TokenDao tokenDao;

    private static Model instance;

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public Model() {
        loginDao = new LoginDao();
        productDao = new ProductDao();
        clientDao = new ClientDao();
        employeeDao = new EmployeeDao();
        orderDao = new OrderDao();
        pmDao = new PayMethodDao();
        tokenDao = new TokenDao();
    }

    // <editor-fold defaultstate="collapsed" desc="Login">
    public Usuario login(String correo, String pass) {
        return loginDao.login(correo, pass);
    }
    
    public int generateToken(Usuario u){
        return tokenDao.generateToken(u);
    }
    
    public Usuario validateClientToken(String token){
        return tokenDao.validateClientToken(token);
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
    public List<Cliente> listAllClients() {
        return clientDao.listAllClients();
    }

    public Cliente findClientByDni(Cliente c) {
        return clientDao.findClientByDni(c);
    }

    public int addClient(Cliente c) {
        return clientDao.addClient(c);
    }

    public int checkIfExist(Cliente c) {
        return clientDao.checkIfExist(c);
    }

    public int modifyPassword(Cliente c) {
        return clientDao.modifyPassword(c);
    }

    public int modifyClient(Cliente c) {
        return clientDao.modifyClient(c);
    }

    public int inactivateClient(Cliente c) {
        return clientDao.inactivateClient(c);
    }
    // </editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Employee">
    public List<Empleado> listAllEmployees(){
        return employeeDao.listAllEmployees();
    }
    
    public Empleado findEmployee(Empleado e){
        return employeeDao.findEmployee(e);
    }
    
    public int addEmployee(Empleado e){
        return employeeDao.addEmployee(e);
    }
    
    public int updateEmployee(Empleado e){
        return employeeDao.updateEmployee(e);
    }
    
    public int inactivateEmployee(Empleado e){
        return employeeDao.inactivateEmployee(e);
    }
    //</editor-fold>
}
