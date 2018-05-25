
package cat.proven.services;

import com.google.gson.Gson;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import proven.modelo.Empleado;
import proven.modelo.Usuario;
import proven.mypizzadao.Model;



@Path("/WSEmpleado")
public class WSEmpleado {

    private final Model model;

    public WSEmpleado(@Context ServletContext context) {

        if (context.getAttribute("Model") != null) {
            model = (Model) context.getAttribute("Model");
        } else {
            model = new Model();          
            context.setAttribute("Model", model);
        }
    }

    @POST
    @Path("/listall")
    @Produces(MediaType.APPLICATION_JSON)
    public String listAllEmployees(@HeaderParam("user-token") String userToken) {
        
        
        
        List<Empleado> listaEmpleados = model.listAllEmployees();
        return new Gson().toJson(listaEmpleados);
    }
    
    @POST
    @Path("/buscar/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public String buscarEmpleado(@PathParam("dni") String dni) {
        Empleado e = model.findEmployee(new Empleado(dni));
        return new Gson().toJson(e);
    }
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String login() {

        String add = "todo";

        return new Gson().toJson(add);
    }
    
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateEemployee() {

        String update = "todo";

        return new Gson().toJson(update);
    }
    
    @POST
    @Path("/baja/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public String inactiveEmployee(@FormParam("dni") String dni) {

        int inactive = model.inactivateEmployee(new Empleado(dni));

        return new Gson().toJson(inactive);
    }

}
