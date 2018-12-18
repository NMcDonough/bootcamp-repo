<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.js"></script>
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/bootcamp.css">
<link rel="stylesheet" href="/css/nav.css">

<%@ include file="partials/nav.html" %>

<body>
    <div class="bootHeader">
        <h1>${bootcamp.name}</h1>
        <img src="${bootcamp.image}" class="bootImg" alt="${bootcamp.name}">
    </div>

    <div class="about">

    </div>

    <div class="categories">
        <h2>Select one of these categories to get started!</h2>

        <div class="catBoxes"></div>
            <ul>
                <c:forEach items="${categories}" var="category">
                    <li class="catBox">
                        <a href="" class="titleLink">
                            <h3>${category.getName()}</h3>
                        </a>
                        
                        <!-- Posts in this category -->
                        <div class="catPostBox">

                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>