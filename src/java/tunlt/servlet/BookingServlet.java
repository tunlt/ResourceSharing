/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import tunlt.item.ItemDAO;
import tunlt.item.ItemDTO;
import tunlt.user.UserDTO;

/**
 *
 * @author Tu
 */
@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

    private final String SEARCH_PAGE = "searchEmployee.jsp";
    private final String BOOKING_PAGE = "booking.jsp";

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
        String url = BOOKING_PAGE;
        HttpSession session = request.getSession();
        ItemDTO item = (ItemDTO) session.getAttribute("ITEM");
        UserDTO user = (UserDTO) session.getAttribute("USER");
        int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        String begin = request.getParameter("txtDateBegin");
        String end = request.getParameter("txtDateEnd");
        String requestMessage = request.getParameter("txtRequest");
        Date date = new Date();
        ItemDAO itemdao = new ItemDAO();
        String currentdate = df1.format(date);
        BookingDAO booking = new BookingDAO();
        boolean check = false;
        try {
            Date datebegin = df2.parse(begin);
            String datebeginfinal = df1.format(datebegin);
            Date dateend = df2.parse(end);
            String dateendfinal = df1.format(dateend);
            Date datenow = df1.parse(currentdate);
                if (datenow.after(datebegin) || datenow.after(dateend)) {
                    request.setAttribute("MESSAGE", "date begin and date end must >= date now");
                } else {
                    if (datebegin.after(dateend)) {
                        request.setAttribute("MESSAGE", "date begin must >= date end");
                    } else {
                        int quantityStock = itemdao.getQuantityByItemID(item.getItemid());
                        if (quantity > quantityStock) {
                            request.setAttribute("MESSAGE", "quantity out stock");
                        } else {
                            if(quantity <= 0){
                                request.setAttribute("MESSAGE", "quantity must >0 ");
                            }else{
                            if (itemdao.UpdateQuantitybyItemID(item.getItemid(), quantityStock - quantity)) {
                                check = booking.insertBooking(user.getUsername(), "New", datebeginfinal, dateendfinal, quantity, requestMessage,item.getItemname(),item.getItemid());
                                if (check) {
                                    request.setAttribute("MESSAGE", "Booking Succsessully! ");
                                    url = SEARCH_PAGE;
                                } else {
                                    request.setAttribute("MESSAGE", "Booking fail! ");
                                }
                            }}
                        }
                    }
                }
        } catch (NamingException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
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
