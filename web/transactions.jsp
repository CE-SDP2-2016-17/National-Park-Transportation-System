<%-- 
    Document   : transactions
    Created on : Mar 24, 2017, 12:33:17 AM
    Author     : sagar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transactions</title>
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
            <form id="form7">
                <h1 style="color:red;margin-left:30%" > Transaction History</h1>
                <table border="1">
                    <tr>
                        <td style="color:#006dcc">Train No &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Train Name &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">From &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">To &nbsp;&nbsp;</td>
                        <td style="color:#006dcc">Date&nbsp;&nbsp;</td> 
                        <td style="color:#006dcc">Class&nbsp;&nbsp;</td> 
                        <td style="color:#006dcc">Price&nbsp;&nbsp;</td> 
                    </tr>
                    <c:forEach var="trains1" items="${sessionScope.available_trsc}">
                        <tr>
                            <td>${trains1.train_no} &nbsp;&nbsp;</td>
                            <td>${trains1.name} &nbsp;&nbsp;</td>
                            <td>${trains1.from} &nbsp;&nbsp;</td>
                            <td>${trains1.to} &nbsp;&nbsp;</td>
                            <td>${trains1.date} &nbsp;&nbsp;</td>
                            <td>${trains1.class_type} &nbsp;&nbsp;</td>
                            <td>${trains1.price} &nbsp;&nbsp;</td>
                        </tr>
                    </c:forEach>
                </table>
                    <tr><a href="userhome.jsp">Home Page</a></tr><br/><br/>

            </form>
    </body>
</html>
