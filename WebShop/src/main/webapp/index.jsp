<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">

    <!-- Bootstrap CSS -->
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">

    <title>Hello, world!</title>
</head>

<body>
<div class="contenier">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03"
                aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="index.jsp">Home</a>

        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="main.jsp">Books</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Cart</a>
                </li>
                <t:login/>
                <li class="nav-item">
                    <p class="text-light bg-dark"> <c:out value="${sessionScope.user.name} ${sessionScope.user.email}"/></p>
            </ul>

            <form action="logout" method="POST" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>

            </form>
        </div>
</div>
<div class="container">
    <div class="row">
        <div class="card col-sm-4">
            <img alt="Card image cap" class="card-img-top"
                 src="http://www.intmash.ru/local/templates/intmash_new/images/no_photo.jpg">
            <div class="card-body">
                <h5 class="card-title text-truncate" data-original-title="" data-placement="top"
                    data-toggle="tooltip"
                    title="">111</h5>
                <p class="card-text">11111111111111111111111111111</p>
                <h5>Price: 10</h5>
                <a class="btn btn-primary" href="#">Buy</a>
            </div>
        </div>
        <div class="card col-sm-4">
            <img alt="Card image cap" class="card-img-top"
                 src="http://www.intmash.ru/local/templates/intmash_new/images/no_photo.jpg">
            <div class="card-body">
                <h5 class="card-title text-truncate" data-original-title="" data-placement="top"
                    data-toggle="tooltip"
                    title="">222</h5>
                <p class="card-text">2222222222222222222222222222222222</p>
                <h5>Price: 12</h5>
                <a class="btn btn-primary" href="#">Buy</a>
            </div>
        </div>
        <div class="card col-sm-4">
            <img alt="Card image cap" class="card-img-top"
                 src="http://www.intmash.ru/local/templates/intmash_new/images/no_photo.jpg">
            <div class="card-body">
                <h5 class="card-title text-truncate" data-original-title="" data-placement="top"
                    data-toggle="tooltip"
                    title="">333</h5>
                <p class="card-text">333333333333333333333333333333333333</p>
                <h5>Price: 12</h5>
                <a class="btn btn-primary" href="#">Buy</a>
            </div>
        </div>
        <div class="card col-sm-4">
            <img alt="Card image cap" class="card-img-top"
                 src="http://www.intmash.ru/local/templates/intmash_new/images/no_photo.jpg">
            <div class="card-body">
                <h5 class="card-title text-truncate" data-original-title="" data-placement="top"
                    data-toggle="tooltip"
                    title="">444</h5>
                <p class="card-text">444444444444444444444444444444444444</p>
                <h5>Price: 6</h5>
                <a class="btn btn-primary" href="#">Buy</a>
            </div>
        </div>
        <div class="card col-sm-4">
            <img alt="Card image cap" class="card-img-top"
                 src="http://www.intmash.ru/local/templates/intmash_new/images/no_photo.jpg">
            <div class="card-body">
                <h5 class="card-title text-truncate" data-original-title="" data-placement="top"
                    data-toggle="tooltip"
                    title="">555</h5>
                <p class="card-text">55555555555555555555555555555555555555555</p>
                <h5>Price: 22</h5>
                <a class="btn btn-primary" href="#">Buy</a>
            </div>
        </div>
        <div class="card col-sm-4">
            <img alt="Card image cap" class="card-img-top"
                 src="http://www.intmash.ru/local/templates/intmash_new/images/no_photo.jpg">
            <div class="card-body">
                <h5 class="card-title text-truncate" data-original-title="" data-placement="top"
                    data-toggle="tooltip"
                    title="">666</h5>
                <p class="card-text">666666666666666666666666666666666666666666</p>
                <h5>Price: 134</h5>
                <a class="btn btn-primary" href="#">Buy</a>
            </div>
        </div>
    </div>
</div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script crossorigin="anonymous"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script crossorigin="anonymous"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script crossorigin="anonymous"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>

</html>