package modelo;

import java.util.Date;

public class Factura {

    private long id_factura;
    private Date fecha;
    private long id_cliente;
    private long id_metodoPago;
    private Boolean cobrado;

    //Constructor
    public Factura(long id_factura, Date fecha, long id_cliente, long id_metodoPago, Boolean cobrado) {
        this.id_factura = id_factura;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_metodoPago = id_metodoPago;
        this.cobrado = cobrado;
    }

    //Getters
    public long getIdFactura() {
        return this.id_factura;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public long getIdCliente() {
        return this.id_cliente;
    }

    public long getIdMetodoPago() {
        return this.id_metodoPago;
    }

    public Boolean getCobrado() {
        return this.cobrado;
    }

    //Setters
    public void setIdFactura(long idfactura) {
        this.id_factura = idfactura;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setIdCliente(long idcliente) {
        this.id_cliente = idcliente;
    }

    public void setIdMetodoPago(long idMetodo) {
        this.id_metodoPago = idMetodo;
    }

    public void setCobrado(Boolean cobrado) {
        this.cobrado = cobrado;
    }

    //toString
    public String toString() {
        return "id_factura = "+id_factura+", fecha = "+fecha+", id_cliente = "+id_cliente+",id_metodoPago = "+id_metodoPago+", cobrado = "+cobrado;
    }
}
