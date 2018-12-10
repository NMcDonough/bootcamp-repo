<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Bootcamps Menu</title>
</head>
<body>
    <h1>This page is meant for making changes to the bootcamps in the database</h1>

    <div class="form-group">
        <form:form method="POST" action="/admin/bootcamps" modelAttribute="bootcamp">
            <p>
                <form:input type="url" path="image" name="image" />
            </p>
            <c:if test="${bootcamp.image}">
                <p>Current image:</p>
                <img src="${bootcamp.image}" alt="bootcamp img">
            </c:if>
            <form:input type="text" path="name"/>
            <input type="submit" class="btn" value="submit"/>
        </form:form>
    </div>

    <div class="bootcamps">
        <table>
            <c:forEach items="${bootcamps}" var="bootcamp">
                <div class="bootcamp">
                    <tr>
                        <img src="${bootcamp.image}" alt="${bootcamp.name}" class="bootImg">
                        <p>${bootcamp.name}</p>
                    </tr>
                </div>
            </c:forEach>
        </table>
        
    </div>
</body>
</html>