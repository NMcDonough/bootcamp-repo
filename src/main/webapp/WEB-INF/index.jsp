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
    <c:set var="loggedIn" value="${fname}"/>

    <div class="container">
        <div class="container" id='header'>
                <nav class="navbar navbar-expand-sm bg-light fixed-top">
                    <!-- TITLE -->
                    <h1>Bootcamp Repo</h1>
                    <!-- Links -->
                    <ul class="navbar-nav">
                        <c:if test="${loggedIn != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="">${fname}</a>
                            </li>
                        </c:if>

                        <li class="nav-item">
                            <a class="nav-link" href="#">Link 2</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="#">Link 3</a>
                        </li>

                        <c:if test="${loggedIn != null}">
                            <li class="nav-item">
                                <a href="/logout" class="nav-link" href="/logout">Logout</a>
                            </li>
                        </c:if>

                        <c:if test="${loggedIn == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="/login">Login/Register</a>
                            </li>
                        </c:if>
                    </ul>
                    <div class="search">
                        <form class="form-inline" action="/action_page.php">
                            <input class="form-control mr-sm-2" type="text" placeholder="Search">
                            <button class="btn btn-success" type="submit">Search</button>
                        </form>
                    </div>
                </nav>
        </div>
    
        <div class="container" id="body">
            <h4>Welcome to Bootcamp Repo! New users, please check out our RULES and FAQ sections</h4>

            <div class='container' id="rules">

            </div>
        </div>
        <div class="footer">
            <p>Footer stuff goes here</p>
        </div>
    </div>
</body>
</html>