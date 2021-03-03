<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>H+ Sport - <fmt:message key="ui.title.editProduct"/></title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:include page="header.jsp"/>

<section id="orders" class="section">
  <div class="container">
    <h2 class="headline"><fmt:message key="ui.header.editProduct"/></h2>
    <form action="controller" method="post" enctype="multipart/form-data">
      <input type="hidden" name="command" value="edit_product"/>
      <input type="hidden" name="page" value="${page}"/>
      <input type="hidden" name="product" value="${product.productId}"/>
      <label for="productName"><fmt:message key="ui.productName"/></label><br/>
      <input type="text" name="productName" id="productName" value="${product.name}" required>
      <br/>
      <label for="cost"><fmt:message key="ui.cost"/></label><br/>
      <input type="number" min="00.01" max="999" step=".01" name="cost" id="cost" value="${product.cost}" required>
      <br/>
      <label for="currentImage"><fmt:message key="ui.currentImage"/></label><br/>
      <img id="currentImage" width="200px" height="150px" src="<c:out value="${product.productImgPath}"/>">
      <br/>
      <label for="image"><fmt:message key="ui.newImage"/></label><br/>
      <input type="checkbox" name="changeImage" value="true">
      <input type="file" accept="image/jpeg" name="image" id="image">
      <br/>
      <label for="description"><fmt:message key="ui.description"/></label><br/>
      <textarea rows="5" cols="25" name="description" id="description" required>${product.description}</textarea>
      <br/>
      <input type="submit" value='<fmt:message key="ui.editProduct.submit"/>' id="submit">
    </form>
  </div>
</section>

<jsp:include page="searchSection.jsp"/>
<jsp:include page="footer.jsp"/>

</body>
</html>
