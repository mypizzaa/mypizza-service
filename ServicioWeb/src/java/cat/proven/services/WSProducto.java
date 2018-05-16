/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.services;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import proven.modelo.Pizza;
import proven.mypizzadao.Model;

/**
 *
 * @author ASUS
 */
@WebService(serviceName = "WSProducto")
@Path("WSProducto")
public class WSProducto {

    private final Model model;
    
    public WSProducto(){
        model = new Model();
    }
    
    //--------------------------------------------------------------------------
    
    /**
     * Lista todas las pizzas de la tienda     
     * @return una lista de pizzas
     */
    @WebMethod(operationName = "pizzas")
    @Path("pizzas")
    @GET
    public String listallpizzas() {
        
        List<Pizza> listapizzas;
        
        listapizzas = model.getAllPizzas();
        
        return new Gson().toJson(listapizzas);
    }

    /**
     * Lista todas las bebidas de la tienda     
     * @return lista de bebidas
     */
    @WebMethod(operationName = "bebidas")
    public String listallbebidas() {
        return "";
    }

    /**
     * Lista todos los ingredientes de la tienda  
     * @return lista de ingredientes
     */
    @WebMethod(operationName = "ingredientes")
    public String listAllIngredients() {
        return "";
    }

    /**
     * Lista todos los ingredientes de la pizza que recibe por parametro
     * @param id idpizza
     * @return una lista de ingredientes
     */
    @WebMethod(operationName = "ingredientespizza")
    public String listIngredientesOfPizza(@WebParam(name = "idpizza") long id) {
        return "";
    }

}
