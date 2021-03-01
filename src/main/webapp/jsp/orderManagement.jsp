<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H+ Sport - <fmt:message key="ui.title.orderManagement"/></title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

  <jsp:include page="header.jsp"/>

  <section id="orders" class="section">
    <div class="container">
      <h2 class="headline"><fmt:message key="ui.listOfOrders"/></h2>
      <c:if test="${orders.size() > 0}">
        <table id="orderHistory">
          <tr>
            <th><fmt:message key="ui.orderNo"/></th>
            <th><fmt:message key="ui.username"/></th>
            <th><fmt:message key="ui.orderId"/></th>
            <th><fmt:message key="ui.orderDate"/></th>
            <th><fmt:message key="ui.products"/></th>
            <th><fmt:message key="ui.cost"/></th>
            <th><fmt:message key="ui.orderStatus"/></th>
          </tr>
          <c:forEach items="${orders}" var="order" varStatus="counter">
            <tr>
              <td>${counter.count}</td>
              <td>${order.username}</td>
              <td>${order.orderId}</td>
              <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/></td>
              <td>
                <table>
                  <c:forEach items="${order.listOfProducts}" var="product">
                    <tr>
                    <td>${product.key.name}</td>
                    <td> x ${product.value}</td>
                    </tr>
                  </c:forEach>
                </table>
              </td>
              <td>$${order.orderCost}</td>
              <td>
                <c:if test="${order.status}">
                  <fmt:message key="ui.orderStatus.approved"/>
                  <form action="controller" method="post">
                    <input type="hidden" name="command" value="rejectOrder">
                    <input type="hidden" name="order" value="${order.orderId}">
                    <input type="submit" value='<fmt:message key="ui.reject"/>'>
                  </form>
                </c:if>
                <c:if test="${!order.status}">
                  <fmt:message key="ui.orderStatus.pending"/>
                  <form action="controller" method="post">
                    <input type="hidden" name="command" value="removeOrderByManager">
                    <input type="hidden" name="order" value="${order.orderId}">
                    <input type="submit" value='<fmt:message key="ui.removeFromOrders"/>'>
                  </form>
                  <form action="controller" method="post">
                    <input type="hidden" name="command" value="approveOrder">
                    <input type="hidden" name="order" value="${order.orderId}">
                    <input type="submit" value='<fmt:message key="ui.approve"/>'>
                  </form>
                </c:if>
              </td>
            </tr>
          </c:forEach>
        </table>
        <br/>
      </c:if>
      <c:if test="${orders.size() == 0}">
        <p><fmt:message key="ui.emptyOrderList"/></p>
      </c:if>
    </div>
  </section>

  <jsp:include page="searchSection.jsp"/>
  <jsp:include page="footer.jsp"/>

</body>
</html>
