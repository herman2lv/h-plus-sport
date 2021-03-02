<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<section id="search" class="search">
  <header class="imageheader"></header>
  <div class="container">
    <h2 class="headline"><fmt:message key="ui.search.title"/></h2>
    <form action="controller" method="get">
      <label for="searchField" class="card-title">
        <fmt:message key="ui.search.searchYourProduct"/>
      </label>
      <input type="search" name="searchString" value="${searchString}" id="searchField">
      <input type="hidden" name="command" value="search">
      <input type="hidden" name="page" value="1"/>
      <input type="submit" value='<fmt:message key="ui.search.button.Search"/>'>
    </form>
  </div>
</section>
