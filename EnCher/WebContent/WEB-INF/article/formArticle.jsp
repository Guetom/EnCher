<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>

<main> 
<section class="enchere">

<!-- Ent�te (titre) de l'ench�re -->
        <div class="p-5 pb-4 text-center">
            <h2 class="fs-1">Vendre un article</h2>
        </div>

        <!-- Bo�te contenant les informations de l'ench�re -->
        <section class="w-75 mx-auto border shadow-sm rounded-1">

            <div class="row py-5">
                <!-- Images de pr�visualisation -->
                <div class="col-lg-5 col-12 px-5 py-1">
                    <img class="image-preview border shadow-sm" src="${pageContext.request.contextPath}/assets/no-image.svg">
                </div>

                <!-- Section principale (informations de l'ench�re) -->
                <form class="form-modif-article col-lg-7 col-12 px-3" method="post" action="${pageContext.request.contextPath}/article/ajouter" enctype="multipart/form-data">

                  <div class="modif-nom-article form-floating">
                    <input type="text" class="form-control" id="nom" name="nom" placeholder=" " required>
                    <label for="nom" class="px-3 text-muted">Nom de l'article</label>
                  </div>

                  <div class="modif-description form-floating">
                    <textarea class="form-control" placeholder="Description" id="description" name="description" style="height: 100px" required></textarea>
                    <label for="description" class="px-3 text-muted">Description</label>
                  </div>

                  <div class="form-floating">
                    <select class="form-select" id="categorie" aria-label="Choissisez votre cat�gorie" required>
                    <c:forEach var="c" items="${categories}">
						<option value="${c.idCategorie}">${c.libelle}</a></option>
					</c:forEach>
                    </select>
                    <label for="categorie-article">Cat�gorie</label>
                  </div>

                    <div class="photo-article">
                      <label for="photo" class="form-label">Images de l'article :</label>
                      <input class="form-control" type="file" id="photo" multiple name="img[]" accept="image/*">
                    </div>

                    <div class="modif-prix-article form-floating">
                      <input type="number" class="form-control" id="prix" name="prix" placeholder=" " min="0" value="0" required>
                      <label for="prix" class="px-3 text-muted">Mise � prix</label>
                    </div>

                    <div class="modif-dateDebut form-floating">
                      <input type="datetime-local" class="form-control" id="dateDebut" name="dateDebut" required>
                      <label for="dateDebut" class="px-3 text-muted">Date de d�but de l'ench�re</label>
                    </div>

                    <div class="modif-dateFin form-floating">
                      <input type="datetime-local" class="form-control" id="dateFin" name="dateFin" required>
                      <label for="dateFin" class="px-3 text-muted">Date de fin de l'ench�re</label>
                    </div>

                    <fieldset class="modif-retrait-article border border-dark rounded-3 p-3">
                      <legend class="float-none w-auto px-3">Retrait</legend>

                      <div class="modif-rue form-floating">
                        <input type="text" class="form-control" id="rue" name="rue" placeholder=" " required>
                        <label for="rue" class="px-3 text-muted">Rue</label>
                      </div>

                      <div class="modif-codePostal form-floating">
                        <input type="text" class="form-control" id="codePostal" name="codePostal" min="0" max="99999" placeholder=" " required>
                        <label for="codePostal" class="px-3 text-muted">Code Postal</label>
                      </div>

                      <div class="modif-ville form-floating">
                        <input type="text" class="form-control" id="ville" name="ville" placeholder=" " required>
                        <label for="ville" class="px-3 text-muted">Ville</label>
                      </div>

                    </fieldset>

                    <div class="enregistrer-annuler-modif d-flex flex-row-1 pt-3">
                      <div><button type="submit" class="btn btn-lg btn-success my-1">Enregistrer</button></div>
                      <div><a href="${pageContext.request.contextPath}" class="btn btn-lg btn-danger mx-3 my-1">Annuler</a></div>
                    </div>

                  </form>
            </div>
        </section>
      </main>

<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/js/preview-image" async></script>
</body>
</html>