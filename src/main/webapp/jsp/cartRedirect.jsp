<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<c:redirect url="controller?command=cart">
  <c:param name="orderStatus" value="${orderStatus}"/>
</c:redirect>
</html>
