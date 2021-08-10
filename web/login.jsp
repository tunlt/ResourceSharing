<%-- 
    Document   : login
    Created on : Jun 5, 2021, 12:29:54 PM
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <head>
        <h1>Login Page</h1>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <script src="https://www.google.com/recaptcha/api.js?hl=en"></script>
    </head>
    <body>
        <h2 style="color: blue">${requestScope.ERROR}</h2>
        <h2 style="color: red">${requestScope.MESSAGE}</h2>
        <form action="DispatchServlet" method="POST">
            Username : <input type="text" name="txtUsername" value="" /><br>
            Password : <input type="password" name="txtPassword" value="" /><br>
             <div class="g-recaptcha" 
                  data-sitekey="6Lcm7RIbAAAAAEeQtIlMoJg1krRL_uG0TSubxB5k" ></div>
            
            <input type="submit" value="Login" name="btnAction" />
            <a href="register.jsp">Register an account</a>         
        </form>        
    </body>
    </body>
</html>
