<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Producto"%>
<%@ page import="modelo.pojo.Vendedor"%>
<%@ page import="modelo.pojo.Cliente"%>
<%@ page import="modelo.pojo.Categoria"%>
<%@ page import="java.util.ArrayList"%>
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
		ArrayList<Categoria> categorias = null;
	ArrayList<Producto> productos = null;
	Categoria categoria = null;
	Vendedor vendedor = null;
	Cliente c = null;
	HttpSession session = request.getSession(false);
	if (request.getAttribute("categorias") != null) {
		categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
	}

	if (request.getAttribute("productos") != null) {
		productos = (ArrayList<Producto>) request.getAttribute("productos");
	}

	if (request.getAttribute("categoria") != null) {

		categoria = (Categoria) request.getAttribute("categoria");
	}

	if (request.getAttribute("vendedor") != null) {

		vendedor = (Vendedor) request.getAttribute("vendedor");
	}

	if (request.getAttribute("c") != null) {

		c = (Cliente) request.getAttribute("c");
	}

	int numProductos = 0;

	if (session.getAttribute("numProductos") != null) {
		numProductos = (Integer) session.getAttribute("numProductos");
	}
	%>

	<div id="container">

		<!-- NAVEGADOR -->
		<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
			<a class="navbar-brand" href="#">FET AQUÍ</a>
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
					<li class="nav-item"><a class="btn btn-primary btn-sm botonesNavegador"
						href="Cesta"><i class="fas fa-shopping-basket"></i> CESTA (<%=numProductos%>)</a></li>
					<li class="nav-item"><a class="btn btn-primary btn-sm botonesNavegador"
						href="OpcionesPerfil.jsp">MI PÁGINA</a></li>
					<li class="nav-item"><a class="btn btn-success btn-sm botonesNavegador"
						href="Logout">SALIR</a></li>
					<%
						if (c.getFoto().equals("FotoPorDefecto")) {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0" href="#">
							<img src="img/user.png" class="rounded-circle z-depth-0"
							alt="avatar image" height="35">
					</a></li>
					<%
						} else {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0" href="#">
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

		<!-- CONTENEDOR PRINCIPAL -->
		<div class="container-fluid">

			<div class="row">

				<div id="lateral" class="col-12 col-sm-12 col-md-6 col-lg-3 col-xl-2">

					<%
						if (vendedor.getFoto().equals("FotoPorDefecto")) {
					%>
					<div>
						<img id="imagenVendedor" src="img/user.png">
					</div>
					<%
						} else {
					%>

					<div>
						<img id="imagenVendedor" src="Imagenes/<%=vendedor.getFoto()%>">
					</div>
					<%
						}
					%>
					<hr>
					<div>
						<h2><%=vendedor.getNombre()%></h2>
					</div>
					<hr>
					<div>
						<h4>LOCALIZACIÓN</h4>
					</div>
					<hr>
					<div>
						<p><%=vendedor.getPoblacion()%></p>
						<hr>
						<p><%=vendedor.getDireccion()%></p>
					</div>
					<hr>
					<div>
						<h4>CONTACTO</h4>
					</div>
					<hr>
					<p><%=vendedor.getTelefono()%></p>
					<hr>
					<p><%=vendedor.getEmail()%></p>
					<hr>
					<div>
						<h4>¿VENDE ONLINE?</h4>
						<%
							if (vendedor.getVenta_online() == 1) {
						%>
						<h5>SI</h5>
						<%
							} else {
						%>
						<h5>NO</h5>
						<%
							}
						%>
					</div>



				</div>

				<div id="main" class="col-12 col-sm-12 col-md-6 col-lg-9 col-xl-10 text-center">

					<!-- DIV ELECCION CATEGORÍA -->
					<%
						if (categoria != null) {
					%>

					<div class="container">
						<!-- DIV BUSQUEDA POR CATEGORÍA -->
						<div class="row">
							<div class="col col-lg-1 text-center">
								<a type="button" class="btn btn-light-blue btn-md"
									href="ObtenerTodosProductos">Volver</a>
							</div>
							<div class="col col-lg-11 m-5 text-center">

								<h3>BUSCAR POR CATEGORÍAS</h3>
								<form id="categorias" action="PaginaVendedor" method="get">
									<input type="hidden" name="id_vendedor"
										value="<%=vendedor.getId_vendedor()%>"> <select
										name="selectCategorias" id="selectCategorias"
										class="browser-default custom-select">

										<option value="<%=categoria.getId()%>" selected><%=categoria.getNombre()%></option>
										<option value="todos">TODOS LOS PRODUCTOS</option>
										<%
											for (Categoria ca : categorias) {
										%>
										<option value=<%=ca.getId()%>>
											<%=ca.getNombre()%></option>
										<%
											}
										%>
									</select>
								</form>
							</div>


						</div>
						<!-- FIN DIV BUSQUEDA POR CATEGORÍA -->
					</div>
					<!-- FIN DIV ELECCION CATEGORÍA -->

					<%
						} else {
					%>

					<div class="container">
						<!-- DIV BUSQUEDA POR CATEGORÍA -->
						<div class="row">
							<div class="col col-lg-1 text-center">
								<a type="button" class="btn btn-light-blue btn-md"
									href="ObtenerTodosProductos">Volver</a>
							</div>
							<div class="col col-lg-11 m-5 text-center">
								<h3>BUSCAR POR CATEGORÍAS</h3>
								<form id="categorias" action="PaginaVendedor" method="get">
									<input type="hidden" name="id_vendedor"
										value="<%=vendedor.getId_vendedor()%>"> <select
										name="selectCategorias" id="selectCategorias"
										class="browser-default custom-select">
										<option value="todos" selected>TODOS LOS PRODUCTOS</option>
										<%
											for (Categoria ca : categorias) {
										%>
										<option value=<%=ca.getId()%>>
											<%=ca.getNombre()%></option>
										<%
											}
										%>
									</select>
								</form>
							</div>


						</div>
						<!-- FIN DIV BUSQUEDA POR CATEGORÍA -->
					</div>
					<!-- FIN DIV ELECCION CATEGORÍA -->

					<%
						}
					%>


					<%
						if (request.getAttribute("error") != null) {
						String error = (String) request.getAttribute("error");
					%>

					<div class="container">
						<!-- DIV ERROR-->
						<div class="row">

							<div class="col col-lg-12 m-5 text-center">
								<h3><%=error%></h3>
							</div>


						</div>
						<!-- FIN DIV BUSQUEDA POR CATEGORÍA -->
					</div>
					<!-- FIN DIV ELECCION CATEGORÍA -->

					<%
						} else {
					%>

					<!-- Card deck -->
					<div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 row-cols-lg-3 row-cols-xl-4">

						<%
							for (Producto pro : productos) {
						%>


						<!-- Card -->
						<div class="col mb-4">
							<div class="card h-100">
								<!--Card image-->
								<div class="view overlay zoom contenedorImagenProducto">
									<%
										if (pro.getFoto().equals("producto.png")) {
									%>
									<img class="img-fluid z-depth-1 imagenProducto"
										src="img/<%=pro.getFoto()%>" alt="Card image cap">

									<%
										} else {
									%>
									<img class="img-fluid z-depth-1 imagenProducto"
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
									<div class="descripcionProducto">
										<p class="card-text"><%=pro.getDescripcion()%></p>
									</div>
									<!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
									<div class="container">
										<div class="row">

											<div class="col col-lg-12 text-center">
												<h5>
													Precio:
													<%=pro.getPrecio()%>€ /
													<%=pro.getFormato()%></h5>
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
														<input type="hidden" name="id_vendedor"
															value="<%=vendedor.getId_vendedor()%>"> <input
															type="hidden" name="id_producto" value="<%=pro.getId()%>">
														<input type="hidden" name="precio"
															value="<%=pro.getPrecio()%>">
														<button type="submit" class="btn btn-light-blue btn-md">
															<i class="fas fa-cart-plus"></i>
														</button>
													</form>
												</div>
											</div>

										</div>
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
						<!-- Card -->
					</div>
					<!-- Card deck -->

				</div>
			</div>
		</div>

		<!------------------------------------------------------MODAL DE ELECCION DE TIPO DE REGISTRO------------------------------------------------------------------------>
		<div class="modal fade" id="modalRegistro" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">¿Eres un profesional o un consumidor?</h4>
						<button type="button" class="close" data-dismiss="modal">×</button>
					</div>


					<div class="modal-body panelEleccionRegistro">
						<button class="btn btn-primary btn-lg" data-toggle="modal"
							data-target="#formularioProfesional">Profesional</button>
						<button class="btn btn-success btn-lg" data-toggle="modal"
							data-target="#formularioConsumidor">Consumidor</button>
					</div>


					<div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
					</div>

				</div>
			</div>
		</div>
		<!------------------------------------------------------MODAL DE REGISTRO DE PROFESIONAL------------------------------------------------------------------------>
		<div class="modal fade" id="formularioProfesional" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h4 class="modal-title w-100 font-weight-bold">Registrarse
							como vendedor</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form action="Principal" method="post">
						<div class="modal-body mx-3">
							<div class="md-form mb-5">
								<i class="fas fa-user prefix grey-text"></i> <input type="text"
									id="orangeForm-name" class="form-control validate"
									name="nombrep"> <label data-error="wrong"
									data-success="right" for="orangeForm-name">Su nombre</label>
							</div>
							<div class="md-form mb-5">
								<i class="fas fa-envelope prefix grey-text"></i> <input
									type="email" id="orangeForm-email"
									class="form-control validate" name="emailp"> <label
									data-error="wrong" data-success="right" for="orangeForm-email">Su
									email</label>
							</div>

							<div class="row my-5">
								<div class="col-md-4">
									<div class="md-form">
										<i class="fas fa-lock prefix"></i> <input type="password"
											id="inputValidationEx2" class="form-control validate"
											name="passwordp"> <label for="inputValidationEx2"
											data-error="Escriba su contraseña correctamente"
											data-success="OK" style="width: 200px;">Password</label>
									</div>
								</div>
								<div class="col-md-5">

									<div class="alert alert-warning password-alert" role="alert">
										<ul>
											<li class="requirements leng"><i
												class="fas fa-check green-text"></i><i
												class="fas fa-times red-text"></i> 8 caracteres</li>
											<li class="requirements big-letter"><i
												class="fas fa-check green-text"></i><i
												class="fas fa-times red-text"></i> 1 letra mayúscula</li>
											<li class="requirements num"><i
												class="fas fa-check green-text"></i><i
												class="fas fa-times red-text"></i> 1 número</li>
											<li class="requirements special-char"><i
												class="fas fa-check green-text"></i><i
												class="fas fa-times red-text"></i> 1 carácter especial</li>
										</ul>
									</div>

								</div>
							</div>

						</div>
						<div class="modal-footer d-flex justify-content-center">
							<button id="registrarse" type="submit"
								class="btn btn-deep-orange" disabled>Registrarse</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!------------------------------------------------------MODAL DE REGISTRO DE CLIENTE------------------------------------------------------------------------>
		<div class="modal fade" id="formularioConsumidor" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h4 class="modal-title w-100 font-weight-bold">Registrarse
							como cliente</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form action="Principal" method="post">
						<div class="modal-body mx-3">
							<div class="md-form mb-5">
								<i class="fas fa-user prefix grey-text"></i> <input type="text"
									id="orangeForm-name2" class="form-control validate"
									name="nombrec"> <label data-error="wrong"
									data-success="right" for="orangeForm-name2">Su nombre</label>
							</div>
							<div class="md-form mb-5">
								<i class="fas fa-envelope prefix grey-text"></i> <input
									type="email" id="orangeForm-email2"
									class="form-control validate" name="emailc"> <label
									data-error="wrong" data-success="right" for="orangeForm-email2">Su
									email</label>
							</div>

							<div class="row my-5">
								<div class="col-md-4">
									<div class="md-form">
										<i class="fas fa-lock prefix"></i> <input type="password"
											id="inputValidationEx3" class="form-control validate"
											name="passwordc"> <label for="inputValidationEx3"
											data-error="Escriba su contraseña correctamente"
											data-success="OK" style="width: 200px;">Password</label>
									</div>
								</div>
								<div class="col-md-5">

									<div class="alert alert-warning password-alert" role="alert">
										<ul>
											<li class="requirements leng"><i
												class="fas fa-check green-text"></i><i
												class="fas fa-times red-text"></i> 8 caracteres</li>
											<li class="requirements big-letter"><i
												class="fas fa-check green-text"></i><i
												class="fas fa-times red-text"></i> 1 letra mayúscula</li>
											<li class="requirements num"><i
												class="fas fa-check green-text"></i><i
												class="fas fa-times red-text"></i> 1 número</li>
											<li class="requirements special-char"><i
												class="fas fa-check green-text"></i><i
												class="fas fa-times red-text"></i> 1 carácter especial</li>
										</ul>
									</div>

								</div>
							</div>

						</div>
						<div class="modal-footer d-flex justify-content-center">
							<button id="registrarse2" type="submit"
								class="btn btn-deep-orange" disabled>Registrarse</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!------------------------------------------------------ MODAL DE LOGIN ------------------------------------------------------------------------------------------->

		<div class="modal fade" id="modalLogin" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h4 class="modal-title w-100 font-weight-bold">ENTRAR</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form action="Login" method="post">
						<div class="modal-body mx-3">

							<div class="md-form mb-5">
								<i class="fas fa-envelope prefix grey-text"></i> <input
									type="email" id="defaultForm-email"
									class="form-control validate" name="email"> <label
									data-error="wrong" data-success="right" for="defaultForm-email">Email</label>
							</div>

							<div class="md-form mb-4">
								<i class="fas fa-lock prefix grey-text"></i> <input
									type="password" id="defaultForm-pass"
									class="form-control validate" name="password"> <label
									data-error="wrong" data-success="right" for="defaultForm-pass">Password</label>
							</div>
						</div>
						<div class="modal-footer d-flex justify-content-center">
							<button type="submit" class="btn btn-default">Login</button>
						</div>
					</form>
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

	<script type="text/javascript">
		function submit() {

			$('#categorias').submit();

		}

		$(function() {
			$('#selectCategorias').on('change', function() {
				submit();
			});
		});
	</script>
	<script src="js/cantidadesCarro.js"></script>

</body>
</html>