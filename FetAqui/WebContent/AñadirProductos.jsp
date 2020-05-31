<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Poblacion"%>
<%@ page import="modelo.pojo.Vendedor"%>
<%@ page import="modelo.pojo.Categoria"%>
<%@ page import="modelo.pojo.Formato"%>
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
		ArrayList<Categoria> categorias = null;
		ArrayList<Formato> formatos = null;
		if (request.getAttribute("vendedor") != null) {
			vendedor = (Vendedor) request.getAttribute("vendedor");
			categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
			formatos = (ArrayList<Formato>) request.getAttribute("formatos");
		}
	%>
	<div id="container" >
		<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
			<a class="navbar-brand" href="Principal">FET AQUÍ</a>
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


		<div class="container">

			<div class="row">

				<div class="col col-lg8">
					<div class="card m-5">

						<h5 class="card-header info-color white-text text-center py-4">
							<strong>AÑADIR PRODUCTO</strong>
						</h5>


						<div class="card-body px-lg-5 pt-0">

							<form action="AñadirProducto" method="post"
								style="color: #757575;" enctype='multipart/form-data'>

								<div class="form-group">
									<label for="nombre">Nombre</label> <input type="text"
										id="nombre" name="nombre" class="form-control"
										aria-describedby="nombre" maxlength="64" required>
								</div>

								<div class="form-group">
									<label for="categoria">Categoria</label> <select id="categoria"
										name="categoria" required>
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
										name="descripcion" class="form-control" maxlength="200"></textarea>
								</div>

								<div class="form-group">
									<label for="precio">Precio </label> <input type="number"
										id="precio" name="precio" class="form-control"
										aria-describedby="precio" step="0.01" min="0" max="10000"
										required> <small id="ayudaVentaOnline"
										class="form-text text-muted">Precio por formato
										elegido. P.E= 2€/KG</small>
								</div>

								<div class="form-group">
									<label for="formato">Formato</label> <select id="formato"
										name="formato" required>
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
										aria-describedby="stock" min="1" max="2147483647" required> <small
										id="ayudaStock" class="form-text text-muted">Cantidad
										disponible para la venta</small>
								</div>

								<div class="custom-file">
									<input type="file" class="custom-file-input" id="foto"
										name="foto" aria-describedby="inputGroupFileAddon01">
									<label id="labelFoto" class="custom-file-label"
										for="inputGroupFile01">Foto del producto</label> <small
										id="ayudaFoto" class="form-text text-muted">Si no
										elige una, se establecerá una por defecto</small>
								</div>

								<div class="custom-control custom-switch">
									<input type="checkbox" class="custom-control-input"
										id="customSwitch1" name="ventaOnline" checked> <label
										id="labelCheckBox" class="custom-control-label"
										for="customSwitch1">Activar venta Online</label> <small
										id="ayudaVentaOnline" class="form-text text-muted">Activado
										por defecto</small>
								</div>

								<button id="añadirProducto"
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