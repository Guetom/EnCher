<%@page import="fr.eni.EnCher.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<% Utilisateur user = (Utilisateur) session.getAttribute("user");
if(user != null){
	%><c:set var="sessionUser" value="${user.idUtilisateur}" /><%
}else{
	%><c:set var="sessionUser" value="0" /><%
}
%>

<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>

<main> <section class="profil">
<div class="container mt-4 mb-4 p-3 d-flex justify-content-center">
	<div class="carte-profil card p-4 shadow-sm bg-light">
		<div class="infos-principales">
			<img class="image-profil border"
				src="${pageContext.request.contextPath}/image/${utilisateur.photoProfil.url}" />
			<!-- Nom et prénom -> infos à cacher si pas utilisateur courant ou admin -->
			<c:if test="${sessionUser == utilisateur.idUtilisateur}">
				<h3>${utilisateur.prenom}, ${utilisateur.nom}</h3>
			</c:if>
			<h3>@${utilisateur.pseudo}</h3>
		</div>
		<c:if test="${sessionUser == utilisateur.idUtilisateur}">
			<!-- Infos secondaires -> infos à cacher si pas utilisateur courant ou admin -->
		<div class="infos-secondaires">
			<div class="email">
				<p>
					<span class="titre-info-secondaire">Email</span> : ${utilisateur.email}
				</p>
			</div>
			<div class="tel">
				<p>
					<span class="titre-info-secondaire">Téléphone</span> :
					0${utilisateur.numeroTel}
				</p>
			</div>
			<div class="adresse">
				<p class="title">
					<span class="titre-info-secondaire">Adresse</span> :
				</p>
				<div class="cp-ville">
					<p class="rue">${utilisateur.rue}</p>
					<p>
						<span class="cp">${utilisateur.codePostal}</span> <span class="ville">${utilisateur.ville}</span>
					</p>
				</div>
			</div>
			<div class="credits">
				<p>
					<span class="titre-info-secondaire">Crédit</span> : <span
						class="prix">${utilisateur.credit}</span>
				</p>
			</div>
		</div>
		</c:if>

		

		<!-- Ne pas cacher dans tout les cas -->
		<div class="px-2 rounded mt-3 bg-white border text-muted">

			<fmt:parseDate var="date" value="${utilisateur.dateCreation}"
				pattern="yyyy-MM-dd'T'HH:mm" />
			<fmt:formatDate value="${date}" pattern="dd/MM/yyyy"
				var="formattedDate" />
			<span class="a-rejoint-le">A rejoint le : ${formattedDate}</span>
		</div>
		
		<c:if test="${sessionUser == utilisateur.idUtilisateur}">
			<!-- A cacher si ce n'est pas l'utilisateur courant ou l'admin -->
			<div class="bouton-modifier mt-3">
				<a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/profil/modifier">Modifier profil</a>
			</div>
		</c:if>
		
	</div>

</div>
</section> </main>

<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>