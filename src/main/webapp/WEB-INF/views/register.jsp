<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscription Adhérent</title>
    <style>
        body {
            font-family: 'Georgia', serif;
            background: #f5f1ea url('https://www.transparenttextures.com/patterns/wood-pattern.png') repeat;
            color: #333;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            color: #5a3e1b;
            margin-top: 40px;
        }

        .form-container {
            background-color: #ffffffcc;
            width: 500px;
            margin: 40px auto;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
        }

        .form-container div {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #4a3424;
        }

        input[type="text"],
        input[type="password"],
        input[type="date"],
        select {
            width: 100%;
            padding: 8px;
            border: 1px solid #b79e85;
            border-radius: 5px;
            background-color: #fefdfb;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #7b5e3b;
            color: white;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
        }

        button:hover {
            background-color: #5c4529;
        }

        .message {
            text-align: center;
            margin-bottom: 10px;
        }

        .message.error {
            color: #d9534f;
        }

        .message.success {
            color: #5cb85c;
        }

        p {
            text-align: center;
        }

        a {
            color: #5a3e1b;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h2>Inscription nouvel adhérent</h2>

<div class="form-container">
    <c:if test="${not empty error}">
        <div class="message error">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="message success">${success}</div>
    </c:if>

    <form:form modelAttribute="registerForm" method="post">
        <div>
            <form:label path="nom">Nom :</form:label>
            <form:input path="nom"/>
        </div>
        <div>
            <form:label path="email">Email :</form:label>
            <form:input path="email"/>
        </div>
        <div>
            <form:label path="motDePasse">Mot de passe :</form:label>
            <form:password path="motDePasse"/>
        </div>
        <div>
            <form:label path="dateNaissance">Date de naissance :</form:label>
            <form:input path="dateNaissance" type="date"/>
        </div>
        <div>
            <form:label path="typeAdherent">Type d'adhérent :</form:label>
            <form:select path="typeAdherent">
                <form:options items="${typesAdherent}" itemValue="libelle" itemLabel="libelle"/>
            </form:select>
        </div>
        <div>
            <button type="submit">S'inscrire</button>
        </div>
    </form:form>

    <p>
        Déjà inscrit ? <a href="${pageContext.request.contextPath}/login">Connexion</a>
    </p>
</div>
</body>
</html>
