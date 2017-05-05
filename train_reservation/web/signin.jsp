<%-- 
    Document   : signin
    Created on : Feb 4, 2017, 7:30:52 PM
    Author     : dell pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="style.jsp"/>
<html>
    <head>
         <style>
  #foo {
    position: fixed;
    bottom: 0;
    right: 0;
  }
</style>
    <script type="text/javascript"> //<![CDATA[ 
var tlJsHost = ((window.location.protocol == "https:") ? "https://secure.comodo.com/" : "http://www.trustlogo.com/");
document.write(unescape("%3Cscript src='" + tlJsHost + "trustlogo/javascript/trustlogo.js' type='text/javascript'%3E%3C/script%3E"));
//]]>
</script>
    </head>
    <body>
        <div id="d">
            <h1 style="color:red;margin-left:33%" > Sign In</h1>
            <form id="form1" action="j_security_check" method="post">
                <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="j_username" placeholder="Username" required/></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="j_password" placeholder="Password" required/></td>
                </tr>
                <tr><td><br></td></tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
            </form>
        </div>
        <div id="foo">
    <script language="JavaScript" type="text/javascript">
TrustLogo("https://trainres.southcentralus.cloudapp.azure.com/comodo_secure_seal_100x85_transp.png", "CL1", "none");
</script>
<a  href="https://www.positivessl.com/" id="comodoTL">Positive SSL</a></div>
    </body>
</html>