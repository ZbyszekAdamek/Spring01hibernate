<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="post" modelAttribute="book">

    <form:hidden path="id"/>

    Title: <form:input path="title" /> <br />
    <form:errors path="title"/><br/>
    Rating: <form:input path="rating" type="number"/> <br />
    <form:errors path="rating"/><br/>
    Description: <form:input path="description"/> <br />
    <form:errors path="description"/><br/>
    Publisher: <form:select path="publisher.id" items="${publishers}"
                            itemLabel="name" itemValue="id"/>
    <form:errors path="publisher"/><br/>
    Author: <form:select path="authorList" items="${authors}"
                         itemValue="id" itemLabel="firstName" multiple="true"/>
    <form:errors path="authorList"/><br/>
    Pages: <form:input path="pages"/><br/>
    <form:errors path="pages"/><br/>
    <input type="submit">

</form:form>