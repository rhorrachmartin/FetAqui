<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Cliente"%>
<%@ page import="modelo.pojo.Vendedor"%>
<%@ page import="modelo.pojo.Post"%>
<%@ page import="modelo.pojo.Producto"%>
<%@ page import="java.util.ArrayList"%>
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
<script src="js/validarPassword.js"></script>
</head>
<body>


	<%
		HttpSession session = request.getSession(false);
	Cliente cliente = null;

	ArrayList<Vendedor> vendedores = null;
	ArrayList<Producto> productos = null;
	ArrayList<Post> posts = null;

	if (session.getAttribute("cliente") != null) {
		cliente = (Cliente) session.getAttribute("cliente");

	}

	if (request.getAttribute("vendedores") != null) {
		vendedores = (ArrayList<Vendedor>) request.getAttribute("vendedores");

	}

	if (request.getAttribute("productos") != null) {
		productos = (ArrayList<Producto>) request.getAttribute("productos");

	}

	if (request.getAttribute("posts") != null) {
		posts = (ArrayList<Post>) request.getAttribute("posts");

	}

	int numProductos = 0;

	if (session.getAttribute("numProductos") != null) {
		numProductos = (Integer) session.getAttribute("numProductos");
	}
	%>
	<div id="container">
		<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
			<a class="navbar-brand logoNavegador" href="#">FET AQUÍ</a>
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
					<li class="nav-item avatar"><a class="nav-link p-0" href="#">
							<img src="img/user.png" class="rounded-circle z-depth-0"
							alt="avatar image" height="35">
					</a></li>
					<%
						} else {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0" href="#">
							<img src="Imagenes/<%=cliente.getFoto()%>"
							class="rounded-circle z-depth-0" alt="avatar image" height="35">
					</a></li>
					<%
						}
					%>
				</ul>
			</div>
		</nav>

		<div class="container-fluid">

			<div class="row row-cols-1 row-cols-sm-2 row-cols-lg-3">

				<div id="lateralProductosPrincipal" class="col">

					<div class="text-center">
						<h4>Productos</h4>
						<hr>

						<!-- Card deck -->
						<div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 row-cols-lg-2 row-cols-xl-2">

							<%
								for (Producto pro : productos) {
							%>


							<!-- Card -->
							<div
								class="sombraProductoInicio mb-4">
								<div class="row ">
									<!--Card image-->
									<div class="col col-lg-12 view overlay zoom ">
										<%
											if (pro.getFoto().equals("producto.png")) {
										%>
										<img
											class="img-fluid z-depth-1 cropInicio rounded mx-auto d-block"
											src="img/<%=pro.getFoto()%>" alt="Card image cap">

										<%
											} else {
										%>
										<img
											class="img-fluid z-depth-1 cropInicio rounded mx-auto d-block"
											src="Imagenes/<%=pro.getFoto()%>" alt="Card image cap">
										<%
											}
										%>
										<div class="mask rgba-white-slight"></div>
									</div>
								</div>
								<!--Card content-->
								<div class="row">

									<!--Title-->
									<div class="col col-lg-12">
										<h4 class="card-title"><%=pro.getNombre()%></h4>
									</div>

									<div class="col col-lg-12">
										<form action="PaginaVendedor" method="get">
											<input type="hidden" name="id_vendedor"
												value="<%=pro.getId_vendedor()%>">
											<button type="submit" class="btn btn-light-blue btn-md">
												<i class="fas fa-user-tie"></i>
											</button>
										</form>
									</div>

									<div class="col col-lg-12">
										<form action="PaginaProducto" method="get">
											<input type="hidden" name="id_vendedor"
												value="<%=pro.getId_vendedor()%>"> <input
												type="hidden" name="id_producto" value="<%=pro.getId()%>">
											<button type="submit" class="btn btn-light-blue btn-md">
												<i class="fas fa-eye"></i>
											</button>
										</form>
									</div>
									<div class="col col-lg-12">
										<div class="cantidad">
											<form action="InsertarPedido" method="post">

												<button class="plus-boton" type="button" name="button">
													<img src="img/plus.svg" alt="" />
												</button>
												<input type="text" id="unidades<%=pro.getId()%>"
													class="unidades" name="cantidad" value="1">
												<button class="minus-boton" type="button" name="button">
													<img src="img/minus.svg" alt="" />
												</button>
												
												<input type="hidden" name="paginaPrincipal" value="1">

												<input type="hidden" name="id_producto"
													value="<%=pro.getId()%>"> <input type="hidden"
													name="precio" value="<%=pro.getPrecio()%>">
												<button type="submit" class="btn btn-light-blue btn-md">
													<i class="fas fa-cart-plus"></i>
												</button>
											</form>
										</div>
									</div>
								</div>

							</div>



							<%
								}
							%>
							<!-- Card -->
						</div>
					</div>
				</div>

				<div id="noticias" class="col">
					<div class="text-center">
						<h4>Noticias</h4>
						<hr>
						<div class="row justify-content-center">
							<%
								for (Post post : posts) {
							%>
							<div class="sombraProductoInicio col col-lg-12 mb-4">
								<strong><%=post.getNombre_autor()%></strong>
								<p><%=post.getTexto()%></p>
								<label>Valoración: <%=post.getValoracion()%></label>
							</div>


							<%
								}
							%>
						</div>
					</div>
				</div>

				<div id="lateral2" class="col">

					<div class="text-center">
						<h4>Vendedores</h4>
						<hr>

						<!-- Card deck -->
						<div class="row">

							<%
								for (Vendedor ven : vendedores) {
							%>


							<!-- Card -->
							<div
								class="sombraProductoInicio col col-xs-12 col-sm-12 col-md-6 col-lg-4 mb-4">
								<div class="row">
									<!--Card image-->
									<div class="col col-lg-12 view overlay zoom ">
										<%
											if (ven.getFoto().equals("desconocido.txt")) {
										%>
										<img
											class="img-fluid z-depth-1 cropInicio rounded mx-auto d-block"
											src="img/user.png" alt="Card image cap">

										<%
											} else {
										%>
										<img
											class="img-fluid z-depth-1 cropInicio rounded mx-auto d-block"
											src="Imagenes/<%=ven.getFoto()%>" alt="Card image cap">
										<%
											}
										%>
										<div class="mask rgba-white-slight"></div>
									</div>
								</div>
								<!--Card content-->
								<div class="row">

									<!--Title-->
									<div class="col col-lg-12">
										<h4 class="card-title"><%=ven.getNombre()%></h4>
									</div>

									<div class="col col-lg-12 colSinPading">
										<form action="PaginaVendedor" method="get">
											<input type="hidden" name="id_vendedor"
												value="<%=ven.getId_vendedor()%>">
											<button type="submit" class="btn btn-light-blue btn-md">
												VER</button>
										</form>
									</div>
								</div>

							</div>



							<%
								}
							%>
							<!-- Card -->
						</div>
					</div>
				</div>

			</div>

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
	<script src="js/cantidadesCarro.js"></script>
</body>
</html>