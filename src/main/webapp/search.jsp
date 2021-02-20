<!DOCTYPE html>
<%@page errorPage="error.jsp" isErrorPage="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>H+ Sport</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

    <jsp:include page="header.jsp"/>
    <jsp:include page="searchSection.jsp"/>
    
	<section id="products" class="section">
		<div class="container">
			<h2 class="headline">Products</h2>
			<p>
				H+ Sport is <em>dedicated to creating</em> eco-friendly,
				high-quality, nutrient-rich, nutritional products that <em>enhance
					active lifestyles</em>.
			</p>
			<p><span id="size">Items in Cart: ${cartSize}</span></p>
		</div>
		<div class="productContainer">
            <c:forEach items="${products}" var="product">
				<form method="get" action="addProduct">
	                <div class="productContainerItem">
	                    <img id="pic1" src="${product.productImgPath}">
	                    <input type="text" name="product" value="${product.name}"><br/>
	                    <button>Add to Cart</button>
	                </div>
	            </form>
            </c:forEach>
		</div>
	</section>
	<br/>
	<br/>
	
    <jsp:include page="footer.jsp"/>

</body>
</html>