<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${sessionScope.language != null}">
	<fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="ui"/>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>H+ Sport - <fmt:message key="ui.title.register"/></title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

	<jsp:include page="header.jsp"/>

	<section id="registration" class="section">
		<div class="container tagline">
		<em><fmt:message key="ui.header.registerUser"/></em><br/>
		<em>${sessionScope.registrationStatus}</em>
		<form action="controller" method="post">
		  <input type="hidden" name="command" value="register_user"/>
			<label for="username"><fmt:message key="ui.username"/></label>
			<input type="text" name="username" id="username" required>
			<br/>
			<label for="password"><fmt:message key="ui.label.password"/></label>
			<input type="password" name="password" id="password" required>
			<br/>
			<label for="fname"><fmt:message key="ui.firsName"/></label>
			<input type="text" name="fname" id="fname" required>
			<br/>
			<label for="lname"><fmt:message key="ui.lastName"/></label>
			<input type="text" name="lname" id="lname" required>
			<br/>
			<label for="dateOfBirth"><fmt:message key="ui.dateOfBirth"/></label>
			<input type="date" name="dateOfBirth" id="dateOfBirth" required>
			<br/>
			<label><fmt:message key="ui.activity"/>
				<label>
					<input type="radio" name="activity" value="Playing a sport">
					<fmt:message key="ui.login.playSport"/>
				</label>
				<label>
					<input type="radio" name="activity" value="Exercise in Gym">
					<fmt:message key="ui.login.exerciseInGym"/>
				</label>
				<br/>
			</label>
			<input type="submit" value='<fmt:message key="ui.login.button.submit"/>' id="submit">
		</form>
		</div>
	</section>

    <jsp:include page="searchSection.jsp"/>
    <jsp:include page="footer.jsp"/>

</body>
</html>
