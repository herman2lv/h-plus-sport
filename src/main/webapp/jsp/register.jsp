<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>H+ Sport - Register</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

	<jsp:include page="header.jsp"/>

	<section id="registration" class="section">
		<div class="container tagline">
		<em>Register User</em><br/>
		<em>${sessionScope.registrationStatus}</em>
		<form action="controller" method="post">
		  <input type="hidden" name="command" value="register_user"/>
			<label for="username">Username</label>
			<input type="text" name="username" id="username" required>
			<br/>
			<label for="password">Password</label>
			<input type="password" name="password" id="password" required>
			<br/>
			<label for="fname">First Name</label>
			<input type="text" name="fname" id="fname" required>
			<br/>
			<label for="lname">Last Name</label>
			<input type="text" name="lname" id="lname" required>
			<br/>
			<label for="dateOfBirth">Date of Birth</label>
			<input type="date" name="dateOfBirth" id="dateOfBirth" required>
			<br/>
			<label >What do you want to do?
				<input type="radio" name="activity" value="Playing a sport">Play a Sport?
				<input type="radio" name="activity" value="Exercise in Gym">Hit the Gym?<br/>
				<input type="submit" value="Submit" id="submit">
			</label>
		</form>
		</div>
	</section>

    <jsp:include page="searchSection.jsp"/>
    <jsp:include page="footer.jsp"/>

</body>
</html>
