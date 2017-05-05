<%--
    Document   : select_pnr_to_cancel
    Created on : Apr 6, 2017, 5:59:15 PM
    Author     : dell pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<style>
    #d{
        background-color: #dddddd;
        width:650px;
        padding:10px;
        margin-top: 1px;
        margin-left:23%;
        font-family:'comic sans ms';
        align-content:center;
    }
    #b1:hover{
        background-color: #224466;
    }
    #tr1
    {
        padding-top: 50px;
    }
</style>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/datepicker.css">
        <link rel="stylesheet" href="bootstrap.css">
        <jsp:include page="commonheader.jsp"/>
    </head>
    <body>
        <div id="d">
            <h1 style="color:red;margin-left:25%" > Select PNR Number</h1>
            <form id="form1" action="canceltickets" method="post">
                <table border="1">
                    <tr>
                        <td style="color: #006dcc">PNR</td>
                        <td style="color: #006dcc">Train_no</td>
                        <td style="color: #006dcc">Date</td>
                        <td style="color: #006dcc">Source</td>
                        <td style="color: #006dcc">Destination</td>
                        <td style="color: #006dcc">Class</td>
                        <td style="color: #006dcc">Seat_no</td>
                        <td style="color: #006dcc">Status</td>
                    </tr>
                    <c:forEach var="pnr_d" items="${sessionScope.booked_pnrs}">
                        <tr>
                            <td>${pnr_d.pnr}</td>
                            <td>${pnr_d.train_no}</td>
                            <td>${pnr_d.date}</td>
                            <td>${pnr_d.src}</td>
                            <td>${pnr_d.dest}</td>
                            <td>${pnr_d.s_type}</td>
                            <td>${pnr_d.seat_no}</td>
                            <td>${pnr_d.status}</td>
                        </tr>
                    </c:forEach>

                </table>
                <table>
                    <tr><td><br></td></tr>
                    <tr>
                        <td>
                            Select PNR :
                        </td>
                        <td>
                            <select name="pnr">
                                <c:forEach var="pnrs" items="${sessionScope.booked_pnrs}">
                                    <option>${pnrs.pnr}</option>
                                </c:forEach>
                            </select>
                        </td>
                    <tr><td><br></td></tr>
                            
                    <tr>
                        <td>
                            <input type="submit" name="Name" value="Cancel Ticket">
                        </td>
                    </tr>

                </table>    
            </form>

        </div>
    </body>
</html>
