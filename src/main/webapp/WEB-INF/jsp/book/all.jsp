<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<h2> Lista wszystkich książek </h2>

<a href="add">
    Dodaj nowa książke
</a>

<c:forEach items="${allBooks}" var="book">
    <br />
    ----------------------------
    <br />
    ${book.title} <br />
    ${book.description} <br />
    ${book.rating} <br />
    ${book.publisher.name} <br />

    <a href="edit?idToEdit=${book.id}">
        Edytuj
    </a>
    </br />
    <a href="remove?toRemoveId=${book.id}">
        Usun
    </a>
    <br />

    ----------------------------
    <br />

</c:forEach>