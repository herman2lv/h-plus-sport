<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.hplussport.com/apptaglib" prefix="app" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html>
<head>
  <link rel="stylesheet" href="css/style.css">
  <meta charset="ISO-8859-1">
  <title>H+ Sport - <fmt:message key="ui.title.login"/></title>
</head>
<body>

<jsp:include page="header.jsp"/>

<section>
  <app:insertDate/>
</section>

<section id="login" class="section">
  <div class="container tagline">
    <c:if test="${error != null}">
      <em>${error}</em>
      <br/>
    </c:if>
    <em><fmt:message key="ui.login.header"/></em>
    <form action="controller" method="post">
      <input type="hidden" name="command" value="login">
      <label for="username"><fmt:message key="ui.label.username"/></label>
      <input type="text" name="username" id="username" required>
      <br/>
      <label for="password"><fmt:message key="ui.label.password"/></label>
      <input type="password" name="password" id="password" required>
      <br/>
      <input type="submit" value='<fmt:message key="ui.button.login"/>'>
    </form>
  </div>
</section>

<jsp:include page="searchSection.jsp"/>
<jsp:include page="footer.jsp"/>

</body>
</html>
