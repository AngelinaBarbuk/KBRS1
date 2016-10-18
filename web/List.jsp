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

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="sidebar-brand"> <h3>News Menu</h3></li>
                <li class="active"><a href="/controller?command=GetList">News List</a></li>
                <li><a href="/controller?command=AddForm">Add News</a></li>
            </ul>
        </div>

        <form method="post" action="/web/controller">
            <div class="col-sm-10 col-md-offset-2 main">
                <h2 class="sub-header">News List</h2>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <c:forEach var="news" items="${newsList}">
                            <tr>
                                <td>
                                    <div>
                                        <div>
                                            <h2>${news.getName()}</h2>
                                        </div>
                                        <div class="row placeholders">
                                            <div class="col-sm-7">
                                                <p align="justify">${news.getText()}</p>
                                            </div>
                                        </div>
                                        <div align="right">
                                            <a href="/controller?id=${news.getId()}&command=View" class="btn btn-primary btn-sm">View</a>
                                            <%--<a href="/controller?id=${news.getId()}&command=EditForm" class="btn btn-primary btn-sm">Edit</a>--%>
                                            <label><input name="delete" type="checkbox" value="${news.getId()}">Delete</label>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <p align="right">
                        <input type="hidden" name="command" value="DeleteSelected">
                        <input type="submit" class="btn btn-primary btn-large" name="command" value="Delete selected" />
                    </p>
                </div>
            </div>
        </form>
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