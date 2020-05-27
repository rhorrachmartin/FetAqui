package modelo.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class AñadirProducto
 */
@WebServlet("/ObtenerTodosVendedores")
public class ObtenerTodosVendedores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Usuarios
	 */
	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	/**
	 * EJB para trabajar con sesiones de cliente
	 */
	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	LoggersEJB logger;

	static final String VENDEDORES_NO_LOGEADO_JSP = "/VendedoresNoLogeado.jsp";
	static final String VENDEDORES_LOGEADO_VENDEDOR_JSP = "/VendedoresLogeadoVendedor.jsp";
	static final String VENDEDORES_LOGEADO_CLIENTE_JSP = "/VendedoresLogeadoCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(VENDEDORES_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		// Intentamos obtener el usuario de la sesión
		Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
		Cliente c = sesionClienteEJB.clienteLogeado(session);

		request.setAttribute("vendedor", v);
		request.setAttribute("cliente", c);

		if (v != null || c != null) {

			if (c != null) {
				rs = getServletContext().getRequestDispatcher(VENDEDORES_LOGEADO_CLIENTE_JSP);
				try {
					if (request.getParameter("selectPoblacion") == null) {

						ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

						request.setAttribute("poblaciones", poblaciones);
						request.setAttribute("vendedores", vendedores);

						rs.forward(request, response);

					} else {
						if (!request.getParameter("selectPoblacion").equals("todos")) {
							Integer id_poblacion = Integer.valueOf(request.getParameter("selectPoblacion"));

							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedoresPoblacion(id_poblacion);
							Poblacion poblacion = poblacionEJB.getPoblacionPorId(id_poblacion);

							if (vendedores.isEmpty()) {
								String error = "No existen vendedores en esta población.";
								request.setAttribute("poblacion", poblacion);
								request.setAttribute("error", error);
							}

							request.setAttribute("poblacion", poblacion);
							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);

							rs.forward(request, response);
						} else {
							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);

							rs.forward(request, response);
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				rs = getServletContext().getRequestDispatcher(VENDEDORES_LOGEADO_VENDEDOR_JSP);
				try {
					if (request.getParameter("selectPoblacion") == null) {

						ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

						request.setAttribute("poblaciones", poblaciones);
						request.setAttribute("vendedores", vendedores);

						rs.forward(request, response);

					} else {
						if (!request.getParameter("selectPoblacion").equals("todos")) {
							Integer id_poblacion = Integer.valueOf(request.getParameter("selectPoblacion"));

							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedoresPoblacion(id_poblacion);
							Poblacion poblacion = poblacionEJB.getPoblacionPorId(id_poblacion);

							if (vendedores.isEmpty()) {
								String error = "No existen vendedores en esta población.";
								request.setAttribute("poblacion", poblacion);
								request.setAttribute("error", error);
							}

							request.setAttribute("poblacion", poblacion);
							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);

							rs.forward(request, response);
						} else {
							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);

							rs.forward(request, response);
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		} else {

			try {
				if (request.getParameter("selectPoblacion") == null) {

					ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
					ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedores", vendedores);

					rs.forward(request, response);

				} else {
					if (!request.getParameter("selectPoblacion").equals("todos")) {
						Integer id_poblacion = Integer.valueOf(request.getParameter("selectPoblacion"));

						ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedoresPoblacion(id_poblacion);
						Poblacion poblacion = poblacionEJB.getPoblacionPorId(id_poblacion);

						if (vendedores.isEmpty()) {
							String error = "No existen vendedores en esta población.";
							request.setAttribute("poblacion", poblacion);
							request.setAttribute("error", error);
						}

						request.setAttribute("poblacion", poblacion);
						request.setAttribute("poblaciones", poblaciones);
						request.setAttribute("vendedores", vendedores);

						rs.forward(request, response);
					} else {
						ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

						request.setAttribute("poblaciones", poblaciones);
						request.setAttribute("vendedores", vendedores);

						rs.forward(request, response);
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(VENDEDORES_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		// Intentamos obtener el usuario de la sesión
		Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
		Cliente c = sesionClienteEJB.clienteLogeado(session);

		request.setAttribute("vendedor", v);
		request.setAttribute("cliente", c);

		if (v != null || c != null) {

			if (c != null) {
				rs = getServletContext().getRequestDispatcher(VENDEDORES_LOGEADO_CLIENTE_JSP);
				try {
					if (request.getParameter("selectPoblacion") == null) {

						ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

						request.setAttribute("poblaciones", poblaciones);
						request.setAttribute("vendedores", vendedores);

						rs.forward(request, response);

					} else {
						if (!request.getParameter("selectPoblacion").equals("todos")) {
							Integer id_poblacion = Integer.valueOf(request.getParameter("selectPoblacion"));

							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedoresPoblacion(id_poblacion);
							Poblacion poblacion = poblacionEJB.getPoblacionPorId(id_poblacion);

							if (vendedores.isEmpty()) {
								String error = "No existen vendedores en esta población.";
								request.setAttribute("poblacion", poblacion);
								request.setAttribute("error", error);
							}

							request.setAttribute("poblacion", poblacion);
							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);

							rs.forward(request, response);
						} else {
							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);

							rs.forward(request, response);
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				rs = getServletContext().getRequestDispatcher(VENDEDORES_LOGEADO_VENDEDOR_JSP);
				try {
					if (request.getParameter("selectPoblacion") == null) {

						ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

						request.setAttribute("poblaciones", poblaciones);
						request.setAttribute("vendedores", vendedores);

						rs.forward(request, response);

					} else {
						if (!request.getParameter("selectPoblacion").equals("todos")) {
							Integer id_poblacion = Integer.valueOf(request.getParameter("selectPoblacion"));

							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedoresPoblacion(id_poblacion);
							Poblacion poblacion = poblacionEJB.getPoblacionPorId(id_poblacion);

							if (vendedores.isEmpty()) {
								String error = "No existen vendedores en esta población.";
								request.setAttribute("poblacion", poblacion);
								request.setAttribute("error", error);
							}

							request.setAttribute("poblacion", poblacion);
							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);

							rs.forward(request, response);
						} else {
							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);

							rs.forward(request, response);
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		} else {

			try {
				if (request.getParameter("selectPoblacion") == null) {

					ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
					ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedores", vendedores);

					rs.forward(request, response);

				} else {
					if (!request.getParameter("selectPoblacion").equals("todos")) {
						Integer id_poblacion = Integer.valueOf(request.getParameter("selectPoblacion"));

						ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedoresPoblacion(id_poblacion);
						Poblacion poblacion = poblacionEJB.getPoblacionPorId(id_poblacion);

						if (vendedores.isEmpty()) {
							String error = "No existen vendedores en esta población.";
							request.setAttribute("poblacion", poblacion);
							request.setAttribute("error", error);
						}

						request.setAttribute("poblacion", poblacion);
						request.setAttribute("poblaciones", poblaciones);
						request.setAttribute("vendedores", vendedores);

						rs.forward(request, response);
					} else {
						ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

						request.setAttribute("poblaciones", poblaciones);
						request.setAttribute("vendedores", vendedores);

						rs.forward(request, response);
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

}
