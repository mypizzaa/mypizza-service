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
public class Cliente extends Usuario{

    public Cliente() {
    }

    public Cliente(long id_usuario, String dni, String nombre, String apellidos, String password, String imagen, String tipo_Usuario, String correo) {
        super(id_usuario, dni, nombre, apellidos, password, imagen, tipo_Usuario, correo);
    }
    
}
