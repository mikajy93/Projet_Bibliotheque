<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Validation réservation</title></head>
<body>
<h2>Réservations en attente de validation</h2>
<c:if test="${not empty error}"><div style="color:red">${error}</div></c:if>
<c:if test="${not empty success}"><div style="color:green">${success}</div></c:if>
<table border="1">
    <tr>
        <th>Réservation #</th>
        <th>Adhérent</th>
        <th>Exemplaire</th>
        <th>Livre</th>
        <th>Date</th>
        <th>Action</th>
    </tr>
    <c:forEach var="resa" items="${reservations}">
        <tr>
            <td>${resa.id_reservation}</td>
            <td>${resa.adherent.nom}</td>
            <td>${resa.exemplaire.id_exemplaire}</td>
            <td>${resa.exemplaire.livre.titre}</td>
            <td>${resa.dateReservation}</td>
            <td>
                <form action="reservations/valider" method="post" style="display:inline;">
                    <input type="hidden" name="idReservation" value="${resa.id_reservation}"/>
                    <button type="submit">Valider</button>
                </form>
                <form action="reservations/refuser" method="post" style="display:inline;">
                    <input type="hidden" name="idReservation" value="${resa.id_reservation}"/>
                    <button type="submit">Refuser</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>