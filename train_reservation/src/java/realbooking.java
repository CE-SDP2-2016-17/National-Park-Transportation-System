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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sagar
 */
@WebServlet(urlPatterns = {"/realbooking"})
public class realbooking extends HttpServlet
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
            throws ServletException, IOException, ClassNotFoundException, SQLException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String date_select = request.getSession().getAttribute("date_select").toString();
        String src_station = request.getSession().getAttribute("src_station").toString();
        String dest_station = request.getSession().getAttribute("dest_station").toString();
                String order_id = request.getSession().getAttribute("order_id").toString();
                String txn_id = request.getSession().getAttribute("txn_id").toString();

                String trno = request.getSession().getAttribute("train_no").toString();
        String class_type = request.getSession().getAttribute("class_type").toString();
        String price = request.getSession().getAttribute("price").toString();
                             int iprice=Integer.parseInt(price);
        String fcms = request.getSession().getAttribute("fcm").toString();
        String scms = request.getSession().getAttribute("scm").toString();
        String gcms = request.getSession().getAttribute("gcm").toString();
            int fcm=Integer.parseInt(fcms);
            int scm=Integer.parseInt(scms);
            int gcm=Integer.parseInt(gcms);
        String mins = request.getSession().getAttribute("min").toString();
            int min=Integer.parseInt(mins);
   String maxs = request.getSession().getAttribute("max").toString();
            int max=Integer.parseInt(maxs);

            String username = request.getSession().getAttribute("username").toString();
                       

       
        
            int train_no=Integer.parseInt(trno);
 int src_km=0,dest_km=0,fc=0,sc=0,gc=0,seat_no=0,temp=0;
