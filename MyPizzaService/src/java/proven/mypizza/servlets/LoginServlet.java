/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proven.mypizza.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import proven.modelo.Usuario;
import proven.mypizzadao.Model;

/**
 *
 * @author alumne
 */
public class LoginServlet extends HttpServlet {

    private Model model;

    @Override
    public void init() throws ServletException {
        this.model = new Model();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP (code)GET(/code) method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP (code)POST(/code) method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        String correo = request.getParameter("correo");
        String password = request.getParameter("password");

        if (correo != null && password != null) {       
            Usuario u = model.login(correo, password);
            if (u != null) {
                session.setAttribute("correo", correo);
                session.setAttribute("userrole", u.getTipoUsuario());               
                MyPizzaRequestResult result = new MyPizzaRequestResult("Login OK", 1);
                request.setAttribute("result", result);
                request.getRequestDispatcher("/WEB-INF/jsp/json-result.jsp").forward(request, response);
            }
        }

        if (session.getAttribute("correo") == null) {
            MyPizzaRequestResult result = new MyPizzaRequestResult("Username or password incorrect", 0);
            request.setAttribute("result", result);
            request.getRequestDispatcher("/WEB-INF/jsp/json-result.jsp").forward(request, response);
        }
    }

}
