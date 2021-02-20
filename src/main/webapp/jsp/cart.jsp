<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H+ Sport - Cart</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

    <jsp:include page="header.jsp"/>
    
    <section id="orders" class="section">
        <c:if test="${cart.size() > 0}">
	        <div class="container">
		        <h2 class="headline">Cart</h2>
		        <table id="orderHistory">
		            <tr>
		                <th>No.</th>
		                <th>Product Name</th>
		            </tr>
		            <c:forEach items="${cart}" var="product" varStatus="counter">
		                <tr>
		                    <td>${counter.count}</td>
		                    <td>${product}</td>
		                </tr>
		            </c:forEach>
		        </table>
		        <br/>
	        </div>
        </c:if>
        <c:if test="${(cart.size() == 0) or (cart == null)}">
            <div class="container">
                <h2 class="headline">Cart</h2>
                <p>You haven't put in your cart anything yet</p>
            </div>
        </c:if>
    </section>

    <jsp:include page="searchSection.jsp"/>
    <jsp:include page="footer.jsp"/>

</body>
</html>
