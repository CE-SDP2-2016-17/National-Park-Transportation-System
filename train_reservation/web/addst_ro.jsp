<%-- 
    Document   : addst_ro
    Created on : 9 Mar, 2017, 2:10:15 PM
    Author     : Smit
--%>
<%@ page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
<form method="post" action="addst_ro">

<table border="2">
<tr>
<td>Route ID</td>
<td>St_NAME</td>
<td>Pos</td>
</tr>
<%
try
{
   
String DB_Driver = getServletContext().getInitParameter("DB_Driver");
        String DB_Con = getServletContext().getInitParameter("DB_Con");
        String DB_Uname = getServletContext().getInitParameter("DB_Username");
        String DB_Password = getServletContext().getInitParameter("DB_Password");
String query="select * from Ro_St";
Class.forName(DB_Driver);
            Connection con = DriverManager.getConnection(DB_Con, DB_Uname, DB_Password  );
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery(query);
while(rs.next())
{

%>
    <tr>
    <td><%=rs.getInt("route_id") %></td>
    <td><%=rs.getString("st_name") %></td>
    <td><%=rs.getString("pos") %></td>
</tr>
        <%

}
%>
    </table>
    <%
    rs.close();
    stmt.close();
    con.close();
    }
catch(Exception e)
{
    e.printStackTrace();
    }




%>
<div id="d">
                <h1 style="color:red;margin-left:25%" > Add Station to route</h1>
                <table>
                    <tr>
                        <td>Route Id:</td>
                        <td><input type="text" name="ro_id" placeholder="Route Id" required></td>
                    </tr>
                    <tr>
                        <td>Station name</td>
                        <td><input type="text" name="st_name" placeholder="st_name"></textarea></td>
                    </tr>
                    <tr>
                        <td>Pos</td>
                        <td><input type="text" name="pos" placeholder="pos"></textarea></td>
                    </tr>
                    <tr>
                        <td><input type="Submit" name="Submit" value="Submit"></td>
                    </tr>
                    <tr><td><label id="label1" hidden="true">label1</label></td></tr>
                </table>
            </div>

</form>
</html> 
