<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<html>
<head>
<meta charset="UTF-8">
<title>H+ Sport - Error</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<jsp:include page="header.jsp"/>

<section id="error" class="section">
  <div class="container">
    <div class="text-content">
      <h2 class="headline">Error occurred</h2>
      <p>We a sorry, but an internal server error has occurred. Try to access the resource later</p>
    </div>
  </div>
</section>

<jsp:include page="searchSection.jsp"/>
<jsp:include page="footer.jsp"/>

</body>
</html>
