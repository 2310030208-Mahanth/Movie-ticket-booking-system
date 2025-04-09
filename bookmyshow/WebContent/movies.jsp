<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movies - BookMyShow Clone</title>
    <style>
        .movie-card {
            border: 1px solid #ddd;
            padding: 15px;
            margin: 10px;
            border-radius: 5px;
            width: 200px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <h1>Now Showing</h1>
    <div id="movies-list">
        <c:forEach items="${movies}" var="movie">
            <div class="movie-card">
                <h3>${movie}</h3>
                <button>Book Tickets</button>
            </div>
        </c:forEach>
    </div>
</body>
</html>
