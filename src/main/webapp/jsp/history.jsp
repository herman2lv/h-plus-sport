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
<title>H+ Sport - <fmt:message key="ui.title.myOrders"/></title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

  <jsp:include page="header.jsp"/>

  <section id="orders" class="section">
    <div class="container">
      <h2 class="headline"><fmt:message key="ui.orderHistory"/></h2>
      <c:if test="${orders.size() > 0}">
        <table id="orderHistory">
          <tr>
            <th><fmt:message key="ui.orderNo"/></th>
            <th><fmt:message key="ui.orderDate"/></th>
            <th><fmt:message key="ui.products"/></th>
            <th><fmt:message key="ui.cost"/></th>
          </tr>
          <c:forEach items="${orders}" var="order" varStatus="counter">
            <tr>
              <td>${counter.count}</td>
              <td><fmt:formatDate value="${order.orderDate}" pattern="YYYY-MM-dd"/></td>
                <td>
                  <table>
                    <c:forEach items="${order.listOfProducts}" var="product">
                      <tr>
                      <td>${product.key.name}</td>
                      <td><img width="100px" height="75px"
                                  src="${product.key.productImgPath}"></td>
                      <td> x ${product.value}</td>
                      </tr>
                    </c:forEach>
                  </table>
                </td>
              <td>$${order.orderCost}</td>
            </tr>
          </c:forEach>
        </table>
        <br/>
      </c:if>
      <c:if test="${orders.size() == 0}">
        <p><fmt:message key="ui.emptyHistory"/></p>
      </c:if>
    </div>
  </section>

  <jsp:include page="searchSection.jsp"/>
  <jsp:include page="footer.jsp"/>

</body>
</html>
