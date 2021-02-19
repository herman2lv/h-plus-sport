<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.epam.hplus.beans.Product" 
        errorPage="error.jsp" isErrorPage="false"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>H+ Sport</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <%@ include file="header.jsp"%>
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
		<%
		List<Product> products = (List) request.getAttribute("products");
		for (Product product : products) {
		%>
			<form method="get" action="addProduct">
                <div class="productContainerItem">
                    <img id="pic1" src="<%= product.getProductImgPath()%>">
                    <input type="text" name="product" value="<%= product.getName()%>"><br/>
                    <button>Add to Cart</button>
                </div>
            </form>
		<%}%>
		</div>
	</section>
	<br/>
	<br/>
	
    <%@include file="searchSection.jsp"%>
	
	<%@include file="footer.jsp"%>

</body>
</html>