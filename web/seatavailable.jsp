<%-- 
    Document   : seatavailable
    Created on : Mar 12, 2017, 3:18:03 PM
    Author     : sagar
--%>

<%@page import="java.lang.String"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seat Available</title>
    </head>
    <body>
        <form id="form4" action="" method="post">
            <table>
                <tr>
                    Train No:
                </tr>
                <tr>
                             <% 
                            //String trno=request.getParameter("train_no");
                            //request.getSession().setAttribute("train_no",trno);
                            String trno=request.getSession().getAttribute("train_no").toString();
                            out.println(trno);

                            //out.println(trno);
                        %>
                </tr>
                <tr>
                    <td>
                    Date of Journey
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <%
                            // String datesel=request.getParameter("date_select");
                            //request.getSession().setAttribute("date_select",datesel);
                            String datesel=request.getSession().getAttribute("date_select").toString();
                            out.println(datesel);

                            //out.println(datesel);
                        %>
                        
                    </td>
                </tr>
                <tr>
                    <td>From Station</td>
                    <td>
                             <% 
                                String src=request.getSession().getAttribute("src_station").toString();
                                out.println(src);
                        %>
                    </td>
                </tr>

                        <tr>
                            <td>No of seats in ${sessionScope.class_type} Class</td>
                    <td>
                        <% 
                            try
                            {
                                String src_station=request.getParameter("src_station");
                                String class_type=request.getParameter("class_type");
                                
                                String DB_Driver = getServletContext().getInitParameter("DB_Driver");                    
                                String DB_Con = getServletContext().getInitParameter("DB_Con");
                                String DB_Uname = getServletContext().getInitParameter("DB_Uname");
                                String DB_Password = getServletContext().getInitParameter("DB_Password");
                                Class.forName(DB_Driver);
                                Connection con=DriverManager.getConnection(DB_Con,DB_Uname,DB_Password);

                                if("seats_fc".equals(class_type.toString()))
                                {
                                    String query=" select seats_fc from capacity where train_no=? and date=? and stations=?" ;
                                    PreparedStatement stmt=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                    stmt.setInt(1,Integer.parseInt(trno));
                                    //stmt.setString(2,src);
                                    stmt.setString(2,datesel);
                                    stmt.setString(3,src);
                                    ResultSet rs=stmt.executeQuery();   
                                    while(rs.next())
                                    {
                                    %>
                                    <tr>
                                        <td><%= rs.getInt("seats_fc")%></td>
                                    </tr>
                                    <%
                                    }

                                }

                                if("seats_sc".equals(class_type.toString()))
                                {
                                    String query=" select seats_sc from capacity where train_no=? and date=? and stations=?" ;
                                    PreparedStatement stmt=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                    stmt.setInt(1,Integer.parseInt(trno));
                                    //stmt.setString(2,src);
                                    stmt.setString(2,datesel);
                                    stmt.setString(3,src);
                                    ResultSet rs=stmt.executeQuery();   
                                    while(rs.next())
                                    {
                                    %>
                                    <tr>
                                        <td><%= rs.getInt("seats_sc")%></td>
                                    </tr>
                                    <%
                                    }

                                }
                                if("seats_tc".equals(class_type.toString()))
                                {
                                    String query=" select seats_tc from capacity where train_no=? and date=? and stations=?" ;
                                    PreparedStatement stmt=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                    stmt.setInt(1,Integer.parseInt(trno));
                                    //stmt.setString(2,src);
                                    stmt.setString(2,datesel);
                                    stmt.setString(3,src);
                                    ResultSet rs=stmt.executeQuery();   
                                    while(rs.next())
                                    {
                                    %>
                                    <tr>
                                        <td><%= rs.getInt("seats_tc")%></td>
                                    </tr>
                                    <%
                                    }

                                }

                                String query=" select seats_fc,seats_sc,seats_tc from capacity where train_no=? and date=?" ;
                                PreparedStatement stmt=con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                //stmt.setString(1,class_type);
                                stmt.setInt(1,Integer.parseInt(trno));
                                //stmt.setString(2,src);
                                stmt.setString(2,datesel);
                                
                            ResultSet rs=stmt.executeQuery();   
                            while(rs.next())
                            {
                            %>
                            <tr>
                                <%--<td><%= rs.getInt("seats_fc")%></td>--%>
                            </tr>
                            <%
                            }
                            rs.close();
                            stmt.close();
                            con.close();
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                    %>
                    </td>

                </tr>
            </table>
        </form>
    </body>
</html>
