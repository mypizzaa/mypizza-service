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
    private String estadoPedido;
    private String direccionPedido;
    private String fechaPedido;
    private List<Producto> prodList;
    private long id_cliente;
    private double precioTotal;
    private long id_factura;

    public Pedido(long id_pedido, String estadoPedido, String direccionPedido, String fechaPedido, List<Producto> prodList, long id_cliente, double precioTotal, long id_factura) {
        this.id_pedido = id_pedido;
        this.estadoPedido = estadoPedido;
        this.direccionPedido = direccionPedido;
        this.fechaPedido = fechaPedido;
        this.prodList = prodList;
        this.id_cliente = id_cliente;
        this.precioTotal = precioTotal;
        this.id_factura = id_factura;
    }

    public Pedido() {
    }
    
  
}
