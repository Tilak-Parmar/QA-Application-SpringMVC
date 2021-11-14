<%-- 
    Document   : signup
    Created on : Apr 27, 2021, 3:31:20 PM
    Author     : tilak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="2; url=${pageContext.request.contextPath}/index.htm" />
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logging In...</title>
    </head>
    <body>
        <h4>${requestScope.msg} You will be redirected to the home page in 2 seconds...</h4>
        <p>If you're not automatically redirected <a href="${pageContext.request.contextPath}">Click Here</p>
    </body>
</html>
