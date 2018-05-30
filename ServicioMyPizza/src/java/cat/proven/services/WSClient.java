/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.services;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import proven.modelo.Cliente;
import proven.modelo.Ingrediente;
import proven.mypizzadao.Model;

/**
 *
 * @author MyPizza
 * @version 1.0
 */
@Path("/WSCliente")
public class WSClient {

    private final Model model;

    public WSClient(@Context ServletContext context) {

        if (context.getAttribute("Model") != null) {
            model = (Model) context.getAttribute("Model");
        } else {
            model = new Model();
            context.setAttribute("Model", model);
        }
    }
    
    /**
     * List al clients
     * @return a list of clients or null if error
     */
    @GET
    @Path("/listall")
    @Produces(MediaType.APPLICATION_JSON)

    public String listAllClients() {

        List<Cliente> listaClientes = model.listAllClients();

        return new Gson().toJson(listaClientes);
    }
    
    /**
     * search a client by dni
     * @param dni of client
     * @return  client found or null if not
     */
    @POST
    @Path("/searchdni")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String findClientByDni(@FormParam("dni") String dni) {
        Cliente c = null;
        if (dni != null) {
            c = model.findClientByDni(new Cliente(dni));
        }
        return new Gson().toJson(c);
    }
    
    /**
     * find a client by phone number
     * @param phone of client
     * @return client found or null if not
     */
    @POST
    @Path("/searchphone")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String findClientByPhone(@FormParam("phone") String phone) {
        Cliente c = null;
        if (phone != null) {
            c = model.findClienteByPhone(phone);
        }
        return new Gson().toJson(c);
    }
    
    /**
     * add a new client 
     * @param dni of client
     * @param name of client
     * @param surname of client
     * @param password of client
     * @param image of client (can be null)
     * @param email of client
     * @param phone of client
     * @param address1 of client
     * @param address2 of client(can be null)
     * @param city of client
     * @param postal_code of client
     * @return rows affected or -1 if error
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addClient(@FormParam("dni") String dni, @FormParam("name") String name, @FormParam("surname") String surname,
            @FormParam("password") String password, @FormParam("image") String image, @FormParam("email") String email,
            @FormParam("phone") String phone, @FormParam("address1") String address1, @FormParam("address2") String address2,
            @FormParam("city") String city, @FormParam("postal_code") String postal_code) {
        int rowsAffected = -1;
        if (dni != null && name != null && surname != null && password != null && email != null && phone != null
                && address1 != null && city != null && postal_code != null) {
            rowsAffected = model.addClient(new Cliente(phone, address1, address2, city, postal_code, dni, name, surname, password, image, email));
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * check if dni exist
     * @param dni to check
     * @return 0 if not exist, -1 if dni is null or >0 if exist
     */
    @POST
    @Path("/checkdni")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String checkIfDniExistsClient(@FormParam("dni") String dni) {
        int rowsAffected = -1;
        if (dni != null) {
            rowsAffected = model.checkIfDniExist(dni);
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * check if email exist
     * @param email to check
     * @return 0 if not exist, -1 if email is null or >0 if exist
     */
    @POST
    @Path("/checkemail")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String checkIfEmailExistsClient(@FormParam("email") String email) {
        int rowsAffected = -1;
        if (email != null) {
            rowsAffected = model.checkIfEmailExist(email);
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * modify client password
     * @param dni of client
     * @param password new password
     * @return rows affected or -1 if params are null
     */
    @POST
    @Path("/modifypass")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String modifyClientPswd(@FormParam("dni") String dni, @FormParam("password") String password) {
        int rowsAffected = -1;
        if (dni != null && password != null) {
            rowsAffected = model.modifyPassword(new Cliente(dni, password));
        }

        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * update an employee by user id 
     * @param id of user
     * @param dni of client
     * @param name of client
     * @param surname of client
     * @param password of client
     * @param image of client (can be null)
     * @param email of client
     * @param phone of client
     * @param address1 of client
     * @param address2 of client (can be null)
     * @param city of client
     * @param postal_code of client
     * @return rows affected or -1 if params are null
     */
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String modifyClient(@FormParam("id") long id, @FormParam("dni") String dni, @FormParam("name") String name, @FormParam("surname") String surname,
            @FormParam("password") String password, @FormParam("image") String image, @FormParam("email") String email,
            @FormParam("phone") String phone, @FormParam("address1") String address1, @FormParam("address2") String address2,
            @FormParam("city") String city, @FormParam("postal_code") String postal_code) {
        int rowsAffected = -1;
        if (id > 0 && dni != null && name != null && surname != null && password != null && email != null && phone != null
                && address1 != null && city != null && postal_code != null) {
            rowsAffected = model.modifyClient(new Cliente(phone, address1, address2, city, postal_code, id, dni, name, surname, password, image, email));
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * inactive a client 
     * @param dni of client
     * @return rows affected or -1 if dni is null
     */
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String inactivateClient(@FormParam("dni") String dni) {
        int rowsAffected = -1;
        if (dni != null) {
            rowsAffected = model.inactivateClient(new Cliente(dni));
        }
        return new Gson().toJson(rowsAffected);
    }

}
