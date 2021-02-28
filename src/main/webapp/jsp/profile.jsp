<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${sessionScope.language != null}">
  <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>H+ Sport - My Profile</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:include page="header.jsp"/>
	
	<section id="profile" class="section">
		<div class="container">
			<h2 class="headline">My Profile</h2>
			<table id="profileTable">
				<tr>
					<td>Username</td>
					<td>${user.username}</td>
				</tr>
				<tr>
					<td>First Name</td>
					<td>${user.firstName}</td>
				</tr>
				<tr>
					<td>Last Name</td>
					<td>${user.lastName}</td>
				</tr>
				<tr>
					<td>Date of Birth</td>
					<td><fmt:formatDate value="${user.dateOfBirth}" pattern="YYYY-MM-dd"/></td>
				</tr>
				<tr>
					<td>Interested in</td>
					<td>${user.activity}</td>
				</tr>
				<tr>
					<td><fmt:message key="ui.userRole"/></td>
					<td>${user.role}</td>
				</tr>
			</table>
		</div>
		<br/>
	</section>

    <jsp:include page="searchSection.jsp"/>
	<jsp:include page="footer.jsp"/>

</body>
</html>
