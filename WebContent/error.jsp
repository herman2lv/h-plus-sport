<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<html>
<head>
<meta charset="UTF-8">
<title>H+ Sport - Error</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<%@ include file="header.jsp"%>

<section id="error" class="section">
  <div class="container">
    <div class="text-content">
      <h2 class="headline">Error occurred</h2>
      <p>We a sorry, but an internal server error has occurred. Try to access the resource later</p>
    </div>
  </div>
</section>
<section id="search" class="section">
  <header class="imageheader"></header>
  <div class="container">
    <h2 class="headline">Search Products</h2>
    <form action="search" method="get">
    <label class="card-title">Search your product</label>
    <input type="search" name="search">
    <input type="submit" value="Search">
    </form>
  </div>
</section>

<%@include file="footer.jsp"%>

</body>
</html>
