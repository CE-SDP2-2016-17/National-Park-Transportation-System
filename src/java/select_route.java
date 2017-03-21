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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell pc
 */
public class select_route extends HttpServlet
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

        int train_no = Integer.parseInt(request.getParameter("train_number"));
        String train_name = request.getParameter("train_name");
        String route = request.getParameter("route");

        HttpSession session = request.getSession();
        session.setAttribute("train_no", train_no);
        session.setAttribute("train_name", train_name);
        session.setAttribute("route_name", route);

        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");

        Connection connection = null;
        ArrayList<String> stationArrayList = new ArrayList<String>();
        ArrayList<Integer> kilometersArrayList = new ArrayList<Integer>();

        try
        {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement preparedStatement;
            ResultSet resultSet;

            String query = "select stations,kilometers from route where route_name = ? order by kilometers";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, route);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                stationArrayList.add(resultSet.getString("stations"));
                kilometersArrayList.add(resultSet.getInt("kilometers"));
            }
            session.setAttribute("stations", stationArrayList);
            session.setAttribute("kilometers", kilometersArrayList);
            response.sendRedirect(request.getContextPath() + "/select_stops.jsp");

            resultSet.close();
            preparedStatement.close();
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
