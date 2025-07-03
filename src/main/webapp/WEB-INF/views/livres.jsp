<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des livres - Bibliothèque</title>
    <style>
        body { font-family: 'Georgia', serif; background: #f5f1ea; }
        table { width: 80%; margin: 40px auto; border-collapse: collapse; }
        th, td { border: 1px solid #b79e85; padding: 10px; }
        th { background: #7b5e3b; color: #fff; }
        tr:nth-child(even) { background: #f5f1ea; }
        tr:nth-child(odd) { background: #fff; }
        caption { font-size: 1.5em; margin-bottom: 20px; color: #5a3e1b; }
        a { color: #5a3e1b; text-decoration: none;}
    </style>
</head>
<body>
    <table>
        <caption>Liste des livres</caption>
        <tr>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Type</th>
            <th>Année</th>
            <th>ISBN</th>
        </tr>
        <c:forEach var="livre" items="${livres}">
            <tr>
                <td>${livre.titre}</td>
                <td>${livre.auteur.nom} ${livre.auteur.prenom}</td>
                <td>${livre.typeLivre.libelle}</td>
                <td>${livre.anneePublication}</td>
                <td>${livre.isbn}</td>
            </tr>
        </c:forEach>
    </table>
    <p style="text-align:center">
        <a href="${pageContext.request.contextPath}/auth/login">Retour à la connexion</a>
    </p>
</body>
</html>