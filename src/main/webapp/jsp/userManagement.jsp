<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H+ Sport - <fmt:message key="ui.title.userManagement"/></title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

  <jsp:include page="header.jsp"/>

  <section id="orders" class="section">
    <div class="container">
      <h2 class="headline"><fmt:message key="ui.listOfUsers"/></h2>
      <c:if test="${users.size() > 0}">
        <table id="orderHistory">
          <tr>
            <th><fmt:message key="ui.no"/></th>
            <th><fmt:message key="ui.username"/></th>
            <th><fmt:message key="ui.firsName"/></th>
            <th><fmt:message key="ui.lastName"/></th>
            <th><fmt:message key="ui.dateOfBirth"/></th>
            <th><fmt:message key="ui.activity"/></th>
            <th><fmt:message key="ui.userRole"/></th>
            <th><fmt:message key="ui.manageUser"/></th>
          </tr>
          <c:forEach items="${users}" var="user" varStatus="counter">
            <tr>
              <td>${counter.count}</td>
              <td>${user.username}</td>
              <td>${user.firstName}</td>
              <td>${user.lastName}</td>
              <td><fmt:formatDate value="${user.dateOfBirth}" pattern="yyyy-MM-dd"/></td>
              <td>${user.activity}</td>
              <td>
                  ${user.role}
              </td>
              <td>
                <c:if test="${user.role != 1}">
                  <form action="controller" method="post">
                    <input type="hidden" name="command" value="removeUser">
                    <input type="hidden" name="order" value="${user.username}">
                    <input type="submit" value='<fmt:message key="ui.removeUser"/>'>
                  </form>
                </c:if>
                <c:if test="${user.role == 3}">
                  <form action="controller" method="post">
                    <input type="hidden" name="command" value="makeManager">
                    <input type="hidden" name="order" value="${user.username}">
                    <input type="submit" value='<fmt:message key="ui.changeRole.manager"/>'>
                  </form>
                </c:if>
                <c:if test="${user.role == 2}">
                  <form action="controller" method="post">
                    <input type="hidden" name="command" value="makeCustomer">
                    <input type="hidden" name="order" value="${user.username}">
                    <input type="submit" value='<fmt:message key="ui.changeRole.customer"/>'>
                  </form>
                </c:if>
              </td>
            </tr>
          </c:forEach>
        </table>
        <br/>
      </c:if>
      <c:if test="${users.size() == 0}">
        <p><fmt:message key="ui.emptyUserList"/></p>
      </c:if>
    </div>
  </section>

  <jsp:include page="searchSection.jsp"/>
  <jsp:include page="footer.jsp"/>

</body>
</html>
