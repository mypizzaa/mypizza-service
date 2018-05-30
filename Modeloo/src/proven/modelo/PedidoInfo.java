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

    public PedidoInfo(long id_pedido_info, long id_estado, String direccion) {
        this.id_pedido_info = id_pedido_info;
        this.id_estado = id_estado;
        this.direccion = direccion;
    }

    public PedidoInfo(String direccion) {
        this.direccion = direccion;
    }

    public PedidoInfo(long id_pedido_info) {
        this.id_pedido_info = id_pedido_info;
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

    @Override
    public String toString() {
        return "PedidoInfo{" + "id_pedido_info=" + id_pedido_info + ", id_estado=" + id_estado + ", direccion=" + direccion + '}';
    }

       
}
