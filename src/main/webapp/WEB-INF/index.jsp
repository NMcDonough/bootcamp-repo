<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- jQuery library -->
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

    <!-- Style Sheets -->
    <link rel="stylesheet" href="/css/bootstrap.css">
    <link rel="stylesheet" href="/css/styles.css">

    <title>Bootcamp Repo</title>
</head>

<body>
    <div class="container">

        <%@ include file="partials/nav.html" %>
    
        <div class="container" id="body">
            <h4>Welcome to Bootcamp Repo! New users, please check out our RULES and FAQ sections</h4>

            <div class='container' id="rules">

            </div>
        </div>

        <div class="container bootcamps">
            <div class="title">
                <h1>Bootcamps</h1>
            </div>

            <div class="bootList">
                <c:forEach items="${bootcamps}" var="bootcamp">
                    <div class="bootcamp">
                        <h3>${bootcamp.name}</h3>
                        <a href="/bootcamp/${bootcamp.name}/${bootcamp.id}">
                            <img class="bootImg" src="${bootcamp.image}" alt="${bootcamp.name}">
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="footer">
            <p>Footer stuff goes here</p>
        </div>
    </div>
</body>
</html>