<%-- 
    Document   : signup
    Created on : Feb 4, 2017, 7:30:31 PM
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
        <form id="form1" action="signup" method="post">
            <div id="d">
                <h1 style="color:red;margin-left:30%" > User Sign Up</h1>
                <table>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="Name" placeholder="Name" required></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="email" name="Email" placeholder="Email" required></td>
                    </tr>
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="Username" placeholder="Username" required></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input id="Password1" type="password" name="Password1" placeholder="Password" required></td>
                    </tr>
                    <tr>
                        <td>Confirm Password:</td>
                        <td><input id="Password2" type=password name="Password2" placeholder="Confirm Password" required></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="Name" value="Submit"></td>
                    </tr>
                </table>
            </div>
        </form>
        
    </body>
</html>
