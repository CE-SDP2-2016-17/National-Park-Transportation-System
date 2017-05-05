/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell pc
 */
public class addadmin extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.lang.CloneNotSupportedException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");
        String Name = request.getParameter("Name");
        String Email = request.getParameter("Email");
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password1");
        byte[] b = Password.getBytes();

try {
      MessageDigest digest = MessageDigest.getInstance("MD5");
		
		//Update input string in message digest
		digest.update(b);


Password = new BigInteger(1, digest.digest()).toString(16);

}
catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

 

        Connection con = null;
        try
        {

            Class.forName(DB_Driver);
            con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement pstm;
            //The query can be update query or can be select query
            String query = "insert into customer(Name,Email,Username,Password) values(?,?,?,?)";
            pstm = con.prepareStatement(query);
            pstm.setString(1, Name);
            pstm.setString(2, Email);
            pstm.setString(3, Username);
            pstm.setString(4, Password);

            int count = pstm.executeUpdate();
              String query1 = "insert into user_roles(Username,role_name) values(?,?)";
            pstm = con.prepareStatement(query1);
            pstm.setString(1, Username);
            pstm.setString(2, "admin");
            pstm.executeUpdate();
            
           response.sendRedirect(request.getContextPath() + "/adminhome.jsp");
        }
        catch (ClassNotFoundException ex)
        {
            out.println(ex);
        } catch (SQLException ex) {
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
            catch (SQLException ex)
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
