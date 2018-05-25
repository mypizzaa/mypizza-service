package cat.proven.services;

import com.google.gson.Gson;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import proven.modelo.Ingrediente;
import proven.modelo.Pizza;
import proven.modelo.Refresco;
import proven.mypizzadao.Model;


@Path("/WSProducto")
public class WSProducto {

    private final Model model;

    public WSProducto(@Context ServletContext context) {
        if (context.getAttribute("Model") != null) {
            model = (Model) context.getAttribute("Model");
        } else {
            model = new Model();
            context.setAttribute("Model", model);
        }
    }

    //--------------------------------------------------------------------------
    /**
     * Lista todas las pizzas de la tienda
     *
     * @return una lista de pizzas si no null
     */
    @GET
    @Path("/pizzas")
    @Produces(MediaType.APPLICATION_JSON)
    public String listAllPizzas() {

        List<Pizza> listapizzas;
        listapizzas = model.getAllPizzas();

        return new Gson().toJson(listapizzas);
    }

    /**
     * Lista todas las bebidas de la tienda
     *
     * @return lista de bebidas si no null
     */
    @GET
    @Path("/bebidas")
    @Produces(MediaType.APPLICATION_JSON)

    public String listAllBebidas() {

        List<Refresco> listaRefrescos;
        listaRefrescos = model.getAllDrinks();

        return new Gson().toJson(listaRefrescos);
    }

    /**
     * Lista todos los ingredientes de la tienda
     *
     * @return lista de ingredientes si no null
     */
    @GET
    @Path("/ingredientes")
    @Produces(MediaType.APPLICATION_JSON)

    public String listAllIngredients() {
        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }

    /**
     * Lista todos los ingredientes de la pizza que recibe por parametro
     *
     * @param id idpizza
     * @return una lista de ingredientes si no null
     */
    @GET
    @Path("/ingredientespizza/{id}")
    @Produces(MediaType.APPLICATION_JSON)

    public String listIngredientesOfPizza(@PathParam("id") long id) {

        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getIngredientsFromPizzaId(id);

        return new Gson().toJson(listaIngredientes);
    }
    
       
    @POST
    @Path("/addpizza")
    @Produces(MediaType.APPLICATION_JSON)

    public String addPizza() {

        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }
    
    
    @POST
    @Path("/addbebida")
    @Produces(MediaType.APPLICATION_JSON)

    public String addBebida() {

         List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }
    
    
    @POST
    @Path("/addingrediente")
    @Produces(MediaType.APPLICATION_JSON)

    public String addIngrediente() {

        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }
    
    @POST
    @Path("/modificarproducto")
    @Produces(MediaType.APPLICATION_JSON)

    public String modifypizza() {

        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }
    
    
    @POST
    @Path("/addingredientespizza")
    @Produces(MediaType.APPLICATION_JSON)

    public String addIngredientPizza() {

        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }
    
    
    @POST
    @Path("/eliminaringredientspizza")
    @Produces(MediaType.APPLICATION_JSON)

    public String removeIngredientsPizza() {

         List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }
    
    @POST
    @Path("/eliminarpizza")
    @Produces(MediaType.APPLICATION_JSON)
    
    public String removePizza() {

        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }
    
    @POST
    @Path("/eliminaringredient")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeIngredient() {

        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }
    
    @POST
    @Path("/eliminardrink")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeDrink() {

        List<Ingrediente> listaIngredientes;
        listaIngredientes = model.getAllIngredients();

        return new Gson().toJson(listaIngredientes);
    }
    
}
