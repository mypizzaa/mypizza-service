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
    
    /**
     * Constructo
     */
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
    /**
     * uses{@link  proven.mypizzadao.persist.LoginDao#login(java.lang.String, java.lang.String) }
     * @param correo email of the user
     * @param pass password of the user
     * @return  user if login is ok or null if not
     */
    public Usuario login(String correo, String pass) {
        return loginDao.login(correo, pass);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.TokenDao#generateToken(proven.modelo.Usuario)}
     * @param u user that log in
     * @return a token with the user or null
     */
    public Token generateToken(Usuario u){
        return tokenDao.generateToken(u);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.TokenDao#validateUser(java.lang.String) }
     * @param token of the user
     * @return a user if token exist or null if not
     */
    public Usuario validateUser(String token){
        return tokenDao.validateUser(token);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Products ">
    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#getAllPizzas() }
     * @return a list of pizzas 
     */
    public List<Pizza> getAllPizzas() {
        return productDao.getAllPizzas();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#getAllIngredients() }
     * @return 
     */
    public List<Ingrediente> getAllIngredients() {
        return productDao.getAllIngredients();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#getAllDrinks() }
     * @return 
     */
    public List<Refresco> getAllDrinks() {
        return productDao.getAllDrinks();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#getIngredientsFromPizzaId(long)}
     * @param id of the pizza
     * @return a list of ingredients of the pizza
     */
    public List<Ingrediente> getIngredientsFromPizzaId(long id) {
        return productDao.getIngredientsFromPizzaId(id);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#addPizza(proven.modelo.Pizza, java.util.List) }
     * @param p info od the pizza
     * @param iList ingredients of the pizza
     * @return rows affected or -1 if error
     */
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
