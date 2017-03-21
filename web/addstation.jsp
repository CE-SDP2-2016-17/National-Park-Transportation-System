<%-- 
    Document   : addstation
    Created on : Feb 20, 2017, 9:53:48 PM
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
        <form id="form1" action="addstation" method="post">
            <div id="d">
                <h1 style="color:red;margin-left:25%" > Add Station</h1>
                <table>
                    <tr>
                        <td>Name:</td>
                        <td><input type="text" name="Name" placeholder="Name" required></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><textarea name="Description" rows="5"  maxlength="200" cols="22"placeholder="Station Description here"></textarea></td>
                    </tr>
                    <tr>
                        <td><input type="Submit" name="Submit" value="Submit"></td>
                    </tr>
                    <tr><td><label id="label1" hidden="true">label1</label></td></tr>
                </table>
            </div>
        </form>
    </body>
</html>
