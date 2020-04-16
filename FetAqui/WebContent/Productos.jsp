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
			<a class="navbar-brand" href="#">FET AQUÍ</a>
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
												<a data-toggle="tooltip" title="ELIMINAR"><i
													class="far fa-trash-alt"></i></a>
											</button>
										</form>
									</div>

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

					<!-- Card -->

					<!-- MODAL DE EDICIÓN DE PRODUCTO -->

					<div class="modal fade modalEditarProducto"
						id="modalEditarProducto<%=pro.getId()%>" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header text-center">
									<h4 class="modal-title w-100 font-weight-bold">EDITAR
										PRODUCTO</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body mx-3">
									<form action="ActualizarProducto" method="post">
										<input type="hidden" name="idProducto"
											value="<%=pro.getId()%>">
										<div class="form-group">
											<label for="nombre">Nombre</label> <input type="text"
												id="nombre" name="nombre" class="form-control"
												aria-describedby="nombre" value="<%=pro.getNombre()%>"
												required> <small id="ayudaNombre"
												class="form-text text-muted">Nombre del producto</small>
										</div>

										<div class="form-group">
											<label for="categoria">Categoria</label> <select
												id="categoria" name="categoria" required>
												<option value="<%=pro.getId_categoria()%>" selected>
													<%=pro.getCategoria()%></option>
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

										<div class="form-group">
											<label for="descripcion">Descripcion del producto</label>
											<textarea rows="5" cols="25" id="descripcion"
												name="descripcion" class="form-control" maxlength="200"><%=pro.getDescripcion()%></textarea>
										</div>

										<div class="form-group">
											<label for="precio">Precio </label> <input type="number"
												id="precio" name="precio" class="form-control"
												aria-describedby="precio" step="0.01" min="0" max="10000"
												value="<%=pro.getPrecio()%>" required> <small
												id="ayudaVentaOnline" class="form-text text-muted">Precio
												por formato elegido. P.E= 2€/KG</small>
										</div>

										<div class="form-group">
											<label for="formato">Formato</label> <select id="formato"
												name="formato" required>
												<option value="<%=pro.getId_formato()%>" selected>
													<%=pro.getFormato()%></option>
												<%
													for (Formato fo : formatos) {
												%>
												<option value=<%=fo.getId()%>>
													<%=fo.getNombre()%></option>
												<%
													}
												%>
											</select>
										</div>

										<div class="form-group">
											<label for="stock">Stock </label> <input type="number"
												id="stock" name="stock" class="form-control"
												aria-describedby="stock" value="<%=pro.getStock()%>"
												required> <small id="ayudaStock"
												class="form-text text-muted">Cantidad disponible
												para la venta</small>
										</div>

										<div class="modal-footer d-flex justify-content-center">
											<button type="submit" class="btn btn-deep-orange">Guardar</button>
										</div>
									</form>
								</div>



							</div>

						</div>

					</div>

					<!-- FIN MODAL DE EDICIÓN DE PRODUCTO -->

					<!-- MODAL DE CAMBIO DE IMAGEN DE PRODUCTO -->

					<div class="modal fade modalLoginAvatar"
						id="modalLoginAvatar<%=pro.getId()%>" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog cascading-modal modal-avatar modal-sm"
							role="document">
							<!--Content-->
							<div class="modal-content">

								<!--Header-->
								<div class="modal-header">
									<img src="Imagenes/<%=pro.getFoto()%>" alt="avatar"
										class="rounded-circle img-responsive">
								</div>
								<!--Body-->

								<div class="modal-body text-center mb-1">

									<h5 class="mt-1 mb-2"><%=pro.getNombre()%></h5>

									<div class="md-form ml-0 mr-0">
										<form action="ActualizarFotoProducto" method="post"
											enctype='multipart/form-data'>
											<div class="input-group">
												<div class="custom-file">
													<input type="hidden" name="idProducto"
														value="<%=pro.getId()%>"> <input type="file"
														class="custom-file-input" id="foto" name="foto"
														aria-describedby="inputGroupFileAddon01" required>
													<label id="labelFoto" class="custom-file-label"
														for="inputGroupFile01">Elija la imagen del
														producto</label>
												</div>
											</div>

											<div class="text-center mt-4">
												<button type="submit" class="btn btn-cyan mt-1">
													Guardar <i class="fas fa-sign-in ml-1"></i>
												</button>
												<button type="button" class="btn btn-danger"
													data-dismiss="modal">Cerrar</button>
											</div>
										</form>
									</div>


								</div>

							</div>
							<!--/.Content-->
						</div>
					</div>

					<!-- FIN MODAL DE CAMBIO DE IMAGEN DE PRODUCTO -->
					<%
						} else {
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
												<a data-toggle="tooltip" title="ELIMINAR"><i
													class="far fa-trash-alt"></i></a>
											</button>
										</form>
									</div>

									<div class="col col-lg-6">
										<button type="button" class="btn btn-light-blue btn-md">
											<a data-toggle="modal"
												data-target="#modalEditarProducto<%=pro.getId()%>"><i
												class="far fa-edit"></i></a>
										</button>
									</div>

									<div class="col col-lg-6">
										<button type="button" class="btn btn-light-blue btn-md">
											<a data-toggle="tooltip" title="CAMBIAR IMAGEN"><i
												class="far fa-images"></i></a>
										</button>
									</div>

								</div>

							</div>
						</div>
					</div>
					<!-- Card -->

					<!-- MODAL DE EDICIÓN DE PRODUCTO -->

					<div class="modal fade modalEditarProducto"
						id="modalEditarProducto<%=pro.getId()%>" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header text-center">
									<h4 class="modal-title w-100 font-weight-bold">EDITAR
										PRODUCTO</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body mx-3">
									<form action="ActualizarProducto" method="post">
										<input type="hidden" name="idProducto"
											value="<%=pro.getId()%>">
										<div class="form-group">
											<label for="nombre">Nombre</label> <input type="text"
												id="nombre" name="nombre" class="form-control"
												aria-describedby="nombre" value="<%=pro.getNombre()%>"
												required> <small id="ayudaNombre"
												class="form-text text-muted">Nombre del producto</small>
										</div>

										<div class="form-group">
											<label for="categoria">Categoria</label> <select
												id="categoria" name="categoria" required>
												<option value="<%=pro.getId_categoria()%>" selected>
													<%=pro.getCategoria()%></option>
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

										<div class="form-group">
											<label for="descripcion">Descripcion del producto</label>
											<textarea rows="5" cols="25" id="descripcion"
												name="descripcion" class="form-control" maxlength="200"><%=pro.getDescripcion()%></textarea>
										</div>

										<div class="form-group">
											<label for="precio">Precio </label> <input type="number"
												id="precio" name="precio" class="form-control"
												aria-describedby="precio" step="0.01" min="0" max="10000"
												value="<%=pro.getPrecio()%>" required> <small
												id="ayudaVentaOnline" class="form-text text-muted">Precio
												por formato elegido. P.E= 2€/KG</small>
										</div>

										<div class="form-group">
											<label for="formato">Formato</label> <select id="formato"
												name="formato" required>
												<option value="<%=pro.getId_formato()%>" selected>
													<%=pro.getFormato()%></option>
												<%
													for (Formato fo : formatos) {
												%>
												<option value=<%=fo.getId()%>>
													<%=fo.getNombre()%></option>
												<%
													}
												%>
											</select>
										</div>

										<div class="form-group">
											<label for="stock">Stock </label> <input type="number"
												id="stock" name="stock" class="form-control"
												aria-describedby="stock" value="<%=pro.getStock()%>"
												required> <small id="ayudaStock"
												class="form-text text-muted">Cantidad disponible
												para la venta</small>
										</div>

										<div class="modal-footer d-flex justify-content-center">
											<button type="submit" class="btn btn-deep-orange">Guardar</button>
										</div>
									</form>
								</div>



							</div>

						</div>

					</div>

					<!-- FIN MODAL DE EDICIÓN DE PRODUCTO -->

					<!-- MODAL DE CAMBIO DE IMAGEN DE PRODUCTO -->

					<div class="modal fade modalLoginAvatar"
						id="modalLoginAvatar<%=pro.getId()%>" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog cascading-modal modal-avatar modal-sm"
							role="document">
							<!--Content-->
							<div class="modal-content">

								<!--Header-->
								<div class="modal-header">
									<img src="Imagenes/<%=pro.getFoto()%>" alt="avatar"
										class="rounded-circle img-responsive">
								</div>
								<!--Body-->

								<div class="modal-body text-center mb-1">

									<h5 class="mt-1 mb-2"><%=pro.getNombre()%></h5>

									<div class="md-form ml-0 mr-0">
										<form action="ActualizarFotoProducto" method="post"
											enctype='multipart/form-data'>
											<div class="input-group">
												<div class="custom-file">
													<input type="hidden" name="idProducto"
														value="<%=pro.getId()%>"> <input type="file"
														class="custom-file-input" id="foto" name="foto"
														aria-describedby="inputGroupFileAddon01" required>
													<label id="labelFoto" class="custom-file-label"
														for="inputGroupFile01">Elija la imagen del
														producto</label>
												</div>
											</div>

											<div class="text-center mt-4">
												<button type="submit" class="btn btn-cyan mt-1">
													Guardar <i class="fas fa-sign-in ml-1"></i>
												</button>
												<button type="button" class="btn btn-danger"
													data-dismiss="modal">Cerrar</button>
											</div>
										</form>
									</div>


								</div>

							</div>
							<!--/.Content-->
						</div>
					</div>

					<!-- FIN MODAL DE CAMBIO DE IMAGEN DE PRODUCTO -->
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