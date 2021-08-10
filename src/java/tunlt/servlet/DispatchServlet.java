/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tu
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
private final String LOGIN_SERVLET= "LoginServlet";
private final String SEARCHPAGE_SERVLER= "SearchOfEmployeeServlet";
private final String VIEWDETAIL_SERVLET = "DetailResource";
private final String VERIFYEMAIL_SERVLET = "VerifyEmailServlet";
private final String REGISTER_SERVLET = "RegisterUserServlet";
private final String BOOKING_SERVLET = "BookingServlet";
private final String LOGOUT_SERVLET = "LogoutServlet";
private final String SEARCHREQUEST_SERVLET ="SearchRequestServlet";
private final String REJECTREQUEST_SERVLET ="RejectRequestServlet";
private final String APPROVEREQUEST_SERVLET ="ApproveRequestServlet";
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
        String button = request.getParameter("btnAction");
        String url = "login.jsp";
        try {
            if(button == null){
                
            }
            else if(button.equals("Login")){
                url = LOGIN_SERVLET;
            }else if(button.equals("Search")){
                url = SEARCHPAGE_SERVLER; 
            }else if(button.equals("Detail")){
                url = VIEWDETAIL_SERVLET;
            }else if(button.equals("VerifyEmail")){
                url = VERIFYEMAIL_SERVLET;
            }else if(button.equals("Register")){
                url = REGISTER_SERVLET;
            }else if(button.equals("Booking")){
                url = BOOKING_SERVLET;
            }else if(button.equals("Logout")){
                url = LOGOUT_SERVLET;
            }else if(button.equals("SearchRequest")){
                url = SEARCHREQUEST_SERVLET;
            }else if(button.equals("Reject")){
                url = REJECTREQUEST_SERVLET;
            }else if(button.equals("Approve")){
                url = APPROVEREQUEST_SERVLET;
            }
        }finally{
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
