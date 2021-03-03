<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>H+ Sport - <fmt:message key="ui.title.search"/></title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<jsp:include page="header.jsp"/>
<jsp:include page="searchSection.jsp"/>

<section id="orders" class="section">
  <div class="container">
    <h2 class="headline"><fmt:message key="ui.headline.products"/></h2>
    <p>
        <span id="size">
          <fmt:message key="ui.itemsInCart"/>
          <c:if test="${cart != null}">
            ${cart.size()}
          </c:if>
          <c:if test="${cart == null}">
            0
          </c:if>
          <br/>
        </span>
    </p>
    <c:if test="${numberOfItems > 0}">
      <fmt:message key="ui.search.foundItems"/> ${numberOfItems}

      <jsp:include page="paginator.jsp"/>

      <table id="orderHistory">
        <tr>
          <th><fmt:message key="ui.no"/></th>
          <th><fmt:message key="ui.productName"/></th>
          <th><fmt:message key="ui.image"/></th>
          <th id="columnToLimit"><fmt:message key="ui.description"/></th>
          <th><fmt:message key="ui.cost"/></th>
          <th></th>
        </tr>
        <c:forEach items="${products}" var="product" varStatus="counter">
          <tr>
            <td>${counter.count + index}</td>
            <td><c:out value="${product.name}"/></td>
            <td><img id="pic1" width="200px" height="150px" src="<c:out value="${product.productImgPath}"/>"></td>
            <td><c:out value="${product.description}"/></td>
            <td>$<c:out value="${product.cost}"/></td>
            <td>
              <form method="get" action="controller">
                <input type="hidden" name="product" value="${product.productId}">
                <input type="hidden" name="page" value="${page}"/>
                <input type="hidden" name="command" value="add_product">
                <button><fmt:message key="ui.addToCart"/></button>
              </form>
            </td>
          </tr>
        </c:forEach>
      </table>

      <jsp:include page="paginator.jsp"/>

    </c:if>
  </div>
</section>
<br/>

<jsp:include page="footer.jsp"/>

</body>
</html>
