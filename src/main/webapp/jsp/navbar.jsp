<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<nav class="nav" role="navigation">
  <div class="container nav-elements">
    <div class="branding">
      <a href="#home"><img src="images/hpluslogo.svg" alt="Logo - H Plus Sports"></a>
    </div>
    <ul class="navbar">
      <li>
        <form action="controller" method="get">
          <input type="hidden" name="command" value="home"/>
          <input type="submit" value='<fmt:message key="ui.header.home"/>'/>
        </form>
      </li>
      <li>
        <form action="controller" method="get">
          <input type="hidden" name="command" value="search"/>
          <input type="hidden" name="searchString" value="">
          <input type="hidden" name="page" value="1"/>
          <input type="submit" value='<fmt:message key="ui.header.catalog"/>'/>
        </form>
      </li>
      <li>
        <form action="controller" method="get">
          <input type="hidden" name="command" value="cart"/>
          <input type="submit" value='<fmt:message key="ui.header.cart"/>'/>
        </form>
      </li>
      <c:if test="${sessionScope.username == null}">
        <li>
          <form action="controller" method="get">
            <input type="hidden" name="command" value="login_page"/>
            <input type="submit" value='<fmt:message key="ui.header.login"/>'/>
          </form>
        </li>
        <li>
          <form action="controller" method="get">
            <input type="hidden" name="command" value="register_user_page"/>
            <input type="submit" value='<fmt:message key="ui.header.newUser"/>'/>
          </form>
        </li>
      </c:if>
      <c:if test="${sessionScope.username != null}">
        <li>
          <form action="controller" method="get">
            <input type="hidden" name="command" value="orders"/>
            <input type="hidden" name="page" value="1"/>
            <input type="submit" value='<fmt:message key="ui.header.myOrders"/>'/>
          </form>
        </li>
        <c:if test="${sessionScope.userRole == 2}">
          <li>
            <form action="controller" method="get">
              <input type="hidden" name="command" value="order_management"/>
              <input type="hidden" name="page" value="1"/>
              <input type="submit" value='<fmt:message key="ui.header.orderManagement"/>'/>
            </form>
          </li>
        </c:if>
        <c:if test="${sessionScope.userRole == 1}">
          <li>
            <form action="controller" method="get">
              <input type="hidden" name="command" value="product_management"/>
              <input type="hidden" name="page" value="1"/>
              <input type="submit" value='<fmt:message key="ui.header.productManagement"/>'/>
            </form>
          </li>
          <li>
            <form action="controller" method="get">
              <input type="hidden" name="command" value="user_management"/>
              <input type="hidden" name="page" value="1"/>
              <input type="submit" value='<fmt:message key="ui.header.userManagement"/>'/>
            </form>
          </li>
        </c:if>
        <li>
          <form action="controller" method="get">
            <input type="hidden" name="command" value="profile"/>
            <input type="submit" value='<fmt:message key="ui.header.myProfile"/>'/>
          </form>
        </li>
        <li>
          <form action="controller" method="get">
            <input type="hidden" name="command" value="logout"/>
            <input type="submit" value='<fmt:message key="ui.header.logout"/>'/>
          </form>
        </li>
      </c:if>
      <li>
        <form action="controller" method="get">
          <input type="hidden" name="command" value="search"/>
          <input type="hidden" name="page" value="1"/>
          <input type="submit" value='<fmt:message key="ui.header.search"/>'/>
        </form>
      </li>
      <li>
        <form action="controller" method="get">
          <input type="hidden" name="command" value="redirect"/>
          <input type="submit" value='<fmt:message key="ui.header.gitHub"/>'/>
        </form>
      </li>
      <li>
        <form action="controller" method="get">
          <input type="hidden" name="command" value="language"/>
          <input type="hidden" name="language" value='<fmt:message key="ui.language"/>'/>
          <input type="submit" value="EN/RU"/>
        </form>
      </li>
    </ul>
  </div>
</nav>
