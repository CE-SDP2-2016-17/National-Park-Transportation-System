<%-- 
    Document   : addroute
    Created on : Feb 22, 2017, 8:06:50 PM
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
            <h1 style="color:red;margin-left:20%" > Add New Route</h1>
            <form id="form1" action="addroute" method="post">
                <table>
                <tr>
                    <td>Route Name:</td>
                    <td><input type="textbox" name="routeName" placeholder="Route Name" style="width:100px" required/></td>
                </tr>
                <tr>
                    <td>Select Source Station:</td>
                    <td>
                        <jsp:useBean id="abc" class="Beans.Admin" scope="page">
                            <select name="src_station">
                                <c:forEach var="item" items="${abc.stations}">
                                    <option>${item}</option>
                                </c:forEach>
                            </select>            
                        </jsp:useBean>
                    </td>
                </tr>
                <tr>
                    <td>Select Total Number of Stations:</td>
                    <td>
                        <select name="no_stations">
                            <c:forEach begin="2" end="5" varStatus="n">
                                <option>${n.index}</option>
                            </c:forEach>
                        </select>
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
