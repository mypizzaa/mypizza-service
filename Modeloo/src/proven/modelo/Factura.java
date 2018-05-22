package proven.modelo;

import java.util.Date;

public class Factura {

    private long id_factura;
    private Date fecha;
    private long id_cliente;
    private long id_metodoPago;
    private Boolean cobrado;
    private long id_pedido;
    private double precio_total;

    //Constructor
    public Factura(long id_factura, Date fecha, long id_cliente, long id_metodoPago, Boolean cobrado, long id_pedido, double precio_total) {
        this.id_factura = id_factura;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_metodoPago = id_metodoPago;
        this.cobrado = cobrado;
        this.id_pedido = id_pedido;
        this.precio_total = precio_total;
    }

    public Factura(Date fecha, long id_cliente, long id_metodoPago, double precio_total) {
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_metodoPago = id_metodoPago;
        this.precio_total = precio_total;
    }
    
    

    //--------------------------------------------------------------------------

    public long getId_factura() {
        return id_factura;
    }

    public void setId_factura(long id_factura) {
        this.id_factura = id_factura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public long getId_metodoPago() {
        return id_metodoPago;
    }

    public void setId_metodoPago(long id_metodoPago) {
        this.id_metodoPago = id_metodoPago;
    }

    public Boolean getCobrado() {
        return cobrado;
    }

    public void setCobrado(Boolean cobrado) {
        this.cobrado = cobrado;
    }

    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    @Override
    public String toString() {
        return "Factura{" + "id_factura=" + id_factura + ", fecha=" + fecha + ", id_cliente=" + id_cliente + ", id_metodoPago=" + id_metodoPago + ", cobrado=" + cobrado + ", id_pedido=" + id_pedido + ", precio_total=" + precio_total + '}';
    }
   
            
}
