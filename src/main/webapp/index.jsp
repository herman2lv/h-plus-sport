<%@ page contentType="text/html;charset=UTF-8" language="java"
         errorPage="jsp/error.jsp" isErrorPage="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>H+ Sport - <fmt:message key="ui.title.main"/></title>
  <link rel="stylesheet" href="css/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<jsp:include page="jsp/headerForMainPage.jsp"/>

<section id="history" class="section">
  <div class="container">
    <div class="text-content">
      <h2 class="headline"><fmt:message key="ui.headline.companyHistory"/></h2>
      <p><fmt:message key="ui.history.firstPart"/></p>
      <p><fmt:message key="ui.history.secondPart"/></p>
      <p><fmt:message key="ui.history.thirdPart"/></p>
    </div>
  </div>
</section>

<section id="products" class="section">
  <header class="imageheader"></header>
  <div class="container">
    <h2 class="headline"><fmt:message key="ui.products"/></h2>
    <p><fmt:message key="ui.productsDescription"/></p>
  </div>
  <ul class="product-list">
    <li class="product-item">
      <img class="product-image" src="images/vitamin-a.jpg" alt="Vitamin A - Product Photo">
      <h2 class="product-name">Vitamin A</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/vitamin-bcomplex.jpg" alt="B Complex - Product Photo">
      <h2 class="product-name">Vitamin-B Complex</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/mineralwater-blueberry.jpg" alt="Blueberry Mineral Water - Product Photo">
      <h2 class="product-name">Blueberry Mineral Water</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/vitamin-c.jpg" alt="Vitamin C - Product Photo">
      <h2 class="product-name">Vitamin C</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/proteinbar-chocolate.jpg" alt="Protein Bar Chocolate - Product Photo">
      <h2 class="product-name">Chocolate Protein Bar</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/vitamin-calcium.jpg" alt="Vitamin Calcium - Product Photo">
      <h2 class="product-name">Calcium Vitamin</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/vitamin-d.jpg" alt="Vitamin D - Product Photo">
      <h2 class="product-name">Vitamin D</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/vitamin-flaxseed-oil.jpg" alt="Flaxseed Oil - Product Photo">
      <h2 class="product-name">Flaxseed Oil Vitamin</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/vitamin-iron.jpg" alt="Vitamin Iron - Product Photo">
      <h2 class="product-name">Iron Vitamin</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/mineralwater-lemonlime.jpg" alt="Mineral Water Lemon Lime - Product Photo">
      <h2 class="product-name">Lemon Lime Mineral Water</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/vitamin-magnesium.jpg" alt="Vitamin Magnesium - Product Photo">
      <h2 class="product-name">Magnesium Vitamin</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/vitamin-multi.jpg" alt="Vitamin Multivitamin - Product Photo">
      <h2 class="product-name">Multi-vitamin</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/proteinbar-peanutbutter.jpg"
           alt="Vitamin Protein Bar Peanut Butter - Product Photo">
      <h2 class="product-name">Peanut Butter Protein Bar</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/mineralwater-strawberry.jpg"
           alt="Mineral Water Strawberry - Product Photo">
      <h2 class="product-name">Strawberry Mineral Water</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/proteinbar-lemon.jpg" alt="Protein Bar Lemon - Product Photo">
      <h2 class="product-name">Lemon Protein Bar</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/mineralwater-orange.jpg" alt="Mineral Water Orange - Product Photo">
      <h2 class="product-name">Orange Mineral Water</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/mineralwater-peach.jpg" alt="Mineral Water Peach - Product Photo">
      <h2 class="product-name">Peach Mineral Water</h2>
    </li>
    <li class="product-item">
      <img class="product-image" src="images/mineralwater-raspberry.jpg" alt="Mineral Water Raspberry - Product Photo">
      <h2 class="product-name">Raspberry Mineral Water</h2>
    </li>
  </ul>
</section>

<section id="guarantee" class="section">
  <header class="imageheader"></header>
  <div class="container">
    <h2 class="headline"><fmt:message key="ui.headline.guarantee"/></h2>
    <p><fmt:message key="ui.guarantee.firstPart"/></p>
    <p><fmt:message key="ui.guarantee.secondPart"/></p>
  </div>
</section>

<section id="people" class="section">
  <header class="imageheader"></header>
  <div class="container">
    <h2 class="headline"><fmt:message key="ui.people"/></h2>
    <div class="people-cards">
      <div class="person-card">
        <img src="images/employees/HenryTwill.jpg" alt="HenryTwill Photo">
        <div class="card-info">
          <h3 class="card-name"><fmt:message key="ui.people.henryTeill"/></h3>
          <h4 class="card-title"><fmt:message key="ui.people.henryTeill.position"/></h4>
        </div>
        <p class="card-text"><fmt:message key="ui.people.henryTeill.about"/></p>
      </div>

      <div class="person-card">
        <img src="images/employees/JessicaNewton.jpg" alt="JessicaNewton Photo">
        <div class="card-info">
          <h3 class="card-name"><fmt:message key="ui.people.jessicaNewton"/></h3>
          <h4 class="card-title"><fmt:message key="ui.people.jessicaNewton.position"/></h4>
        </div>
        <p class="card-text"><fmt:message key="ui.people.jessicaNewton.about"/></p>
      </div>

      <div class="person-card">
        <img src="images/employees/PhiTang.jpg" alt="PhiTang Photo">
        <div class="card-info">
          <h3 class="card-name"><fmt:message key="ui.people.phiTang"/></h3>
          <h4 class="card-title"><fmt:message key="ui.people.phiTang.position"/></h4>
        </div>
        <p class="card-text"><fmt:message key="ui.people.phiTang.about"/></p>
      </div>

      <div class="person-card">
        <img src="images/employees/MariaSontas.jpg" alt="MariaSontas Photo">
        <div class="card-info">
          <h3 class="card-name"><fmt:message key="ui.people.mariaSontas"/></h3>
          <h4 class="card-title"><fmt:message key="ui.people.mariaSontas.position"/></h4>
        </div>
        <p class="card-text"><fmt:message key="ui.people.mariaSontas.about"/></p>
      </div>

      <div class="person-card">
        <img src="images/employees/AngelaHaston.jpg" alt="AngelaHaston Photo">
        <div class="card-info">
          <h3 class="card-name"><fmt:message key="ui.people.angelaHashton"/></h3>
          <h4 class="card-title"><fmt:message key="ui.people.angelaHashton.position"/></h4>
        </div>
        <p class="card-text"><fmt:message key="ui.people.angelaHashton.about"/></p>
      </div>

      <div class="person-card">
        <img src="images/employees/MichaelLewiston.jpg" alt="MichaelLewiston Photo">
        <div class="card-info">
          <h3 class="card-name"><fmt:message key="ui.people.michaelLewiston"/></h3>
          <h4 class="card-title"><fmt:message key="ui.people.michaelLewiston.position"/></h4>
        </div>
        <p class="card-text"><fmt:message key="ui.people.michaelLewiston.about"/></p>
      </div>
    </div>
  </div>
</section>

<jsp:include page="jsp/searchSection.jsp"/>
<jsp:include page="jsp/footer.jsp"/>

</body>
</html>
