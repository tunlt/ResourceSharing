<%-- 
    Document   : managerequest
    Created on : Jul 21, 2021, 8:14:10 AM
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Request</title>

    </head>
    <h2 style="color: red">Information Request</h2> 
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Request User Name</th>
                    <th>Date Begin</th>
                    <th>Date End</th>
                    <th>Quantity</th>                        
                    <th>Request Message</th>                        
                </tr>
            </thead>
            <tbody>
                <c:set var="booking" value="${sessionScope.BOOKING}"/>
                <tr>
                    <td>${booking.item}</td>
                    <td>${booking.username}</td>
                    <td>${booking.datebook}</td>
                    <td>${booking.dateend}</td>
                    <td>${booking.quantity}</td>
                    <td>${booking.requestMessage}</td>
                </tr>
            </tbody>
        </table>
        <c:if test="${BOOKING.statusorder eq requestScope.ISNEW}">
            <h3 style="color: blue">Request Manager</h3>
            <h4 style="color: red">${requestScope.MESSAGE_MANAGE}</h4>

            <form action="DispatchServlet">                
                <input type="text" name="txtResponseMessage" value="${txtResponseMessage}"/>
                <input type="hidden" name="txtQuantity" value="${booking.quantity}" />
                <input type="hidden" name="txtItemID" value="${booking.itemid}" />
                <input type="submit" value="Reject" name="btnAction" placeholder="Reason reject..."/>
                <input type="submit" value="Approve" name="btnAction"/>
            </form>
        </c:if>
    </body>
</html>
