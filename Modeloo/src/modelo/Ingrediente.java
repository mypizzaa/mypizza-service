/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ASUS
 */
public class Ingrediente extends Producto {

    private long id_ingrediente;

    public Ingrediente(long id_prod, String nombre, double precio, String img, long id_tip, long id_ingrediente) {
        super(id_prod, nombre, precio, img, id_tip);
        this.id_ingrediente = id_ingrediente;
    }

    //Getters
    public long getIdIngrediente() {
        return this.id_ingrediente;
    }

    //Setter
    public void setIdIngrediente(long idIngrediente) {
        this.id_ingrediente = idIngrediente;
    }

    //toString
    public String toString() {
      
        return super.toString()+", id_ingrediente = "+id_ingrediente;
    }

}
