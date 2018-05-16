package cat.proven.services;

import com.google.gson.Gson;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import proven.modelo.Cliente;
import proven.mypizzadao.Model;

@WebService(serviceName = "WSCliente")
public class WSCliente {
    
    private final Model model;
    
    public WSCliente() {
        model = new Model();
    }
    
    @WebMethod(operationName = "add")
    public String addClient(
            @WebParam(name = "dni") String dni,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellidos") String apellidos,
            @WebParam(name = "password") String password,
            @WebParam(name = "imagen") String imagen,
            @WebParam(name = "tipousuario") String tipousuario,
            @WebParam(name = "correo") String correo,
            @WebParam(name = "primeradireccion") String primeradireccion,
            @WebParam(name = "segundadireccion") String segundadireccion,
            @WebParam(name = "tlf") String tlf
    ) {
        
        Cliente c = new Cliente(dni, nombre, apellidos, password, imagen, tipousuario, correo, primeradireccion, segundadireccion, tlf);
        int added = model.addClient(c);
        
        return new Gson().toJson(added);
    }
    
    @WebMethod(operationName = "checkifexsits")
    public String checkClient(@WebParam(name = "correo") String correo) {
        
        Cliente c = new Cliente(correo);
        int exists = model.checkIfExist(c);
        
        return new Gson().toJson(exists);
    }
    
    @WebMethod(operationName = "inactiveclient")
    public String inactivateClient(@WebParam(name = "correo") String correo) {
        
        Cliente c = new Cliente(correo);
        int inactivated = model.inactivateClient(c);
        
        return new Gson().toJson(inactivated);
    }
    
    @WebMethod(operationName = "changepassword")
    public String changePassword(@WebParam(name = "correo") String correo,
            @WebParam(name = "password") String password) {
        
        Cliente c = new Cliente(correo, password);        
        int changed = model.changePassword(c);
        
        return new Gson().toJson(changed);
    }
    
}
