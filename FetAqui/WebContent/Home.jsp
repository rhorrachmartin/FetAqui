<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
								id="orangeForm-name" class="form-control validate" name="nombrep">
							<label data-error="wrong" data-success="right"
								for="orangeForm-name">Su nombre</label>
						</div>
						<div class="md-form mb-5">
							<i class="fas fa-envelope prefix grey-text"></i> <input
								type="email" id="orangeForm-email" class="form-control validate"
								name="emailp"> <label data-error="wrong"
								data-success="right" for="orangeForm-email">Su email</label>
						</div>

						<div class="row my-5">
							<div class="col-md-4">
								<div class="md-form">
									<i class="fas fa-lock prefix"></i> <input type="password"
										id="inputValidationEx2" class="form-control validate"
										name="passwordp"> <label for="inputValidationEx2"
										data-error="wrong" data-success="right" style="width: 200px;">Password</label>
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
						<button type="submit" class="btn btn-deep-orange">Registrarse</button>
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
								id="orangeForm-name" class="form-control validate" name="nombrec">
							<label data-error="wrong" data-success="right"
								for="orangeForm-name">Su nombre</label>
						</div>
						<div class="md-form mb-5">
							<i class="fas fa-envelope prefix grey-text"></i> <input
								type="email" id="orangeForm-email" class="form-control validate"
								name="emailc"> <label data-error="wrong"
								data-success="right" for="orangeForm-email">Su email</label>
						</div>

						<div class="row my-5">
							<div class="col-md-4">
								<div class="md-form">
									<i class="fas fa-lock prefix"></i> <input type="password"
										id="inputValidationEx2" class="form-control validate"
										name="passwordc"> <label for="inputValidationEx2"
										data-error="wrong" data-success="right" style="width: 200px;">Password</label>
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
						<button type="submit" class="btn btn-deep-orange">Registrarse</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/mdb.min.js"></script>
	<script src="js/validarPassword.js"></script>
</body>
</html>