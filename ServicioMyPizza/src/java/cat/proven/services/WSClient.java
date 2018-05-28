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
    
    @GET
    @Path("/listall")
    @Produces(MediaType.APPLICATION_JSON)

    public String listAllClients() {

        List<Cliente> listaClientes;
        listaClientes = model.listAllClients();

        return new Gson().toJson(listaClientes);
    }
    
    
    @POST
    @Path("/buscar/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String findClientByDni(@PathParam("dni") String dni) {        
        Cliente c = model.findClientByDni(new Cliente(dni));    
        return new Gson().toJson(c);
    }
    
    @POST
    @Path("/buscar/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String findClientByPhone(@PathParam("phone") String phone) {  
        Cliente c = null;
        if (phone != null){
            c = model.findClienteByPhone(phone);
        }
        return new Gson().toJson(c);
    }
    
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)

    public String addClient() {

        String add = "todo";

        return new Gson().toJson(add);
    }
 
    @POST
    @Path("/comprobar/{dni}")
    @Produces(MediaType.APPLICATION_JSON)

    public String checkIfExistsClient(@PathParam("dni") String dni) {

        int exists = model.checkIfExist(new Cliente(dni));

        return new Gson().toJson(exists);
    }
    
    
    @POST
    @Path("/modificarpswd/{dni}/{pswd}")
    @Produces(MediaType.APPLICATION_JSON)

    public String modifyClientPswd(@PathParam("dni") String dni, @PathParam("pswd") String password) {

        int modified = model.modifyPassword(new Cliente(dni,password));

        return new Gson().toJson(modified);
    }
    
    
    @POST
    @Path("/modificar")
    @Produces(MediaType.APPLICATION_JSON)

    public String modifyClient() {

        String modify = "todo";

        return new Gson().toJson(modify);
    }
    
    
    @POST
    @Path("/baja/{dni}")
    @Produces(MediaType.APPLICATION_JSON)

    public String inactivateClient(@PathParam("dni") String dni) {

        int inactivated = model.inactivateClient(new Cliente(dni));

        return new Gson().toJson(inactivated);
    }
    
    
    
    
}
