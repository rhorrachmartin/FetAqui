<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FET AQUI</title>
<!--  FONT AWESOME -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<!-- BOOTSRAP core CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Material design bootstrap -->
<link rel="stylesheet" href="css/mdb.min.css">
<!-- MI CSS -->
<link rel="stylesheet" href="css/estilos.css">
<!-- -------------------SCRIPTS--------------- -->
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/mdb.min.js"></script>
<script src="js/validarPassword.js"></script>
</head>
<body>


	<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			</ul>
			<ul class="navbar-nav ml-auto nav-flex-icons">
			<li class="nav-item"><a class="btn btn-primary btn-sm" href="Perfil">PERFIL</a></li>
				<li class="nav-item"><a class="btn btn-success btn-sm" href="Logout">SALIR</a></li>
				<li class="nav-item avatar"><a class="nav-link p-0" href="#">
						<img src="img/user.png" class="rounded-circle z-depth-0"
						alt="avatar image" height="35">
				</a></li>
			</ul>
		</div>
	</nav>




	<%
		if (request.getAttribute("error") == null) {
	%>
	<div class="container">
		<div class="row loginPanel">
			<div class="lg lg-12 ">
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#modalRegistro">Darse de alta</button>
				<button class="btn btn-success btn-lg" data-toggle="modal"
					data-target="#modalLogin">Entrar</button>
			</div>
		</div>
	</div>
	<%
		} else {
			String error = (String) request.getAttribute("error");
	%>


	<div class="container">

		<div class="row loginPanel">
			<div class="lg lg-12 ">
				<div class="row loginPanelAlerta">
					<div class="lg lg-12">
						<button class="btn btn-danger bnt-lg"><%=error%></button>
					</div>
				</div>
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#modalRegistro">Darse de alta</button>
				<button class="btn btn-success btn-lg" data-toggle="modal"
					data-target="#modalLogin">Entrar</button>
			</div>
		</div>
	</div>

	<%
		}
	%>

</body>
</html>