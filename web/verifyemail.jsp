<%-- 
    Document   : verifyemail
    Created on : Jul 19, 2021, 12:12:57 AM
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Page</title>
    </head>
    <body>
        <h2 Style="color: blue">We have sent you a verification email, please enter to activate your account </h2>
        <h4 Style="color: red">${requestScope.MESSAGE}</h4>
        <form action="DispatchServlet" method="POST">
            <input type="text" name="txtCode" value="${param.txtCode}" />
            <input type="submit" value="VerifyEmail" name="btnAction"/></br>        
        </form>
            <a href="SendVerifyEmailAgain" style="color: blueviolet">click here to Send code again!</a>            
    </body>
</html>
