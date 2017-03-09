<%-- 
    Document   : signin
    Created on : Feb 4, 2017, 7:30:52 PM
    Author     : dell pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="style.jsp"/>
<html>
    <head>
        <jsp:include page="commonheader.jsp"/>
    </head>
    <body>
        <div id="d">
            <h1 style="color:red;margin-left:33%" > Sign In</h1>
            <form id="form1" action="signin" method="post">
                <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" placeholder="Username" required/></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password" placeholder="Password" required/></td>
                </tr>
                <tr><td><br></td></tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
            </form>
        </div>
    </body>
</html>