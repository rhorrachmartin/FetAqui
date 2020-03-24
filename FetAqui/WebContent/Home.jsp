<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FET AQUI</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
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

	<div class="container">
		<div class="row loginPanel">
			<div class="lg lg-12 ">
				<button class="btn btn-primary btn-lg" data-toggle="modal"
					data-target="#modalRegistro">Darse de alta</button>
				<button class="btn btn-success btn-lg">Entrar</button>
			</div>
		</div>
	</div>


	<div class="modal fade" id="modalRegistro">
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
					<h4 class="modal-title">Relle el formulario para
						registrarse</h4>
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

						<div class="form-group">
							<label for="pwd">Password:</label> <input type="password"
								class="form-control" id="password1" placeholder="Enter password"
								name="password1" required> 
								<input type="password"
								class="form-control" id="password2" placeholder="Enter password"
								name="password2" required>
							<div class="valid-feedback">OK</div>
							<div class="invalid-feedback">Porfavor rellene este campo. </div>
						</div>

						<div class="form-group form-check">
							<label class="form-check-label"> <input
								class="form-check-input" type="checkbox" name="remember"
								required> He leído bla bla..
								<div class="valid-feedback">Valid.</div>
								<div class="invalid-feedback">Acepte para confirmar registro.</div>
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

	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.js"></script>

</body>
</html>