<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

    <title>Login</title>
</head>
<body>
    <div class='container'>
        <div class='row'>
            <div class="col-6-md login">
                <form method="post" action="/login">
                    <p>
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email"/>
                    </p>
                    <p>
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password"/>
                    </p>
                    <input type="submit" value="Login!"/>
                </form>   
            </div>

            <div class="col-6-md register">
                <form:form method="POST" action="/registration" modelAttribute="newUser">
                    <p>
                        <form:label path="email">Email:</form:label>
                        <form:input type="email" path="email"/>
                        <form:errors path="email" class="red"/>
                    </p>
                    <p>
                        <form:label path="fname">First Name:</form:label>
                        <form:input path="fname"/>
                        <form:errors path="fname" class="red"/>
                    </p>
                    <p>
                        <form:label path="lname">Last Name:</form:label>
                        <form:input path="lname"/>
                        <form:errors path="lname" class="red"/>
                    </p>
                    <p>
                        <form:label path="password">Password:</form:label>
                        <form:password path="password"/>
                        <form:errors path="password" class="red"/>
                    </p>
                    <p>
                        <form:label path="confirm">Password Confirmation:</form:label>
                        <form:password path="confirm"/>
                        <form:errors path="confirm" class="red"/>
                    </p>
                    <input type="submit" value="Register!"/>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>