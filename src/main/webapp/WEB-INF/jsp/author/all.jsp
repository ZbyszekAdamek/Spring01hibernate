<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<h2> Lista wszystkich autorów </h2>

<a href="add">
    Dodaj nowego autora
</a>

<c:forEach items="${allAuthors}" var="author">
    <br />
    ----------------------------
    <br />
    ${author.firstName} <br />
    ${author.lastName} <br />

    <a href="edit?idToEdit=${author.id}">
        Edytuj
    </a>
    </br />
    <a href="remove?toRemoveId=${author.id}">
        Usun
    </a>
    <br />

    ----------------------------
    <br />

</c:forEach>