<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>

<table class="paginator">
  <tr>
    <td>
      <c:if test="${page > 1}">
      <form action="controller" method="get">
        <input type="hidden" name="command" value="${paginatorCommand}"/>
        <input type="hidden" name="page" value="1"/>
        <input type="submit" value='<fmt:message key="ui.button.firstPage"/>'/>
      </form>
    </td>
    <td>
      <form action="controller" method="get">
        <input type="hidden" name="command" value="${paginatorCommand}"/>
        <input type="hidden" name="page" value="${page - 1}"/>
        <input type="submit" value='<fmt:message key="ui.button.previousPage"/>'/>
      </form>
      </c:if>
    </td>
    <td>
      <c:if test="${numberOfPages > 1}">
        <fmt:message key="ui.currentPage"/> ${page} / ${numberOfPages}
      </c:if>
    </td>
    <td>
      <c:if test="${page < numberOfPages}">
      <form action="controller" method="get">
        <input type="hidden" name="command" value="${paginatorCommand}"/>
        <input type="hidden" name="page" value="${page + 1}"/>
        <input type="submit" value='<fmt:message key="ui.button.nextPage"/>'/>
      </form>
    </td>
    <td>
      <form action="controller" method="get">
        <input type="hidden" name="command" value="${paginatorCommand}"/>
        <input type="hidden" name="page" value="${numberOfPages}"/>
        <input type="submit" value='<fmt:message key="ui.button.lastPage"/>'/>
      </form>
      </c:if>
    </td>
  </tr>
</table>
