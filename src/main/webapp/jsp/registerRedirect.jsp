<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
  <c:redirect url="controller?command=registerUserPage">
    <c:param name="registrationStatus" value="${registrationStatus}"/>
  </c:redirect>
</html>