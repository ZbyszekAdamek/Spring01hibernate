<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" modelAttribute="publisher">

    <form:hidden path="id"/>

    Nazwa: <form:input path="name" /> <br />

    <input type="submit">

</form:form>