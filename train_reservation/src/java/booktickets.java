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
import java.text.DateFormat;
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
 String date_select=(String)request.getParameter("date_select");
            session.setAttribute("date_select",date_select);
             int scprice=0,fcprice=0,gcprice=0;
                       

        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");

        ArrayList<Avail_Trains> available1 = new ArrayList<Avail_Trains>();


   int src_km=0,dest_km=0,fc=1000,sc=1000,gc=1000;
String day = null;
String stat=null;

 

        try
        {   SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
        Date date = null,currentDatePlusOne = null,cdp=null;
        try {
            date = dateformat.parse(date_select);
            
            DateFormat dayFormate=new SimpleDateFormat("EEEE"); 
            day=dayFormate.format(date);
               Date currentdate = new Date();
 Calendar c = Calendar.getInstance();
  c.setTime(currentdate);

 c.add(Calendar.MONTH, 1);
 currentDatePlusOne = c.getTime();
   c.setTime(currentdate);
   c.roll(Calendar.DATE,-1);
 cdp = c.getTime();

Date cur = new Date();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(date.before(currentDatePlusOne)&& date.after(cdp))
        {
            String query = "select a.train_no,c.name,a.dept_time,b.arrival_time from schedule a,schedule b,trains c,running_days r where a.stops=? and b.stops=? and a.kilometers < b.kilometers and a.train_no = b.train_no and a.train_no=c.train_no and a.train_no=r.train_no and  r.running_days=? ";
            Class.forName(DB_Driver);
     
  
             
       


             
            Connection con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
      PreparedStatement pstm = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstm.setString(1, src_station);
            pstm.setString(2, dest_station);
                        pstm.setString(3, day); 

            ResultSet rs = pstm.executeQuery();
            while (rs.next())
            {
                    fc=1000;sc=1000;gc=1000;
                int train_no = rs.getInt("train_no");
                String name = rs.getString("name");
                String arrival_time = rs.getString("arrival_time");
                String dept_time = rs.getString("dept_time");
                
                
                
                
               
                 String query1 = "select a.kilometers,b.kilometers from schedule a,schedule b where a.train_no=? and b.train_no=? and a.stops=? and b.stops=?";
      PreparedStatement pstm1 = con.prepareStatement(query1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstm1.setInt(1, train_no);
            pstm1.setInt(2, train_no);
            pstm1.setString(3, src_station);
            pstm1.setString(4, dest_station);
            ResultSet rs1 = pstm1.executeQuery();
            while (rs1.next()) {
                src_km = rs1.getInt(1);
                dest_km = rs1.getInt(2);}
            pstm1.close();
            rs1.close();
            int tot_km=dest_km-src_km;
             fcprice=tot_km*3;
             scprice=tot_km*2;
             gcprice=tot_km*1;
                session.setAttribute("fcprice",fcprice);
                                  session.setAttribute("scprice",scprice);
                        session.setAttribute("gcprice",gcprice);  
                
                
                
                
                
                    
                String query2="select stops from schedule where kilometers>=? and kilometers<=? and train_no=?";
      PreparedStatement pstm2 = con.prepareStatement(query2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstm2.setInt(1,src_km); 
                        pstm2.setInt(2,dest_km);     
            pstm2.setInt(3,train_no); 
ResultSet rs2 = pstm2.executeQuery();
while(rs2.next())
{
    stat=rs2.getString(1);
       String query3="select seats_fc,seats_sc,seats_gc from capacity where train_no=? and stations=? and date=?";
      PreparedStatement pstm3 = con.prepareStatement(query3,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        pstm3.setInt(1,train_no);
         pstm3.setString(2,stat);
         pstm3.setString(3,date_select);
         ResultSet rs3 = pstm3.executeQuery();
         if(rs3.next()==false)
         {
             String query4="select seats_fc,seats_sc,seats_gc from trains where train_no=?";
      PreparedStatement pstm4 = con.prepareStatement(query4,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        pstm4.setInt(1,train_no);
        ResultSet rs4 = pstm4.executeQuery();
        while(rs4.next()){
             if(fc>rs4.getInt(1))
             fc=rs4.getInt(1);
               if(sc>rs4.getInt(2))
                          sc=rs4.getInt(2);
               if(gc>rs4.getInt(3))
             gc=rs4.getInt(3);
        }pstm4.close();
                    rs4.close();


         }
         else
         {
            do
        
         {
               if(fc>rs3.getInt(1))
             fc=rs3.getInt(1);
               if(sc>rs3.getInt(2))
                          sc=rs3.getInt(2);
               if(gc>rs3.getInt(3))
             gc=rs3.getInt(3);

         } while(rs3.next());
         }pstm3.close();
                     rs3.close();

         

}pstm2.close();
            rs2.close();


//                String j_date=rs.getString("j_date").toString();
                //out.println(name);
                //out.println(train_no);
                //out.println(arrival_time);
                //out.println(dept_time);
                //out.println("<br/>");
                Avail_Trains avail_Trains = new Avail_Trains(train_no, name, arrival_time, dept_time,fc,sc,gc,fcprice,scprice,gcprice);
                available1.add(avail_Trains);
            }

            session.setAttribute("available", available1);
            //Avail_Trains aa = (Avail_Trains) session.getAttribute("available");
            //out.println(session.getAttribute("available"));
            con.close();
            pstm.close();
            rs.close();
            response.sendRedirect(request.getContextPath() + "/available.jsp");
        }
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
