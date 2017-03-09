<%-- 
    Document   : addroute_db
    Created on : Feb 26, 2017, 7:02:29 PM
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
            <h1 style="color:red;margin-left:10%" > Add Stations in Route</h1>
            <form name="form1" action="addroute_db" method="post">
                <table>
                    <c:if test="${sessionScope.route_name == null}">
                        <c:redirect url="/NationalPark/adminhome.jsp"/>
                    </c:if>
                    <jsp:useBean id="abc" class="Beans.Admin" scope="page"/>
                    <c:forEach begin="1" end="${sessionScope.no_station - 1}" varStatus="n">
                        <c:choose>
                            <c:when test="${n.index == sessionScope.no_station - 1}">
                                <tr>
                                    <td>Destination Station:</td>
                                    <td>
                                        <select name="station_${n.index}">
                                            <option disabled="disabled">--- Select Station ---</option>
                                            <c:forEach var="item" items="${abc.stations}">
                                                <option>${item}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <tr><td></td></tr>
                                    <td>Distance from source:</td>
                                    <td>
                                        <input type="text" name="km_${n.index}" style="width: 100px" min="2" max="300" placeholder="in kilometers" required/>
                                    </td>
                                </tr>
                                <tr><td><br></td></tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td>Station No: ${n.index}</td>
                                    <td>
                                        <select name="station_${n.index}">
                                            <option disabled="disabled">--- Select Station ---</option>
                                            <c:forEach var="item" items="${abc.stations}">
                                                <option>${item}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <tr><td></td></tr>
                                    <td>Distance from source:</td>
                                    <td>
                                        <input type="text" name="km_${n.index}" style="width: 100px" min="2" max="300" placeholder="in kilometers"
                                               required/>
                                    </td>
                                </tr>
                                <tr><td><br></td></tr>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <tr>
                        <td>
                            ${sessionScope.error}
                        </td>           
                    </tr>                        
                    <tr>
                        <td>
                            <input type="submit" value="Submit"/>
                        </td>
                    </tr>            
                </table>
            </form> 
        </div>
    </body>
</html>
