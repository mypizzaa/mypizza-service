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
    private long id_pedido_info;    
    private Producto producto;
    private String observaciones;
    private int cantidad;
    private double precio;

    public Pedido(long id_pedido, long id_pedido_info, Producto producto, String observaciones, int cantidad, double precio) {
        this.id_pedido = id_pedido;
        this.id_pedido_info = id_pedido_info;
        this.producto = producto;
        this.observaciones = observaciones;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Pedido(Producto producto, String observaciones, int cantidad, double precio) {
        this.producto = producto;
        this.observaciones = observaciones;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Pedido() {
    }

    public long getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(long id_pedido) {
        this.id_pedido = id_pedido;
    }

    public long getId_pedido_info() {
        return id_pedido_info;
    }

    public void setId_pedido_info(long id_pedido_info) {
        this.id_pedido_info = id_pedido_info;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id_pedido=" + id_pedido + ", id_pedido_info=" + id_pedido_info + ", Producto=" + producto + ", observaciones=" + observaciones + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }  
}
