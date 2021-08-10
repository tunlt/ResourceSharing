/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tunlt.Booking.BookingDAO;
import tunlt.Booking.BookingDTO;

/**
 *
 * @author Tu
 */
@WebServlet(name = "SearchRequestServlet", urlPatterns = {"/SearchRequestServlet"})
public class SearchRequestServlet extends HttpServlet {

    private final String SEARCHREQUEST_PAGE = "searchRequestAdmin.jsp";

    private final int COUNTPAGE = 20;

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
        String searchValue = request.getParameter("txtSearchrequest");
        String cboxCate = request.getParameter("cboxCategory");
        request.setAttribute("CBOXCHANGE", cboxCate);
        int index = Integer.parseInt(request.getParameter("intdex"));
        BookingDAO bookingdao = new BookingDAO();
        String url = SEARCHREQUEST_PAGE;
        try {
            List<BookingDTO> list = bookingdao.ListBooking(searchValue, cboxCate);
            if (list.isEmpty()) {
                request.setAttribute("MESSAGE_SEARCH", "No match record!");
            } else {
                int pageEnd = bookingdao.countPage(list) / COUNTPAGE;
                if (bookingdao.countPage(list) % COUNTPAGE != 0) {
                    pageEnd++;
                }
                if (pageEnd != 1) {
                    request.setAttribute("COUNT_PAGE", pageEnd);
                }
                List<BookingDTO> lists = bookingdao.getBookByPage(searchValue, cboxCate, index, COUNTPAGE);
                request.setAttribute(("SEARCHLISTREQUEST"), lists);
            }
        } catch (NamingException ex) {
            Logger.getLogger(SearchOfEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchOfEmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
