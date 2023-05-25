<%@ page import="fr.eni.EnCher.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!empty listeCodesErreur || !empty sessionScope.listeCodesErreurs}">
    <div class="encher-alert alert alert-danger alert-dismissible text-start fade show my-3 w-50 mx-auto position-absolute start-50 translate-middle" role="alert">
        <strong class="fs-3">Erreur !</strong>
        <ul>
            <c:forEach var="code" items="${listeCodesErreur}">
                <li>${LecteurMessage.getMessageErreur(code)}</li>
            </c:forEach>
            <c:forEach var="code" items="${sessionScope.listeCodesErreurs}">
                <li>${LecteurMessage.getMessageErreur(code)}</li>
            </c:forEach>
        </ul>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>
<% request.getSession().removeAttribute("listeCodesErreurs"); %>

<c:if test="${successMessage != messageSucces}">
	<div class="encher-alert alert alert-success alert-dismissible text-start fade show my-3 w-50 mx-auto position-absolute start-50 translate-middle" role="alert">
		${messageSucces}
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	<% request.getSession().removeAttribute("messageSucces"); %>
</c:if>