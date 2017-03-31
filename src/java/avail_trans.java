/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Beans.Avail_transactions;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sagar
 */
@WebServlet(urlPatterns = {"/avail_trans"})
public class avail_trans extends HttpServlet
{

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
            throws ServletException, IOException, SQLException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            String DB_Driver = getServletContext().getInitParameter("DB_Driver");
            String DB_Con = getServletContext().getInitParameter("DB_Con");
            String DB_Uname = getServletContext().getInitParameter("DB_Username");
            String DB_Password = getServletContext().getInitParameter("DB_Password");
            String user_id = request.getSession().getAttribute("user_id").toString();

            ArrayList<Avail_transactions> available2 = new ArrayList<>();
            try {

                String query = "select a.train_no,b.name,a.src_code,a.dest_code,a.date,a.class,a.price from booking a,trains b where a.passenger_id = ? and a.train_no=b.train_no ";
                Class.forName(DB_Driver);
                Connection con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, user_id);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int train_no = rs.getInt("train_no");
                    String name = rs.getString(2);
                    String from = rs.getString("src_code");
                    String to = rs.getString("dest_code");
                    String date = rs.getString("date");
                    String class_type = rs.getString("class");
                    int price = rs.getInt("price");

                    Avail_transactions avail_transactions = new Avail_transactions(train_no, name, from, to, date, class_type, price);
                    available2.add(avail_transactions);
                }

                session.setAttribute("available_trsc", available2);
                con.close();
                stmt.close();
                rs.close();
                response.sendRedirect(request.getContextPath() + "/transactions.jsp");

            }
            catch (ClassNotFoundException | SQLException ex) {
                out.println(ex);
            }
            finally {
                out.close();
            }

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
            throws ServletException, IOException
    {
        try {
            processRequest(request, response);
        }
        catch (SQLException ex) {
            Logger.getLogger(avail_trans.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException, IOException
    {
        try {
            processRequest(request, response);
        }
        catch (SQLException ex) {
            Logger.getLogger(avail_trans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
