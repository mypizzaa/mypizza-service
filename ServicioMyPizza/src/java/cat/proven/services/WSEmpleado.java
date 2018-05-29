package cat.proven.services;

import com.google.gson.Gson;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
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

/**
 *
 * @author MyPizza
 * @version 1.0
 */
@Path("/WSEmpleado")
public class WSEmpleado {

    private final Model model;

    /**
     * Constructor
     *
     * @param context ServletContext
     */
    public WSEmpleado(@Context ServletContext context) {

        if (context.getAttribute("Model") != null) {
            model = (Model) context.getAttribute("Model");
        } else {
            model = new Model();
            context.setAttribute("Model", model);
        }
    }

    /**
     * List all employees
     *
     * @return a list of employees or null in json
     */
    @GET
    @Path("/listall")
    @Produces(MediaType.APPLICATION_JSON)
    public String listAllEmployees() {
        List<Empleado> eList = model.listAllEmployees();
        return new Gson().toJson(eList);
    }

    /**
     * List employees by dni
     *
     * @param dni of employees
     * @return json of the employees or null if error
     */
    @POST
    @Path("/buscar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String buscarEmpleado(@FormParam("dni") String dni) {
        Empleado e = model.findEmployee(new Empleado(dni));
        return new Gson().toJson(e);
    }

    /**
     * Add an employee with the given params
     *
     * @param horaEntrada start journey of employee
     * @param horaSalida end jouney of employee
     * @param horasSemanales hours a week of employee
     * @param salario money of employee
     * @param dni DNI of employee
     * @param nombre name of employee
     * @param apellidos surname of employee
     * @param password password of employee
     * @param imagen image of employee 
     * @param tipoUsuario kind of employee
     * @param correo emnail of employee
     * @return rows affected or -1 if error
     */
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addEmployee(@FormParam("hora_entrada") String horaEntrada, @FormParam("hora_salida") String horaSalida,
            @FormParam("horas_semanales") int horasSemanales, @FormParam("salario") double salario, @FormParam("dni") String dni,
            @FormParam("nombre") String nombre, @FormParam("apellidos") String apellidos, @FormParam("password") String password,
            @FormParam("imagen") String imagen, @FormParam("tipo_usuario") String tipoUsuario, @FormParam("correo") String correo) {

        int rowsAffected = -1;
        if (horaEntrada != null && horaSalida != null && horasSemanales > 0 && salario > 0
                && dni != null && nombre != null && apellidos != null && password != null && correo != null) {
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            try {
                Time horEnt = new Time(formatter.parse(horaEntrada).getTime());
                Time horaSal = new Time(formatter.parse(horaSalida).getTime());
                rowsAffected = model.addEmployee(new Empleado(horEnt, horaSal, horasSemanales, salario, dni, nombre, apellidos, password, imagen, tipoUsuario, correo));
            } catch (ParseException ex) {
                rowsAffected = -2;
            }
        }

        return new Gson().toJson(rowsAffected);
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateEmployee(@FormParam("id")Long id, @FormParam("hora_entrada") String horaEntrada, @FormParam("hora_salida") String horaSalida,
            @FormParam("horas_semanales") int horasSemanales, @FormParam("salario") double salario, @FormParam("dni") String dni,
            @FormParam("nombre") String nombre, @FormParam("apellidos") String apellidos, @FormParam("password") String password,
            @FormParam("imagen") String imagen, @FormParam("tipo_usuario") String tipoUsuario, @FormParam("correo") String correo) {

        int rowsAffected = -1;
        if (id!=null && horaEntrada != null && horaSalida != null && horasSemanales > 0 && salario > 0
                && dni != null && nombre != null && apellidos != null && password != null && correo != null) {
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            try {
                Time horEnt = new Time(formatter.parse(horaEntrada).getTime());
                Time horaSal = new Time(formatter.parse(horaSalida).getTime());
                rowsAffected = model.updateEmployee(new Empleado(horEnt, horaSal, horasSemanales, salario, id, dni, nombre, apellidos, password, imagen, tipoUsuario, correo));
            } catch (ParseException ex) {
                rowsAffected = -2;
            }
        }

        return new Gson().toJson(rowsAffected);
    }

    @POST
    @Path("/baja/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public String inactiveEmployee(@FormParam("dni") String dni) {

        int inactive = model.inactivateEmployee(new Empleado(dni));

        return new Gson().toJson(inactive);
    }

}
