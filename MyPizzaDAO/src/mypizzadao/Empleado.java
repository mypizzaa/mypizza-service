/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypizzadao;

import java.util.Date;

/**
 *
 * @author alumne
 */
public class Empleado extends Usuario {

    private long id_empleado;
    private Date hora_entrada;
    private Date hora_salida;
    private int horasSemanales;
    private double Salario;

    public Empleado(long id_empleado, Date hora_entrada, Date hora_salida, int horasSemanales, double Salario) {
        this.id_empleado = id_empleado;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.horasSemanales = horasSemanales;
        this.Salario = Salario;
    }

    public Empleado(long id_empleado, Date hora_entrada, Date hora_salida, int horasSemanales, double Salario, long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo) {
        super(id_usuario, dni, nombre, apellidos, password, imagen, tipo_Usuario, correo);
        this.id_empleado = id_empleado;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.horasSemanales = horasSemanales;
        this.Salario = Salario;
    }
    
    
}
