<%@page import="fr.eni.EnCher.bo.Article"%>
<%@page import="fr.eni.EnCher.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>

<main> 
<section class="enchere">

<!-- Ent�te (titre) de l'ench�re -->
        <div class="p-5 pb-4 text-center">
            <h2 class="fs-1">D�tail de la vente</h2>
        </div>

        <!-- Bo�te contenant les informations de l'ench�re -->
        <section class="w-75 mx-auto border shadow-sm rounded-1">

            <div class="row py-5">
                <!-- Images -->
                <c:choose>
					<c:when test="${article.listeImage.size() > 0}">
					<div class="col-lg-5 col-12 px-5 py-1">
                    <div id="carouselExampleIndicators" class="carousel slide bg-white border shadow-sm"> <!--data-bs-ride="carousel"-->
                        <div class="carousel-indicators">
                          <c:forEach var="c" items="${article.listeImage}" varStatus="loop">
						<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${ loop.count-1 }" aria-label="Slide ${ loop.count }" ${loop.first ? 'class="active" aria-current="true"' : ''}></button>
						</c:forEach>
                        </div>
                        <div class="carousel-inner">
                          <c:forEach var="c" items="${article.listeImage}" varStatus="loop">
						<div class="carousel-item ${loop.first ? 'active' : ''}">
                            <img src="${pageContext.request.contextPath}/image/${c.url}" class="d-block w-100" alt="...">
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

                <!-- Section principale (informations de l'ench�re) -->
                <div class="col-lg-7 col-12 px-3">

                    <!-- Ent�te (titre) de l'ench�re -->
                    <div class="entete pb-3">
                        <h3 class="fs-2">${ article.nom }</h2>
                    </div>

                    <div class="description row p-1">
                        <div class="col-lg-2 col-12"><h5>Description :</h5></div>
                        <div class="col-lg-10 col-12"><p>${ article.description }</p></div>
                    </div>

                    <div class="categorie row py-1">
                        <div class="col-lg-2 col-12"><h5>Cat�gorie :</h5></div>
                        <div class="col-lg-10 col-12"><p>${ article.categorie.libelle }</p></div>
                    </div>

                    <div class="mise-a-prix row p-1">
                        <div class="col-lg-2 col-12"><h5>Mise � prix :</h5></div>
                        <div class="col-lg-10 col-12"><p><span class="prix">${ article.prix }</span> cr�dits</p></div>
                    </div>
                    
                    <c:choose>
					    <c:when test="${ enchere != null }">
						    <div class="meilleure-offre row p-1">
		                        <div class="col-lg-2 col-12"><h5>Meilleure offre :</h5></div>
		                        <div class="col-lg-10 col-12"><p><span class="prix">${ enchere.montant }</span> cr�dits par <a href="${pageContext.request.contextPath}/profil?pseudo=${enchere.encherisseur.pseudo}">${ enchere.encherisseur.pseudo }</a></p></div>
		                    </div>
					    </c:when>
					    <c:otherwise>
					    	<h5>Pas encore de proposition</h5>
					    </c:otherwise>
					</c:choose>
					
                    <div class="fin-enchere row p-1">
					    <div class="col-lg-2 col-12"><h5>Fin de l'ench�re :</h5></div>
					    <div class="col-lg-10 col-12">
					        <fmt:parseDate var="date" value="${article.dateFin}" pattern="yyyy-MM-dd'T'HH:mm" />
					        <fmt:formatDate value="${date}" pattern="dd/MM/yyyy HH:mm" var="formattedDate" />
					        <p>${formattedDate}</p>
					    </div>
					</div>

                    <div class="vendeur row p-1">
                        <div class="col-md-2 col-12"><h5>Vendeur :</h5></div>
                        <div class="col-md-10 col-12"><a href="${pageContext.request.contextPath}/profil?pseudo=${article.proprietaire.pseudo}">${ article.proprietaire.pseudo }</a></div>
                    </div>
					
					<c:if test="${user != null && user.idUtilisateur != article.proprietaire.idUtilisateur}">
	                    <form class="proposition row p-1" method="post" action="${pageContext.request.contextPath}/article/encherir" enctype="multipart/form-data">
	                        <div class="col-lg-2 col-md-4 col-12"><h5>Faire une proposition :</h5></div>
	                        <div class="col-md-5 col-6">
	                        <input type="number" id="proposition" name="proposition" class="form-control" min="${enchere.montant}" value="${enchere.montant}">
	                        <input type="hidden" id="idArticle" name="idArticle" value="${ article.idArticle }" /></div>
	                        <div class="col-lg-5 col-md-3 col-6"><button type="submit" class="btn btn-primary">Ench�rir</button></div>
	                    </form>
                    </c:if>
                    
                </div>
            </div>
        </section>
      </main>

<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
</body>
</html>