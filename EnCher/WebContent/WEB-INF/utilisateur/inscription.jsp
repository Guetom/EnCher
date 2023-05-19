<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription</title>
<!-- Bootstrap and CSS CUSTOM -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
</head>
<body class="text-center bg-light">
	<main class="m-auto">
	<form method="post"
		action="${pageContext.request.contextPath}/inscription">
		<img class="mb-2"
			src="${pageContext.request.contextPath}/assets/logo.png "
			alt="Logo EnCher">
		<h1 class="h3 mb-5 fw-semibold">Inscrivez-vous</h1>

		<div class="row col-lg-6 m-auto">
			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="pseudo" name="pseudo"
					placeholder=" "required> <label for="pseudo"
					class="px-3 text-muted">Pseudo</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="nom" name="nom"
					placeholder=" " required> <label for="nom" class="px-3 text-muted">Nom</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="prenom" name="prenom"
					placeholder=" " required> <label for="prenom" class="px-3 text-muted">Prénom</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="date" class="form-control" id="date_naissance" name="date_naissance"
					placeholder=" " required> <label for="date_naissance" class="px-3 text-muted">Date de naissance</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="email" class="form-control" id="email" name="email"
					placeholder=" " required> <label for="email" class="px-3 text-muted">Adresse
					email</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="url" class="form-control" id="photo" name="photo"
					placeholder=" " required> <label for="photo" class="px-3 text-muted">Photo de profil</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="tel" class="form-control" id="telephone"
					name="telephone" placeholder=" "> <label for="telephone"
					class="px-3 text-muted">Téléphone</label>
			</div>


			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="rue" name="rue"
					placeholder=" " required> <label for="rue" class="px-3 text-muted">Rue</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="code_postal"
					name="code_postal" placeholder=" " required> <label
					for="code_postal" class="px-3 text-muted">Code postal</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="text" class="form-control" id="ville" name="ville"
					placeholder=" " required> <label for="ville" class="px-3 text-muted">Ville</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="password" class="form-control" id="password"
					name="password" placeholder=" " required> <label for="password"
					class="px-3 text-muted">Mot de passe</label>
			</div>

			<div class="form-floating col-lg-6 mb-4">
				<input type="password" class="form-control" id="password_confirm"
					name="password_confirm" placeholder=" " required> <label
					for="password_confirm" class="px-3 text-muted">Confirmer le
					mot de passe</label>
			</div>

			<div class="col-6 mx-auto d-flex">
				<button class="w-100 btn btn-lg btn-primary mb-1 mx-3" type="submit">S'inscrire</button>
				<a class="w-100 btn btn-lg btn-danger mb-1 mx-3"
					href="${pageContext.request.contextPath}/connexion">J'ai déjà
					un compte</a>
			</div>

		</div>
	</form>
	</main>

	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>


</body>
</html>