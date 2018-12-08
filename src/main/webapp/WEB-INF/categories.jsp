<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categories Menu</title>
</head>
<body>
    <div class="newCat">
        <h1>This page is for making changes to the categories in the database</h1>
        <br>
        <h3>Add a category</h3>
        <form:form method="POST" action="/admin/categories" modelAttribute="category">
            <p>
                 <form:label path="name">Name:</form:label>
                 <form:input path="name"/>
                 <form:errors path="name" class="red"/>
            </p>

            <p>
                <form:label path="accessLevel">Access Level:</form:label>
                <form:select path="accessLevel">
                    <form:option value="1">Everyone</form:option>
                    <form:option value="2">Users</form:option>
                    <form:option value="3">Moderators</form:option>
                    <form:option value="5">Admins</form:option>
                </form:select>
                <form:errors path="accessLevel" class="red"/>
           </p>
             <input type="submit" value="create"/>
        </form:form>
    </div>

    <div class="categories">
        <c:forEach items="${categories}" var="category">
            <h1><c:out value="${category.name}"/></h1>
            <h2><c:out value="Access Level: ${category.accessLevel}"/></h2>
            <div class="catOptions">
                <a href="/admin/categories/delete/${category.id}">Delete</a>
            </div>
        </c:forEach>	
    </div>
</body>
</html>