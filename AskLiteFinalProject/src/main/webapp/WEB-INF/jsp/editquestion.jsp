<%-- 
    Document   : editquestion
    Created on : Apr 28, 2021, 12:37:53 PM
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


        <div class="container">
            <div style="border-bottom: 1px solid gray; border-radius: 5px;">
                <form action="${pageContext.request.contextPath}/questions.htm" method="POST" class="needs-validation" novalidate>
                    <h2 class="card-body">Edit Your Question</h2>
                    <div class="form-group">
                        <input type="text" class="form-control card-body" id="question" placeholder="What's your doubt?"
                               name="question" value="${requestScope.question.getQuestion()}" required>
                        <div class="invalid-feedback">Required</div>
                    </div>
                    <input type="hidden" name="queID" value="${requestScope.question.getID()}">
                    <button type="submit" class="btn btn-success" style="width: 100px; margin-bottom: 10px; ">Edit</button>
                </form>
            </div>
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
