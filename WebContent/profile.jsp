<!DOCTYPE html >
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	
	<section id="profile" class="section">
		<div class="container">
        <jsp:useBean id="user" scope="request" type="com.epam.hplus.beans.User"/>
			<h2 class="headline">My Profile</h2>
			<table id="profile">
				<tr>
					<td>Username</td>
					<td><jsp:getProperty property="username" name="user"/></td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><jsp:getProperty property="firstName" name="user"/></td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td><jsp:getProperty property="lastName" name="user"/></td>
				</tr>
				<tr>
					<td>Age</td>
					<td>${user.age}</td>
				</tr>
				<tr>
					<td>Interested in</td>
					<td>${user.activity}</td>
				</tr>
			</table>
		</div>
		<br/>
	</section>

	<jsp:include page="footer.jsp"/>

</body>
</html>
