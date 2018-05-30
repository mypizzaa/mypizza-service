package cat.proven.services;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import proven.modelo.Cliente;
import proven.modelo.Factura;
import proven.modelo.Pedido;
import proven.modelo.PedidoInfo;
import proven.modelo.Producto;
import proven.mypizzadao.Model;

/**
 *
 * @author MyPizza
 * @version 1.0
 */
@Path("/WSPedido")
public class WSPedido {

    private final Model model;

    public WSPedido(@Context ServletContext context) {

        if (context.getAttribute("Model") != null) {
            model = (Model) context.getAttribute("Model");
        } else {
            model = new Model();
            context.setAttribute("Model", model);
        }
    }

    /**
     * List all info order
     *
     * @return a json of a list of order info
     */
    @GET
    @Path("/listall")
    @Produces(MediaType.APPLICATION_JSON)
    public String listOrderInfo() {

        List<PedidoInfo> piList = model.getAllInfoOrder();

        return new Gson().toJson(piList);
    }

    /**
     * List all info order
     *
     * @param id
     * @return a json of a list of order info
     */
    @POST
    @Path("/productsorder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String listProductsOfAnOrder(@FormParam("id") long id) {
        List<Pedido> pList = null;
        if (id > 0) {
            pList = model.getOrderFromProductInfo(new PedidoInfo(id));
        }

        return new Gson().toJson(pList);
    }

    @GET
    @Path("/receivedorders")
    @Produces(MediaType.APPLICATION_JSON)
    public String receivedOrders() {
        List<PedidoInfo> piList = model.getAllReceivedOrders();
        return new Gson().toJson(piList);
    }

    @GET
    @Path("/coockingorders")
    @Produces(MediaType.APPLICATION_JSON)
    public String coockingOrders() {
        List<PedidoInfo> piList = model.getAllCookingOrders();
        return new Gson().toJson(piList);
    }

    @GET
    @Path("/readyorders")
    @Produces(MediaType.APPLICATION_JSON)
    public String readyOrders() {
        List<PedidoInfo> piList = model.getAllReadyOrders();
        return new Gson().toJson(piList);
    }

    @GET
    @Path("/deliveryorders")
    @Produces(MediaType.APPLICATION_JSON)
    public String deliveyOrders() {
        List<PedidoInfo> piList = model.getAllDeliveryOrders();
        return new Gson().toJson(piList);
    }

    @GET
    @Path("/paidorders")
    @Produces(MediaType.APPLICATION_JSON)
    public String paidOrders() {
        List<PedidoInfo> piList = model.getAllPaidOrders();
        return new Gson().toJson(piList);
    }

    @POST
    @Path("/createorder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createOrder(@FormParam("address") String address, @FormParam("client_id") long clientId,
            @FormParam("payMethod_id") long payMethodId, @FormParam("total_price") double totalPrice,
            @FormParam("date_time") String dateTime) {
        int id_pedido_info = -1;
        if (address != null && clientId > 0 && payMethodId > 0 && totalPrice > 0 && dateTime != null) {
            id_pedido_info = (int) model.createOrder(new PedidoInfo(address), new Factura(dateTime, clientId, payMethodId, totalPrice));
        } else {
            id_pedido_info = -2;
        }
        return new Gson().toJson(id_pedido_info);
    }

    /*Json example
    [{"id_pedido_info":12,"producto":{"id_producto": 3},"observaciones":"Con atun","cantidad":1,"precio": 0.5},{"id_pedido_info":12, "producto":{"id_producto":4},"cantidad":3,"precio":0.5}]
     */
    /**
     * Set products to order
     *
     * @param pList list of products
     * @return rows affected
     */
    @POST
    @Path("/productsorder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createProductOrders(String pList) {
        int rowsAffected = -1;
        if (pList != null) {
            List<Pedido> pedList = new Gson().fromJson(pList, new TypeToken<List<Pedido>>() {
            }.getType());
            rowsAffected = model.setProductsToOrder(pedList);
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * Set order to coock
     * @param id of the order
     * @return rows affected or -1 if error
     */
    @POST
    @Path("/ordertocook")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String setOrdertoCoock(@FormParam("id") long id) {
        int rowsAffected = -1;
        if (id > 0) {
            rowsAffected = model.setOrderToCoock(new PedidoInfo(id));
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * Set order to ready
     * @param id of the order
     * @return rows affected or -1 if error
     */
    @POST
    @Path("/ordertoready")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String setOrdertoReady(@FormParam("id") long id) {
        int rowsAffected = -1;
        if (id > 0) {
            rowsAffected = model.setOrderToReady(new PedidoInfo(id));
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * Set order to delivery
     * @param id of the order
     * @return rows affected or -1 if error
     */
    @POST
    @Path("/ordertodelivery")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String setOrderToDelivery(@FormParam("id") long id) {
        int rowsAffected = -1;
        if (id > 0) {
            rowsAffected = model.setOrderToDelivery(new PedidoInfo(id));
        }
        return new Gson().toJson(rowsAffected);
    }
    
    /**
     * Set order to paid
     * @param id of the order
     * @return rows affected or -1 if error
     */
    @POST
    @Path("/paid")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String paid(@FormParam("id") long id) {
        int rowsAffected = -1;
        if (id > 0) {
            rowsAffected = model.setBillToPaid(new PedidoInfo(id));
        }
        return new Gson().toJson(rowsAffected);
    }
    
    @POST
    @Path("/orderbill")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String orderBill(@FormParam("id") long id) {
        Factura f = null;
        if (id > 0) {
            f = model.getOrderBill(new PedidoInfo(id));
        }
        return new Gson().toJson(f);
    }
    
    @POST
    @Path("/clientorders")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String clientOrders(@FormParam("id") long id) {
        List<PedidoInfo> piList = null;
        if (id > 0) {
            piList = model.getOrdersByClient(new Cliente(id));
        }
        return new Gson().toJson(piList);
    }
}
