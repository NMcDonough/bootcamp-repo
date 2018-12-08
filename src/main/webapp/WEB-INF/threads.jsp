<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Threads Menu</title>
</head>
<body>
    <h1>This page is meant for making changes to the threads in the database</h1>

    <div class="threads">
        <c:forEach items="${threads}" var="thread">
            <h1><c:out value="${thread.title}"/></h1>
            <div class="threadOptions">
                <a href="/admin/threads/delete/${thread.id}">Delete</a>
            </div>
        </c:forEach>	
    </div>
</body>
</html>