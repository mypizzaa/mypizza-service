/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypizzadao;

/**
 *
 * @author alumne
 */
public class Usuario {

    private long id_usuario;
    private String dni;
    private String nombre;
    private String apellidos;
    private String password;
    private String imagen;
    private String tipo_Usuario;
    private String correo;

    public Usuario() {
    }

    public Usuario(long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo) {
        this.id_usuario = id_usuario;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
        this.imagen = imagen;
        this.tipo_Usuario = tipo_Usuario;
        this.correo = correo;
    }

    public Usuario(String password, String correo) {
        this.password = password;
        this.correo = correo;
    }

    public Usuario(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTipo_Usuario() {
        return tipo_Usuario;
    }

    public void setTipo_Usuario(String tipo_Usuario) {
        this.tipo_Usuario = tipo_Usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", password=" + password + ", imagen=" + imagen + ", tipo_Usuario=" + tipo_Usuario + ", correo=" + correo + '}';
    }

    
    
}
