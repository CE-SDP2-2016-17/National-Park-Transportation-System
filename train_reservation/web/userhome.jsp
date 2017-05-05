<%-- 
    Document   : userhome.jsp
    Created on : Mar 11, 2017, 9:24:30 PM
    Author     : dell pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="style.jsp"/>
<html>
    <head>
        <jsp:include page="commonheader.jsp"/>
     
    </head>
    <body>
        <div id="d">
            <h1 style="color:red;margin-left:20%" > Welcome ${sessionScope.username}</h1>
                <table>
                    <tr><a href="booktickets.jsp">Book Tickets</a></tr><br/><br/>
                    <tr><a href="select_pnr">Cancel Tickets</a></tr><br/><br/>
                </table>
            </div>
    </body>
</html>
