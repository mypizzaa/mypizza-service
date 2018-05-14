package modelo;

public class MetodoPago {

    private long id_metodoPago;
    private String nombre;
    private String detalles;

    public MetodoPago(long id_metodo, String nombre, String detalles) {
        this.id_metodoPago = id_metodo;
        this.nombre = nombre;
        this.detalles = detalles;
    }

    //getters
    public long getIdMetodoPago() {
        return this.id_metodoPago;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDetalles() {
        return this.detalles;
    }

    //Setters
    public void setIdMetodoPago(long id) {
        this.id_metodoPago = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    //toString{
    public String toString() {
        return "id_metodo_pago = " + id_metodoPago + " , nombre = " + nombre + " , detalles = " + detalles;
    }

}
