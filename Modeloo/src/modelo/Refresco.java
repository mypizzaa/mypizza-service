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
public class Refresco extends Producto {

    private long id_refresco;

    public Refresco(long id_prod, String nombre, double precio, String img, long id_tip, long id_refresco) {
        super(id_prod, nombre, precio, img, id_tip);
        this.id_refresco = id_refresco;
    }

    //Getters
    public long getIdRefresco() {
        return this.id_refresco;
    }

    //Setter
    public void setIdRefresco(long idRefresco) {
        this.id_refresco = idRefresco;
    }

    //ToString
    public String toString() {
        
        return super.toString()+", id_refresco = "+this.id_refresco;
    }

}
