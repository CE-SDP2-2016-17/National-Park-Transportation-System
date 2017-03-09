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
public class addroute_db extends HttpServlet
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

        HttpSession session = request.getSession(false);
        if (session == null)
        {
            response.sendRedirect("/NationalPark/index.jsp");
        }
        PrintWriter out = response.getWriter();

        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");
        String route_name = session.getAttribute("route_name").toString();
        String src_station = session.getAttribute("src_station").toString();
        int no_station = Integer.parseInt(session.getAttribute("no_station").toString());

        int max_km = 0;

        ArrayList<String> stationArrayList = new ArrayList<String>();
        ArrayList<Integer> kmArrayList = new ArrayList<Integer>();
        stationArrayList.add(new String(src_station));
        kmArrayList.add(0);

        Integer i;
        for (i = 1; i <= (no_station - 1); i++)
        {
            String station = request.getParameter("station_" + i.toString());
            int kms = Integer.parseInt(request.getParameter("km_" + i.toString()));

            if (stationArrayList.contains(station) || kms <= max_km)
            {
                session.setAttribute("error", "Error: station name repeated !!!");
                response.sendRedirect(request.getContextPath() + "/addroute_db.jsp");
            }
            else
            {
                stationArrayList.add(station);
                kmArrayList.add(kms);
                max_km = kms;
            }
        }

        Connection connection = null;

        try
        {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement preparedStatement;
            connection.setAutoCommit(false);

            String query = "insert into route(route_name,stations,kilometers) values(?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            for (i = 0; i < stationArrayList.size(); i++)
            {
                preparedStatement.setString(1, route_name);
                preparedStatement.setString(2, stationArrayList.get(i));
                preparedStatement.setInt(3, kmArrayList.get(i));
                preparedStatement.addBatch();
            }

            int count[] = preparedStatement.executeBatch();
            connection.commit();

            out.println("<!DOCTYPE html>");
            out.println("<jsp:include page=\"style.jsp\"/>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Train Reservation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"d\">");
            out.println("<table>");

            for (i = 0; i < count.length; i++)
            {
                out.print("<tr>");
                out.print("<td>");
                out.println("Query " + i + " has effected " + count[i] + " rows");
                out.print("</td>");
                out.print("</tr>");
            }

            out.println("</table>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
