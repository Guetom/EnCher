<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="/WEB-INF/template/header.jsp"></jsp:include>

<h1>HI</h1>
	<c:choose>
		<c:when test="${listeArticles.size()>0}">
			<c:forEach var="c" items="${listeArticles}">
				<ul>
					<li>${c.nom} ${c.prix} euro</li>
				</ul>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>Pas d'article actuellement.</p>
		</c:otherwise>
	</c:choose>

</body>

<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>

</html>