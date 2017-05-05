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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author Smit
 */
@WebServlet(urlPatterns = {"/addst_ro"})
public class addst_ro extends HttpServlet {

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
        /*
         HttpSession session = request.getSession(false);
         if (session == null)
         {
         RequestDispatcher rd = request.getRequestDispatcher("adminhome.jsp");
         rd.forward(request, response);
         }
         else
         {

         }
         */
        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");
        String pos = request.getParameter("pos");
        String ro_id = request.getParameter("ro_id");
        String st_name = request.getParameter("st_name");
int p,r;
        Connection con = null;
        try
        {
            Class.forName(DB_Driver);
            con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            //ResultSet rs;
            //The query can be update query or can be select query
            /*
             String query1 = "select * from station where Name = ?";
             pstm = con.prepareStatement(query1);

             pstm.setString(1, Name);
             rs = pstm.executeQuery();
             if (rs.next())
             {
             boolean b = true;

             session.setAttribute("notavailable", b);
             response.sendRedirect("/addstation");
             }*/
            
            
            r=Integer.parseInt(ro_id);
            p=Integer.parseInt(pos);
            String query = "select * from ro_st where route_id = ? order by pos";
           PreparedStatement stmt=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
          stmt.setInt(1,r);
            ResultSet rs=stmt.executeQuery();
            int i=1;
                    while(rs.next())
                    {
                            if(i>=p)
                            {
                            rs.updateInt("pos",i+1);
                            rs.updateRow();
                            }
                                
                                i++;
                    }
               String query1 ="insert into ro_st(route_id,st_name,pos) values(?,?,?)";
               
                stmt=con.prepareStatement(query1);
             stmt.setInt(1,r);
                          stmt.setString(2,st_name);
             stmt.setInt(3,p);

             
            stmt.executeUpdate();
            response.sendRedirect("/train_reservation/adminhome.jsp");
        }
        catch (ClassNotFoundException ex)
        {
            out.println(ex);
        }
        catch (SQLException ex)
        {
            out.println(ex);
        }
        finally
        {
            try
            {
                if (con != null)
                {
                    con.close();
                }
            }
            catch (Exception ex)
            {
                out.println(ex);
            }
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
