/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.modelo;

/**
 *
 * @author alumne
 */
public class PedidoInfo {
    
    private long id_pedido_info;
    private long id_estado;
    private String direccion;
    private String dia_hora;
    private long id_cliente;

    public PedidoInfo(long id_pedido_info, long id_estado, String direccion, String dia_hora, long id_cliente) {
        this.id_pedido_info = id_pedido_info;
        this.id_estado = id_estado;
        this.direccion = direccion;
        this.dia_hora = dia_hora;
        this.id_cliente = id_cliente;
    }

    public PedidoInfo(String direccion, String dia_hora, long id_cliente) {
        this.direccion = direccion;
        this.dia_hora = dia_hora;
        this.id_cliente = id_cliente;
    }

    public PedidoInfo() {
    }

    public long getId_pedido_info() {
        return id_pedido_info;
    }

    public void setId_pedido_info(long id_pedido_info) {
        this.id_pedido_info = id_pedido_info;
    }

    public long getId_estado() {
        return id_estado;
    }

    public void setId_estado(long id_estado) {
        this.id_estado = id_estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDia_hora() {
        return dia_hora;
    }

    public void setDia_hora(String dia_hora) {
        this.dia_hora = dia_hora;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    @Override
    public String toString() {
        return "PedidoInfo{" + "id_pedido_info=" + id_pedido_info + ", id_estado=" + id_estado + ", direccion=" + direccion + ", dia_hora=" + dia_hora + ", id_cliente=" + id_cliente + '}';
    }     
}
