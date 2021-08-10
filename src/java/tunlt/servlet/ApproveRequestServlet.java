/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tunlt.Booking.BookingDAO;
import tunlt.Booking.BookingDTO;
import tunlt.BookingDetail.BookingDetailDAO;
import tunlt.item.ItemDAO;
import tunlt.user.UserDTO;

/**
 *
 * @author Tu
 */
@WebServlet(name = "ApproveRequestServlet", urlPatterns = {"/ApproveRequestServlet"})
public class ApproveRequestServlet extends HttpServlet {

    private final String SEARCHREQUESTADMIN = "searchRequestAdmin.jsp";
    private final String MANAGEREQUEST = "managerequest.jsp";

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
        String responeMessage = request.getParameter("txtResponseMessage");
        String itemid = request.getParameter("txtItemID");
        HttpSession session = request.getSession();
        BookingDTO booking = (BookingDTO) session.getAttribute("BOOKING");
        BookingDAO bookingdao = new BookingDAO();
        UserDTO censorname = (UserDTO) session.getAttribute("LOGIN_USER");
        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        BookingDetailDAO bookingdetaildao = new BookingDetailDAO();
        ItemDAO itemdao = new ItemDAO();
        boolean insert = false;
        boolean updatestatus = false;
        String url = "";
        try {

            insert = bookingdetaildao.insertBookingDetail(itemid, censorname.getUsername(), responeMessage, booking.getBookingid());
            updatestatus = bookingdao.UpdateStatusbybookingID(booking.getBookingid(), "Approved");
            if (insert && updatestatus) {
                request.setAttribute("MESSAGE_MANAGE", "Approved successfully!");
                url = SEARCHREQUESTADMIN;
            } else {
                request.setAttribute("MESSAGE_MANAGE", "Approved Fail!");
                url = MANAGEREQUEST;
            }

        } catch (NamingException ex) {
            Logger.getLogger(RejectRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RejectRequestServlet.class.getName()).log(Level.SEVERE, null, ex);
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
