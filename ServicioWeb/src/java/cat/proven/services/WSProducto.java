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
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import proven.modelo.Ingrediente;
import proven.modelo.Pizza;
import proven.modelo.Refresco;
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
     * @return una lista de pizzas si no null
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
     * @return lista de bebidas si no null
     */
    @WebMethod(operationName = "bebidas")
    @Path("bebidas")
    @GET
    public String listallbebidas() {
        
        List<Refresco> listaRefrescos;        
        listaRefrescos = model.getAllDrinks();
        
        return new Gson().toJson(listaRefrescos);
    }

    /**
     * Lista todos los ingredientes de la tienda  
     * @return lista de ingredientes si no null
     */
    @WebMethod(operationName = "ingredientes")
    @Path("ingredientes")
    @GET
    public String listAllIngredients() {
        List<Ingrediente> listaIngredientes;        
        listaIngredientes = model.getAllIngredients();
        
        return new Gson().toJson(listaIngredientes);
    }

    /**
     * Lista todos los ingredientes de la pizza que recibe por parametro
     * @param id idpizza
     * @return una lista de ingredientes si no null
     */
    @WebMethod(operationName = "ingredientespizza")
    public String listIngredientesOfPizza(@WebParam(name = "idpizza") long id) {
        
        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getIngredientsFromPizzaId(id);
        
        return new Gson().toJson(listaIngredientes);
    }

}
