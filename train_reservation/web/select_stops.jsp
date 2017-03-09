<%-- 
    Document   : select_stops
    Created on : Mar 8, 2017, 8:10:29 PM
    Author     : dell pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<style>
    #d{
                background-color: #dddddd;
		width:400px;
		padding:10px;	
		margin-top: 1px;
                margin-left:35%;
                font-family:'comic sans ms';
                align-content:center;
            }
</style>
<html>
    <head>
        <jsp:include page="commonheader.jsp"/>
    </head>
    <body>
        <div>
            <a href="adminhome.jsp"><h3 style="color:#49DC09;margin-left:90%" > Logout</h3></a>
        </div>
        <div id="d">
            <h1 style="color:red;margin-left:20%" > Add New Train</h1>
            <form id="form1" action="select_stops" method="post">
                <table>
                    <tr>
                        <td><h3 style="color:green;margin-left:5%" > Select Stops:</h3></td>
                    </tr>
                    <c:forEach var="station" items="${sessionScope.stations}">
                        <tr>
                            <td><input type="checkbox" name="stops" value="${station}"> ${station}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td><h3 style="color:green;margin-left:5%" > Select Running Days:</h3></td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="running_days" value="Monday"> Monday</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="running_days" value="Tuesday"> Tuesday</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="running_days" value="Wednesday"> Wednesday</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="running_days" value="Thursday"> Thursday</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="running_days" value="Friday"> Friday</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="running_days" value="Saturday"> Saturday</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="running_days" value="Sunday"> Sunday</td>
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
