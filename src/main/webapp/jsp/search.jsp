<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>H+ Sport - Search</title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

  <jsp:include page="header.jsp"/>
  <jsp:include page="searchSection.jsp"/>
    
	<section id="orders" class="section">
		<div class="container">
			<h2 class="headline">Products</h2>
			<p>
				H+ Sport is <em>dedicated to creating</em> eco-friendly,
				high-quality, nutrient-rich, nutritional products that <em>enhance
					active lifestyles</em>.
			</p>
			<p>
        <span id="size">
          Items in <a href="cart">Cart<a>:
          <c:if test="${cart != null}">
            ${cart.size()}
          </c:if>
          <c:if test="${cart == null}">
            0
          </c:if>
          <br/>
          Found items in catalog: ${products.size()}
        </span>
      </p>
        <c:if test="${products.size() > 0}">
          <table id="orderHistory">
            <tr>
              <th>No.</th>
              <th>Product Name</th>
              <th>Image</th>
              <th id="columnToLimit">Description</th>
              <th>Cost</th>
              <th></th>
            </tr>
            <c:forEach items="${products}" var="product" varStatus="counter">
              <tr>
                <td>${counter.count}</td>
                <td>${product.name}</td>
                <td><img id="pic1" width="200px" height="150px" src="${product.productImgPath}"></td>
                <td>${product.description}</td>
                <td>$${product.cost}</td>
                <td>
                  <form method="get" action="controller">
                    <input type="hidden" name="product" value="${product.productId}">
                    <input type="hidden" name="command" value="add_product">
                    <button>Add to Cart</button>
                  </form>
                </td>
              </tr>
            </c:forEach>
          </table>
        </c:if>
    </div>
	</section>
	<br/>

  <jsp:include page="footer.jsp"/>

</body>
</html>
