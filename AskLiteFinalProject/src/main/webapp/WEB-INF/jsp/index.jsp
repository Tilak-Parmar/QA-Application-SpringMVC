<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
            <form action="${pageContext.request.contextPath}/index.htm" method="post" class="form-inline my-2 my-lg-0 needs-validation" novalidate>
                <input class="form-control mr-sm-2" type="email" placeholder="Email" name="email" required title="'<' and '>' characters will be converted to '_'">
                <input class="form-control mr-sm-2" type="password" placeholder="Password" name="password" title="'<' and '>' characters will be converted to '_'" required>
                <button type="submit" class="btn btn-success my-2 my-sm-0 mr-sm-2" type="button" style="width: 100px;">Login</button>
            </form>
            <button type="button" class="btn btn-primary my-2 my-sm-0 mr-sm-2" style="width: 100px;" data-toggle="modal"
                    data-target="#myModal">Signup</button>

        </div>
    </nav>

    <c:if test="${requestScope.error}">
        <div class="alert alert-warning alert-dismissible fade show">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Error!</strong> ${requestScope.errormsg}
        </div>
    </c:if>


    <!-- The Modal -->
    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">AskLite Signup</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/signup.htm" method="post" class="needs-validation" novalidate>
                        <div class="form-group">
                            <label for="fname">First Name:</label>
                            <input type="text" class="form-control" id="fname" placeholder="First Name" name="fname" title="'<' and '>' characters will be converted to '_'" required >
                            <div class="invalid-feedback">Required</div>
                        </div>
                        <div class="form-group">
                            <label for="lname">Last Name:</label>
                            <input type="text" class="form-control" id="lname" placeholder="Last Name" name="lname" title="'<' and '>' characters will be converted to '_'" required>
                            <div class="invalid-feedback">Required</div>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="uname" placeholder="Email" name="email" title="'<' and '>' characters will be converted to '_'" required>
                            <div class="invalid-feedback">Please check the email format</div>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" id="pwd" placeholder="Password" name="pwd" title="'<' and '>' characters will be converted to '_'" required>
                            <div class="invalid-feedback">Required</div>
                        </div>
                        <div class="form-group form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" name="remember" required> I agree on the Terms & Conditions of AskLite
                                <div class="invalid-feedback">Check this checkbox to continue</div>
                            </label>
                        </div>
                        <button type="submit" class="btn btn-success" style="width: 100px;">Signup</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div style="border-bottom: 1px solid gray; border-radius: 5px;">

            <h2 class="card-body">Ask a Question</h2>
            <div class="form-group">
                <input type="text" class="form-control card-body" id="question" placeholder="Have something in mind? Login or SignUp first..." name="question" maxlength="255" required>
                <div class="invalid-feedback">Required</div>
            </div>
            <button title="Please Login to Ask a Question" class="btn btn-success" style="width: 100px; margin-bottom: 10px; margin-left: 20px;" disabled>Submit</button>

        </div>

        <c:forEach items="${requestScope.questions}" var="question">
            <!-- START This part is created when a Question is submitted -->
            <div class="card">
                
                    <h2 class="card-body">${question.getQuestion()}</h2>
                    <h6 class="card-body">- ${requestScope.userDao.searchUserbyID(question.getUserID()).getFirstName()} ${requestScope.userDao.searchUserbyID(question.getUserID()).getLastName()}</h6>
                    <div class="form-group">
                        <textarea class="form-control card-body" rows="5" placeholder="Want to give your thoughts about this? Login or SignUp first..." required></textarea>
                        <div class="invalid-feedback">Required</div>
                    </div>
                    <button class="btn btn-danger" style="width: 100px; margin-bottom: 10px; margin-left: 20px;" disabled>Reply</button>
                
                <!-- START Repeated for every Answer Submit -->
                <c:forEach items="${question.getAnswersList()}" var="answer">
                    <pre class="card-body" style="white-space: pre-wrap;">${answer.getAnswer()}</pre>
                    <p class="card-body" style="border-bottom: 1px solid black; border-radius: 5px;">- ${requestScope.userDao.searchUserbyID(answer.getUserID()).getFirstName()} ${requestScope.userDao.searchUserbyID(answer.getUserID()).getLastName()}</p>
                </c:forEach>
                <!-- END Answer End -->
            </div>
        </c:forEach>
        <!-- END Question submit -->
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
