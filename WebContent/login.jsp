<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.hplussport.com/apptaglib" prefix="app"%>
<html>
<head>
	<link rel="stylesheet" href="css/style.css">
	<meta charset="ISO-8859-1">
	<title>H+ Sport</title>
</head>
<body>

	<jsp:include page="header.jsp"/>

	<section>
	<app:insertDate/>
	</section>
	
	<section id="login" class="section">
		<div class="container tagline">
            <c:if test="${error != null}">
                <em>${error}</em><br />
			</c:if>
			<em>LOGIN USER</em>
			<form action="login" method="post">
				<label>Username</label>
				<input type="text" name="username"
					   id="username"><br /> <label>Password</label> 
                <input type="password" name="password" id="password"><br/>
                <input type="submit" value="Login">
			</form>
		</div>
	</section>
	
    <jsp:include page="searchSection.jsp"/>
    <jsp:include page="footer.jsp"/>
    
</body>
</html>
