
package proven.modelo;

public class Ingrediente extends Producto {

    private long id_ingrediente;

    //constructor
    public Ingrediente(long id_prod, String nombre, double precio, String img, long id_tip, long id_ingrediente) {
        super(id_prod, nombre, precio, img, id_tip);
        this.id_ingrediente = id_ingrediente;
    }

    public Ingrediente(long id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }
    

    //--------------------------------------------------------------------------
    
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
