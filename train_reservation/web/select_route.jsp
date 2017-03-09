<%-- 
    Document   : addtrain
    Created on : Mar 7, 2017, 6:58:54 PM
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
            <h1 style="color:red;margin-left:20%" > Add New Train</h1>
            <form id="form1" action="select_route" method="post">
                <table>
                <tr>
                    <td>Train Number:</td>
                    <td><input type="text" name="train_number" placeholder="Train Number" style="width:100px" required/></td>
                </tr>
                <tr>
                    <td>Train Name:</td>
                    <td><input type="text" name="train_name" placeholder="Train Name" style="width:100px" required/></td>
                </tr>
                <tr>
                    <td>Select Route:</td>
                    <td>
                        <jsp:useBean id="admin" class="Beans.Admin" scope="page">
                            <select name="route">
                                <c:forEach var="item" items="${admin.routes}">
                                    <option>${item}</option>
                                </c:forEach>
                            </select>            
                        </jsp:useBean>
                    </td>
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
