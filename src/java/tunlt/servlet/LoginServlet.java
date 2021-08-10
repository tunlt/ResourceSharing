/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tunlt.category.CategoryDAO;
import tunlt.user.UserDAO;
import tunlt.user.UserDTO;
import tunlt.utils.verifyRecaptcha;

/**
 *
 * @author Tu
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

//    private final String HOMEPAGE = "home.jsp";
    private final String LOGINPAGE = "login.jsp";
    private final String SEARCHEMPLOYEEPAGE = "searchEmployee.jsp";
    private final String SEARCHREQUESTPAGE = "searchRequestAdmin.jsp";
    private final String VERIFYEMAILPAGE = "verifyemail.jsp";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
        boolean verifyRecap = false;
        verifyRecap = verifyRecaptcha.verify(gRecaptchaResponse);
        UserDAO userdao = new UserDAO();

        String url = LOGINPAGE;
        try {
            if (verifyRecap) {
                if (userdao.checkLogin(username, password)) {

                    HttpSession session = request.getSession(true);
                    UserDTO dto = userdao.getUser(username);
                    if (dto.getStatus().equalsIgnoreCase("New")) {
                        url = VERIFYEMAILPAGE;
                        session.setAttribute("NOTVERIFYEMAIL", dto);
                        request.setAttribute("MESSAGE", "Your account not verify please click send code again and verify email");
                    } else {
                        session.setAttribute("LOGIN_USER", dto);
                        if (dto.isIsManager()) {
                            ArrayList list = new ArrayList();
                            list.add("all");
                            list.add("New");
                            list.add("Deleted");
                            list.add("Approved");                            
                            session.setAttribute("LISTSTATUS", list);
                            session.setAttribute("isManager", dto);
                            url = SEARCHREQUESTPAGE;
                        } else {
                            CategoryDAO dao = new CategoryDAO();
                            session.setAttribute("LISTCATE", dao.getCategory());
                            session.setAttribute("USER", dto);
                            url = SEARCHEMPLOYEEPAGE;

                        }
                    }
                } else {
                    request.setAttribute("ERROR", "Username or Password invalid! ");
                }
            } else {
                request.setAttribute("ERROR", "Please check robot");
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
