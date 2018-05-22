package proven.modelo;

import java.sql.Time;
import java.util.Date;

public class Empleado extends Usuario {

    private long id_empleado;
    private Time hora_entrada;
    private Time hora_salida;
    private int horasSemanales;
    private double Salario;

    //constructor
    public Empleado(long id_empleado, Time hora_entrada, Time hora_salida, int horasSemanales, double Salario, long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo, int activo) {
        super(id_usuario, dni, nombre, apellidos, password, imagen, tipo_Usuario, correo, activo);
        this.id_empleado = id_empleado;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.horasSemanales = horasSemanales;
        this.Salario = Salario;
    }

    //--------------------------------------------------------------------------
    //getters
    public long getIdEmpleado() {
        return this.id_empleado;
    }

    public Time getHoraEntrada() {
        return this.hora_entrada;
    }

    public Time getHoraSalida() {
        return this.hora_salida;
    }

    public int getHorasSemanales() {
        return this.horasSemanales;
    }

    public double getSalario() {
        return this.Salario;
    }

    //Setters
    public void setIdEmpleado(long idEmpleado) {
        this.id_empleado = idEmpleado;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.hora_entrada = horaEntrada;
    }

    public void setHoraSalida(Time horaSalida) {
        this.hora_salida = horaSalida;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public void setSalario(double salario) {
        this.Salario = salario;
    }

    //ToString
    public String ToString() {

        StringBuilder sb = new StringBuilder();
        sb.append(" Empleado [ ");
        sb.append("id_empleado = " + id_empleado);
        sb.append(" ,hora_salida =  " + hora_salida);
        sb.append(" ,hora_entrada = " + hora_entrada);
        sb.append(" ,horas semanales = " + horasSemanales);
        sb.append(" ,salario = " + Salario);

        return sb.toString();
    }

}
