<%-- 
    Document   : ticketbooked
    Created on : Mar 23, 2017, 12:58:19 AM
    Author     : sagar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ticket booked </h1>
        <form action="email" method="post">
            <input type="submit" name="email" value="Get email">
            <% 
                String email_add=request.getSession().getAttribute("email").toString();
                String train_no = request.getSession().getAttribute("train_no").toString();
                String datesel = request.getSession().getAttribute("date_select").toString();
                String src_station = request.getSession().getAttribute("src_station").toString();
                String dest_station = request.getSession().getAttribute("dest_station").toString();
                String class_type = request.getSession().getAttribute("class_type").toString();
                String username = request.getSession().getAttribute("username").toString();
                
                
            %>
            <input type="hidden" name="email_add"  value="<%= email_add %>">
            <input type="hidden" name="train_no"  value="<%= train_no %>">
            <input type="hidden" name="datesel"  value="<%= datesel %>">
            <input type="hidden" name="src_station"  value="<%= src_station %>">
            <input type="hidden" name="dest_station"  value="<%= dest_station %>">
            <input type="hidden" name="class_type"  value="<%= class_type %>">
            <input type="hidden" name="username"  value="<%= username %>">
            
        </form>
        <form action="logout" method="post">
            <input type="submit" name="logout" value="logout">
            
        </form>
    </body>
</html>
