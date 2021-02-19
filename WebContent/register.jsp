<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>H+ Sport</title>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

	<%@include file="header.jsp"%>

	<section id="registration" class="section">
	 <div class="container tagline">
	 <em>Register User</em><br/>
	 <em>${registraionStatus}</em>
		<form action="register" method="post">
			<label>Username</label> <input type="text" name="username" id="username"><br/>
			<label>Password</label> <input type="password" name="password" id="password"><br/>
			<label>First Name</label> <input type="text" name="fname" id="fname"><br/>
			<label>Last Name</label> <input type="text" name="lname" id="lname"><br/>
			<label>Age</label> <input type="text" name="age" id="age"><br/>
			<label>What do you want to do? </label> 
			<input type="radio" name="activity" id="activity" value="Playing a sport">Play a Sport?
			<input type="radio" name="activity" id="activity" value="Exercise in Gym">Hit the Gym?<br/>
			<input type="submit" value="Submit" id="submit">
		</form>
		</div>
	</section>

	<%@include file="searchSection.jsp"%>
    
    <%@include file="footer.jsp"%>

</body>
</html>