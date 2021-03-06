<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Cliente"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FET AQUI</title>
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<!-- Google Fonts -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
<link
	href="https://fonts.googleapis.com/css2?family=Amatic+SC:wght@700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap"
	rel="stylesheet">
<!-- Bootstrap core CSS -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Material Design Bootstrap -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.16.0/css/mdb.min.css"
	rel="stylesheet">
<link href="css/estilos.css" type="text/css" rel="stylesheet">
<!-- -------------------SCRIPTS--------------- -->
<!-- JQuery -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.16.0/js/mdb.min.js"></script>

<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
<script src="js/validarPassword.js"></script>

</head>
<body>


	<%
		HttpSession session = request.getSession(false);
	Cliente cliente = null;
	if (session.getAttribute("cliente") != null) {
		cliente = (Cliente) session.getAttribute("cliente");

	}

	int numProductos = 0;

	if (session.getAttribute("numProductos") != null) {
		numProductos = (Integer) session.getAttribute("numProductos");
	}
	%>
	<div id="container">
		<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
			<a class="navbar-brand" href="Principal">FET AQUÍ</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="Principal">Inicio</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ObtenerTodosProductos">Productos</a></li>
					<li class="nav-item"><a class="nav-link"
						href="ObtenerTodosVendedores">Vendedores</a></li>
				</ul>
				<ul class="navbar-nav ml-auto nav-flex-icons">
					<li class="nav-item"><a
						class="btn btn-primary btn-sm botonesNavegador" href="Cesta"><i
							class="fas fa-shopping-basket"></i> CESTA (<%=numProductos%>)</a></li>
					<li class="nav-item"><a
						class="btn btn-primary btn-sm botonesNavegador"
						href="OpcionesPerfil.jsp">MI PÁGINA</a></li>
					<li class="nav-item"><a
						class="btn btn-success btn-sm botonesNavegador" href="Logout">SALIR</a></li>
					<%
						if (cliente.getFoto().equals("FotoPorDefecto")) {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0" href="OpcionesPerfil.jsp">
							<img src="img/user.png" class="rounded-circle z-depth-0"
							alt="avatar image" height="35">
					</a></li>
					<%
						} else {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0" href="OpcionesPerfil.jsp">
							<img src="Imagenes/<%=cliente.getFoto()%>"
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
		<div class="container">
			<div class="row justify-content-center m-5">
				<div class="lg lg-12 ">
					<a class="btn btn-primary btn-lg" href="Perfil">ACTUALIZAR
						PERFIL</a> <a class="btn btn-info btn-lg" href="ObtenerPedidosCliente">PEDIDOS</a>
				</div>
			</div>
		</div>
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
				<li><a id="baja" class="btn btn-danger btn-sm">DARME DE
						BAJA</a></li>
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

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>

	<script>
		$(document)
				.ready(
						function() {
							$('#baja')
									.on(
											'click',
											function() {
												bootbox
														.confirm({
															message : "¿Seguro que desea darse de baja?",
															buttons : {
																confirm : {
																	label : 'Sí',
																	className : 'btn-success'
																},
																cancel : {
																	label : 'No',
																	className : 'btn-danger'
																}
															},
															callback : function(
																	result) {
																if (result) {
																	var url = "BajaCliente";
																	window.location.href = url;

																} else {
																	bootbox
																			.alert({
																				message : "Gracias por seguir confiando en nosotros!"
																			});
																}
															}
														});
											});
						});
	</script>

</body>
</html>