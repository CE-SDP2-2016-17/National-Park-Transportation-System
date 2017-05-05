<%--
    Document   : available
    Created on : Mar 9, 2017, 9:07:36 PM
    Author     : sagar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Availability</title>
        <link rel="stylesheet" type="text/css" href="css/datepicker.css">
        <link rel="stylesheet" href="bootstrap.css">
        <jsp:include page="commonheader.jsp"/>
    </head>
    <body>
        <style>
            #d{
                background-color: #dddddd;
                width:650px;
                padding:10px;
                margin-top: 80px;
                margin-left:25%;
                font-family:'comic sans ms';
                align-content:center;
            }
            #b1:hover{
                background-color: #224466;
            }
        </style>
        <div id="d">
            <form id="form1" action="pgRedirect.jsp" method="post">
                <h1 style="color:red;margin-left:30%" > Select Train</h1>
                <table border="1">
                    <tr>
                        <td style="color:#006dcc">Train No &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Train Name &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">From &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Arrival Time &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">To &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Departure Time &nbsp;&nbsp;</td>
                                                <td style="color:#006dcc">Fc &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Sc &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Gc &nbsp;&nbsp;</td>
                                                <td style="color:#006dcc">Fc Price &nbsp;&nbsp;</td>
                                      <td style="color:#006dcc">Sc Price &nbsp;&nbsp;</td>

                                      <td style="color:#006dcc">Gc Price &nbsp;&nbsp;</td>
                        

                    </tr>
                    <c:forEach var="trains" items="${sessionScope.available}">
                        <tr>
                            <td>${trains.train_no} &nbsp;&nbsp;</td>
                            <td>${trains.name} &nbsp;&nbsp;</td>
                            <td>${sessionScope.src_station} &nbsp;&nbsp;</td>
                            <td>${trains.arrival_time} &nbsp;&nbsp;</td>
                            <td>${sessionScope.dest_station} &nbsp;&nbsp;</td>
                            <td>${trains.dept_time} &nbsp;&nbsp;</td>
                            <c:choose>
    <c:when test="${trains.fc <= 0}">
        <td>WL${trains.fc-1} &nbsp;&nbsp;</td>
    </c:when>
   
    <c:otherwise>
        <td>${trains.fc} &nbsp;&nbsp;</td>
    </c:otherwise>
</c:choose>     <c:choose>
    <c:when test="${trains.sc <= 0}">
        <td>WL${trains.sc-1} &nbsp;&nbsp;</td>
    </c:when>
   
    <c:otherwise>
        <td>${trains.sc} &nbsp;&nbsp;</td>
    </c:otherwise>
</c:choose>     <c:choose>
    <c:when test="${trains.gc <=0}">
        <td>WL${trains.gc-1} &nbsp;&nbsp;</td>
    </c:when>
   
    <c:otherwise>
        <td>${trains.gc} &nbsp;&nbsp;</td>
    </c:otherwise>
</c:choose>
                            
                                                        <td>${trains.fcprice} &nbsp;&nbsp;</td>
                            <td>${trains.scprice} &nbsp;&nbsp;</td>
                            <td>${trains.gcprice} &nbsp;&nbsp;</td>


                        </tr>
                    </c:forEach>
                </table>
                <br/>
                <table>
                    <tr>
                        <td>Select Train_No</td>
                        <td>
                            <select name="train_no">
                                <c:forEach var="trains" items="${sessionScope.available}">
                                    <option>${trains.train_no}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Class</td>
                        <td>
                            <select name="class_type">
                                <option name="first" value="seats_fc">first</option>
                                <option name="second" value="seats_sc">second</option>
                                <option name="third" value="seats_gc">third</option>
                            </select>
                        </td>
                    </tr>
                   
                    <tr><td><br/></td></tr>
                    <tr>
                        <td><input type="Submit" name="check_Avail" value="Book Ticket"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
