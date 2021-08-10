/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tunlt.user.UserDAO;
import tunlt.user.UserDTO;
import tunlt.utils.SendEmail;

/**
 *
 * @author Tu
 */
@WebServlet(name = "RegisterUserServlet", urlPatterns = {"/RegisterUserServlet"})
public class RegisterUserServlet extends HttpServlet {

    private final String VERIFYEMAIL = "verifyemail.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "";
        String username = request.getParameter("txtUserName");
        String password = request.getParameter("txtPassword");
        String fullname = request.getParameter("txtFullName");
        String address = request.getParameter("txtAddress");
        String email = request.getParameter("txtEmail");
        int phone = Integer.parseInt(request.getParameter("txtPhone"));
        UserDAO dao = new UserDAO();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            SendEmail sm = new SendEmail();
            String code = sm.getRandom();
            HttpSession session = request.getSession();
            session.setAttribute("CODEVERIFY", code);
            session.setMaxInactiveInterval(30 * 60);
            UserDTO user = new UserDTO(username, password, fullname, address, email, phone, sdf.format(date), "New", false);
            if (fullname.matches("[a-zA-Z_\\s]+")) {
                if (request.getParameter("txtPhone").length() < 9 || request.getParameter("txtPhone").length() > 12) {
                    request.setAttribute("MESSAGE_REGIS", "Phone is number have lenght from 9-12 ");
                    url = "register.jsp";
                } else {
                    boolean test = false;
                    if (dao.insertUser(user) > 0) {
                        test = sm.sendEmail(user, code);
                    } else {
                        request.setAttribute("MESSAGE_REGIS", "username is existed!");
                        url = "register.jsp";
                    }
                    if (test) {
                        session.setAttribute("NOTVERIFYEMAIL", user);
                        url = VERIFYEMAIL;
                    }
                }
            } else {
                request.setAttribute("MESSAGE_REGIS", "Invalid FullName! ");
                url = "register.jsp";
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
