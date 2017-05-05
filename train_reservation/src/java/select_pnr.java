/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Beans.PNR;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class select_pnr extends HttpServlet
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
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");

        Connection con = null;
        try
        {
            Class.forName(DB_Driver);
            con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement preparedStatement;

            String query = "select booking_id,date,train_no,src_code,dest_code,class,seat_no,status,order_id,price,txn_id from booking where username=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, session.getAttribute("username").toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<PNR> bookings = new ArrayList<>();
            Date curDate = new Date();
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(curDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (resultSet.next())
            {
                int pnr = resultSet.getInt(1);
                String date = resultSet.getString(2);

                Date d = sdf.parse(date);
                cal2.setTime(d);

                if (cal2.after(cal1))
                {
                    PNR pnrs = new PNR();

                    pnrs.setPnr(pnr);
                    pnrs.setDate(date);
                    pnrs.setTrain_no(resultSet.getInt(3));
                    pnrs.setSrc(resultSet.getString(4));
                    pnrs.setDest(resultSet.getString(5));
                    String s_type = resultSet.getString(6);

                    switch (s_type)
                    {
                        case "seats_fc":
                            pnrs.setS_type("seats_fc");
                            break;
                        case "seats_sc":
                            pnrs.setS_type("seats_sc");
                            break;
                        case "seats_gc":
                            pnrs.setS_type("seats_gc");
                            break;
                    }
                    pnrs.setSeat_no(resultSet.getInt(7));
                    String status = resultSet.getString(8);
                    String order_id = resultSet.getString(9);
                                                pnrs.setOrder_id(order_id);
                                                 int price = resultSet.getInt(10);
                                                pnrs.setPrice(price);
                    String txn_id = resultSet.getString(11);
                                                                    pnrs.setTxn_id(txn_id);


                    switch (status)
                    {
                        case "c":
                            pnrs.setStatus("Confirm");
                            break;

                        case "w":
                            pnrs.setStatus("Waitlist");
                            break;
                    }

                    bookings.add(pnrs);
                }
            }
            session.setAttribute("booked_pnrs", bookings);

        }
        catch (ClassNotFoundException | SQLException | ParseException e)
        {
            out.println(e);
        }
        response.sendRedirect(request.getContextPath() + "/select_pnr_to_cancel.jsp");
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
