/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Beans.PNR;
import Beans.PaytmConstants;
import com.paytm.merchant.CheckSumServiceHelper;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.css.StyleOrigin.USER_AGENT;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author dell pc
 */
public class canceltickets extends HttpServlet
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
            throws ServletException, IOException, Exception
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int pnr = Integer.parseInt(request.getParameter("pnr")),wmin=0;
        HttpSession session = request.getSession();

        String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");

        Connection connection = null;
        int src_km=0,dest_km=0,fc=0,sc=0,gc=0;String stat=null;

        try
        {
            
           ArrayList<PNR> pnr1= (ArrayList<PNR>)request.getSession().getAttribute("booked_pnrs");
                     Iterator<PNR> it = pnr1.iterator();
                      while (it.hasNext()) {
                                           PNR pn=it.next();
                                           if(pn.getPnr()==pnr){
                                               String status=pn.getStatus();
                                               int s_no=pn.getSeat_no();
                                               String date_select=pn.getDate();
                                               int train_no=pn.getTrain_no();
                                               String class_type=pn.getS_type();
                                               String src_station=pn.getSrc();
                                               String dest_station=pn.getDest();
                                               String order_id=pn.getOrder_id();
                                               int price=pn.getPrice();
                                               String txn_id=pn.getTxn_id();
                                               

            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            PreparedStatement preparedStatement;
            
   String query1 = "select a.kilometers,b.kilometers from schedule a,schedule b where a.train_no=? and b.train_no=? and a.stops=? and b.stops=?";
      PreparedStatement pstm1 = connection.prepareStatement(query1,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
            String query17 = "select seat_no,booking_id from schedule a,schedule b,booking where a.train_no=b.train_no and a.train_no=booking.train_no and a.stops=booking.src_code and b.stops=booking.dest_code and date=? and a.train_no=? and class=? and a.kilometers>=? and b.kilometers<=? and status=?";
         PreparedStatement pstm17 = connection.prepareStatement(query17,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
pstm17.setString(1,date_select);
pstm17.setInt(2,train_no);
pstm17.setString(3,class_type);
pstm17.setInt(4,src_km);

pstm17.setInt(5,dest_km);

    pstm17.setString(6,"w");

ResultSet rs17 = pstm17.executeQuery();

       String query = "delete from booking where booking_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,pnr);




            int result = preparedStatement.executeUpdate();
                      
                      
                       if(                     "Confirm".equals(status))
            {
                wmin=1;
                while(rs17.next()){
                    if(rs17.getInt(1)==1){
                       int tem1= rs17.getInt(2);
                String query2 = "update booking set status=?,seat_no=? where booking_id =?";
            preparedStatement = connection.prepareStatement(query2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            preparedStatement.setString(1,"c");
                        preparedStatement.setInt(2,s_no);
            preparedStatement.setInt(3,tem1);

             preparedStatement.executeUpdate();

                }

            }rs17.beforeFirst();
            }
            else
            {
                
                wmin=s_no;
            }
            while(rs17.next())
            {
                if(rs17.getInt(1)>wmin)
                {
                       int tem2= rs17.getInt(1)-1 ;   
                String query3 = "update booking set seat_no=? where booking_id = ?  ";
            preparedStatement = connection.prepareStatement(query3,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        preparedStatement.setInt(1,tem2);
            preparedStatement.setInt(2,rs17.getInt(2));

             preparedStatement.executeUpdate();           
                     

                }
            }
                      
               String query8="select stops from schedule where kilometers>=? and kilometers<? and train_no=?";
      PreparedStatement pstm2 = connection.prepareStatement(query8,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstm2.setInt(1,src_km); 
                        pstm2.setInt(2,dest_km);     
            pstm2.setInt(3,train_no); 
ResultSet rs2 = pstm2.executeQuery();
while(rs2.next())
{
    stat=rs2.getString(1);
        String query5="select seats_fc,seats_sc,seats_gc from capacity where train_no=? and stations=? and date=?";
      PreparedStatement pstm3 = connection.prepareStatement(query5,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        pstm3.setInt(1,train_no);
         pstm3.setString(2,stat);
         pstm3.setString(3,date_select);
         ResultSet rs3 = pstm3.executeQuery();
          
      rs3.next();
              switch (class_type) {
                    case "seats_fc":
                        fc=rs3.getInt(1);
                        break;
                    case "seats_sc":
 sc=rs3.getInt(2);                        break;
                    case "seats_gc":
 gc=rs3.getInt(3);                        break;

                }
         
         
         switch (class_type) {
                    case "seats_fc":
 String query11 = "update capacity set seats_fc = ? where date = ? and stations=? and train_no=?" ;
                          PreparedStatement pstm11 =  connection.prepareStatement(query11,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      fc=fc+1;
                          pstm11.setInt(1,fc);
                    pstm11.setString(2,date_select);
                                        pstm11.setString(3,stat);

                    pstm11.setInt(4,train_no); pstm11.executeUpdate();
         
        pstm11.close();
                                            break;
                    case "seats_sc":
   String query12 = "update capacity set seats_sc = ? where date = ? and stations=? and train_no=?" ;
                          PreparedStatement pstm12 =  connection.prepareStatement(query12,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      sc=sc+1;
                          pstm12.setInt(1,sc);
                    pstm12.setString(2,date_select);
                                        pstm12.setString(3,stat);

                    pstm12.setInt(4,train_no); pstm12.executeUpdate();
         
        pstm12.close();
                     break;
                    case "seats_gc":
   String query13 = "update capacity set seats_gc = ? where date = ? and stations=? and train_no=?" ;
                          PreparedStatement pstm13 =  connection.prepareStatement(query13,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
      gc=gc+1;
                          pstm13.setInt(1,gc);
                    pstm13.setString(2,date_select);
                                        pstm13.setString(3,stat);

                    pstm13.setInt(4,train_no); pstm13.executeUpdate();
         
        pstm13.close();
                   break;

                }rs3.close();
         
          


                }pstm2.close();
            rs2.close();
            HttpURLConnection connect = null;
  Random randomGenerator = new Random();
int randomInt = randomGenerator.nextInt(1000000);
           String randomint=Integer.toString(randomInt);
         
                   String targetURL="https://pguat.paytm.com/oltp/HANDLER_INTERNAL/REFUND";   

                // CALL GetText method to make post method call
                targetURL=targetURL.concat("?JsonData=");
                String MID=PaytmConstants.MID;
                price= (int) (price-(0.2*price));
String ORDERID=order_id;
String TXNID=txn_id;
String REFUNDAMOUNT=Integer.toString(price);
String TXNTYPE="REFUND";
String REFID=randomint;

//String CHECKSUMHASH="";
String CHECKSUM="";
TreeMap paramMap=new TreeMap();
paramMap.put("MID",MID);
paramMap.put("ORDERID",ORDERID);
paramMap.put("TXNID",TXNID);
paramMap.put("REFUNDAMOUNT",REFUNDAMOUNT);
paramMap.put("TXNTYPE",TXNTYPE);

try{
CHECKSUM=CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(PaytmConstants.MERCHANT_KEY,paramMap);
}
catch(Exception e)
{
}

JSONObject jsonRequestObj = new JSONObject();
jsonRequestObj.put("MID",MID);
jsonRequestObj.put("ORDERID", ORDERID);
jsonRequestObj.put("TXNID", TXNID);
jsonRequestObj.put("REFUNDAMOUNT", REFUNDAMOUNT);
jsonRequestObj.put("TXNTYPE", TXNTYPE);

jsonRequestObj.put("CHECKSUM", CHECKSUM);

jsonRequestObj.put("REFID", REFID);

String targetURL1 = targetURL.concat(jsonRequestObj.toString());


URL url = new URL(targetURL1);

connect = (HttpURLConnection)url.openConnection();
connect.setRequestMethod("POST");
String urlParameters=jsonRequestObj.toString();

connect.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));

connect.setUseCaches(false);
connect.setDoOutput(true);

//Send request
DataOutputStream wr = new DataOutputStream (connect.getOutputStream());
wr.writeBytes(urlParameters);
wr.close();

            
   InputStream is = connect.getInputStream();
BufferedReader rd = new BufferedReader(new InputStreamReader(is));
StringBuilder resp = new StringBuilder(); // or StringBuffer if not Java 5+
String line= new String("hello");   

while((line = rd.readLine()) != null) {
resp.append(line);
resp.append('\r');
}
rd.close();         
String res=resp.toString();
session.setAttribute("res",res);
        if (result >= 1)
            {
                //out.println("success");
                session.setAttribute("issuccess", "Cancellation Successful");
            }
            else
            {
                //out.println("not success");
                session.setAttribute("issuccess", "Cancellation Successful");
            }

       
                                           }
                      }
        
        }
                      
        catch (ClassNotFoundException | SQLException e)
        {
            out.println(e);
        }
        response.sendRedirect(request.getContextPath() + "/userhome.jsp");
        
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
        } catch (Exception ex) {
            Logger.getLogger(canceltickets.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(canceltickets.class.getName()).log(Level.SEVERE, null, ex);
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