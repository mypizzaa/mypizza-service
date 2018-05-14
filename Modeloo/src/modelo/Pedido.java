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
public class Pedido {

    private long id_pedido;
    private long id_pedido_info;
    private long id_producto;
    private String observaciones;
    private long id_cliente;

    //Construcor
    public Pedido(long id_pedido, long id_pedido_info, long id_producto, String observaciones, long id_cliente) {
        this.id_pedido = id_pedido;
        this.id_pedido_info = id_pedido_info;
        this.id_producto = id_producto;
        this.observaciones = observaciones;
        this.id_cliente = id_cliente;
    }

    //Getters
    public long getIdPedido() {
        return this.id_pedido;
    }

    public long getIdPedidoInfo() {
        return this.id_pedido_info;
    }

    public long getIdProducto() {
        return this.id_producto;
    }

    public String getObservaciones() {
        return this.observaciones;
    }

    public long getIdCliente() {
        return this.id_cliente;
    }

    //Setters
    public void setIdPedido(long idPedido) {
        this.id_pedido = idPedido;
    }

    public void setIdPedidoInfo(long idPedidoInfo) {
        this.id_pedido = idPedidoInfo;
    }

    public void setIdProducto(long idProd) {
        this.id_producto = idProd;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setIdCliente(long idCliente) {
        this.id_cliente = idCliente;
    }

    //toString
    public String ToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido [ ");
        sb.append(" id_pedido = " + id_pedido);
        sb.append(", id_pedido_info = " + id_pedido_info);
        sb.append(", id_producto = " + id_producto);
        sb.append(", Observaciones = " + observaciones);
        sb.append(", id_cliente = " + id_cliente);
        sb.append(" ]");

        return sb.toString();
    }
}
