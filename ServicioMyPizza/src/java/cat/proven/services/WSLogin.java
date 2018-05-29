package cat.proven.services;

import com.google.gson.Gson;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import proven.modelo.Token;
import proven.modelo.Usuario;
import proven.mypizzadao.Model;

/**
 * 
 * @author MyPizza
 * @version 1.0
 */
@Path("/WSLogin")
public class WSLogin {

    private final Model model;

    public WSLogin(@Context ServletContext context) {

        if (context.getAttribute("Model") != null) {
            model = (Model) context.getAttribute("Model");
        } else {
            model = new Model();
            context.setAttribute("Model", model);
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String login(@FormParam("correo") String correo,
            @FormParam("password") String password) {
        Token token = null;
        Usuario u = model.login(correo, password);
        if (u!=null) {
            token  = model.generateToken(u);
        }

        return new Gson().toJson(token);
    }
}
