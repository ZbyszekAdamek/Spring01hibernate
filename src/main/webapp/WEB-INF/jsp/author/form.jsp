<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" modelAttribute="author">

    <form:hidden path="id"/>

    First name: <form:input path="firstName" /> <br />
    Last name: <form:input path="lastName" /> <br />

    <input type="submit">

</form:form>