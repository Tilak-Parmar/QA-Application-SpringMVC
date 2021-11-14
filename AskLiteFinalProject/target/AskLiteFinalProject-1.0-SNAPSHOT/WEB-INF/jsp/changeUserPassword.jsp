<%-- 
    Document   : changeUserPassword
    Created on : Apr 28, 2021, 3:40:38 PM
    Author     : tilak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <title>AskLite Home - Ask your questions</title>
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
                <a href="${pageContext.request.contextPath}/questions.htm" class="btn btn-primary" style="width: 200px; margin-right: 10px;">Your Questions</a>
                <a href="${pageContext.request.contextPath}/changepassword.htm" class="btn btn-success" style="width: 200px; margin-right: 10px;">Change Password</a>
                <a href="${pageContext.request.contextPath}/logout.htm" class="btn btn-danger" style="width: 300px;">Logout ${sessionScope.user.getEmail()}</a>
            </div>
        </nav>

    <c:if test="${requestScope.success}">
        <div class="alert alert-warning alert-dismissible fade show">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>${requestScope.successmsg}</strong> 
        </div>
    </c:if>

    <div class="container">
        <br />
        <h1>Change Your Password</h1>
        <form action="${pageContext.request.contextPath}/changepassword.htm" method="POST" class="needs-validation" novalidate>
            <div class="form-group">
                <label for="curpwd">Current Password:</label>
                <input type="password" class="form-control" placeholder="Enter Curent Password" name="curpwd" required>
            </div>
            <div class="form-group">
                <label for="newpwd">New Password:</label>
                <input type="password" class="form-control" placeholder="Enter New Password" name="newpwd" required>
            </div>

            <button type="submit" class="btn btn-primary">Update</button>
        </form>
    </div>

    <footer class="footer" style="width: 100%; height: 60px; line-height: 60px; background-color: #f5f5f5; position: absolute; bottom: 0;">
        <div class="container">
            <span class="text-muted">Copyright 1999-2021 by Tilak Parmar. All Rights Reserved.</span>
        </div>
    </footer>

    <script>
        // Disable form submissions if there are invalid fields
        (function () {
            'use strict';
            window.addEventListener('load', function () {
                // Get the forms we want to add validation styles to
                var forms = document.getElementsByClassName('needs-validation');
                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>
</body>
</html>
