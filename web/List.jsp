<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>News</title>


    <!-- Latest compiled and minified CSS -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://bootstrap-3.ru/examples/dashboard/dashboard.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet"
          href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>

<%@include file="header.jsp"%>
<c:set var="currentUser" value="${session.getAttribute('currentSessionUser')}"/>
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

        <form method="post" action="/web/controller">
            <div class="col-sm-10 col-md-offset-2 main">
                <h2 class="sub-header">News List</h2>
                <div>
                    <table class="table table-striped">
                        <c:forEach var="news" items="${newsList}">
                            <tr>
                                <td>
                                    <div>
                                        <div>
                                            <a href="/controller?id=${news.getId()}&command=ViewCommand" ><h2>${news.getName()}</h2></a>

                                        </div>
                                        <div class="row placeholders">
                                            <div class="col-sm-7">
                                                <p align="justify">${news.getText()}</p>
                                            </div>
                                        </div>
                                        <span class="pull-left">${news.author.name}</span>
                                        <%--<span class="pull-right">
                                            <a href="/controller?id=${news.getId()}&command=ViewCommand" class="btn btn-primary btn-sm">View</a>
                                            &lt;%&ndash;<a href="/controller?id=${news.getId()}&command=EditForm" class="btn btn-primary btn-sm">Edit</a>&ndash;%&gt;
                                           &lt;%&ndash; <label><input name="delete" type="checkbox" value="${news.getId()}">Delete</label>&ndash;%&gt;
                                        </span>--%>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                  <%--  <p align="right">
                        <input type="hidden" name="command" value="DeleteSelected">
                        <input type="submit" class="btn btn-primary btn-large" name="command" value="Delete selected" />
                    </p>--%>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>