<%-- 
    Document   : adminhome
    Created on : Feb 20, 2017, 7:53:55 PM
    Author     : dell pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="style.jsp"/>
<html>
    <head>
        <jsp:include page="commonheader.jsp"/>
    </head>
    <body>
        <div id="d">
                <h1 style="color:red;margin-left:20%" > Welcome Admin</h1>
                <table>
                    <tr><a href="addstation.jsp">Add New Station</a></tr><br/><br/>
                    <tr><a href="addroute.jsp">Add New Route</a></tr><br/><br/>
                    <tr><a href="select_route.jsp">Add New Train</a></tr><br/><br/>
                </table>
            </div>
    </body>
</html>
