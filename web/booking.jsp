<%-- 
    Document   : booking.jsp
    Created on : Jul 18, 2021, 2:07:58 PM
    Author     : Tu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Page</title>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            });
            $(function () {
                $("#datepicker1").datepicker({dateFormat: 'dd/mm/yy'});
            });
        </script>
    </head>

    <h2 style="color: red">Item Detail</h3>        
    <body>
        <h3 style="color: red">${requestScope.MESSAGE}</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Color</th>
                    <th>Category</th>
                    <th>Quantity</th>                        
                </tr>
            </thead>
            <tbody>
                <c:set var="item" value="${sessionScope.ITEM}"/>
                <tr>
                    <td>${item.itemid}</td>
                    <td>${item.itemname}</td>
                    <td>${item.color}</td>
                    <td>${item.categoryName}</td>
                    <td>${item.quantity}</td>
                </tr>
            </tbody>
        </table>

        <h3 style="color: blue">Input information Booking</h3>
        <form action="DispatchServlet" method="POST">
            <c:if test="${empty param.txtQuantity}">
                Quantity: <input type="number" name="txtQuantity" value="1" /></br>
            </c:if>
            <c:if test="${not empty param.txtQuantity}">
                Quantity: <input type="number" name="txtQuantity" value="${param.txtQuantity}" /></br>
            </c:if>
            Date Using: <input type="text" name="txtDateBegin" id="datepicker" value = "${param.txtDateBegin}" required/></br>
            Date End: <input type="text" name="txtDateEnd" id="datepicker1" value ="${param.txtDateEnd}" required/></br>
            Message: <textarea name="txtRequest" rows="4" cols="20" value="${param.txtRequest}" required></textarea>
            <input type="submit" value="Booking" name="btnAction" />
        </form>
            <a href="searchEmployee.jsp" style="color: blueviolet">Click here Back</a> 
    </body>
</html>
