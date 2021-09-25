<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form:form method="post" modelAttribute="book">
    Title: <form:input path="title"/><br/>
    Rating: <form:input path="rating" type="number"/><br/>
    Description: <form:input path="description"/><br/>

    Publisher: <form:select path="publisher.id" items="${publishers}"
                            itemLabel="name" itemValue="id"/>

    <input type="submit">

</form:form>