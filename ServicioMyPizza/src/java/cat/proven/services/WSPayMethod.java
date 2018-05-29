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
import proven.modelo.MetodoPago;
import proven.mypizzadao.Model;
/**
 * 
 * @author MyPizza
 * @version 1.0
 */
@Path("/WSPayMethod")
public class WSPayMethod {

    private final Model model;
    
    /**
     * Constructor
     * @param context ServletContext 
     */
    public WSPayMethod(@Context ServletContext context) {

        if (context.getAttribute("Model") != null) {
            model = (Model) context.getAttribute("Model");
        } else {
            model = new Model();
            context.setAttribute("Model", model);
        }
    }
    
    /**
     * List all pay methods
     * @return 
     */
    @GET
    @Path("/listall")
    @Produces(MediaType.APPLICATION_JSON)
    public String listAllClients() {

        List<MetodoPago> methodList = model.listAllMethods();

        return new Gson().toJson(methodList);
    }
    
    /**
     * get form params and if name is not null send params to model
     * @param name of the pay method
     * @param otherDetails other details of the pay method
     * @return json of rows affected
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addPayMehod(@FormParam("name") String name, @FormParam("other_details") String otherDetails) {
        int rowsAffected=-1;
        if (name!=null){
            rowsAffected = model.addPayMethod(new MetodoPago(name, otherDetails));
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * get form params and if name is not null send params to model
     * @param id
     * @param name of the pay method
     * @param otherDetails other details of the pay method
     * @return json of rows affected
     */
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updatePayMethod(@FormParam("id") Long id, @FormParam("name") String name, @FormParam("other_details") String otherDetails) {
        int rowsAffected=-1;
        if (name!=null && id !=null && id>0){
            rowsAffected = model.updatePayMethod(new MetodoPago(id, name, otherDetails));
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * get form params and if name is not null send params to model
     * @param id of the method pay
     * @return json of rows affected
     */
    @POST
    @Path("/remove")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String removePayMethod(@FormParam("id") Long id) {
        int rowsAffected=-1;
        if (id !=null && id>0){
            rowsAffected = model.removePayMethod(new MetodoPago(id));
        }
        return new Gson().toJson(rowsAffected);
    }
}
