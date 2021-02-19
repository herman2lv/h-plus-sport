<!DOCTYPE html>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="ISO-8859-1">
<title>H+ Sport</title>

</head>
<body>
	
	<jsp:include page="header.jsp"/>
	
	<section>
	<%=displayDate()%>
	</section>
	<section id="login" class="section">
		<div class="container tagline">
			<%if(request.getAttribute("error") != null){%>
			<em>${error}</em><br />
			<%}%>
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
	
<%!
public String displayDate(){
	SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
	Date toDate = Calendar.getInstance().getTime();
	return dateFormat.format(toDate);
}
%>
</body>
</html>
