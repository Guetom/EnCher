<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connexion</title>
<!-- Bootstrap and CSS CUSTOM -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
	<main
		class="d-flex flex-column justify-content-center flex-grow-1 form-signin w-100 m-auto">
	<form method="post"
		action="${pageContext.request.contextPath}/connexion">
		<img class="mb-4"
			src="${pageContext.request.contextPath}/assets/logo.png"
			alt="Logo EnCher">
		<h1 class="h3 mb-3 fw-semibold">Connectez-vous</h1>

		<div class="form-floating">
			<input type="text" class="form-control" id="emailPseudo" name="emailPseudo"
				placeholder="" required> <label for="emailPseudo">Adresse email ou Pseudo</label>
		</div>
		<div class="form-floating">
			<input type="password" class="form-control" id="password"
				name="password" placeholder=" " required> <label for="password">Mot
				de passe</label>
		</div>

		<!-- <div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me">
				Se souvenir de moi
			</label>
		</div> -->
		<button class="w-100 btn btn-lg btn-primary mb-1" type="submit">Se
			connecter</button>
		<p>
			Pas de compte ? <a
				href="${pageContext.request.contextPath}/inscription">Créer un
				compte</a>
		</p>
	</form>
	</main>

	<jsp:include page="/WEB-INF/template/footer.jsp"></jsp:include>


</body>
</html>