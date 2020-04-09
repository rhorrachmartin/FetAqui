<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Poblacion"%>
<%@ page import="modelo.pojo.Vendedor"%>
<%@ page import="modelo.pojo.Categoria"%>
<%@ page import="modelo.pojo.Formato"%>
<%@ page import="modelo.pojo.Producto"%>
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
<script src="js/validarPasswordPerfilCliente.js"></script>
</head>
<body>
	<%
		Vendedor vendedor = null;
		ArrayList<Categoria> categorias = null;
		ArrayList<Formato> formatos = null;
		ArrayList<Producto> productos = null;
		int contadorCheckBox = 0;
		if (request.getAttribute("vendedor") != null) {
			vendedor = (Vendedor) request.getAttribute("vendedor");
			categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
			formatos = (ArrayList<Formato>) request.getAttribute("formatos");
			productos = (ArrayList<Producto>) request.getAttribute("productos");
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
					<li class="nav-item avatar"><a class="nav-link p-0" href="#">
							<img src="img/user.png" class="rounded-circle z-depth-0"
							alt="avatar image" height="35">
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


		<main class="container">

			<div class="row mt-5">

				<div class="col col-lg12">

					<div class="card">
						<div class="card-body  text-center">
							<h2>PRODUCTOS EN SU TIENDA</h2>
						</div>
					</div>

				</div>

			</div>
			<hr>
			<!-- ESPACIO EN BLANCO -->
			<div class="container m-5">

				<div class="row">

					<div class="col col-lg12" style="height: 50px"></div>
				</div>
			</div>
			<!-- FIN ESPACIO EN BLANCO -->
			<div class="container">
				<!-- Card deck -->
				<div class="row row-cols-1 row-cols-md-3">

					<%
						for (Producto pro : productos) {
							contadorCheckBox++;
					%>
					<%
						if (pro.getVenta_online() == 1) {
					%>


					<!-- Card -->
					<div class="col mb-4">
						<div class="card h-100">
							<!--Card image-->
							<div id="contenedorImagenProducto" class="view overlay zoom">
							<%if(pro.getFoto().equals("producto.png")) {%>
								<img id="imagenProducto" class="img-fluid z-depth-1"
									src="img/<%=pro.getFoto()%>" alt="Card image cap">
									
							<%}else{ %>
								<img id="imagenProducto" class="img-fluid z-depth-1"
									src="Imagenes/<%=pro.getFoto()%>" alt="Card image cap">
							<%} %>
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

										<div class="custom-control custom-switch">
											<form class="ventaOnlineProducto"
												id="ventaOnlineProducto<%=pro.getId()%>"
												action="VentaOnlineProducto" method="post">
												<input type="hidden" name="producto"
													value="<%=pro.getId()%>"> <input type="checkbox"
													class="custom-control-input"
													id="customSwitch<%=contadorCheckBox%>" name="ventaOnline"
													checked onchange="submit(<%=pro.getId()%>)"> <label
													class="custom-control-label labelCheckBox"
													for="customSwitch<%=contadorCheckBox%>">ON</label>
											</form>
										</div>
									</div>

									<div class="col col-lg-6">
										<form action="EliminarProducto" method="post">
											<input type="hidden" name="producto" value="<%=pro.getId()%>">
											<button type="submit" class="btn btn-light-blue btn-md">
												<a data-toggle="tooltip" title="ELIMINAR"><i class="far fa-trash-alt"></i></a>
											</button>
										</form>
									</div>

									<div class="col col-lg-6">
										<button type="button" class="btn btn-light-blue btn-md">
											<a data-toggle="tooltip" title="EDITAR INFORMACIÓN"><i class="far fa-edit"></i></a>
										</button>
									</div>
									
									<div class="col col-lg-6">
										<button type="button" class="btn btn-light-blue btn-md">
											<a data-toggle="tooltip" title="CAMBIAR IMAGEN"><i class="far fa-images"></i></a>
										</button>
									</div>

								</div>
							</div>
						</div>
					</div>

					<!-- Card -->
					<%
						} else {
					%>
					<!-- Card -->
					<div class="col mb-4">
						<div class="card h-100">
							<!--Card image-->
							<div id="contenedorImagenProducto" class="view overlay zoom">
								<%if(pro.getFoto().equals("producto.png")) {%>
								<img id="imagenProducto" class="img-fluid z-depth-1"
									src="img/<%=pro.getFoto()%>" alt="Card image cap">
									
							<%}else{ %>
								<img id="imagenProducto" class="img-fluid z-depth-1"
									src="Imagenes/<%=pro.getFoto()%>" alt="Card image cap">
							<%} %>
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
										<div class="custom-control custom-switch">
											<form class="ventaOnlineProducto"
												id="ventaOnlineProducto<%=pro.getId()%>"
												action="VentaOnlineProducto" method="post">
												<input type="hidden" name="producto"
													value="<%=pro.getId()%>"> <input type="checkbox"
													class="custom-control-input"
													id="customSwitch<%=contadorCheckBox%>" name="ventaOnline"
													onchange="submit(<%=pro.getId()%>)"> <label
													class="custom-control-label labelCheckBox"
													for="customSwitch<%=contadorCheckBox%>">OFF</label>
											</form>
										</div>
									</div>

									<div class="col col-lg-6">
										<form action="EliminarProducto" method="post">
											<input type="hidden" name="producto" value="<%=pro.getId()%>">
											<button type="submit" class="btn btn-light-blue btn-md">
												<a data-toggle="tooltip" title="ELIMINAR"><i class="far fa-trash-alt"></i></a>
											</button>
										</form>
									</div>

									<div class="col col-lg-6">
										<button type="button" class="btn btn-light-blue btn-md">
											<a data-toggle="tooltip" title="EDITAR INFORMACIÓN"><i class="far fa-edit"></i></a>
										</button>
									</div>
									
									<div class="col col-lg-6">
										<button type="button" class="btn btn-light-blue btn-md">
											<a data-toggle="tooltip" title="CAMBIAR IMAGEN"><i class="far fa-images"></i></a>
										</button>
									</div>

								</div>

							</div>
						</div>
					</div>
					<!-- Card -->
					<%
						}
					%>

					<%
						}
					%>

				</div>
				<!-- Card deck -->
			</div>
		</main>

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
		function submit(idProducto) {
			document.gelElementById('ventaOnlineProducto'+idProducto).submit();
		}
	</script>
</body>
</html>