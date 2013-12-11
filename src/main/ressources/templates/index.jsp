<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head> 
    <title>Accueil du Blog</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      
</head>
<body>
	<h1>Bienvenue sur notre blog</h1>
	<p><a href="/">Acceuil</a></p>
	<p><a href="/ajouterArticle">Ajouter Article</a></p>
	<p><a href="/inscription">S'incrire</a></p>
<%-- 	<p>${sessionScope['scopedTarget.sessionBean'].loggin}</p> --%>
	<p th:text="${#sessionBean.loggin}"></p>
<!-- 	<div th:if=>  -->
<!-- 	   <p th:text="'Bienvenue ' + ${userconnecter.loggin}">userconnecter</p><a href="@{/user/profil(id=${user.id})}">Modifier son profil</a></div> -->
<!-- 	<p th:unless="${userconnecter}"><a href="/connexion">Se connecter</a></p> -->
	
    <p th:if="${message_error}" th:text="${message_error}"></p>
    <table border="1">
    <caption>liste des Articles sur le Site</caption>
    	<tr>
    		<th>Titre</th>
    		<th>Date de publication</th>
			<th>Contenu</th>
			</tr><tr th:each="article: ${articles}">
			<td th:text="${article.titre}">titre</td>
			<td th:text="${article.date}">date</td>
			<td th:text="${article.contenu}">contenu</td>
		</tr>
	</table>
	
	
	<table border="1">
	   <caption>liste des utilisateurs</caption>
    	<tr>
    		<th>Id</th>
    		<th>Login</th>
			<th>Mot de passe</th>
			</tr><tr th:each="user: ${users}">
			<td th:text="${user.id}">id</td>
			<td><a href ="userTemplate.html" th:href="@{/user/profil(id=${user.id})}"><span th:text="${user.loggin}"></span></a></td>
			<td th:text="${user.password}">Mot de passe</td>
		</tr>
	</table>
    
</body>
</html>