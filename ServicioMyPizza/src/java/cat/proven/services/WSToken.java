/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.services;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import proven.modelo.Usuario;
import proven.mypizzadao.Model;

/**
 *
 * @author alumne
 */
@Path("/WSToken")
public class WSToken implements ContainerRequestFilter {

    private final Model model;
    private static final String API_KEY = "X-API-KEY";

    public WSToken(@Context ServletContext context) {
        if (context.getAttribute("Model") != null) {
            model = (Model) context.getAttribute("Model");
        } else {
            model = new Model();
            context.setAttribute("Model", model);
        }

    }   

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        final String apiKey = containerRequestContext.getHeaders().getFirst(API_KEY);
        URI myURI = containerRequestContext.getUriInfo().getAbsolutePath();
        String myPath = myURI.getPath();
        // securing the services
        if (!isPublicService(myPath) && !isValidApiKey(apiKey)) {
            containerRequestContext
                    .abortWith(
                            Response
                                    .status(Response.Status.UNAUTHORIZED)
                                    .entity(new Gson().toJson("User not authenticated"))
                                    .build()
                    );
        }
    }

    /**
     * Validates if service is Public to access without ApiKey
     *
     * @param path : path of service
     * @return
     */
    private boolean isPublicService(String path) {
        boolean isPublic = false;
        // TODO : put public services
        if (path.endsWith("/login")) {
            isPublic = true;
        }
        return isPublic;
    }

    /**
     * Validates if apiKey is valid
     *
     * @param apiKey : api key of service
     * @return
     */
    private boolean isValidApiKey(String apiKey) {
        boolean isValid = false;
        Usuario u = null;
        // TODO : validation of key 
        if (apiKey != null && !apiKey.isEmpty()) {
            u = model.validateUser(apiKey);
            if (u!=null) {
                isValid = true;
            }
        }
        return isValid;
    }
}
