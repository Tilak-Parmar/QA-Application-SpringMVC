<%-- 
    Document   : login
    Created on : Apr 20, 2021, 6:13:27 PM
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
                <a href="${pageContext.request.contextPath}/questions" class="btn btn-primary" style="width: 200px; margin-right: 10px;">Your Questions</a>
                <a href="${pageContext.request.contextPath}/changepassword.htm" class="btn btn-success" style="width: 200px; margin-right: 10px;">Change Password</a>
                <a href="${pageContext.request.contextPath}/logout.htm" class="btn btn-danger" style="width: 300px;">Logout ${sessionScope.user.getEmail()}</a>
            </div>
        </nav>

        <c:if test="${requestScope.success}">
            <div class="alert alert-success alert-dismissible fade show">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Success!</strong> ${requestScope.successmsg}
            </div>
        </c:if>

        <div class="container">
            <div style="border-bottom: 1px solid gray; border-radius: 5px;">
                <form action="${pageContext.request.contextPath}/askquestion.htm" method="POST" class="needs-validation" novalidate>
                    <h2 class="card-body">Ask a Question</h2>
                    <div class="form-group">
                        <input type="text" class="form-control card-body" id="question" placeholder="What's your doubt?"
                               name="question" required>
                        <div class="invalid-feedback">Required</div>
                    </div>
                    <button type="submit" class="btn btn-success" style="width: 100px; margin-bottom: 10px; margin-left: 20px;">Submit</button>
                </form>
            </div>

            <c:forEach items="${requestScope.questions}" var="question">
                <!-- START This part is created when a Question is submitted -->
                <div class="card">
                    <form action="${pageContext.request.contextPath}/submitanswer.htm" method="POST" class="needs-validation" novalidate>
                        <h2 class="card-body">${question.getQuestion()}</h2>
                        <h6 class="card-body">- ${requestScope.userDao.searchUserbyID(question.getUserID()).getFirstName()} ${requestScope.userDao.searchUserbyID(question.getUserID()).getLastName()}</h6>
                        <div class="form-group">
                            <textarea class="form-control card-body" rows="5" placeholder="What are your thoughts on this?" required name="answer"></textarea>
                            <div class="invalid-feedback">Required</div>
                        </div>
                        <input type="hidden" name="queID" value="${question.getID()}">
                        <button type="submit" class="btn btn-danger" style="width: 100px; margin-bottom: 10px; margin-left: 20px;">Reply</button>
                    </form>
                    <!-- START Repeated for every Answer Submit -->
                    <c:forEach items="${question.getAnswersList()}" var="answer">
                        <pre class="card-body" style="white-space: pre-wrap;">${answer.getAnswer()}</pre>
                        <c:if test="${answer.getUserID() eq sessionScope.user.getID()}">
                            <a href="${pageContext.request.contextPath}/editanswer.htm?ansID=${answer.getID()}" class="btn btn-warning" style="width: 100px; margin-bottom: 10px; margin-left: 20px;">Edit</a>
                        </c:if>
                        <p class="card-body" style="border-bottom: 1px solid black; border-radius: 5px;">- ${requestScope.userDao.searchUserbyID(answer.getUserID()).getFirstName()} ${requestScope.userDao.searchUserbyID(answer.getUserID()).getLastName()}</p>
                    </c:forEach>
                    <!-- END Answer End -->
                </div>
            </c:forEach>

        </div>

        <footer class="footer" style="width: 100%; height: 60px; line-height: 60px; background-color: #f5f5f5;">
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
