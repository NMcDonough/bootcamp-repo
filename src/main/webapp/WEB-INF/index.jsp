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
    <c:set var="loggedIn" value="${user.fname}"/>

    <div class="container">
        <div class="container dropdown" id='header'>
                <nav class="navbar navbar-expand-sm bg-light fixed-top">
                    <!-- TITLE -->
                    <h1 class="nav-item">Bootcamp Repo</h1>
                    <!-- Links -->
                    <ul class="navbar-nav">
                        <c:if test="${loggedIn != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="">${user.fname}</a>
                            </li>
                        </c:if>

                        <li class="nav-item">
                            <a class="nav-link" href="#">Link 2</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="#">Link 3</a>
                        </li>

                        <c:if test="${loggedIn == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="/login">Login/Register</a>
                            </li>
                        </c:if>

                        <c:if test="${loggedIn != null && user.userlevel == 5}">
                            <li class="nav-item">
                                <div class="dropdown btn-group">
                                    <button type="button" class="btn nav-link" href="/admin/categories">Admin Panel</button>
                                    <button type="button" class="btn dropdown-toggle dropdown-toggle-split" id="dropwdonMenuLink" aria-haspopup="true" aria-expanded="false" data-toggle="dropdown" href="#">
                                        <span class="sr-only">Toggle Dropdown</span>
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        <a class="dropdown-item" href="/admin/categories">Categories</a>
                                        <a class="dropdown-item" href="/admin/threads">Threads</a>
                                        <a class="dropdown-item" href="/admin/posts">Posts</a>
                                        <a class="dropdown-item" href="/admin/users">Users</a>
                                    </div>
                                </div>
                            </li>    
                        </c:if>

                        <c:if test="${loggedIn != null}">
                            <li class="nav-item">
                                <a href="/logout" class="nav-link" href="/logout">Logout</a>
                            </li>
                        </c:if>
                    </ul>

                    <div class="search nav-item">
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