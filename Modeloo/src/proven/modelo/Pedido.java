/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.modelo;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class Pedido {

    private long id_pedido;
    private long id_estado;
    private String direccionPedido;
    private String fechaDiaPedido;
    private long id_producto;
    private long id_cliente;
    private double precioTotal;
    private long id_factura;

    public Pedido(long id_pedido, long id_estado, String direccionPedido, String fechaDiaPedido, long id_producto, long id_cliente, double precioTotal, long id_factura) {
        this.id_pedido = id_pedido;
        this.id_estado = id_estado;
        this.direccionPedido = direccionPedido;
        this.fechaDiaPedido = fechaDiaPedido;
        this.id_producto = id_producto;
        this.id_cliente = id_cliente;
        this.precioTotal = precioTotal;
        this.id_factura = id_factura;
    }

    public Pedido() {
    }

    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public long getId_estado() {
        return id_estado;
    }

    public void setId_estado(long id_estado) {
        this.id_estado = id_estado;
    }

    public String getDireccionPedido() {
        return direccionPedido;
    }

    public void setDireccionPedido(String direccionPedido) {
        this.direccionPedido = direccionPedido;
    }

    public String getFechaDiaPedido() {
        return fechaDiaPedido;
    }

    public void setFechaDiaPedido(String fechaDiaPedido) {
        this.fechaDiaPedido = fechaDiaPedido;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public long getId_factura() {
        return id_factura;
    }

    public void setId_factura(long id_factura) {
        this.id_factura = id_factura;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id_pedido=" + id_pedido + ", id_estado=" + id_estado + ", direccionPedido=" + direccionPedido + ", fechaDiaPedido=" + fechaDiaPedido + ", id_producto=" + id_producto + ", id_cliente=" + id_cliente + ", precioTotal=" + precioTotal + ", id_factura=" + id_factura + '}';
    }
}
