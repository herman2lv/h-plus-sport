<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<c:redirect url="controller?command=user_management&page=">
  <c:set value="${page}" var="page" scope="session"/>
</c:redirect>
</html>
