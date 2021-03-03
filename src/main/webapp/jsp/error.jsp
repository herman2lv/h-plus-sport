<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <p>
        <fmt:message key="ui.error.code"/> ${errorCode}
        <c:if test="${errorCode == null}">
          <fmt:message key="ui.errorCode500"/>
        </c:if>
      </p>
      <p>
        ${errorMessage}
        <c:if test="${errorMessage == null}">
          <fmt:message key="ui.errorMessage500"/>
        </c:if>
      </p>
    </div>
  </div>
</section>

<jsp:include page="searchSection.jsp"/>
<jsp:include page="footer.jsp"/>

</body>
</html>
