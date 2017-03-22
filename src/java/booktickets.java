/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Beans.Avail_Trains;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell pc
 */
public class booktickets extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String src_station = request.getParameter("src_station");
        String dest_station = request.getParameter("dest_station");
        session.setAttribute("src_station", src_station);
        session.setAttribute("dest_station", dest_station);

        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");

        ArrayList<Avail_Trains> available1 = new ArrayList<Avail_Trains>();

        try
        {
            String query = "select a.train_no,c.name,a.dept_time,b.arrival_time from schedule a,schedule b,trains c where a.stops=? and b.stops=? and a.kilometers < b.kilometers and a.train_no = b.train_no and a.train_no=c.train_no";
            Class.forName(DB_Driver);
            Connection con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, src_station);
            stmt.setString(2, dest_station);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                int train_no = rs.getInt("train_no");
                String name = rs.getString(2);
                String arrival_time = rs.getString("arrival_time");
                String dept_time = rs.getString("dept_time");
//                String j_date=rs.getString("j_date").toString();
                //out.println(name);
                //out.println(train_no);
                //out.println(arrival_time);
                //out.println(dept_time);
                //out.println("<br/>");
                Avail_Trains avail_Trains = new Avail_Trains(train_no, name, arrival_time, dept_time);
                available1.add(avail_Trains);
            }

            session.setAttribute("available", available1);
            //Avail_Trains aa = (Avail_Trains) session.getAttribute("available");
            //out.println(session.getAttribute("available"));
            con.close();
            stmt.close();
            rs.close();
            response.sendRedirect(request.getContextPath() + "/available.jsp");
        }
        catch (ClassNotFoundException ex)
        {
            out.println(ex);
        }
        catch (SQLException e)
        {
            out.println(e);
        }
        finally
        {
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
            throws ServletException, IOException
    {
        processRequest(request, response);
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
