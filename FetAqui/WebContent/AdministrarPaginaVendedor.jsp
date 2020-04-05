<%@page import="modelo.controlador.VentasOnline"%>
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
		int ventaOnline = 0;

		if (session.getAttribute("vendedor") != null) {
			vendedor = (Vendedor) session.getAttribute("vendedor");
			ventaOnline = vendedor.getVenta_online();

		}
	%>
	<div id="container" style="min-height: 60vh">
		<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="Principal">Inicio</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
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




		<%
			if (request.getAttribute("error") == null) {
		%>
		<%
			if (ventaOnline == 1) {
		%>
		<div class="container">
			<div class="row loginPanel">
				<div class="lg lg-12 ">
					<a class="btn btn-primary btn-lg" href="AñadirProducto">AÑADIR
						PRODUCTOS</a> <a class="btn btn-info btn-lg" href="#">MODIFICAR
						PRODUCTOS</a>
					<div class="custom-control custom-switch">
						<form id="ventaOnline" action="VentasOnline" method="post">
							<input type="checkbox" class="custom-control-input"
								id="customSwitch1" name="ventaOnline" checked> <label
								id="labelCheckBox" class="custom-control-label"
								for="customSwitch1">Ventas online activadas</label>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%
			} else {
		%>
		<div class="container">
			<div class="row loginPanel">
				<div class="lg lg-12 ">
					<a class="btn btn-primary btn-lg" href="AñadirProducto">AÑADIR
						PRODUCTOS</a> <a class="btn btn-info btn-lg" href="#">MODIFICAR
						PRODUCTOS</a>
					<div class="custom-control custom-switch">
						<form id="ventaOnline" action="VentasOnline" method="post">
							<input type="checkbox" class="custom-control-input"
								id="customSwitch1" name="ventaOnline"> <label
								id="labelCheckBox" class="custom-control-label"
								for="customSwitch1">Ventas online desactivadas</label>
						</form>
					</div>
				</div>
			</div>
		</div>


		<%
			}
		%>
		<%
			}
		%>
	</div>
	<!-- Footer -->
	<footer class="page-footer font-small unique-color-dark pt-4 ">

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
	<script type="text/javascript">
		function submit() {

			$('#ventaOnline').submit();

		}

		$(function() {
			$('#customSwitch1').on('change', function() {
				submit();
			});
		});
	</script>


</body>
</html>