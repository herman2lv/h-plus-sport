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
	
	<footer class="footer">
		<div class="container">
			<nav class="nav" role="navigation">
				<div class="container nav-elements">
					<div class="branding">
						<a href="#home"><img src="images/hpluslogo.svg"
							alt="Logo - H Plus Sports"></a>
						<p class="address">
							100 Main Street<br> Seattle, WA 98144
						</p>
					</div>
				</div>
			</nav>
			<p class="legal">H+ Sport is a fictitious brand created by
				lynda.com solely for the purpose of training. All products and
				people associated with H+ Sport are also fictitious. Any resemblance
				to real brands, products, or people is purely coincidental.
				Information provided about the product is also fictitious and should
				not be construed to be representative of actual products on the
				market in a similar product category.</p>
		</div>
	</footer>

</body>
</html>