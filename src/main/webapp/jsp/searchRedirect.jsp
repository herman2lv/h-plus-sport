<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
  <c:redirect url="controller?command=search">
    <c:param name="searchString" value="${sessionScope.searchString}"/>
  </c:redirect>
</html>
