<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav class="nav" role="navigation">
	<div class="container nav-elements">
		<div class="branding">
            <a href="#home"><img src="images/hpluslogo.svg" alt="Logo - H Plus Sports"></a>
		</div>
		<ul class="navbar">
			<li><a href="home">home</a></li>
			<li><a href="search">catalog</a></li>
			<li><a href="home#people">people</a></li>
			<li><a href="cart">cart</a></li>
			<c:if test="${sessionScope.username == null}">
				<li><a href="login">login</a></li>
				<li><a href="register">new user?</a></li>
            </c:if>
            <c:if test="${sessionScope.username != null}">
				<li><a href="orderHistory">my orders</a></li>
				<li><a href="profile"><i>${sessionScope.username}</i></a></li>                          
				<li><a href='logout'>logout</a></li>
            </c:if>
			<li><a href="#search">search</a></li>
			<li><a href="redirect">GitHub</a></li>
		</ul>
	</div>
</nav>
