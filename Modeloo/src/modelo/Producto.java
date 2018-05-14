
package modelo;

public class Producto {

    private long id_producto;
    private String nombre;
    private double precio;
    private String imagen;
    private long id_tipo;

    //Constructores
    public Producto(long id_prod, String nombre, double precio, String img, long id_tip) {
        this.id_producto = id_prod;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = img;
        this.id_tipo = id_tip;
    }

    //Getters
    public long getIdProducto() {
        return this.id_producto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getPrecio() {
        return this.precio;
    }

    public String getImagen() {
        return this.imagen;
    }

    public long getIdTipo() {
        return this.id_tipo;
    }

    //Setters
    public void setIdProduct(long idProd) {
        this.id_producto = idProd;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImagen(String img) {
        this.imagen = img;
    }

    public void setIdTipo(long idTipo) {
        this.id_tipo = idTipo;
    }

    //ToString
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto [");
        sb.append(" id_producto = " + id_producto);
        sb.append(", nombre = " + nombre);
        sb.append(", precio = " + precio);
        sb.append(", imagen = " + imagen);
        sb.append(", id_tipo = " + id_tipo);
        sb.append(" ]");

        return sb.toString();
    }
}
