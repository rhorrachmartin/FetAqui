<%@page import="modelo.controlador.VentasOnline"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Vendedor"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>FET AQUI</title>
<!-- Font Awesome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
<!-- Google Fonts -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
<link
	href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap"
	rel="stylesheet">
<!-- Bootstrap core CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">
<!-- Material Design Bootstrap -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.16.0/css/mdb.min.css" rel="stylesheet">
<link href="css/estilos.css" type="text/css" rel="stylesheet">
<!-- -------------------SCRIPTS--------------- -->
<!-- JQuery -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.16.0/js/mdb.min.js"></script>
<script src="js/validarPassword.js"></script>
</head>
<body>


	<%
		HttpSession session = request.getSession(false);
		Vendedor vendedor = null;
		int ventaOnline = 0;

		if (session.getAttribute("vendedor") != null) {
			vendedor = (Vendedor) session.getAttribute("vendedor");
			ventaOnline = vendedor.getVenta_online();

		}
	%>
	<div id="container" >
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
                    <li class="nav-item"><a class="nav-link" href="ObtenerTodosVendedores">Vendedores</a></li>
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




		<%
			if (request.getAttribute("error") == null) {
		%>
		<%
			if (ventaOnline == 1) {
		%>
		<div class="container">
			<div class="row justify-content-center m-5">
				<div class="lg lg-12 ">
					<a class="btn aqua-gradient btn-lg" href="AñadirProducto">AÑADIR
						PRODUCTOS</a> <a class="btn aqua-gradient btn-lg" href="ObtenerProductosVendedor">MODIFICAR
						PRODUCTOS</a>
					<div class="custom-control custom-switch text-center">
						<form id="ventaOnline" action="VentasOnline" method="post">
							<input type="checkbox" class="custom-control-input "
								id="customSwitch1" name="ventaOnline" checked> <label
								id="labelCheckBox" class="custom-control-label labelCheckBox2"
								for="customSwitch1">Ventas online activadas</label>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%
			} else {
		%>
		<div class="container">
			<div class="row justify-content-center m-5">
				<div class="lg lg-12 ">
					<a class="btn btn-primary btn-lg" href="AñadirProducto">AÑADIR
						PRODUCTOS</a> <a class="btn btn-info btn-lg" href="ObtenerProductosVendedor">MODIFICAR
						PRODUCTOS</a>
					<div class="custom-control custom-switch text-center">
						<form id="ventaOnline" action="VentasOnline" method="post">
							<input type="checkbox" class="custom-control-input"
								id="customSwitch1" name="ventaOnline"> <label
								id="labelCheckBox" class="custom-control-label labelCheckBox2"
								for="customSwitch1">Ventas online desactivadas</label>
						</form>
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
		function submit() {

			$('#ventaOnline').submit();

		}

		$(function() {
			$('#customSwitch1').on('change', function() {
				submit();
			});
		});
	</script>


</body>
</html>