<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Poblacion"%>
<%@ page import="modelo.pojo.Vendedor"%>
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
		Vendedor vendedor = null;
	if (request.getAttribute("vendedor") != null) {
		vendedor = (Vendedor) request.getAttribute("vendedor");
	}
	%>
	<div id="container">
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
					<li class="nav-item"><a class="nav-link"
						href="ObtenerTodosVendedores">Vendedores</a></li>
				</ul>

				<ul class="navbar-nav ml-auto nav-flex-icons">
					<li class="nav-item"><a class="btn btn-primary btn-sm botonesNavegador"
						href="PaginaPropioVendedor">MI PÁGINA</a></li>
					<li class="nav-item"><a class="btn btn-primary btn-sm botonesNavegador"
						href="OpcionesPerfilVendedor.jsp">ADMINISTRACIÓN</a></li>
					<li class="nav-item"><a class="btn btn-success btn-sm botonesNavegador"
						href="Logout">SALIR</a></li>
					<%
						if (vendedor.getFoto().equals("FotoPorDefecto")) {
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

		<div class="container" id="botonesPerfil">

			<div class="row">

				<div class="col lg lg-4"></div>

				<div class="col lg lg-4">

					<div class="text-center">
						<a href="" class="btn aqua-gradient" data-toggle="modal"
							data-target="#modalLoginAvatar">Cambiar foto de perfil</a>
					</div>

				</div>

				<div class="col lg lg-4">

					<div class="text-center">
						<a href="" class="btn peach-gradient" data-toggle="modal"
							data-target="#modalRegisterForm">CAMBIAR PASSWORD</a>
					</div>

				</div>

				<div class="col lg lg-4"></div>

			</div>

		</div>


		<div class="modal fade" id="modalLoginAvatar" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog cascading-modal modal-avatar modal-sm"
				role="document">
				<!--Content-->
				<div class="modal-content">

					<!--Header-->
					<div class="modal-header">
						<img src="Imagenes/<%=vendedor.getFoto()%>" alt="avatar"
							class="rounded-circle img-responsive">
					</div>
					<!--Body-->

					<div class="modal-body text-center mb-1">

						<h5 class="mt-1 mb-2"><%=vendedor.getNombre()%></h5>

						<div class="md-form ml-0 mr-0">
							<form action="ActualizarFotoPerfilVendedor" method="post"
								enctype='multipart/form-data'>
								<div class="input-group">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="foto"
											name="foto" aria-describedby="inputGroupFileAddon01" required>
										<label id="labelFoto" class="custom-file-label"
											for="inputGroupFile01">Elija su foto de perfil</label>
									</div>
								</div>
								<div class="row my-5">
									<div class="col-md-4">
										<div class="md-form">
											<i class="fas fa-lock prefix"></i> <input type="password"
												id="inputValidationEx3" class="form-control validate"
												name="password" required> <label
												for="inputValidationEx3"
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

		<!-- --------------------------------------MODAL DE CAMBIO DE PASSWORD------------------------------------------- -->

		<div class="modal fade" id="modalRegisterForm" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header text-center">
						<h4 class="modal-title w-100 font-weight-bold">CAMBIO DE
							PASSWORD</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body mx-3">
						<form action="ActualizarPasswordVendedor" method="post">
							<div class="form-group">
								<label for="passAntiguo">Password actual</label> <input
									type="password" id="passAntiguo" name="passAntiguo"
									class="form-control" aria-describedby="password" value=""
									required> <small id="ayudaPass"
									class="form-text text-muted">Contraseña actual</small>
							</div>

							<div class="row my-5">
								<div class="col-md-4">
									<div class="md-form">
										<i class="fas fa-lock prefix"></i> <input type="password"
											id="inputValidationEx3" class="form-control validate"
											name="passNuevo1"> <label for="inputValidationEx3"
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
							<div class="row my-5">
								<div class="col-md-4">
									<div class="md-form">
										<i class="fas fa-lock prefix"></i> <input type="password"
											id="inputValidationEx3" class="form-control validate"
											name="passNuevo2"> <label for="inputValidationEx3"
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
							<div class="modal-footer d-flex justify-content-center">
								<button type="submit" class="btn btn-deep-orange">Guardar</button>
							</div>
						</form>
					</div>



				</div>

			</div>

		</div>



		<!-- ------------------------------------------------------------------------------------------------------------------------------ -->

		<div class="container">

			<div class="row">

				<div class="col col-lg8">
					<div class="card m-5">

						<h5 class="card-header info-color white-text text-center py-4">
							<strong>Modificación de datos</strong>
						</h5>


						<div class="card-body px-lg-5 pt-0">

							<form action="ActualizarPerfilVendedor" method="post"
								style="color: #757575;">

								<div class="form-group">
									<label for="nif">NIF</label> <input type="text" id="nif"
										name="nif" class="form-control" aria-describedby="nombre"
										value=<%=vendedor.getNif()%> required>
								</div>

								<div class="form-group">
									<label for="nombre">Nombre</label> <input type="text"
										id="nombre" name="nombre" class="form-control"
										aria-describedby="nombre" value="<%=vendedor.getNombre()%>"
										required>
								</div>

								<div class="form-group">
									<label for="telefono">Telefono </label> <input type="text"
										id="telefono" name="telefono" class="form-control"
										aria-describedby="telefono"
										value="<%=vendedor.getTelefono()%>" required>
								</div>

								<div class="form-group">
									<label for="direccion">Direccion </label> <input type="text"
										id="direccion" name="direccion" class="form-control"
										aria-describedby="direccion"
										value="<%=vendedor.getDireccion()%>" required>
								</div>

								<div class="form-group">
									<label for="poblacion">Poblacion </label> <select
										id="poblacion" name="poblacion"
										class="browser-default custom-select">
										<option value=<%=vendedor.getIdPoblacion()%>
											selected="selected"><%=vendedor.getPoblacion()%></option>
										<%
											ArrayList<Poblacion> poblaciones = (ArrayList<Poblacion>) request.getAttribute("poblaciones");

										for (Poblacion p : poblaciones) {
										%>
										<option value=<%=p.getId()%>><%=p.getNombre()%></option>
										<%
											}
										%>
									</select>
								</div>

								<%
									String error = "";

								if (request.getAttribute("error") != null) {
									error = (String) request.getAttribute("error");
								%>
								<div class="form-group">
									<label for="passAntiguo">Password actual</label> <input
										type="password" id="passAntiguo" name="passAntiguo"
										class="form-control" aria-describedby="password" value=""
										required> <small id="ayudaPass"
										class="form-text text-muted"><strong
										style="color: red"><%=error%></strong></small>
								</div>
								<%
									} else {
								%>
								<div class="form-group">
									<label for="passAntiguo">Password actual</label> <input
										type="password" id="passAntiguo" name="passAntiguo"
										class="form-control" aria-describedby="password" value=""
										required> <small id="ayudaPass"
										class="form-text text-muted">Para guardar los cambios
										introduzca su contraseña</small>
								</div>
								<%
									}
								%>





								<!--Modal: Login with Avatar Form-->



								<button id="actualizarPerfil"
									class="btn btn-outline-info btn-rounded btn-block z-depth-0 my-4 waves-effect"
									type="submit">Guardar</button>


							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
		<hr>

		<div class="container m-5">

			<div class="row">

				<div class="col col-lg12" style="height: 100px"></div>
			</div>
		</div>
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

</body>
</html>