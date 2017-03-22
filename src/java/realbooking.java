/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@WebServlet(urlPatterns = {"/realbooking"})
public class realbooking extends HttpServlet
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
            throws ServletException, IOException, ClassNotFoundException, SQLException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String train_no = request.getSession().getAttribute("train_no").toString();
        String datesel = request.getSession().getAttribute("date_select").toString();
        String src_station = request.getSession().getAttribute("src_station").toString();
        String dest_station = request.getSession().getAttribute("dest_station").toString();
        String class_type = request.getSession().getAttribute("class_type").toString();
        String user_id = request.getSession().getAttribute("user_id").toString();

        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");
        Connection con = null;

        try {
            Class.forName(DB_Driver);
            con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement pstm = null;
            String query1 = "select a.kilometers,b.kilometers from schedule a,schedule b where a.train_no=? and b.train_no=? and a.stops=? and b.stops=?";
            pstm = con.prepareStatement(query1);
            pstm.setString(1, train_no);
            pstm.setString(2, train_no);
            pstm.setString(3, src_station);
            pstm.setString(4, dest_station);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                int src_km = rs.getInt(1);
                int dest_km = rs.getInt(2);
                int total_kms = dest_km - src_km;

                switch (class_type) {
                    case "seats_fc":

                }

            }
        }
        catch (ClassNotFoundException e) {
            out.println(e);
        }
        catch (SQLException e) {
            out.println(e);
        }

        try {
//                out.println("hello");
            Class.forName(DB_Driver);
            con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement pstm = null;
            String query = "insert into booking(train_no,date,src_code,dest_code,class,status,passenger_id) values(?,?,?,?,?,'c',?)";
            pstm = con.prepareStatement(query);
            pstm.setString(1, train_no);
            pstm.setString(2, datesel);
            pstm.setString(3, src_station);
            pstm.setString(4, dest_station);
            pstm.setString(5, class_type);
            pstm.setString(7, user_id);
        }
        finally {
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
            throws ServletException, IOException
    {
        try {
            processRequest(request, response);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(realbooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            Logger.getLogger(realbooking.class.getName()).log(Level.SEVERE, null, ex);
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
        catch (ClassNotFoundException ex) {
            Logger.getLogger(realbooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            Logger.getLogger(realbooking.class.getName()).log(Level.SEVERE, null, ex);
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
