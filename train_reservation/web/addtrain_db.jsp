<%-- 
    Document   : addtrain_db
    Created on : Mar 8, 2017, 11:55:01 PM
    Author     : dell pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
    <head>
        <jsp:include page="commonheader.jsp"/>
    </head>
    <body>
        <style>
            #d{
                        background-color: #dddddd;
                        width:600px;
                        padding:10px;	
                        margin-top: 5px;
                        margin-left:25%;
                        font-family:'comic sans ms';
               }
        </style>
        <div id="d">
            <h1 style="color:red;margin-left:30%" > Add New Train</h1>
            <form id="form1" action="addtrain_db" method="post">
                <table>
                    <tr>
                        <td><h3 style="color:green" > Enter Train Schedule</h3></td>
                    </tr>
                    <tr>
                        <td style="color:#3679EA">Station Name </td>
                        <td style="color:#3679EA">Arrival Time </td>
                        <td style="color:#3679EA">Departure Time </td>
                    </tr>
                    <tr><td><br></td></tr>
                    <c:forEach var="stop" items="${sessionScope.stops}">
                        <tr>
                            <td>${stop}</td>
                            <td><input type="time" name="arrival_${stop}"/></td>
                            <td><input type="time" name="departure_${stop}"/></td>
                        </tr>
                    </c:forEach>
                <tr>
                    <td><h3 style="color:green" > Enter seat details</h3></td>
                </tr>
                <tr>
                    <td>Seats in First class:</td>
                    <td><input type="text" name="seats_fc" style="width:130px"/></td>
                </tr>
                <tr>
                    <td>Seats in Second class:</td>
                    <td><input type="text" name="seats_sc" style="width:130px"/></td>
                </tr>
                <tr>
                    <td>Seats in Third class:</td>
                    <td><input type="text" name="seats_gc" style="width:130px"/></td>
                </tr>
                <tr><td><br></td></tr>
                <tr>
                    <td><input type="submit" style="color:blue" value="Submit"/></td>
                </tr>
            </table>
            </form>
        </div>
    </body>
</html>