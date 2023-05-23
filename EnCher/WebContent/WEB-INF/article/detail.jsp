<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/template/alert.jsp"></jsp:include>

<main> 
<section class="enchere">

<!-- Entête (titre) de l'enchère -->
        <div class="p-5 pb-4 text-center">
            <h2 class="fs-1">Détail de la vente</h2>
        </div>

        <!-- Boîte contenant les informations de l'enchère -->
        <section class="w-75 mx-auto border shadow-sm rounded-1">

            <div class="row py-5">
                <!-- Images -->
                <c:choose>
					<c:when test="${article.listeImage.size() > 0}">
					<div class="col-lg-5 col-12 px-5 py-1">
                    <div id="carouselExampleIndicators" class="carousel slide bg-white border shadow-sm"> <!--data-bs-ride="carousel"-->
                        <div class="carousel-indicators">
                          <c:forEach var="c" items="${article.listeImage}" varStatus="loop">
						<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${ loop.count }" aria-label="Slide ${ loop.count }"></button>
						</c:forEach>
                        </div>
                        <div class="carousel-inner">
                          <c:forEach var="c" items="${article.listeImage}" varStatus="loop">
						<div class="carousel-item ${loop.first ? 'active' : ''}">
                            <img src="${c.url}" class="d-block w-100" alt="...">
                          </div>
                        </c:forEach>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                          <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                          <span class="carousel-control-next-icon" aria-hidden="true"></span>
                          <span class="visually-hidden">Next</span>
                        </button>
                      </div>
                    </div>
					</c:when>
					<c:otherwise>
						<h3 class="p-5">Pas d'image pour cet article.</h3>
					</c:otherwise>
				</c:choose>

                <!-- Section principale (informations de l'enchère) -->
                <div class="col-lg-7 col-12 px-3">

                    <!-- Entête (titre) de l'enchère -->
                    <div class="entete pb-3">
                        <h3 class="fs-2">${ article.nom }</h2>
                    </div>

                    <div class="description row p-1">
                        <div class="col-lg-2 col-12"><h5>Description :</h5></div>
                        <div class="col-lg-10 col-12"><p>${ article.description }</p></div>
                    </div>

                    <div class="categorie row py-1">
                        <div class="col-lg-2 col-12"><h5>Catégorie :</h5></div>
                        <div class="col-lg-10 col-12"><p>${ article.categorie.libelle }</p></div>
                    </div>

                    <div class="mise-a-prix row p-1">
                        <div class="col-lg-2 col-12"><h5>Mise à prix :</h5></div>
                        <div class="col-lg-10 col-12"><p><span class="prix">${ article.prix }</span> points</p></div>
                    </div>
                    
                    <c:choose>
					    <c:when test="${ enchere != null }">
						    <div class="meilleure-offre row p-1">
		                        <div class="col-lg-2 col-12"><h5>Meilleure offre :</h5></div>
		                        <div class="col-lg-10 col-12"><p><span class="prix">${ enchere.montant }</span> points par <a href="#">${ enchere.encherisseur.pseudo }</a></p></div>
		                    </div>
					    </c:when>
					    <c:otherwise>
					    	<h5>Pas encore de proposition</h5>
					    </c:otherwise>
					</c:choose>

                    <div class="fin-enchere row p-1">
                        <div class="col-lg-2 col-12"><h5>Fin de l'enchère :</h5></div>
                        <div class="col-lg-10 col-12"><p>${ article.dateFin }</p></div>
                    </div>

                    <div class="vendeur row p-1">
                        <div class="col-md-2 col-12"><h5>Vendeur :</h5></div>
                        <div class="col-md-10 col-12"><a href="#">${ article.proprietaire.pseudo }</a></div>
                    </div>

                    <form class="proposition row p-1">
                        <div class="col-lg-2 col-md-4 col-12"><h5>Faire une proposition :</h5></div>
                        <div class="col-md-5 col-6"><input type="number" id="proposition" class="form-control" min="${ enchere.montant }" value="${ enchere.montant }"></div>
                        <div class="col-lg-5 col-md-3 col-6"><button type="submit" class="btn btn-primary">Enchérir</button></div>
                    </form>

                </div>
            </div>
        </section>
      </main>

<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>