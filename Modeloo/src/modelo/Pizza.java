package modelo;

public class Pizza extends Producto {

    private long id_pizza;

    public Pizza(long id_prod, String nombre, double precio, String img, long id_tip, long id_pizza) {
        super(id_prod, nombre, precio, img, id_tip);
        this.id_pizza = id_pizza;
    }

    //Getters
    public long getIdPizza() {
        return this.id_pizza;
    }

    //Setters
    public void setIdPizza(long idPizza) {
        this.id_pizza = idPizza;
    }

    //ToString
    public String toString() {
        
        return super.toString()+", id_pizza = "+id_pizza;
    }

}
