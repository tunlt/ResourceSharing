<%-- 
    Document   : historyrequest
    Created on : Jul 21, 2021, 10:58:48 AM
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="DispatchServlet" method="POST">
            Search: <input type="text" name="txtSearch" value="${param.txtSearch}" placeholder="Search by name..." />
            <select name="cboxCategory">     
                <option>all</option>
                <c:forEach var="u" items="${sessionScope.LISTCATE}">
                    <option value="${u.categoryName}" <c:if test="${requestScope.CBOXCHANGE eq u.categoryName}">selected</c:if>>
                        ${u.categoryName}
                    </option> 
                </c:forEach>                                                           
            </select>
            <input type="hidden" name="intdex" value="1" />         
            <input type="submit" value="Search" name="btnAction" /></br>
            <input type="submit" value="Logout" name="btnAction"/>
        </form>
    </head>

<c:set var="resultList" value="${requestScope.SEARCHLIST}"/>
<c:if test="${not empty resultList}">
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>                    
                    <th>Color</th>
                    <th>Category Name</th>
                    <th>Quantity</th>
                    <th>Action</th>

                </tr>
            </thead>
            <tbody>
            <c:forEach items="${resultList}" var="dto" 
                       varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${dto.itemid}</td>
                    <td>${dto.itemname}</td>
                    <td>${dto.color}</td>
                    <td>${dto.categoryName}</td>
                    <td>${dto.quantity}</td>
                    <td><a href="DetailResource?itemid=${dto.itemid}" >View Detail</a></td>
                </tr>                
            </c:forEach>
            </tbody>
        </table>
</c:if>

<c:if test="${empty resultList}">
    <h2 style="color: red">${requestScope.MESSAGE_SEARCH}</h2>
</c:if>
<c:forEach begin="1" end="${COUNT_PAGE}" var="i">
    <a href =SearchOfEmployeeServlet?txtSearch=${param.txtSearch}&cboxCategory=${param.cboxCategory}&intdex=${i}>${i}</a>       
</c:forEach>
</body>
</body>
</html>
