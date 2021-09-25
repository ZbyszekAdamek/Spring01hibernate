<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:forEach items="${allErrors}" var="error">
    <br/>
    --------------------------
    <br/>
    ${error.getPropertyPath()} : ${error.getMessage()}<br/>

    --------------------------

</c:forEach>
