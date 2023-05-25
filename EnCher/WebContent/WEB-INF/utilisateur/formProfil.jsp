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
	%><c:set var="sessionUser" value="${user.idUtilisateur}" />
<%
}else{
	%><c:set var="sessionUser" value="0" />
<%
}
%>

<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>

<main> <section class="profil">
<div class="modal fade" id="modalSupprimer" tabindex="-1" aria-labelledby="modalSupprimerLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="modalSupprimerLabel">Voulez-vous vraiment supprimer votre compte ?</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-footer">
            <form  method="post" action="${pageContext.request.contextPath}/profil/supprimer"
	enctype="multipart/form-data">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Non je reste</button>
            <button type="submit" class="btn btn-danger">Oui je le veux</button>
          </form>
          </div>
        </div>
      </div>
    </div>
    
<h2 class="my-4">Modifier profil</h2>
<form class="container mb-4 p-3 pt-0" method="post"
	action="${pageContext.request.contextPath}/profil/modifier"
	enctype="multipart/form-data">
	<div class="carte-profil card p-4 shadow-sm bg-light">
		<div class="row col-12 m-auto">

			<!-- 

              DANS LES CHAMPS VALUE, METTRE LA VALEUR COURANTE (nom courant de l'utilisateur, adresse courante, etc...)

            -->

			<div class="photo-profil-container col-lg-7 mx-auto mb-4">
				<!-- On va chercher ici l'image courante de l'utilisateur -->
				<img class="image-profil image-preview mx-auto border"
					src="${pageContext.request.contextPath}/image/${user.photoProfil.url}" />
				<h3 class="mb-3">${user.pseudo}</h3>
				<div class="form-image form-photo-profil">
					<input class="form-control mx-auto" type="file" id="photo-profil" name="photo-profil"
						accept="image/*">
				</div>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="nom" name="nom"
					placeholder=" " value="${user.nom}" required> <label for="nom"
					class="px-3 text-muted">Nom</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="prenom" name="prenom"
					placeholder=" " value="${user.prenom}" required> <label for="prenom"
					class="px-3 text-muted">Prénom</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="date" class="form-control" id="date_naissance"
					name="date_naissance" placeholder=" " value="${user.dateNaissance}" required> <label
					for="date_naissance" class="px-3 text-muted">Date de
					naissance</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="email" class="form-control" id="email" name="email"
					placeholder=" " value="${user.email}" required> <label for="email"
					class="px-3 text-muted">Adresse email</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="tel" class="form-control" id="telephone"
					name="telephone" placeholder=" " value="0${user.numeroTel}" required> <label
					for="telephone" class="px-3 text-muted">Téléphone</label>
			</div>


			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="rue" name="rue"
					placeholder=" " value="${user.rue}" required> <label for="rue"
					class="px-3 text-muted">Rue</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="code_postal"
					name="code_postal" placeholder=" " value="${user.codePostal}" required> <label
					for="code_postal" class="px-3 text-muted">Code postal</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="ville" name="ville"
					placeholder=" " value="${user.ville}" required> <label for="ville"
					class="px-3 text-muted">Ville</label>
			</div>

			<div class="col-lg-12 mx-auto d-flex justify-content-center">
				<button class="btn btn-lg btn-primary mb-1 mx-2" type="submit">Valider</button>
				<a class="btn btn-lg btn-secondary mb-1 mx-2" href="${pageContext.request.contextPath}/profil">Annuler</a>
				<a class="btn btn-lg btn-danger mb-1 mx-2" data-bs-toggle="modal" data-bs-target="#modalSupprimer">Supprimer
					mon compte</a>
			</div>
		</div>
	</div>

</form>
</section> </main>

<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/js/preview-image.js" async></script>

</body>
</html>