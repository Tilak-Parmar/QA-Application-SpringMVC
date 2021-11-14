<%-- 
    Document   : questions
    Created on : Apr 28, 2021, 3:41:13 AM
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


        <div class="container">
            <br />
            <h1>List of Your Questions</h1>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Question</th>
                        <th scope="col">Approved</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.questions}" var="question" varStatus="counter">
                        <tr>
                            <th scope="row">${counter.count}</th>
                            <td>${question.getQuestion()}</td>
                            <td>${question.isApproved()}</td>
                            <td><a href="${pageContext.request.contextPath}/editquestion.htm?queID=${question.getID()}" class="btn btn-warning" style="width: 100px;">Edit</a></td>
                            <td><a href="${pageContext.request.contextPath}/questions.htm?queID=${question.getID()}" class="btn btn-danger" style="width: 100px;">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <footer class="footer" style="width: 100%; height: 60px; line-height: 60px; background-color: #f5f5f5;">
            <div class="container">
                <span class="text-muted">Copyright 1999-2021 by Tilak Parmar. All Rights Reserved.</span>
            </div>
        </footer>
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
