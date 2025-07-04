<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Réservation d'exemplaire</title></head>
<body>
<h2>Réserver un exemplaire</h2>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<c:if test="${not empty success}"><div style="color:green">${success}</div></c:if>

<form action="reservation" method="post">
    <label for="idExemplaire">Exemplaire :</label>
    <select name="idExemplaire" required>
        <c:forEach var="ex" items="${exemplaires}">
            <option value="${ex.id_exemplaire}">
                Livre: ${ex.livre.titre} (Exemplaire #${ex.id_exemplaire})
            </option>
        </c:forEach>
    </select>
    <br>
    <label for="dateReservation">Date de réservation :</label>
    <input type="date" name="dateReservation" required/><br>
    <button type="submit">Réserver</button>
</form>
</body>
</html>