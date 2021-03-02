<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html>
<head>
  <meta charset="UTF-8">
  <title>H+ Sport - <fmt:message key="ui.title.error"/></title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<jsp:include page="header.jsp"/>

<section id="error" class="section">
  <div class="container">
    <div class="text-content">
      <h2 class="headline"><fmt:message key="ui.error.headline"/></h2>
      <p><fmt:message key="ui.generalErrorMessage"/></p>
    </div>
  </div>
</section>

<jsp:include page="searchSection.jsp"/>
<jsp:include page="footer.jsp"/>

</body>
</html>
