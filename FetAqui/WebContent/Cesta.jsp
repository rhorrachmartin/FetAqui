<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ page import="modelo.pojo.Cliente"%>
<%@ page import="modelo.pojo.PedidoDetallado"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="javax.servlet.http.HttpSession"%>
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
		HttpSession session = request.getSession(false);
	Cliente cliente = null;
	ArrayList<PedidoDetallado> pDetallado = null;
	String error = "";
	double totalPedido = 0;
	DecimalFormat df = new DecimalFormat("####0.00");

	if (session.getAttribute("cliente") != null) {
		cliente = (Cliente) session.getAttribute("cliente");

	}

	if (request.getAttribute("pedidoDetallado") != null) {
		pDetallado = (ArrayList<PedidoDetallado>) request.getAttribute("pedidoDetallado");
	}

	int numProductos = 0;

	if (session.getAttribute("numProductos") != null) {
		numProductos = (Integer) session.getAttribute("numProductos");
	}
	%>
	<div id="container">
		<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
			<a class="navbar-brand" href="Principal">FET AQUÍ</a>
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
						class="btn btn-primary btn-sm botonesNavegador" href="#"><i
							class="fas fa-shopping-basket"></i> CESTA (<%=numProductos%>)</a></li>
					<li class="nav-item"><a
						class="btn btn-primary btn-sm botonesNavegador"
						href="OpcionesPerfil.jsp">MI PÁGINA</a></li>
					<li class="nav-item"><a
						class="btn btn-success btn-sm botonesNavegador" href="Logout">SALIR</a></li>
					<%
						if (cliente.getFoto().equals("FotoPorDefecto")) {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0" href="OpcionesPerfil.jsp">
							<img src="img/user.png" class="rounded-circle z-depth-0"
							alt="avatar image" height="35">
					</a></li>
					<%
						} else {
					%>
					<li class="nav-item avatar"><a class="nav-link p-0" href="OpcionesPerfil.jsp">
							<img src="Imagenes/<%=cliente.getFoto()%>"
							class="rounded-circle z-depth-0" alt="avatar image" height="35">
					</a></li>
					<%
						}
					%>
				</ul>
			</div>
		</nav>

		<main class="container">

			<div class="row divPedido">

				<div class="col col-lg-12 text-center">
					<h3>SU PEDIDO</h3>
				</div>

				<hr>

				<%
					if (request.getAttribute("error") != null) {
					error = (String) request.getAttribute("error");
				%>

				<div class="col col-lg-12 text-center">
					<h2><%=error%></h2>
				</div>
				<%
					} else {
				%>

				<div class="col col-lg-12">

					<div class="row">
						<div class="col col-lg-12">
							<p>
								<strong>Dirección: </strong><%=pDetallado.get(0).getDireccion()%>
							</p>
						</div>
						<div class="col col-lg-12">
							<p>
								<strong>Población: </strong><%=pDetallado.get(0).getPoblacion()%>
							</p>
						</div>
						<div class="col col-lg-12">
							<p>
								<strong>Fecha de entrega: </strong><%=pDetallado.get(0).getFecha_entrega()%>
							</p>
						</div>
					</div>

				</div>
				<hr>
				<div class="col col-lg-12 text-center">
					<h4>PEDIDO DETALLADO</h4>
					<div class="row">



						<div class="col col-lg-12">
							<div class="table-responsive">
								<!--Table-->
								<table class="table">
									<!--Table head-->
									<thead>
										<tr>
											<th class="th-lg"></th>
											<th class="th-lg">Vendedor</th>
											<th class="th-lg">Producto</th>
											<th class="th-lg">Cantidad</th>
											<th class="th-lg">Precio Unitario</th>
											<th class="th-lg">Precio Total</th>
											<th class="th-lg"></th>
										</tr>
										<!--Table body-->
									<tbody>


										<!--Table head-->
										<%
											for (PedidoDetallado pd : pDetallado) {

											totalPedido = totalPedido + pd.getPrecio_final();
										%>
										<tr>
											<td>
												<%
													if (pd.getFoto().equals("producto.png")) {
												%>
												<div class="thumbnail">
													<img class="img-responsive imagenProductoTabla"
														src="img/<%=pd.getFoto()%>">
												</div> <%
 	} else {
 %>
												<div class="thumbnail">
													<img class="img-responsive imagenProductoTabla"
														src="Imagenes/<%=pd.getFoto()%>" alt="Card image cap">
												</div> <%
 	}
 %>
											</td>
											<td><%=pd.getVendedor()%></td>
											<td><%=pd.getProducto()%></td>
											<td><%=pd.getCantidad()%></td>
											<td><%=pd.getPrecio_unidad()%>€</td>
											<td><%=pd.getPrecio_final()%>€</td>
											<td>

												<form action="BorrarProductoCesta" method="post">
													<input type="hidden" name="id_detalle"
														value="<%=pd.getId_detalle()%>">
														<input type="hidden" name="id_pedido"
														value="<%=pd.getId_pedido()%>">
													<button type="submit" class="btn btn-light-blue btn-md">
														<a data-toggle="tooltip" title="ELIMINAR"><i
															class="far fa-trash-alt"></i></a>
													</button>

												</form>

											</td>
										</tr>
										<%
											}
										%>
									</tbody>
									<!--Table body-->
								</table>
								<!--Table-->

							</div>
						</div>

						<div class="col col-lg-12 justify-content-center text-center">
							<%
								if (totalPedido == 0) {
							%>
							<h1>TOTAL: 0/€</h1>
							<%
								} else {
							%>
							<h1>
								TOTAL:
								<%=df.format(totalPedido)%>/€
							</h1>
							<%
								}
							%>
						</div>

						<div class="col col-lg-12 text-center">
							<form action="ConfirmarPedido" method="get">
								<input type="hidden" name="totalPedido" value="<%=totalPedido%>">
								<input type="hidden" name="id_pedido"
									value="<%=pDetallado.get(0).getId_pedido()%>">
								<button type="submit" class="btn btn-light-blue btn-md">
									<a>CONFIRMAR PEDIDO Y PAGAR</a>
								</button>

							</form>
						</div>
						<%
							}
						%>
					</div>
				</div>



			</div>
		</main>
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

</body>
</html>