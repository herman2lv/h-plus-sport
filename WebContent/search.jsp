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
			<p><span id="size">Items in Cart: {6}</span></p>
		</div>
		<div class="productContainer">
		<%
		List<Product> products = (List) request.getAttribute("products");
		for (Product product : products) {
		%>
			<form method="get" action="addProducts">
                <div class="productContainerItem">
                    <img id="pic1" src="<%= product.getProductImgPath()%>">
                    <input type="text" name="product" value="<%= product.getName()%>"><br/>
                    <button>Add to Cart</button>
                </div>
            </form>
		<%}%>
		</div>
	</section>
	<br />
	<br />
	<section id="search" class="section">
		<header class="imageheader"></header>
		<div class="container">
			<h2 class="headline">Search Products</h2>
			<form action="search" method="get">
				<label class="card-title">Search your product</label> <input
					type="search" name="search"> <input type="submit"
					value="Search">
			</form>
		</div>
	</section>
	
	<%@include file="footer.jsp"%>

</body>
</html>