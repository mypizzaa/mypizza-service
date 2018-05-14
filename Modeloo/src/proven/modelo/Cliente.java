/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.modelo;

/**
 *
 * @author ASUS
 */
public class Cliente extends Usuario {

    private long id_cliente;
    private String primeraDireccion;
    private String segundaDireccion;
    private String telefono;

    public Cliente(long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo, long id_cliente, String primeraDireccion, String segundaDireccion, String telefono) {
        super(id_usuario, dni, nombre, apellidos, password, imagen, tipo_Usuario, correo);
        this.id_cliente = id_cliente;
        this.primeraDireccion = primeraDireccion;
        this.segundaDireccion = segundaDireccion;
        this.telefono = telefono;
    }

    //getters
    public long getIdCliente() {
        return this.id_cliente;
    }

    public String getPrimeraDireccion() {
        return this.primeraDireccion;
    }

    public String getSegundaDireccion() {
        return this.segundaDireccion;
    }

    //setters
    public void setIdCliente(long idcliente) {
        this.id_cliente = idcliente;
    }

    public void setPrimeraDireccion(String direccion) {
        this.primeraDireccion = direccion;
    }

    public void setSegundaDireccion(String direccion) {
        this.segundaDireccion = direccion;
    }

    //toString
    public String toString() {
        return "";
    }

}
