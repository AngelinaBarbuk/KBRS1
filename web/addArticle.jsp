<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.beans.User" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Title</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link href="http://bootstrap-3.ru/examples/dashboard/dashboard.css" rel="stylesheet">


    <link rel="stylesheet" href="css/image_preview.css">
    <link type="text/css" rel="stylesheet"
          href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>

<%@include file="header.jsp"%>
<c:set var="currentUser" value='<%=session.getAttribute("currentSessionUser")%>'/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="sidebar-brand"> <h3>News Menu</h3></li>
                <li class="active"><a href="/controller?command=ListCommand">News List</a></li>
                <li><a href="/controller?command=AddFormCommand">Add Article</a></li>
                <li><a href="/login.jsp">Log out</a></li>
            </ul>
        </div>
        <div class="col-sm-8 col-md-offset-2 main">
            <h2 class="sub-header">Add</h2>
            <div class="container-fluid" >
                <form method="post" action="/controller"  role="form" class="feedback-form-1">

                    <div class="col-sm-6">

                        <div class="form-group row">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" id="title" name="title" placeholder="Title"/>
                        </div>
                        <div class="form-group row">
                            <label for="content">Content:</label>
                            <textarea class="form-control" id="content" name="content" placeholder="Content" rows="5"></textarea>
                        </div>
                        <input type="hidden" name="command" value="AddCommand">
                        <input type="submit" class="btn btn-primary" value="Save"/>
                        <a href="/controller?command=ListCommand" class="btn btn-default">Cancel</a>
                    </div>
                    <div class="col-sm-6">
                        <c:forEach items="${users}" var="oneUser">
                            <c:if test="${currentUser.id != oneUser.id}">
                                <span style="font-size: large; margin-bottom: 5px;">${oneUser.name}</span><br>
                                <c:forEach items="${permissionTypes}" var="type">
                                   <label> <input type="checkbox" name="check_${oneUser.id}" value="${type.id}" >${type.name}</label><br>
                                </c:forEach>
                                <br>
                            </c:if>

                        </c:forEach>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

</body>
</html>
