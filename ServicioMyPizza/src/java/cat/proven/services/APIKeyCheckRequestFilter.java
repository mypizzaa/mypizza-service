/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.services;
//@Provider

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

public class APIKeyCheckRequestFilter implements ContainerRequestFilter {

    private static final String API_KEY = "X-API-KEY";

    @Override
    public void filter(ContainerRequestContext containerRequestContext)
            throws IOException {
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
    public boolean isPublicService(String path) {
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
    public boolean isValidApiKey(String apiKey) {
        boolean isValid = false;
        // TODO : validation of key 
        if (apiKey != null && !apiKey.isEmpty()) {
            if (isValid(apiKey)) {
                isValid = true;
            }
        }
        return isValid;
    }
    
    private boolean isValid(String apiKey){
        boolean b = false;
        
        return b;
    }
}
