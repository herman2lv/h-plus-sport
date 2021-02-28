<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H+ Sport - <fmt:message key="ui.cart"/></title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

  <jsp:include page="header.jsp"/>

  <section id="orders" class="section">
    <c:if test="${cart.size() > 0}">
      <div class="container">
        <h2 class="headline"><fmt:message key="ui.cart"/></h2>
        <p>${orderStatus}</p>
        <table id="orderHistory">
          <tr>
            <th><fmt:message key="ui.no"/></th>
            <th><fmt:message key="ui.productName"/></th>
            <th><fmt:message key="ui.image"/></th>
            <th><fmt:message key="ui.cost"/></th>
            <th><fmt:message key="ui.number"/></th>
            <th></th>
          </tr>
          <c:forEach items="${groupedProducts}" var="productEntry" varStatus="counter">
            <tr>
              <td>${counter.count}</td>
              <td>${productEntry.key.name}</td>
              <td>
                <img width="100px" height="75px" src="${productEntry.key.productImgPath}">
              </td>
              <td>$${productEntry.key.cost}</td>
              <td>${productEntry.value}</td>
              <td>
                <form action="controller" method="put">
                  <input type="hidden" name="command" value="removeProduct"/>
                  <input type="hidden" name="product" value="${productEntry.key.productId}"/>
                  <input type="submit" value='<fmt:message key="ui.removeFromCart"/>'/>
                </form>
              </td>
            </tr>
          </c:forEach>
          <tr>
            <td></td>
            <td></td>
            <td><fmt:message key="ui.totalCost"/></td>
            <td>$${cartTotalCost}</td>
            <td></td>
            <td>
              <form action="controller" method="put">
                <input type="hidden" name="command" value="makeOrder"/>
                <input type="submit" value='<fmt:message key="ui.makeOrder"/>'/>
              </form>
            </td>
          </tr>
        </table>
        <br/>
      </div>
    </c:if>
    <c:if test="${(cart.size() == 0) or (cart == null)}">
      <div class="container">
        <h2 class="headline"><fmt:message key="ui.cart"/></h2>
        <p><fmt:message key="ui.emptyCart"/></p>
      </div>
    </c:if>
  </section>

  <jsp:include page="searchSection.jsp"/>
  <jsp:include page="footer.jsp"/>

</body>
</html>
