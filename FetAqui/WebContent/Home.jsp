<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FET AQUI</title>
<!--  FONT AWESOME -->
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<!-- BOOTSRAP core CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Material design bootstrap -->
<link rel="stylesheet" href="css/mdb.min.css">
<!-- MI CSS -->
<link rel="stylesheet" href="css/estilos.css">
</head>
<body>


	<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			</ul>
		</div>
	</nav>


	<%
		if (request.getAttribute("error") == null) {
	%>
	<div class="container">
		<div class="row loginPanel">
			<div class="lg lg-12 ">
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#modalRegistro">Darse de alta</button>
				<button class="btn btn-success btn-lg">Entrar</button>
			</div>
		</div>
	</div>
	<%
		} else {
	%>
	<div class="container">

		<div class="row loginPanel">
			<div class="lg lg-12 ">
				<div class="row loginPanelAlerta">
					<div class="lg lg-12">
						<button class="btn btn-danger bnt-lg">CORREO YA EXISTENTE</button>
					</div>
				</div>
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#modalRegistro">Darse de alta</button>
				<button class="btn btn-success btn-lg">Entrar</button>
			</div>
		</div>
	</div>

	<%
		}
	%>

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

	<div class="modal fade" id="formularioProfesional">
		<div class="modal-dialog">
			<div class="modal-content">


				<div class="modal-header">
					<h4 class="modal-title">Rellene el formulario para registrarse</h4>
					<button type="button" class="close" data-dismiss="modal">×</button>
				</div>


				<div class="modal-body">
					<form class="was-validated" action="Principal" method="post">
						<div class="form-group">
							<label for="nombre">Nombre:</label> <input type="text"
								class="form-control" id="nombre"
								placeholder="Introduzca su nombre" name="nombre" required>
							<div class="valid-feedback">OK</div>
							<div class="invalid-feedback">Porfavor rellene este campo.
								Este nombre será el que veran los otros usuarios</div>
						</div>
						<div class="form-group">
							<label for="email1">Email:</label> <input type="email"
								class="form-control" id="email1"
								placeholder="Introduzca su email" name="email1" required>
							<input type="email" class="form-control" id="email2"
								placeholder="Introduzca su email otra vez" name="email2"
								required>
							<div class="valid-feedback">OK</div>
							<div class="invalid-feedback">Porfavor rellene este campo.</div>
						</div>

						<div class="mf-form">
							<i class="fas fa-lock prefix"></i> <input type="password"
								id="inputValidationEx2" class="form-control validate"> <label
								for="inputValidationEx2" data-error="wrong" data-success="right"
								style="width: 200px;">Type your password</label>
						</div>

						<div class="form-group form-check">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" name="remember"
								required> He leído bla bla..
								<div class="valid-feedback">Valid.</div>
								<div class="invalid-feedback">Acepte para confirmar
									registro.</div>
							</label>
						</div>
						<button type="submit" class="btn btn-primary">Aceptar</button>
					</form>
				</div>


				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
				</div>

			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/mdb.min.js"></script>
</body>
</html>