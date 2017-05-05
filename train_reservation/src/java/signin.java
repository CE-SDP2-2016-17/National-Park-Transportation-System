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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell pc
 */
public class signin extends HttpServlet
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
String username = request.getRemoteUser();
       
        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");
String role_name=null;
      Connection con;
            try
            {
                Class.forName(DB_Driver);
                con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
                PreparedStatement ps = con.prepareStatement("select role_name,Email from user_roles,customer where user_roles.Username=customer.Username and user_roles.Username=?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    role_name= rs.getString(1);
                     String email_add= rs.getString(2);
                                         session.setAttribute("email_add",email_add);

                   

                }
                 if(role_name.equals("user"))
                    response.sendRedirect(request.getContextPath() + "/userhome.jsp");
                    else
                         response.sendRedirect(request.getContextPath() + "/adminhome.jsp");
               
            }
            catch (ClassNotFoundException e)
            {
                out.println(e);

            }
            catch (SQLException e)
            {
                out.println(e);
            }
            catch (IOException e)
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
