<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>


<form:form method="post" modelAttribute="student">
    Imię: <form:input path="firstName"/><br/>
    Nazwisko: <form:input path="lastName"/><br/>
    Płeć <br/>
    Mężczyzna: <form:radiobutton path="gender" value="M"/><br/>
    Kobieta: <form:radiobutton path="gender" value="F"/><br/>
    Notatki: <form:textarea path="notes"/><br/>

    Zaznacz, jeśli zgadzasz się na otrzymywanie newslettera:
    <form:checkbox path="mailingList" />
    </br>
    Obsługiwane języki programowania: </br>
    <form:select path="programmingSkills" multiple="true">
        <form:options items="${languages}"/>
    </form:select>
    <br/>
    Wybierz kraj: </br>
    <form:select path="country" multiple="false">
        <form:options items="${countries}"/>
    </form:select>
    <br/>

    Zaznacz hobby: <br/>
    Programowanie: <form:checkbox path="hobbies"
                        value="programowanie"/></br>
    Muzyka: <form:checkbox path="hobbies"
                         value="muzyka"/></br>
    Sport: <form:checkbox path="hobbies"
                         value="sport"/></br>
    Inne: <form:checkbox path="hobbies"
                           value="inne"/></br>
    </br>
    <input type="submit">
</form:form>