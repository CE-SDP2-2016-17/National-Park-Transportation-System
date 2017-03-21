<%-- 
    Document   : booktickets
    Created on : Mar 15, 2017, 9:04:41 PM
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
            <h1 style="color:red;margin-left:20%" > Welcome ${sessionScope.username}</h1>
            <form id="form1" action="booktickets" method="post">
                <table>
                    <tr>
                    <td>
                        From Station 
                    </td>
                    <td>
                        <jsp:useBean id="bean12" class="Beans.Admin" scope="page">
                                <select id="src_station" name="src_station">
                                    <c:forEach var="item" items="${bean12.stations}">
					<option>${item}</option>
                                    </c:forEach>
				</select>
                        </jsp:useBean>
                    </td>
                    <tr><td><br></td></tr>
                    <tr>
                        <td>
                        To Station 
                        </td>
                        <td>
                            <jsp:useBean id="bean13" class="Beans.Admin" scope="page">
                                <select name="dest_station">
                                    <c:forEach var="item" items="${bean13.stations}">
                                        <option>${item}</option>
                                    </c:forEach>
                                </select>
                            </jsp:useBean>
                        </td>
                    </tr>
                    <tr><td><br></td></tr>
                    <tr>
                    <td>
                        <button type="submit"  value="submit" >Search Trains</button>
                    </td>
                </tr>
                </table>
            </form>
                
        </div>
    </body>
</html>