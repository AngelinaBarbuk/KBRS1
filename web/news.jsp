<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Title</title>

    <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <link href="http://bootstrap-3.ru/examples/dashboard/dashboard.css" rel="stylesheet">
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
        <div class="col-sm-10 col-md-offset-2 main">
            <h2 class="sub-header">News</h2>
            <div class="table">
                <div>
                    <div>
                        <h2>${article.getName()}</h2>
                    </div>
                    <div class="row placeholders">
                        <div class="col-sm-7">
                            <p align="justify">${article.getText()}</p>
                        </div>
                    </div>
                    <div align="left">
                        <form method="post" action="/list">
                            <input type="hidden" name="id" value="${article.getId()}">
                            <a href="/controller?id=${article.getId()}&number=${number}&command=EditFormCommand" class="btn btn-primary btn-sm ${currentUser.checkPermission(article.id, 2) ? '' : 'hide'}">Edit</a>
                            <a href="/controller?delete=${article.getId()}&command=DeleteCommand" class="btn btn-primary btn-sm ${currentUser.checkPermission(article.id, 1) ? '' : 'hide'}">Delete</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="../../dist/js/bootstrap.min.js"></script>
<script src="../../assets/js/docs.min.js"></script>
</body>
</html>
