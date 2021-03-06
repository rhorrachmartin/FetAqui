<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Producto"%>
<%@ page import="modelo.pojo.Vendedor"%>
<%@ page import="modelo.pojo.Cliente"%>
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
<script src="js/validarPassword.js"></script>
</head>
<body>
	<%
		Producto producto = null;
	Vendedor vendedor = null;
	Cliente c = null;
	HttpSession session = request.getSession(false);

	if (request.getAttribute("producto") != null) {
		producto = (Producto) request.getAttribute("producto");
	}

	if (request.getAttribute("vendedor") != null) {
		vendedor = (Vendedor) request.getAttribute("vendedor");
	}

	if (session.getAttribute("cliente") != null) {

		c = (Cliente) session.getAttribute("cliente");
	}

	int numProductos = 0;

	if (session.getAttribute("numProductos") != null) {
		numProductos = (Integer) session.getAttribute("numProductos");
	}
	%>

	<div id="container">

		<!-- NAVEGADOR -->
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
						if (c.getFoto().equals("FotoPorDefecto")) {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0" href="OpcionesPerfil.jsp">
							<img src="img/user.png" class="rounded-circle z-depth-0"
							alt="avatar image" height="35">
					</a></li>
					<%
						} else {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0" href="OpcionesPerfil.jsp">
							<img src="Imagenes/<%=c.getFoto()%>"
							class="rounded-circle z-depth-0" alt="avatar image" height="35">
					</a></li>
					<%
						}
					%>
				</ul>
			</div>
		</nav>
		<!-- FIN NAVEGADOR -->

		<div id="contenedorProducto" class="container mt-5 mb-5">

			<!-- CABECERA PRODUCTO -->
			<div class="row">

				<div class="col col-lg-12 text-center">
					<h3><%=producto.getNombre()%></h3>
					<h5><%=vendedor.getNombre()%></h5>
				</div>

			</div>
			<!-- FIN CABECERA PRODUCTO -->
			<hr>
			<!-- FOTO PRODUCTO -->
			<div class="row">
				<div class="col col-lg-12 mx-auto contenedorImagenProductoGrande">
					<%
						if (producto.getFoto().equals("producto.png")) {
					%>
					<img class="img-fluid imagenProductoGrande rounded mx-auto d-block"
						src="img/<%=producto.getFoto()%>" alt="Card image cap">

					<%
						} else {
					%>
					<img class="img-fluid imagenProductoGrande rounded mx-auto d-block"
						src="Imagenes/<%=producto.getFoto()%>" alt="Card image cap">
					<%
						}
					%>
				</div>
			</div>
			<!-- FIN FOTO PRODUCTO -->
			<hr>
			<!-- DESCRIPCIÓN DEL PRODUCTO -->
			<div class="row">

				<div class="text-center" style="width: 100%;">
					<h3>Descripción del producto</h3>
				</div>

				<div class="col col-lg-12"
					style="margin: 1px; word-wrap: break-word;">
					<p><%=producto.getDescripcion()%>
					<p>
				</div>
			</div>
			<!-- FIN DESCRIPCIÓN DEL PRODUCTO -->
			<hr>

			<!-- PRECIO PRODUCTO -->
			<div class="row">
				<div class="col col-lg-12 text-center">
					<h3>PRECIO</h3>
					<p><%=producto.getPrecio()%>
						€/
						<%=producto.getFormato()%></p>
				</div>
			</div>
			<!-- FIN PRECIO PRODUCTO -->


			<div class="col col-lg-12 text-center">
				<div class="cantidad">
					<form action="InsertarPedido" method="post">

						<button class="plus-boton" type="button" name="button">
							<img src="img/plus.svg" alt="" />
						</button>
						<input type="text" id="unidades<%=producto.getId()%>"
							class="unidades" name="cantidad" value="1">
						<button class="minus-boton" type="button" name="button">
							<img src="img/minus.svg" alt="" />
						</button>

						<input type="hidden" name="paginaProducto" value="1"> 
						<input type="hidden" name="id_producto" value="<%=producto.getId()%>">
						<input type="hidden" name="precio"
							value="<%=producto.getPrecio()%>">
						<button type="submit" class="btn btn-light-blue btn-md">
							<i class="fas fa-cart-plus"></i>
						</button>
					</form>
				</div>
			</div>

			<div class="col col-lg-12 text-center">
				<a type="button" class="btn btn-light-blue btn-md"
					href="ObtenerTodosProductos">IR A PRODUCTOS</a>
				<form action="PaginaVendedor" method="post">
					<input type="hidden" name="id_vendedor"
						value="<%=producto.getId_vendedor()%>">
					<button type="submit" class="btn btn-light-blue btn-md">
						IR AL VENDEDOR</button>
				</form>
			</div>
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



		<%
			boolean activado = false;
		if (request.getAttribute("activado") != null) {
			activado = (boolean) request.getAttribute("activado");
		}

		String email = "";
		if (request.getAttribute("email") != null) {
			email = (String) request.getAttribute("email");
		}
		%>

		<%
			if (activado == true) {
		%>
		<script>
			$(document).ready(function() {
				$("#centralModalSuccess").modal('show');
			});
		</script>
		<%
			}
		%>

		<%
			if (!email.equals("")) {
		%>
		<script>
			$(document).ready(function() {
				$("#centralModalInfo").modal('show');
			});
		</script>
		<%
			}
		%>

		<!-- Central Modal Medium Info -->
		<div class="modal fade" id="centralModalInfo" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-notify modal-info" role="document">
				<!--Content-->
				<div class="modal-content">
					<!--Header-->
					<div class="modal-header">
						<p class="heading lead">Información importante</p>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true" class="white-text">&times;</span>
						</button>
					</div>

					<!--Body-->
					<div class="modal-body">
						<div class="text-center">
							<i class="fas fa-check fa-4x mb-3 animated rotateIn"></i>
							<p>
								Le hemos enviado un correo electrónico para confirmar su
								usuario. <br> Haga click sobre el enlace que le hemos
								enviado para activarlo
							</p>
						</div>
					</div>

					<!--Footer-->
					<div class="modal-footer justify-content-center">
						<a type="button" class="btn btn-primary" data-dismiss="modal">Entendido</a>
					</div>
				</div>
				<!--/.Content-->
			</div>
		</div>
	</div>
	<script src="js/cantidadesCarro.js"></script>

</body>
</html>