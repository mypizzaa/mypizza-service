
package cat.proven.services;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import proven.mypizzadao.Model;


@Path("/WSPedido")
public class WSPedido {
    
    private final Model model;

    public WSPedido(@Context ServletContext context) {

        if (context.getAttribute("Model") != null) {
            model = (Model) context.getAttribute("Model");
        } else {
            model = new Model();
            context.setAttribute("Model", model);
        }
    }
        
    @POST
    @Path("/nuevopedido")
    @Produces(MediaType.APPLICATION_JSON)
    public String nuevoPedido() {
        
        String nuevopedido = "todo";
        
        return new Gson().toJson(nuevopedido);
    }
}