String day = null;
String stat=null;           
            //response.sendRedirect("/train_reservation/seatavailable.jsp");
          

               ArrayList<Avail_Trains> available1= (ArrayList<Avail_Trains>)request.getSession().getAttribute("available");

        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");
        Connection con = null;
         
          
           
        
       

        try {
            
         
               
           
                 
               
               Class.forName(DB_Driver);   
            con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            
      
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
            
            
            
                
                String query2="select stops from schedule where kilometers>=? and kilometers<? and train_no=?";
      PreparedStatement pstm2 = con.prepareStatement(query2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstm2.setInt(1,src_km); 
                        pstm2.setInt(2,dest_km);     
            pstm2.setInt(3,train_no); 
ResultSet rs2 = pstm2.executeQuery();
while(rs2.next())
{
    fc=fcm;sc=scm;gc=gcm;
    stat=rs2.getString(1);
       String query3="select seats_fc,seats_sc,seats_gc from capacity where train_no=? and stations=? and date=?";
      PreparedStatement pstm3 = con.prepareStatement(query3,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        pstm3.setInt(1,train_no);
         pstm3.setString(2,stat);
         pstm3.setString(3,date_select);
         ResultSet rs3 = pstm3.executeQuery();
          
         if(rs3.next()==false)
         {
           
String query4 = "insert into capacity(train_no,stations,date,seats_fc,seats_sc,seats_gc) values(?,?,?,?,?,?)";      PreparedStatement pstm4 = con.prepareStatement(query4,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        pstm4.setInt(1,train_no);
                pstm4.setString(2,stat);
        pstm4.setString(3,date_select);
        pstm4.setInt(4,fc);
        pstm4.setInt(5,sc);
        pstm4.setInt(6,gc);
         pstm4.executeUpdate();    

       pstm4.close();


         }else{
              switch (class_type) {
                    case "seats_fc":
                        fc=rs3.getInt(1);
                        break;
                    case "seats_sc":
 sc=rs3.getInt(2);                        break;
                    case "seats_gc":
 gc=rs3.getInt(3);                        break;

                }
         }
         
         switch (class_type) {
                    case "seats_fc":
 String query11 = "update capacity set seats_fc = ? where date = ? and stations=? and train_no=?" ;
                          PreparedStatement pstm11 =  con.prepareStatement(query11,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      fc=fc-1;
                          pstm11.setInt(1,fc);
                    pstm11.setString(2,date_select);
                                        pstm11.setString(3,stat);

                    pstm11.setInt(4,train_no); pstm11.executeUpdate();
         
        pstm11.close();
                                            break;
                    case "seats_sc":
   String query12 = "update capacity set seats_sc = ? where date = ? and stations=? and train_no=?" ;
                          PreparedStatement pstm12 =  con.prepareStatement(query12,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      sc=sc-1;
                          pstm12.setInt(1,sc);
                    pstm12.setString(2,date_select);
                                        pstm12.setString(3,stat);

                    pstm12.setInt(4,train_no); pstm12.executeUpdate();
         
        pstm12.close();
                     break;
                    case "seats_gc":
   String query13 = "update capacity set seats_gc = ? where date = ? and stations=? and train_no=?" ;
                          PreparedStatement pstm13 =  con.prepareStatement(query13,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      gc=gc-1;
                          pstm13.setInt(1,gc);
                    pstm13.setString(2,date_select);
                                        pstm13.setString(3,stat);

                    pstm13.setInt(4,train_no); pstm13.executeUpdate();
         
        pstm13.close();
                   break;

                }rs3.close();
         
          

         

}pstm2.close();
            rs2.close();
            
            
            
            
              String query17 = "select seat_no from schedule a,schedule b,booking where a.train_no=b.train_no and a.train_no=booking.train_no and a.stops=booking.src_code and b.stops=booking.dest_code and date=? and a.train_no=? and class=? and a.kilometers>=? and b.kilometers<=? and status=?";
         PreparedStatement pstm17 = con.prepareStatement(query17,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
pstm17.setString(1,date_select);
pstm17.setInt(2,train_no);
pstm17.setString(3,class_type);
pstm17.setInt(4,src_km);

pstm17.setInt(5,dest_km);
if(min<=0)
    pstm17.setString(6,"w");
else{
    pstm17.setString(6,"c");
}


ResultSet rs17 = pstm17.executeQuery();
if(min<=0)max=max/2;
int x=1,ind=0;
while(x<=max)
{
   ind=0;
while(rs17.next())
{
    temp=rs17.getInt(1);
    if(x==temp)
    {
        ind=1;
    }
}if(ind==0){
    seat_no=x;
break;}
x++;rs17.beforeFirst();
}
pstm17.close();rs17.close();
            
            
            
               PreparedStatement pstm16 = null;
            String query16 = "insert into booking(train_no,date,src_code,dest_code,class,status,username,price,seat_no,order_id,txn_id) values(?,?,?,?,?,?,?,?,?,?,?)";
            
            pstm16 = con.prepareStatement(query16);
            pstm16.setInt(1, train_no);
            pstm16.setString(2, date_select);
            pstm16.setString(3, src_station);
            pstm16.setString(4, dest_station);
            pstm16.setString(5, class_type);
            if(min<=0)
  pstm16.setString(6, "w");else{
  pstm16.setString(6, "c");}

            pstm16.setString(7, username);
            pstm16.setInt(8,iprice);
            pstm16.setInt(9,seat_no);
            pstm16.setString(10,order_id);
            pstm16.setString(11,txn_id);


            pstm16.executeUpdate(); 
pstm16.close();
    session.setAttribute("price",price);
                      session.setAttribute("seat_no",seat_no);
 response.sendRedirect(request.getContextPath() + "/email");                    

                  
           
           }
       
          
        catch (ClassNotFoundException e) {
            out.println(e);
        }
        catch (SQLException e) {
            out.println(e);
        }
       finally {
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
        try {
            processRequest(request, response);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(realbooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            Logger.getLogger(realbooking.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(realbooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            Logger.getLogger(realbooking.class.getName()).log(Level.SEVERE, null, ex);
        }
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