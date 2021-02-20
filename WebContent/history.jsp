<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H+ Sport</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

    <header id="home" class="header">
        <nav class="nav" role="navigation">
            <div class="container nav-elements">
                <div class="branding">
                    <a href="#home"><img src="images/hpluslogo.svg"
                        alt="Logo - H Plus Sports"></a>
                </div>
                <ul class="navbar">
                    <li><a href="home">home</a></li>
                    <li><a href="orderHistory">order history</a></li>
                    <li><a href="profile">view my profile</a></li>
                    <li><a href='logout'>logout</a></li>
                    <li><a href="redirect">linkedIn</a></li>
                </ul>
            </div>
        </nav>
    </header>
    
    <section id="orders" class="section">
    <c:if test="${orders != null}">
        <div class="container">
	        <h2 class="headline"></h2>
	        <table id="orderHistory">
	            <tr>
	                <th>Order No.</th>
	                <th>Product Name</th>
	                <th>Order Date</th>
	                <th>Product Image</th>
	            </tr>
	            <c:forEach items="${orders}" var="order" varStatus="counter">
	                <tr>
	                    <td>${counter.count}</td>
	                    <td>${order.product}</td>
	                    <td><fmt:formatDate value="${order.orderDate}" pattern="YYYY-MM-dd"/></td>
	                    <td><img width="200px" height="150px" src="${order.productImgPath}"></td>
	                </tr>
	            </c:forEach>
	        </table>
	        <br/>
        </div>
    </c:if>
    </section>

    <jsp:include page="searchSection.jsp"/>
    <jsp:include page="footer.jsp"/>

</body>
</html>
