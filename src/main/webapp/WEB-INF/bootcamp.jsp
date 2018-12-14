<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/bootcamp.css">
<link rel="stylesheet" href="/css/nav.css">

<%@ include file="partials/nav.html" %>

<body>
    <div class="bootHeader">
        <h1>${bootcamp.name}</h1>
        <img src="${bootcamp.image}" alt="${bootcamp.name}">
    </div>

    <div class="about">

    </div>

    <div class="categories">
        <h2>Select one of these categories to get started!</h2>

        <div class="catBoxes">
            <c:forEach items="${categories}" var="category">
                <div class="catBox">
                    <h3>${category.getName()}</h3>
                </div>
            </c:forEach>
        </div>
    </div>
</body>