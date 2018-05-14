package proven.mypizza.servlets;
 
//import proven.friends.lib.FriendForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import java.util.List;
import javax.servlet.RequestDispatcher;
import proven.modelo.Pizza;
import proven.mypizzadao.Model;
 
/**
 * Servlet to resolve requests in Friends application
 *
 * @author ProvenSoft
 */
public class ProductServletJson extends HttpServlet {
 
    private Model model;
 
 
    @Override
    public void init() throws ServletException {
        this.model = new Model();
    }
 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the resultCode.">
    /**
     * Handles the HTTP GET method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGetRequest(request, response);
    }
 
    /**
     * Handles the HTTP POST method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPostRequest(request, response);
    }
 
    /**
     * Handles the HTTP PUT method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }
 
    /**
     * Handles the HTTP DELETE method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }
    
    /**
     * ProcessRequest
     * executes actions sended by get to manage categories.
     *
     * @param request
     * @param response
     */
     public void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "list_all_pizzas": //list all pizzas
                    listAllPizzas(request, response);
                    break;             
            }
        } else { // parameter action needed
            redirectError(request, response, "no_action");
        }
    }
 
    /**
     * ProcessRequest
     * executes actions sended by post to manage categories.
     *
     * @param request
     * @param response
     */
    public void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
            }
        } else { // parameter action needed
            redirectError(request, response, "no_action");
        }
    }
 
    /**
     * serves a formated list of all players
     *
     * @param request
     * @param response
     *
     */
    public void listAllPizzas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Pizza> entityList = (List<Pizza>) model.getAllPizzas();
        MyPizzaRequestResult result = new MyPizzaRequestResult(entityList, 1);
        request.setAttribute("result", result);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/json-result.jsp");
        rd.forward(request, response);
    }
 
    /**
     * serves Bad Request errors with HTTP Status 400
     *
     * @param request
     * @param response
     * @param error
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
   public void redirectError(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        String errorMsg = null;
        switch (error) {
            case "bad_parameter": // bad parameter action
                errorMsg = "Bad Parameter action";
                break;
            default: // need parameter action
                errorMsg = "Parameter action needed";
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMsg);
    }

 
}