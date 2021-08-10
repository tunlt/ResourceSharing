<%-- 
    Document   : register
    Created on : Jul 18, 2021, 11:18:33 PM
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Register User</h1>
        <h3 style="color: red">${requestScope.MESSAGE_REGIS}</h3>
        <form action="DispatchServlet" method="POST">
            UserName: <input type="text" name="txtUserName" value="" required=""/></br>
            PassWord: <input type="password" name="txtPassword" value="" required=""/></br>
            FullName: <input type="text" name="txtFullName" value="" required=""/></br>
            Email: <input type="text" name="txtEmail" value="" required=""/></br>
            Phone: <input type="text" name="txtPhone" value="" required=""/></br>
            Address: <textarea name="txtAddress" rows="4" cols="20" required=""></textarea></br>
            <input type="submit" value="Register" name="btnAction"/>
        </form>
        <a href="login.jsp">Back</a>
    </body>
</html>
