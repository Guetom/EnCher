<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>

<main>

<div class="p-5 pb-0 mx-auto rounded-3 text-center">
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

<c:if test="${ !empty sessionScope.pseudo}">
    <p>Hello ${ sessionScope.pseudo } !</p>
</c:if>

<!-- Champ de recherche -->
<div class="container mt-5">
	<div class="row d-flex justify-content-center">
		<div class="col-md-10">
			<form class="card p-3 bg-light py-4 shadow-sm">
				<h5>Filtrez votre recherche :</h5>
				<div class="input-group mt-3">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-expanded="false">Catégorie</button>

					<div class="dropdown-menu dropdown-menu-dark shadow p-2"
						aria-labelledby="dropdownMenuButton">
						<ul>
							<li><a class="dropdown-item rounded-2 active" href="#">Toutes
									les catégories</a></li>
						</ul>
						<hr class="dropdown-divider">
						<ul class="scrollable-menu">
							<li><a class="dropdown-item rounded-2" href="#">Animalerie</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Art,
									antiquités</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Auto,
									moto</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Bateaux,
									voiles, nautisme</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Beauté,
									bien-être, parfums</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Bijoux,
									montres</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Bricolage</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Céramique,
									verres</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Collections</a></li>
							<li><a class="dropdown-item rounded-2" href="#">DVD,
									Cinéma</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Électroménager</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Image,
									son</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Immobilier</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Informatique,
									réseaux</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Instruments
									de musique</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Jardin,
									terasses</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Jouets
									et jeux</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Jeux-vidéos</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Livres,
									BD</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Musique,
									CD, vinyles</a></li>
							<li><a class="dropdown-item rounded-2" href="#">Téléphonie,
									mobilité</a></li>
						</ul>
					</div>

					<input type="text" class="form-control"
						aria-label="Text input with dropdown button"
						placeholder="Un beaauuuuu salon de jardin...">
					<button class="btn btn-primary btn-block">Rechercher</button>
				</div>
				<div class="mt-3">
					<a data-bs-toggle="collapse" href="#more-filters" role="button" aria-expanded="false" aria-controls="more-filters" class="advanced"> Plus d'options de filtrages... <i
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
											type="checkbox" value="" id="check1" checked> <label
											class="form-check-label" for="check1"> Enchères
												ouvertes </label></li>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check2" checked> <label
											class="form-check-label" for="check2"> Mes enchères </label>
										</li>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check3" checked> <label
											class="form-check-label" for="check3"> Enchères
												remportées </label></li>
									</ul>

								</div>
								<div class="col-md-6">
									<input class="form-check-input" type="radio" name="radio1"
										id="radio2"> <label class="form-check-label"
										for="radio2"> Mes ventes </label>
									<ul>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check1" disabled> <label
											class="form-check-label" for="check1"> Mes ventes en
												cours </label></li>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check2" disabled> <label
											class="form-check-label" for="check2"> Ventes non
												débutées </label></li>
										<li class="form-check"><input class="form-check-input"
											type="checkbox" value="" id="check3" disabled> <label
											class="form-check-label" for="check3"> Ventes
												terminées </label></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<c:choose>
	<c:when test="${listeArticles.size()>0}">
		<div class="m-3">
			<div class="row justify-content-center">
			<c:forEach var="c" items="${listeArticles}">
				<div class="col-auto col-xxl-2 col-xl-3 col-md-4 col-sm-6">
					<div class="card mx-2 my-3">
						<img src="${c.photoPrincipal.url}" class="card-img-top img-fluid"
							alt="...">
						<div class="card-body">
							<h5 class="card-title text-center">${c.nom}</h5>
							<p class="card-text text-truncate">${c.description}</p>
							<a href="">En savoir plus ...</a>
						</div>
						<div class="card-footer">
							<small class="text-body-secondary">L'enchere commence
								dans 15 min</small> 
								<a href="#">${c.proprietaire.pseudo}</a>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>

	</c:when>
	<c:otherwise>
		<p>Pas d'article actuellement.</p>
	</c:otherwise>
</c:choose>


</main>


<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
<script src="./js/index.js" async></script>
</body>
</html>