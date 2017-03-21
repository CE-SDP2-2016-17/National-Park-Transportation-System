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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell pc
 */
public class addtrain_db extends HttpServlet
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

        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");

        HttpSession session = request.getSession(false);

        String route = session.getAttribute("route_name").toString();
        int train_no = Integer.parseInt(session.getAttribute("train_no").toString());
        String train_name = session.getAttribute("train_name").toString();
        String[] stops = (String[]) session.getAttribute("stops");

        //ArrayList<String> arrival_time = new ArrayList<String>();
        //ArrayList<String> departure_time = new ArrayList<String>();
        ArrayList<Integer> kilometers = new ArrayList<Integer>();
        ArrayList<String> arrival_time = new ArrayList<String>();
        ArrayList<String> departure_time = new ArrayList<String>();

        for (int i = 0; i < stops.length; i++)
        {
            arrival_time.add(request.getParameter("arrival_" + stops[i]));
            departure_time.add(request.getParameter("departure_" + stops[i]));
        }
        int seats_fc = Integer.parseInt(request.getParameter("seats_fc"));
        int seats_sc = Integer.parseInt(request.getParameter("seats_sc"));
        int seats_tc = Integer.parseInt(request.getParameter("seats_tc"));

        Connection connection = null;
        ResultSet resultSet;

        try
        {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement preparedStatement;

            String query = "SELECT kilometers FROM route WHERE route_name=? and stations IN (";
            for (int i = 0; i < stops.length; i++)
            {
                if (i == (stops.length - 1))
                {
                    query += "'" + stops[i] + "'";
                }
                else
                {
                    query += "'" + stops[i] + "',";
                }
            }

            query += ") ORDER BY kilometers";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, route);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                kilometers.add(resultSet.getInt("kilometers"));
            }

            String query1 = "insert into trains(train_no,name,seats_fc,seats_sc,seats_tc) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, train_no);
            preparedStatement.setString(2, train_name);
            preparedStatement.setInt(3, seats_fc);
            preparedStatement.setInt(4, seats_sc);
            preparedStatement.setInt(5, seats_tc);

            int count = preparedStatement.executeUpdate();

            connection.setAutoCommit(false);
            String query2 = "insert into schedule(train_no,stops,kilometers,arrival_time,dept_time) values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query2);

            for (int i = 0; i < stops.length; i++)
            {
                preparedStatement.setInt(1, train_no);
                preparedStatement.setString(2, stops[i]);
                preparedStatement.setInt(3, kilometers.get(i));
                preparedStatement.setString(4, arrival_time.get(i));
                preparedStatement.setString(5, departure_time.get(i));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dummy</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Success");
            out.println("</body>");
            out.println("</html>");

            preparedStatement.close();
            resultSet.close();
        }
        catch (ClassNotFoundException e)
        {
            try
            {
                connection.rollback();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(addtrain_db.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(addtrain_db.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println(e);
        }

        finally
        {
            out.close();
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
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
