/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Empleado extends Usuario {

    private long id_empleado;
    private Date hora_entrada;
    private Date hora_salida;
    private int horasSemanales;
    private double Salario;

    public Empleado(long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo, long id_empleado, Date hora_entrada, Date hora_salida, int horasSemanales, double salario) {
        super(id_usuario, dni, nombre, apellidos, password, imagen, tipo_Usuario, correo);
        this.id_empleado = id_empleado;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.horasSemanales = horasSemanales;
        this.Salario = salario;
    }
    //getters

    public long getIdEmpleado() {
        return this.id_empleado;
    }

    public Date getHoraEntrada() {
        return this.hora_entrada;
    }

    public Date getHoraSalida() {
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

    public void setHoraEntrada(Date horaEntrada) {
        this.hora_entrada = horaEntrada;
    }

    public void setHoraSalida(Date horaSalida) {
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
