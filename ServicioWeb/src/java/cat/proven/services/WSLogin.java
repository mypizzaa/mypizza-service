package cat.proven.services;

import com.google.gson.Gson;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import proven.modelo.Usuario;
import proven.mypizzadao.Model;

@WebService(serviceName = "WSLogin")
public class WSLogin {

    private final Model model;

    public WSLogin() {

        this.model = new Model();
    }

    @WebMethod(operationName = "login")
    public String hello(@WebParam(name = "correo") String correo,
            @WebParam(name = "password") String password) {

        Usuario u = model.login(correo, password);
        
        return new Gson().toJson(u);
    }
}
