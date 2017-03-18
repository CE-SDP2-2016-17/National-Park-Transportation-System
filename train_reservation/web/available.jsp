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
            <form id="form1" action="available_db" method="post">
                <h1 style="color:red;margin-left:30%" > Select Train</h1>
                <table border="1">
                    <tr>
                        <td style="color:#006dcc">Train No &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Train Name &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">From &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Arrival Time &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">To &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Departure Time &nbsp;&nbsp;</td>
                    </tr>
                    <c:forEach var="trains" items="${sessionScope.available}">
                        <tr>
                            <td>${trains.train_no} &nbsp;&nbsp;</td>
                            <td>${trains.name} &nbsp;&nbsp;</td>
                            <td>${sessionScope.src_station} &nbsp;&nbsp;</td>
                            <td>${trains.arrival_time} &nbsp;&nbsp;</td>
                            <td>${sessionScope.dest_station} &nbsp;&nbsp;</td>
                            <td>${trains.dept_time} &nbsp;&nbsp;</td>
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
                            <select name="class">
                                <option>First</option>
                                <option>Second</option>
                                <option>Third</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Date of Journey </td>
                        <td><input type="date" name="date_of_journey"></td>
                    </tr>
                    <tr><td><br/></td></tr>
                    <tr>
                        <td><input type="Submit" name="check_Avail" value="Check Availability"></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>