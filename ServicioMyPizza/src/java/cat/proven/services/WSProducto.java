package cat.proven.services;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import proven.modelo.Ingrediente;
import proven.modelo.Pizza;
import proven.modelo.Producto;
import proven.modelo.Refresco;
import proven.mypizzadao.Model;

/**
 *
 * @author MyPizza
 * @version 1.0
 */
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

        return new Gson().toJson("pizzas:" + listapizzas);
    }
    
    /**
     * Find a prodcut by dni,
     * @param name of the product
     * @return json of the product found or null if not
     */
    @POST
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String findProductByName(@FormParam("name") String name) {
        Producto p = null;
        if (name != null) {
            p = model.findProductByName(new Producto(name));
        }
        return new Gson().toJson(p);
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

    /**
     * get params if they are not null send params to model
     *
     * @param name name of the pizza
     * @param price price of the pizza
     * @param image image of the pizza
     * @param list list of ingredients in the pizza
     * @return json with rows affected
     */
    @POST
    @Path("/addpizza")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addPizza(@FormParam("name") String name, @FormParam("price") double price,
            @FormParam("image") String image, @FormParam("idIngredient") List<Long> list) {
        int rowsAffected = -1;
        if (list != null && name != null && price > 0 && image != null) {
            List<Ingrediente> iList = new ArrayList<>();
            for (Long l : list) {
                iList.add(new Ingrediente(l));
            }
            rowsAffected = model.addPizza(new Pizza(name, price, image, 1), iList);
        }
        return new Gson().toJson(rowsAffected);
    }

    /**
     * get params if they are not null send params to model
     *
     * @param name of the drink
     * @param price of the drink
     * @param image image of the drink
     * @return json with rows affected
     */
    @POST
    @Path("/addbebida")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addBebida(@FormParam("name") String name, @FormParam("price") double price,
            @FormParam("image") String image) {
        int rowsAffected = -1;
        if (name != null && price > 0 && image != null) {
            rowsAffected = model.addDrink(new Refresco(name, price, image, 2));
        }

        return new Gson().toJson(rowsAffected);
    }

    /**
     * get params if they are not null send params to model
     *
     * @param name name of the ingredient
     * @param price price of the ingredient
     * @param image image of the ingredient
     * @return json of rows affected
     */
    @POST
    @Path("/addingrediente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addIngrediente(@FormParam("name") String name, @FormParam("price") double price,
            @FormParam("image") String image) {
        int rowsAffected = -1;
        if (name != null && price > 0 && image != null) {
            rowsAffected = model.addIngredient(new Ingrediente(name, price, image, 3));
        }
        return new Gson().toJson(rowsAffected);
    }

    /**
     * get params if they are not null send params to model
     *
     * @param id_product id of the product to modify
     * @param name name of the product
     * @param price price of the product
     * @param image image of the product
     * @return
     */
    @POST
    @Path("/modificarproducto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String modifypizza(@FormParam("id_product") long id_product, @FormParam("name") String name, @FormParam("price") double price,
            @FormParam("image") String image) {
        int rowsAffected = -1;
        if (id_product > 0 && name != null && price > 0 && image != null) {
            rowsAffected = model.modifyProductInfo(new Producto(id_product, name, price, image));
        }
        return new Gson().toJson(rowsAffected);
    }

    /**
     * get params if they are not null send params to model
     *
     * @param idPizza id of the pizza to add ingredients
     * @param list list of ids of the ingredients to add to the pizza
     * @return json of the rows affected
     */
    @POST
    @Path("/addingredientespizza")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addIngredientPizza(@FormParam("id_pizza") long idPizza, @FormParam("idIngredient") List<Long> list) {
        int rowsAffected = -1;
        if (idPizza > 0 && list != null) {
            List<Ingrediente> iList = new ArrayList<>();
            for (Long l : list) {
                iList.add(new Ingrediente(l));
            }
            rowsAffected = model.addIgredientsToPizza(new Pizza(idPizza), iList);
        }
        return new Gson().toJson(rowsAffected);
    }

    /**
     * get params if they are not null send params to model
     *
     * @param idPizza id of the pizza to update
     * @param list list of ids of ingredients to remove in the pizza
     * @return json of rows affected
     */
    @POST
    @Path("/eliminaringredientspizza")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removeIngredientsPizza(@FormParam("id_pizza") long idPizza, @FormParam("idIngredient") List<Long> list) {
        int rowsAffected = -1;
        if (idPizza > 0 && list != null) {
            List<Ingrediente> iList = new ArrayList<>();
            for (Long l : list) {
                iList.add(new Ingrediente(l));
            }
            rowsAffected = model.removeIngredientFromPizza(new Pizza(idPizza), iList);
        }
        return new Gson().toJson(rowsAffected);
    }

    /**
     * get params if they are not null send params to model
     *
     * @param idPizza id of the pizza to remove
     * @return json of the rows affected
     */
    @POST
    @Path("/eliminarpizza")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removePizza(@FormParam("id_pizza") long idPizza) {
        int rowsAffected = -1;
        if (idPizza > 0) {
            rowsAffected = model.removePizza(new Pizza(idPizza));
        }
        return new Gson().toJson(rowsAffected);
    }

    /**
     * get params if they are not null send params to model
     *
     * @param idIngredient id of the ingredient to remove
     * @return json of the rows affected
     */
    @POST
    @Path("/eliminaringredient")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removeIngredient(@FormParam("id_ingredient") long idIngredient) {
        int rowsAffected = -1;
        if (idIngredient > 0) {
            rowsAffected = model.removeIngrediente(new Ingrediente(idIngredient));
        }
        return new Gson().toJson(rowsAffected);
    }

    /**
     * get params if they are not null send params to model
     *
     * @param idDrink id of the drink to remove
     * @return json of the rows affected
     */
    @POST
    @Path("/eliminardrink")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removeDrink(@FormParam("id_drink") long idDrink) {
        int rowsAffected = -1;
        if (idDrink > 0) {
            rowsAffected = model.removeDrink(new Refresco(idDrink));
        }
        return new Gson().toJson(rowsAffected);
    }

}
