<%-- 
    Document   : userhome.jsp
    Created on : Mar 11, 2017, 9:24:30 PM
    Author     : dell pc
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="Beans.Avail_transactions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="style.jsp"/>
<html>
    <head>
        <jsp:include page="commonheader.jsp"/>
    </head>
    <body>
        <form id="form1" method="post">
        <div id="d">
            <h1 style="color:red;margin-left:20%" > Welcome ${sessionScope.username}</h1>
            <table>
                    <tr><a href="booktickets.jsp">Book Tickets</a></tr><br/><br/>
                    <tr><a href="addroute.jsp">Cancel Tickets</a></tr><br/><br/>
                </table>
            </div>
        </form>
            <form id="form5" method="post" action="avail_trans">
                <div id="d">
                    You can visit your previous ticket booking history by pressing this button
                    <td><input type="submit" name="trans" value="show transactions"></td>
                </div>
            </form>
            
    </body>
</html>
