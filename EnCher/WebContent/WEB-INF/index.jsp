<%@page import="fr.eni.EnCher.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>

<main> <section>
<div class="p-5 pb-0 mx-auto rounded-3 text-center accueil-texte-principal">
	<div class="container-fluid">
		<h1 class="display-5 fw-bold">
			Et en <span class="text-success">hausse</span> !
		</h1>
		<h2 class="fs-4 fw-normal">
			Le site parfait pour vos petites (<span>et grosses</span>) enchères !
			</br>Commencez dès maintenant, et acquérez de nombreuses affaires !
		</h2>
	</div>
</div>
</section> <section>
<div class="container mt-5">
	<div class="row d-flex justify-content-center">
		<div class="col-md-10">
			<form class="card p-3 bg-light py-4 shadow-sm" method="post" action="${pageContext.request.contextPath}/" enctype="multipart/form-data">
				<h5>Filtrez votre recherche :</h5>
				<div class="input-group mt-3">
				
				<select class="form-select" id="categorie" name="categorie" aria-label="Choissisez votre catégorie">
					<option class="dropdown-item" value="0" selected="selected">Toutes les catégories</option>
				<c:forEach var="c" items="${categories}">
				  <option class="dropdown-item" value="${c.idCategorie}">${c.libelle}</option>
				 </c:forEach>
				</select>
				
				<%-- <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                data-bs-toggle="dropdown" aria-expanded="false">
                Catégorie
              </button>
              
              <div class="dropdown-menu shadow p-2" aria-labelledby="dropdownMenuButton">
                <ul>
                  <li><a class="dropdown-item rounded-2 active" href="#">Toutes les catégories</a></li>
                </ul>
                <hr class="dropdown-divider">
                <ul class="scrollable-menu mb-0">
                  <c:forEach var="c" items="${categories}">
				  <li><option class="dropdown-item" value="${c.idCategorie}">${c.libelle}</option></li>
				  </c:forEach>
                </ul>
              </div> --%>

					<input type="text" class="form-control"
						aria-label="Text input with dropdown button"
						placeholder="Un beaauuuuu salon de jardin..."  id="recherche" name="recherche" value="${recherche}">
					<button type="submit" class="btn btn-primary btn-block">Rechercher</button>
				</div>
				<% Utilisateur user = (Utilisateur) session.getAttribute("user");
					
					if(user != null){
						%>
				<div class="mt-3">
					<a data-bs-toggle="collapse" href="#more-filters" role="button"
						aria-expanded="false" aria-controls="more-filters"
						class="advanced"> Plus d'options de filtrages... <i
						class="fa fa-angle-down"></i>
					</a>
					<div class="collapse" id="more-filters">
						<div class="card card-body mt-2">
							<div class="row">
								<div class="col-md-6">
									<input class="form-check-input" type="radio" name="radio1"
										id="radio1" checked> <label class="form-check-label"
										for="radio1"> Achats </label>
									<ul>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check1" name="check1" checked> <label
											class="form-check-label" for="check1"> Enchères
												ouvertes </label></li>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check2" name="check2" checked> <label
											class="form-check-label" for="check2"> Mes enchères </label>
										</li>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check3" name="check3" checked> <label
											class="form-check-label" for="check3"> Enchères remportées </label></li>
									</ul>

								</div>
								<div class="col-md-6">
									<input class="form-check-input" type="radio" name="radio2"
										id="radio2"> <label class="form-check-label"
										for="radio2"> Mes ventes </label>
									<ul>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check4" name="check4" disabled> <label
											class="form-check-label" for="check4"> Mes ventes en
												cours </label></li>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check5" name="check5" disabled> <label
											class="form-check-label" for="check5"> Ventes non
												débutées </label></li>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check6" name="check6" disabled> <label
											class="form-check-label" for="check6"> Ventes
												terminées </label></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				
						<%
					}
						%>
			</form>
		</div>
	</div>
</div>
</section> <section class="p-2">
<div class="d-flex justify-content-center flex-wrap">
	<c:choose>
		<c:when test="${listeArticles.size()>0}">
			<c:forEach var="c" items="${listeArticles}">

				<div class="card border-1 m-2 shadow" style="width: 22em;">
					<img src="${pageContext.request.contextPath}/image/${c.photoPrincipal.url}" class="" alt="${c.nom}"
						style="max-height: 10em; margin: auto;">
					<div class="card-body">
						<div class="d-flex flex-row">
							<h5 class="card-title w-100">${c.nom}</h5>
							<span class="badge text-bg-primary align-self-start">${c.prix} crédits</span>
						</div>
						<p class="card-text text-truncate">${c.description}</p>
						<a class="card-text" href="${pageContext.request.contextPath}/article?id=${c.idArticle}">En savoir plus</a>
					</div>
					<div class="card-footer">
						<small class="text-body-secondary">Mis en ligne il y a 2
							heures par </small> <a href="#">${c.proprietaire.pseudo}</a>
					</div>
				</div>
			</c:forEach>

		</c:when>
		<c:otherwise>
			<h3 class="p-5">Pas d'article pour le moment</h3>
		</c:otherwise>
	</c:choose>

</div>
</section> </main>


<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/js/index.js" async></script>
</body>
</html>