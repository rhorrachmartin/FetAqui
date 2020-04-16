<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Vendedor"%>
<%@ page import="javax.servlet.http.HttpSession"%>
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


	<%
		HttpSession session = request.getSession(false);
		Vendedor vendedor = null;

		if (session.getAttribute("vendedor") != null) {
			vendedor = (Vendedor) session.getAttribute("vendedor");

		}
	%>
	<div id="container" style="min-height: 60vh">
		<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
			<a class="navbar-brand" href="#">FET AQUÍ</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="Principal">Inicio</a></li>
					<li class="nav-item"><a class="nav-link" href="Productos">Productos</a></li>
					<li class="nav-item"><a class="nav-link" href="ObtenerTodosVendedores">Vendedores</a></li>
				</ul>
				<ul class="navbar-nav ml-auto nav-flex-icons">
					<li class="nav-item"><a class="btn btn-primary btn-sm"
						href="OpcionesPerfilVendedor.jsp">MI PÁGINA</a></li>
					<li class="nav-item"><a class="btn btn-success btn-sm"
						href="Logout">SALIR</a></li>
					<%
						if (vendedor.getFoto().equals("desconocido.txt")) {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0"
						href="OpcionesPerfilVendedor.jsp"> <img src="img/user.png"
							class="rounded-circle z-depth-0" alt="avatar image" height="35">
					</a></li>
					<%
						} else {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0"
						href="OpcionesPerfilVendedor.jsp"> <img
							src="Imagenes/<%=vendedor.getFoto()%>"
							class="rounded-circle z-depth-0" alt="avatar image" height="35">
					</a></li>
					<%
						}
					%>
				</ul>
			</div>
		</nav>
	</div>
	<!-- Footer -->
	<footer class="page-footer font-small unique-color-dark pt-4">

		<!-- Footer Elements -->
		<div class="container">

			<!-- Call to action -->
			<ul class="list-unstyled list-inline text-center py-2">
				<li class="list-inline-item">
					<h5 class="mb-1">Regístrate gratis!</h5>
				</li>
				<li class="list-inline-item"><a href="Login"
					class="btn btn-outline-white btn-rounded" data-toggle="modal"
					data-target="#modalRegistro">Darse de alta</a></li>
			</ul>
			<!-- Call to action -->

		</div>
		<!-- Footer Elements -->

		<!-- Copyright -->
		<div class="footer-copyright text-center py-3 ">
			© 2020 Copyright: <a href="https://fetaqui.com/"> fetaqui.com</a>
		</div>
		<!-- Copyright -->

	</footer>
	<!-- Footer -->

</body>
</html>