<%-- 
    Document   : searchRequestAdmin
    Created on : Jul 21, 2021, 2:00:36 AM
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Request</title>
    <h1 style="color: blue">Welcome: ${sessionScope.LOGIN_USER.fullname}</h1>
    <h3 style="color: red">${requestScope.MESSAGE_MANAGE}</h3>
    <form action="DispatchServlet" >
        Search: <input type="text" name="txtSearchrequest" value="${param.txtSearchrequest}" placeholder="Seach by name..."/>    
        <input type="hidden" name="intdex" value="1" />
        <select name="cboxCategory" >     
            <c:forEach var="liststatus" items="${sessionScope.LISTSTATUS}">
                <option <c:if test="${requestScope.CBOXCHANGE eq liststatus}">selected</c:if>>${liststatus}</option>
            </c:forEach>                                                   
        </select>
        </br>          
        <input type="submit" value="SearchRequest" name="btnAction" />
        <input type="submit" value="Logout" name="btnAction"/>
    </form>
</head>
<body>
    <c:set var="resultList" value="${requestScope.SEARCHLISTREQUEST}"/>
    <c:if test="${not empty resultList}">
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Request User Name</th>
                    <th>Date Begin</th>
                    <th>Date End</th>
                    <th>Quantity</th>
                    <th>Status Booking</th>
                    <th>Request Message</th>
                    <th>View detail</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${resultList}" var="dto" 
                           varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${dto.item}</td>
                        <td>${dto.username}</td>
                        <td>${dto.datebook}</td>
                        <td>${dto.dateend}</td>
                        <td>${dto.quantity}</td>
                        <td>${dto.statusorder}</td>
                        <td>${dto.requestMessage}</td>                       
                        <td><a href="Detailrequest?bookingid=${dto.bookingid}" >View Detail</a></td>
                    </tr>                
                </c:forEach>
            </tbody>
        </table>

    </c:if>
    <c:if test="${empty resultList}">
        <h2 style="color: red">${requestScope.MESSAGE_SEARCH}</h2>
    </c:if>
    <c:forEach begin="1" end="${COUNT_PAGE}" var="i">
        <a href =SearchRequestServlet?txtSearchrequest=${param.txtSearchrequest}&cboxCategory=${param.cboxCategory}&intdex=${i}>${i}</a>       
    </c:forEach>
</body>

</html>
