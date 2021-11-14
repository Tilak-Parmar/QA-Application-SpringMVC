<%-- 
    Document   : error
    Created on : Apr 28, 2021, 2:45:47 AM
    Author     : tilak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <title>AskLite - Error Page</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}">AskLite!</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navb">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">About</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
            </ul>
            <form action="${pageContext.request.contextPath}/index.htm" method="post" class="form-inline my-2 my-lg-0 needs-validation" novalidate>
                <input class="form-control mr-sm-2" type="email" placeholder="Email" name="email" required title="'<' and '>' characters will be converted to '_'">
                <input class="form-control mr-sm-2" type="password" placeholder="Password" name="password" title="'<' and '>' characters will be converted to '_'" required>
                <button type="submit" class="btn btn-success my-2 my-sm-0 mr-sm-2" type="button" style="width: 100px;">Login</button>
            </form>
            <button type="button" class="btn btn-primary my-2 my-sm-0 mr-sm-2" style="width: 100px;" data-toggle="modal"
                    data-target="#myModal">Signup</button>

        </div>
    </nav>
        <h1>${requestScope.errormsg}</h1>
        <footer class="footer" style="width: 100%; height: 60px; line-height: 60px; background-color: #f5f5f5; bottom: 0; position: absolute;">
        <div class="container">
            <span class="text-muted">Copyright 1999-2021 by Tilak Parmar. All Rights Reserved.</span>
        </div>
    </footer>
    </body>
</html>