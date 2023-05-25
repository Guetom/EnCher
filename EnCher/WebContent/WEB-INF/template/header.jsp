<%@page import="fr.eni.EnCher.bo.Utilisateur"%>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/favicon.ico">
<meta name="description" content="">
<meta name="author" content="">

<title>EnCher</title>

<!-- Bootstrap and CSS CUSTOM -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/enchere.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/profil.css" rel="stylesheet">

</head>

<body>
	<header class="sticky-top bg-light">
		<nav
			class="navbar navbar-expand-md px-3 ">
			<div class="container-fluid">
				<a class="" href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/assets/logo.png" height="50">
				</a>
				<!-- <a class="navbar-brand mx-1" href="#">
                    <span class="fs-3 fw-bold">EnCher</span>
                </a> -->
				<button data-bs-toggle="collapse" class="navbar-toggler"
					data-bs-target="#nav">
					<span class="visually-hidden">Toggle navigation</span><span
						class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="nav">

					<ul class="navbar-nav mx-auto">
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}">Enchères</a></li>
						<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/article/ajouter">Vendre

								un article</a></li>
					</ul>

					<% Utilisateur user = (Utilisateur) session.getAttribute("user");
					
					if(user != null && user.getPhotoProfil() == null){
						%>
					<div class="dropdown">
						<a class="navbar-brand dropdown-toggle" role="button"
							data-bs-toggle="dropdown"> <img
							src="${pageContext.request.contextPath}/assets/profil.jpg"
							alt="Avatar Logo" height="60" class="rounded-pill">
						</a>
						<div class="dropdown-menu dropdown-menu-end">
						
						</div>
						<ul >
							<li><h5 class="dropdown-header">${user.pseudo}</h5></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/profil">Mon profil</a></li>
							<li><a class="dropdown-item disconnect" href="${pageContext.request.contextPath}/deconnection">Se
									déconnecter</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><h5 class="dropdown-header">Crédit : <span class="prix">${user.credit}</span></h5></li>
						</ul>
					</div>
					<%
					}else if(user != null){
						%>
					<div class="dropdown">
						<a class="navbar-brand dropdown-toggle" role="button"
							data-bs-toggle="dropdown"> <img
							src="${pageContext.request.contextPath}/image/${ sessionScope.user.photoProfil.url }" alt="Avatar Logo"
							height="50" class="rounded-pill">
						</a>
						<ul class="dropdown-menu dropdown-menu-end">
							<li><h4 class="dropdown-header">${user.pseudo}</h5></li>
							<li><a class="dropdown-item" href="${pageContext.request.contextPath}/profil">Mon profil</a></li>
							<li><a class="dropdown-item disconnect" href="${pageContext.request.contextPath}/deconnection">Se
									déconnecter</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><h5 class="dropdown-header">Crédit : <span class="text-success">${user.credit}</span></h5></li>
						</ul>
					</div>
					<%
					}
					else{
						%><div class="btn-group d-flex" role="group">
						<a class="btn btn-primary fw-bold rounded-end rounded-4"
							type="button"
							href="${pageContext.request.contextPath}/inscription">S'inscrire</a>
						<a
							class="btn btn-outline-secondary fw-bold rounded-start rounded-4"
							type="button" href="${pageContext.request.contextPath}/connexion">Se
							connecter</a>
					</div>
					<%
					}
					%>


				</div>
			</div>
		</nav>
	</header>
	
<jsp:include page="/WEB-INF/template/alert.jsp"></jsp:include>