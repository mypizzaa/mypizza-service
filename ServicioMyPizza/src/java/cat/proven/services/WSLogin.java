package cat.proven.services;

import com.google.gson.Gson;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import proven.modelo.Usuario;
import proven.mypizzadao.Model;



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
    public String login(@FormParam("correo") String correo,
            @FormParam("password") String password) {

        Usuario u = model.login(correo, password);

        return new Gson().toJson(u);
    }
      

}
