package proven.mypizzadao;

import java.util.List;
import proven.modelo.*;
import proven.mypizzadao.persist.*;

/**
 * 
 * @author MyPizza
 * @version 1.0
 */
public class Model {

    private final LoginDao loginDao;
    private final ProductDao productDao;
    private final ClientDao clientDao;
    private final EmployeeDao employeeDao;
    private final OrderDao orderDao;
    private final PayMethodDao pmDao;
    private final TokenDao tokenDao;

    /**
     * Constructor
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

    // <editor-fold defaultstate="collapsed" desc="Login && Token">
    /**
     * uses{@link  proven.mypizzadao.persist.LoginDao#login(java.lang.String, java.lang.String)
     * }
     *
     * @param correo email of the user
     * @param pass password of the user
     * @return user if login is ok or null if not
     */
    public Usuario login(String correo, String pass) {
        return loginDao.login(correo, pass);
    }

    /**
     * uses{@link proven.mypizzadao.persist.TokenDao#generateToken(proven.modelo.Usuario)}
     *
     * @param u user that log in
     * @return a token with the user or null
     */
    public Token generateToken(Usuario u) {
        return tokenDao.generateToken(u);
    }

    /**
     * uses{@link proven.mypizzadao.persist.TokenDao#validateUser(java.lang.String)
     * }
     *
     * @param token of the user
     * @return a user if token exist or null if not
     */
    public Usuario validateUser(String token) {
        return tokenDao.validateUserToken(token);

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Products ">
    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#getAllPizzas() }
     *
     * @return a list of pizzas
     */
    public List<Pizza> getAllPizzas() {
        return productDao.getAllPizzas();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#findProductByName(proven.modelo.Producto) }
     * @param product to find
     * @return  product found or null if error
     */
    public Producto findProductByName(Producto product){
        return productDao.findProductByName(product);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#getAllIngredients() }
     *
     * @return
     */
    public List<Ingrediente> getAllIngredients() {
        return productDao.getAllIngredients();
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#getAllDrinks() }
     *
     * @return
     */
    public List<Refresco> getAllDrinks() {
        return productDao.getAllDrinks();
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#getIngredientsFromPizzaId(long)}
     *
     * @param id of the pizza
     * @return a list of ingredients of the pizza
     */
    public List<Ingrediente> getIngredientsFromPizzaId(long id) {
        return productDao.getIngredientsFromPizzaId(id);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#addPizza(proven.modelo.Pizza, java.util.List)
     * }
     *
     * @param p info od the pizza
     * @param iList ingredients of the pizza
     * @return rows affected or -1 if error
     */
    public int addPizza(Pizza p, List<Ingrediente> iList) {
        return productDao.addPizza(p, iList);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#addIngredient(proven.modelo.Ingrediente)
     * }
     *
     * @param ing ingredient to add
     * @return rows affected or -1 if error
     */
    public int addIngredient(Ingrediente ing) {
        return productDao.addIngredient(ing);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#addDrink(proven.modelo.Refresco)
     * }
     *
     * @param r drink to add
     * @return rows affected or -1 if error
     */
    public int addDrink(Refresco r) {
        return productDao.addDrink(r);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#modifyProductInfo(proven.modelo.Producto)
     * }
     *
     * @param p product with new parameters
     * @return rows affected or -1 if error
     */
    public int modifyProductInfo(Producto p) {
        return productDao.modifyProductInfo(p);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#addIngredientsToPizza(proven.modelo.Pizza, java.util.List)
     * }
     *
     * @param p pizza
     * @param iList list of ingredients to add to the pizza
     * @return rows affected or -1 if error
     */
    public int addIgredientsToPizza(Pizza p, List<Ingrediente> iList) {
        return productDao.addIngredientsToPizza(p, iList);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#removeIngredientsFromPizza(proven.modelo.Pizza, java.util.List)
     * }
     *
     * @param p pizza of the ingredients to remove
     * @param iList list with the ingredients to remove
     * @return rows affected or -1 if error
     */
    public int removeIngredientFromPizza(Pizza p, List<Ingrediente> iList) {
        return productDao.removeIngredientsFromPizza(p, iList);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#removePizza(proven.modelo.Pizza)
     * }
     *
     * @param p pizza to remove
     * @return rows affected or -1 if error
     */
    public int removePizza(Pizza p) {
        return productDao.removePizza(p);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#removeIngredient(proven.modelo.Ingrediente)
     * }
     *
     * @param ing ingredient to remove
     * @return rows affected or -1 if error
     */
    public int removeIngrediente(Ingrediente ing) {
        return productDao.removeIngredient(ing);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ProductDao#removeDrink(proven.modelo.Refresco)
     * }
     *
     * @param r drink to remove
     * @return rows affected or -1 if error
     */
    public int removeDrink(Refresco r) {
        return productDao.removeDrink(r);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Client">
    /**
     * uses{@link proven.mypizzadao.persist.ClientDao}
     * @return list of all clients or null if error
     */
    public List<Cliente> listAllClients() {
        return clientDao.listAllClients();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ClientDao#findClientByDni(proven.modelo.Cliente) }
     * @param c client to find by dni
     * @return client found or null if error
     */
    public Cliente findClientByDni(Cliente c) {
        return clientDao.findClientByDni(c);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ClientDao#findClientByPhone(java.lang.String) }
     * @param phone phone of the client to find
     * @return client found or null if error
     */
    public Cliente findClienteByPhone(String phone) {
        return clientDao.findClientByPhone(phone);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ClientDao#addClient(proven.modelo.Cliente) }
     * @param c client to add
     * @return rows affected or -1 if error
     */
    public int addClient(Cliente c) {
        return clientDao.addClient(c);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ClientDao#checkIfExist(proven.modelo.Cliente) }
     * @param c client  to check if exist by dni or email
     * @return rows affected if exist or -1 if error
     */
    public int checkIfExist(Cliente c) {
        return clientDao.checkIfExist(c);
    }

    /**
     * uses{@link proven.mypizzadao.persist.ClientDao#modifyPassword(proven.modelo.Cliente) }
     * @param c client to modify the password
     * @return rows affected or -1 if error
     */
    public int modifyPassword(Cliente c) {
        return clientDao.modifyPassword(c);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ClientDao#modifyPassword(proven.modelo.Cliente) }
     * @param c client with new parameters
     * @return rows affected or -1 if error
     */
    public int modifyClient(Cliente c) {
        return clientDao.modifyClient(c);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.ClientDao#inactivateClient(proven.modelo.Cliente) }
     * @param c client to inactive
     * @return rows affected or -1 if error
     */
    public int inactivateClient(Cliente c) {
        return clientDao.inactivateClient(c);
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Employee">
    /**
     * uses{@link proven.mypizzadao.persist.EmployeeDao#listAllEmployees()}   
     * @return a list of employees or null if error
     */
    public List<Empleado> listAllEmployees() {
        return employeeDao.listAllEmployees();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.EmployeeDao#findEmployee(proven.modelo.Empleado)}   
     * @param e employee to find
     * @return  employee or null if error
     */
    public Empleado findEmployee(Empleado e) {
        return employeeDao.findEmployee(e);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.EmployeeDao#addEmployee(proven.modelo.Empleado) }
     * @param e employee to add
     * @return rows affected or -1 if error
     */
    public int addEmployee(Empleado e) {
        return employeeDao.addEmployee(e);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.EmployeeDao#updateEmployee(proven.modelo.Empleado) }
     * @param e employee with new params
     * @return rows affected or -1 if error
     */
    public int updateEmployee(Empleado e) {
        return employeeDao.updateEmployee(e);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.EmployeeDao#inactivateEmployee(proven.modelo.Empleado) }
     * @param e employee to inactivate
     * @return rows affected or -1 if error
     */
    public int inactivateEmployee(Empleado e) {
        return employeeDao.inactivateEmployee(e);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Order">
    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#createOrder(proven.modelo.PedidoInfo, java.util.List, proven.modelo.Factura)}     
     * @param pi order info
     * @param pList list of products of the order
     * @param f bill to create
     * @return rows affected or -1 if error
     */
    public int createOrder(PedidoInfo pi, List<Pedido> pList, Factura f) {
        return orderDao.createOrder(pi, pList, f);
    }

    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#setOrderToCoock(proven.modelo.PedidoInfo)
     * }
     *
     * @param pi order info to set to coock
     * @return rows affected or -1 if error
     */
    public int setOrderToCoock(PedidoInfo pi) {
        return orderDao.setOrderToCoock(pi);
    }

    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#setOrderToCoock(proven.modelo.PedidoInfo)
     * }
     *
     * @param pi order info to set to ready
     * @return rows affected or -1 if error
     */
    public int setOrderToReady(PedidoInfo pi) {
        return orderDao.setOrderToReady(pi);
    }

    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#setOrderToDelivery(proven.modelo.PedidoInfo)
     * }
     *
     * @param pi order info to set to delivery
     * @return rows affected or -1 if error
     */
    public int setOrderToDelivery(PedidoInfo pi) {
        return orderDao.setOrderToDelivery(pi);
    }

    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#setBillToPaid(proven.modelo.PedidoInfo)
     * }
     *
     * @param pi order info to set to paid
     * @return rows affected or -1 if error
     */
    public int setBillToPaid(PedidoInfo pi) {
        return orderDao.setBillToPaid(pi);
    }

    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#getAllInfoOrder() }
     *
     * @return a list of the orders
     */
    public List<PedidoInfo> getAllInfoOrder() {
        return orderDao.getAllInfoOrder();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#getOrderFromProductInfo(proven.modelo.PedidoInfo)  }
     * @param pi order to get the products
     * @return list of products whith thar order
     */
    public List<Pedido> getOrderFromProductInfo(PedidoInfo pi) {
        return orderDao.getOrderFromProductInfo(pi);
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#getAllRecivedOrders() () }
     *
     * @return a list of the orders that are received
     */
    public List<PedidoInfo> getAllReceivedOrders() {
        return orderDao.getAllReceivedOrders();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#getAllCookingOrders() () }
     *
     * @return a list of the orders that are coocking
     */
    public List<PedidoInfo> getAllCookingOrders() {
        return orderDao.getAllCookingOrders();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#getAllReadyOrders() () }
     *
     * @return a list of the orders that are ready
     */
    public List<PedidoInfo> getAllReadyOrders() {
        return orderDao.getAllReadyOrders();
    }
    
    /**
     * uses{@link proven.mypizzadao.persist.OrderDao#getAllDeliveryOrders() () () }
     *
     * @return a list of the orders that are ready
     */
    public List<PedidoInfo> getAllDeliveryOrders() {
        return orderDao.getAllDeliveryOrders();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Pay method">
    /**
     * uses{@link proven.mypizzadao.persist.PayMethodDao#listAllMethods() }
     *
     * @return a list of al methods to pay
     */
    public List<MetodoPago> listAllMethods() {
        return pmDao.listAllMethods();
    }

    /**
     * uses{@link proven.mypizzadao.persist.PayMethodDao#addPayMethod(proven.modelo.MetodoPago)
     * }
     *
     * @param mp pay method to add
     * @return rows affected or -1 if error
     */
    public int addPayMethod(MetodoPago mp) {
        return pmDao.addPayMethod(mp);
    }

    /**
     * uses{@link proven.mypizzadao.persist.PayMethodDao#updatePayMethod(proven.modelo.MetodoPago)
     * }
     *
     * @param mp pay method to modify
     * @return rows affected or -1 if error
     */
    public int updatePayMethod(MetodoPago mp) {
        return pmDao.updatePayMethod(mp);
    }

    /**
     * uses{@link proven.mypizzadao.persist.PayMethodDao#removePayMethod(proven.modelo.MetodoPago)
     * }
     *
     * @param mp pay method to remove
     * @return rows affected or -1 if error
     */
    public int removePayMethod(MetodoPago mp) {
        return pmDao.removePayMethod(mp);
    }
    //</editor-fold>
}
