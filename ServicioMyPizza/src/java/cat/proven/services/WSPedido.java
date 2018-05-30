package cat.proven.services;

import com.google.gson.Gson;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
    @Path("/createOrder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createOrder(@FormParam("address") String address, @FormParam("client_id") long clientId,
            @FormParam("payMethod_id") long payMethodId, @FormParam("total_price") double totalPrice,
            @FormParam("date_time") String dateTime, @FormParam("product_id") List<Long> productId,
            @FormParam("observations") String observations, @FormParam("quantity") int quantity,
            @FormParam("price") double price) {
        int rowsAffected = -1;
        if (address != null && clientId > 0 && payMethodId > 0 && totalPrice > 0 && dateTime != null
                && productId != null && quantity > 0 && price > 0) {
            List<Pedido> pList = new ArrayList();
            for (Long l: productId){
                pList.add(new Pedido(new Producto(l), observations, quantity, price));
            }
            rowsAffected = model.createOrder(new PedidoInfo(address), pList, new Factura(dateTime, clientId, payMethodId, totalPrice));
        }
        return new Gson().toJson(rowsAffected);
    }

}
