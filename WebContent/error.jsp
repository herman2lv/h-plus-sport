<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<html>
<head>
<meta charset="UTF-8">
<title>H+ Sport - Error</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header id="home" class="header">
  <nav class="nav" role="navigation">
    <div class="container nav-elements">
      <div class="branding">
        <a href="#home"><img src="images/hpluslogo.svg" alt="Logo - H Plus Sports"></a>
      </div><!-- branding -->
      <ul class="navbar">
        <li><a href="home">home</a></li>
        <li><a href="home#history">history</a></li>
        <li><a href="home#products">products</a></li>
        <li><a href="login">login</a></li>
        <li><a href="home#people">people</a></li>
        <li><a href="#search">search</a></li>
        <li><a href="register">new user?</a></li>
        <li><a href="redirect">linkedIn</a></li>
      </ul>
    </div>
  </nav>
</header>
<section id="error" class="section">
  <div class="container">
    <div class="text-content">
      <h2 class="headline">Error occurred</h2>
      <p>We a sorry, but an internal server error has occurred. Try to access the resource later</p>
    </div>
  </div>
</section>
<section id="search" class="section">
  <header class="imageheader"></header>
  <div class="container">
    <h2 class="headline">Search Products</h2>
    <form action="search" method="get">
    <label class="card-title">Search your product</label>
    <input type="search" name="search">
    <input type="submit" value="Search">
    </form>
  </div>
</section>
<footer class="footer">
  <div class="container">
  <nav class="nav" role="navigation">
    <div class="container nav-elements">
      <div class="branding">
        <a href="#home"><img src="images/hpluslogo.svg" alt="Logo - H Plus Sports"></a>
        <p class="address">100 Main Street<br>
        Seattle, WA 98144
      </p>
      </div>
    </div>
  </nav>
  <p class="legal">H+ Sport is a fictitious brand created by lynda.com solely for the purpose of training. All products and people associated with H+ Sport are also fictitious. Any resemblance to real brands, products, or people is purely coincidental. Information provided about the product is also fictitious and should not be construed to be representative of actual products on the market in a similar product category.</p>
</div>
</footer>
</body>
</html>
