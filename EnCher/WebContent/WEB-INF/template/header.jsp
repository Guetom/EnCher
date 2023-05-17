<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>EnCher</title>

    <!-- Bootstrap and CSS CUSTOM -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    
</head>

<body class="auction-page min-vh-100">
  <header>
    <nav class="navbar navbar-expand-sm navbar-light bg-light bg-gradient sticky-top">
      <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/assets/logo.png" alt="Logo EnCher" style="height:60px;"
            class="img-fluid"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavId">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavId">
          <!-- Modifi� 'justify-content-end' � la place de 'justify-self-end' -->
          <ul class="navbar-nav ms-auto mt-2 mt-lg-0"> <!-- Ajout de 'ms-auto' ici -->
            <li class="nav-item mx-sm-1">
              <a class="nav-link" href="#">Ench�res</a>
            </li>
            <li class="nav-item mx-sm-1">
              <a class="nav-link" href="#">Vendre un article</a>
            </li>
            <li class="nav-item mx-sm-3">
              <div class="dropdown">
                <a class="navbar-brand dropdown-toggle" role="button" data-bs-toggle="dropdown">
                  <img src="${pageContext.request.contextPath}/assets/profil.jpg" alt="Avatar Logo" style="width:40px;" class="rounded-pill">
                </a>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li><a class="dropdown-item" href="#">Mon profil</a></li>
                  <li><a class="dropdown-item disconnect" href="#">Se d�connecter</a></li>
                </ul>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>