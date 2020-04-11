<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Producto"%>
<%@ page import="modelo.pojo.Categoria"%>
<%@ page import="java.util.ArrayList"%>
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

	<div id="container" style="min-height: 80vh">

		<!-- NAVEGADOR -->
		<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="Principal">Inicio</a></li>
					<li class="nav-item"><a class="nav-link" href="Productos">Productos</a></li>
					<li class="nav-item"><a class="nav-link" href="Vendedores">Vendedores</a></li>
				</ul>
				<ul class="navbar-nav ml-auto nav-flex-icons">
					<%
						if (request.getAttribute("error") != null) {
							String error = (String) request.getAttribute("error");
					%>
					<li class="nav-item"><a class="btn btn-danger btn-sm"
						data-toggle="modal" data-target="#"><%=error%></a></li>
					<%
						}
					%>
					<li class="nav-item"><a class="btn btn-primary btn-sm"
						data-toggle="modal" data-target="#modalRegistro">REGISTRARSE</a></li>

					<li class="nav-item"><a class="btn btn-success btn-sm"
						data-toggle="modal" data-target="#modalLogin">LOGIN</a></li>

					<li class="nav-item avatar"><a class="nav-link p-0" href="#">
							<img src="img/user.png" class="rounded-circle z-depth-0"
							alt="avatar image" height="35">
					</a></li>
				</ul>
			</div>
		</nav>
		<!-- FIN NAVEGADOR -->

		<!-- DIV ELECCION CATEGORÍA -->

		<%
			ArrayList<Categoria> categorias = null;
			ArrayList<Producto> productos = null;

			if (request.getAttribute("categorias") != null) {
				categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
			}
			
			if (request.getAttribute("productos") != null) {
				productos = (ArrayList<Producto>) request.getAttribute("productos");
			}
		%>

		<div class="container">
			<!-- DIV BUSQUEDA POR CATEGORÍA -->
			<div class="row">

				<div class="col col-lg-12 text-center">
					<h3>BUSCAR POR CATEGORÍAS</h3>
					<select class="browser-default custom-select">
						<%
							for (Categoria ca : categorias) {
						%>
						<option value=<%=ca.getId()%>>
							<%=ca.getNombre()%></option>
						<%
							}
						%>
					</select>

				</div>


			</div>
			<!-- FIN DIV BUSQUEDA POR CATEGORÍA -->
		</div>
		<!-- FIN DIV ELECCION CATEGORÍA -->
		
		<!-- Card deck -->
		<div class="row row-cols-1 row-cols-md-3">

					<%
						for (Producto pro : productos) {
					%>


					<!-- Card -->
					<div class="col mb-4">
						<div class="card h-100">
							<!--Card image-->
							<div id="contenedorImagenProducto" class="view overlay zoom">
								<%
									if (pro.getFoto().equals("producto.png")) {
								%>
								<img id="imagenProducto" class="img-fluid z-depth-1"
									src="img/<%=pro.getFoto()%>" alt="Card image cap">

								<%
									} else {
								%>
								<img id="imagenProducto" class="img-fluid z-depth-1"
									src="Imagenes/<%=pro.getFoto()%>" alt="Card image cap">
								<%
									}
								%>
								<div class="mask rgba-white-slight"></div>
							</div>

							<!--Card content-->
							<div class="card-body text-center">

								<!--Title-->
								<h4 class="card-title"><%=pro.getNombre()%></h4>
								<!--Text-->
								<p id="descripcionProducto" class="card-text"><%=pro.getDescripcion()%></p>
								<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
								<div class="row">

									<div class="col col-lg-6">
										<button type="button" class="btn btn-light-blue btn-md">
											<a data-toggle="modal"
												data-target="#modalEditarProducto<%=pro.getId()%>"><i
												class="far fa-edit"></i></a>
										</button>
									</div>

									<div class="col col-lg-6">
										<button type="button" class="btn btn-light-blue btn-md">
											<a data-toggle="modal"
												data-target="#modalLoginAvatar<%=pro.getId()%>"><i
												class="far fa-images"></i></a>
										</button>
									</div>

								</div>
							</div>
						</div>
					</div>
					
					<%} %>

					<!-- Card -->
				</div>
				<!-- Card deck -->
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