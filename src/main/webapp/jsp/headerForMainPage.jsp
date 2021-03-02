<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<header id="home" class="header">
  <jsp:include page="navbar.jsp"/>
  <div class="container tagline">
    <h2 class="headline"><fmt:message key="ui.ourMission"/></h2>
    <p><fmt:message key="ui.ourMissionDefinition"/></p>
  </div>
</header>
