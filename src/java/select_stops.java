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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell pc
 */
public class select_stops extends HttpServlet
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
        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");

        String[] stops = request.getParameterValues("stops");
        String[] running_days = request.getParameterValues("running_days");
        int train_no = Integer.parseInt(session.getAttribute("train_no").toString());

        session.setAttribute("stops", stops);

        Connection connection = null;

        try
        {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement preparedStatement;

            String query = "insert into running_days(train_no,running_days) values(?,?)";
            preparedStatement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            for (int i = 0; i < running_days.length; i++)
            {
                preparedStatement.setInt(1, train_no);
                preparedStatement.setString(2, running_days[i]);
                preparedStatement.addBatch();
            }
            int count[] = preparedStatement.executeBatch();
            connection.commit();

            response.sendRedirect(request.getContextPath() + "/addtrain_db.jsp");
            preparedStatement.close();
        }
        catch (ClassNotFoundException e)
        {
            try
            {
                connection.rollback();
            }
            catch (SQLException ex)
            {
                out.println(ex);
            }

            out.println(e);
        }
        catch (SQLException e)
        {
            try
            {
                connection.rollback();
            }
            catch (SQLException ex)
            {
                out.println(ex);
            }
            out.println(e);
        }
        finally
        {
            out.close();
            try
            {
                connection.close();
            }
            catch (SQLException ex)
            {
                out.println(ex);
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
