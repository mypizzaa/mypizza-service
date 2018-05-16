
package cat.proven.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "WSEmpleado")
public class WSEmpleado {

    
    /**
     * 
     * @param txt
     * @return 
     */    
    @WebMethod(operationName = "add")
    public String addEmpleado(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "modify")
    public String modifyEmpleado(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "remove")
    public String removeEmpleado(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
