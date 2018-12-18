<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Bootcamps Menu</title>

    <script src="/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/bootcamps.css">
    <link rel="stylesheet" href="/css/nav.css">
</head>
<body>
    <%@ include file="partials/nav.html" %>

    <h2>This page is meant for making changes to the bootcamps in the database</h2>

    <div class="form-group">
        <form:form method="POST" action="/admin/bootcamps" modelAttribute="bootcamp">
            <p>
                <form:label path="image">Image URL</form:label>
                <form:input type="url" path="image" name="image" />
            </p>
            <p>
                <form:label path="name">Name</form:label>
                <form:input type="text" path="name"/>
            </p>
            <input type="submit" class="btn" value="submit"/>
        </form:form>
    </div>

    <div class="bootcamps">
        <table>
            <c:forEach items="${bootcamps}" var="bootcamp">
                <div class="bootBox">
                    <tr>
                        <div>
                            <p>${bootcamp.name}</p>
                            <img src="${bootcamp.image}" alt="${bootcamp.name}" class="bootImg">
                        </div>
                    </tr>
                    <tr>
                        <a href="/admin/bootcamps/edit/${bootcamp.id}">Edit</a> |
                        <a href="/admin/bootcamps/delete/${bootcamp.id}">Delete</a>
                    </tr>
                </div>
            </c:forEach>
        </table>
        
    </div>
</body>
</html>