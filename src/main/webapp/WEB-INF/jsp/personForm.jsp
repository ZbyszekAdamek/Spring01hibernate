<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>


<form:form method="post" modelAttribute="person">
    Login: <form:input path="login"/>
    Email: <form:input path="email"/>
    Password: <form:password path="password"/>
    <input type="submit">
</form:form>

<%--
<form method="post">
    Login: <input type="text" name="login"/><br/>
    Email: <input type="email" name="email"/><br/>
    Password: <input type="password" name="password"/><br/>

    <input type="submit">
</form>--%>
