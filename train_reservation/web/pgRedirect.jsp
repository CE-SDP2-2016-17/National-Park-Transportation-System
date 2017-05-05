<%@page import="Beans.Avail_Trains"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%@page import="Beans.PaytmConstants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.paytm.merchant.CheckSumServiceHelper"%>    
<%  

    Random randomGenerator = new Random();
	int randomInt = randomGenerator.nextInt(1000000),price=0;
           String randomint=Integer.toString(randomInt);
        String trno=(String)request.getParameter("train_no");
            session.setAttribute("train_no",trno);
                        session.setAttribute("order_id",randomint);

            int train_no=Integer.parseInt(trno);
            
                     String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");
        Class.forName(DB_Driver);           Connection con = null;
int fcm=0,scm=0,gcm=0,max=0,min=0;
            con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password);
            String class_type=(String)request.getParameter("class_type");
            session.setAttribute("class_type",class_type);
                     String username = request.getSession().getAttribute("username").toString();
                      String query8 = "select seats_fc,seats_sc,seats_gc from trains where train_no=?";
           PreparedStatement pstm8 = con.prepareStatement(query8,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
pstm8.setInt(1,train_no);
ResultSet rs8 = pstm8.executeQuery();
                        while (rs8.next()) {
                                    fcm = rs8.getInt(1);
                scm = rs8.getInt(2);
                                gcm = rs8.getInt(3);}
                       
                        pstm8.close();rs8.close();
                                 session.setAttribute("fcm",fcm);
            session.setAttribute("scm",scm);
            session.setAttribute("gcm",gcm); switch (class_type) {
                    case "seats_fc":
                        max=fcm;
                        break;
                    case "seats_sc":
 max=scm;                        break;
                    case "seats_gc":
 max=gcm;                        break;

                }   
            

                     ArrayList<Avail_Trains> available1= (ArrayList<Avail_Trains>)request.getSession().getAttribute("available");
                     Iterator<Avail_Trains> it = available1.iterator();
                      while (it.hasNext()) {
                 Avail_Trains av=it.next();
                  if(av.getTrain_no()==train_no)
                  {
    int fcprice = Integer.parseInt(request.getSession().getAttribute("fcprice").toString());
                        int scprice = Integer.parseInt(request.getSession().getAttribute("scprice").toString());
                        int gcprice = Integer.parseInt(request.getSession().getAttribute("gcprice").toString());
                         switch (class_type) {
                    case "seats_fc":
                        price = fcprice;
                                                min=av.getFc() ;

                        break;
                    case "seats_sc":
                        price = scprice;
                                                min=av.getSc() ;

                        break;
                    case "seats_gc":
                        price = gcprice;
                                                min=av.getGc() ;

                        break;

                }
                     session.setAttribute("price",price);
                  }}
                                  session.setAttribute("max",max);
            session.setAttribute("min",min);
            if(min>-(max/2))
            {
            
TreeMap<String,String> parameters = new TreeMap<String,String>();
String paytmChecksum =  "";

parameters.put("ORDER_ID",randomint);
parameters.put("CUST_ID",username);
parameters.put("INDUSTRY_TYPE_ID","Retail");
parameters.put("CHANNEL_ID","WEB");
parameters.put("TXN_AMOUNT",Integer.toString(price));

parameters.put("MID",PaytmConstants.MID);
parameters.put("CHANNEL_ID",PaytmConstants.CHANNEL_ID);
parameters.put("INDUSTRY_TYPE_ID",PaytmConstants.INDUSTRY_TYPE_ID);
parameters.put("WEBSITE",PaytmConstants.WEBSITE);
parameters.put("MOBILE_NO","9876543210");
parameters.put("EMAIL","test@gmail.com");
parameters.put("CALLBACK_URL", "https://nationalpark.southcentralus.cloudapp.azure.com/pgResponse.jsp");
String checkSum =  CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(PaytmConstants.MERCHANT_KEY, parameters);
StringBuilder outputHtml = new StringBuilder();
outputHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
outputHtml.append("<html>");
outputHtml.append("<head>");
outputHtml.append("<title>Merchant Check Out Page</title>");
outputHtml.append("</head>");
outputHtml.append("<body>");
outputHtml.append("<center><h1>Please do not refresh this page...</h1></center>");
outputHtml.append("<form method='post' action='"+ PaytmConstants.PAYTM_URL +"' name='f1'>");
outputHtml.append("<table border='1'>");
outputHtml.append("<tbody>");
for(Map.Entry<String,String> entry : parameters.entrySet()) {
	String key = entry.getKey();
	String value = entry.getValue();
	outputHtml.append("<input type='hidden' name='"+key+"' value='" +value+"'>");	
}	  
	  
outputHtml.append("<input type='hidden' name='CHECKSUMHASH' value='"+checkSum+"'>");
outputHtml.append("</tbody>");
outputHtml.append("</table>");
outputHtml.append("<script type='text/javascript'>");
outputHtml.append("document.f1.submit();");
outputHtml.append("</script>");
outputHtml.append("</form>");
outputHtml.append("</body>");
outputHtml.append("</html>");
out.println(outputHtml);
            }
%>