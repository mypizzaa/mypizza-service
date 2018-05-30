package proven.modelo;

import java.util.Date;

public class Factura {

    private long id_factura;
    private long id_cliente;
    private long id_metodoPago;
    private long id_pedido_info;
    private double precio_total;
    private String fecha;
    private int cobrado;

    //Constructor

    public Factura(long id_factura, long id_cliente, long id_metodoPago, long id_pedido_info, double precio_total, String fecha, int cobrado) {
        this.id_factura = id_factura;
        this.id_cliente = id_cliente;
        this.id_metodoPago = id_metodoPago;
        this.id_pedido_info = id_pedido_info;
        this.precio_total = precio_total;
        this.fecha = fecha;
        this.cobrado = cobrado;
    }
    

    public Factura(String fecha, long id_cliente, long id_metodoPago, double precio_total) {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public int getCobrado() {
        return cobrado;
    }

    public void setCobrado(int cobrado) {
        this.cobrado = cobrado;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }

    public long getId_pedido_info() {
        return id_pedido_info;
    }

    public void setId_pedido_info(long id_pedido_info) {
        this.id_pedido_info = id_pedido_info;
    }

    @Override
    public String toString() {
        return "Factura{" + "id_factura=" + id_factura + ", id_cliente=" + id_cliente + ", id_metodoPago=" + id_metodoPago + ", id_pedido_info=" + id_pedido_info + ", precio_total=" + precio_total + ", fecha=" + fecha + ", cobrado=" + cobrado + '}';
    }

    

}
