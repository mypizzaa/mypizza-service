package cat.proven.mypizza.webservice;
 
import com.google.gson.Gson;
import java.util.List;
import java.util.Locale;
 
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import mypizzadao.persist.LoginDao;
 
 
/**
 * Service of friend application
 * @author ProvenSoft
 */
@Path("/login")
public class LoginService {
   /**
    * data model to provide data access.
    */
   private final LoginDao model;
 
   /**
    * Constructor. It gets a reference to the model
    * and saves it in the application context to 
    * @param context the application context
    */
   public LoginService(@Context ServletContext context){        
        if(context.getAttribute("model") != null) {
            model = (LoginDao) context.getAttribute("model");
        } else {
            model = new LoginDao();
            context.setAttribute("model", model);
        }
   }
 
   /**
    * Verify if a user exist according to user and password
     * @param correo
     * @param password
    * @return list of friends in json
    */
   @POST
   @Path("/login")
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   @Produces(MediaType.APPLICATION_JSON)
   public String login(@FormParam("correo") String correo, @FormParam("password") String password) {     
      return new Gson().toJson(model.login(correo, password));
   }
   

}
