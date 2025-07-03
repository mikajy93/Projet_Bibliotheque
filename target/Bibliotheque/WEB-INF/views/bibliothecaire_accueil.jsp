<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Tableau de bord Bibliothécaire</title>
</head>
<body>
<h2>Demandes de prêt</h2>
<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>
<c:if test="${not empty success}">
    <div style="color:green">${success}</div>
</c:if>
<table border="1">
    <tr>
        <th>Adhérent</th>
        <th>Exemplaire</th>
        <th>Type de prêt</th>
        <th>Action</th>
    </tr>
    <c:forEach var="demande" items="${demandesPret}">
        <tr>
            <td>${demande.idAdherent}</td>
            <td>${demande.idExemplaire}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/bibliothecaire/validerPret">
                    <input type="hidden" name="idAdherent" value="${demande.idAdherent}" />
                    <input type="hidden" name="idExemplaire" value="${demande.idExemplaire}" />
                    <select name="idTypePret">
                        <option value="1">À domicile</option>
                        <option value="2">Sur place</option>
                    </select>
                    <button type="submit">Valider</button>
                </form>
            </td>
            <td>
                <!-- bouton refuser possible -->
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>