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
 * Clase controlador encargado de obtener todos los vendedores
 * 
 * @author ramon
 *
 */
@WebServlet("/ObtenerTodosVendedores")
public class ObtenerTodosVendedores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	VendedorEJB vendedorEJB;

	@EJB
	PoblacionEJB poblacionEJB;

	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	@EJB
	SesionClienteEJB sesionClienteEJB;

	@EJB
	LoggersEJB logger;

	static final String VENDEDORES_NO_LOGEADO_JSP = "/VendedoresNoLogeado.jsp";
	static final String VENDEDORES_LOGEADO_VENDEDOR_JSP = "/VendedoresLogeadoVendedor.jsp";
	static final String VENDEDORES_LOGEADO_CLIENTE_JSP = "/VendedoresLogeadoCliente.jsp";
	static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	// Método doGet encargado de mostrar la página de Vendedores tanto con usuarios
	// logeaos o no logeados
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(VENDEDORES_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos obtener el usuario de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
			Cliente c = sesionClienteEJB.clienteLogeado(session);

			// COmprobamos que exista un usuario en sesión
			if (v != null || c != null) {

				// Si es un usuario cliente
				if (c != null) {

					// Lo metemos en la request
					request.setAttribute("cliente", c);

					// RS hacia VENDEDORES_LOGEADO_CLIENTE_JSP
					rs = getServletContext().getRequestDispatcher(VENDEDORES_LOGEADO_CLIENTE_JSP);

					// Cargamos todas las poblaciones y todos los vendedores
					ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
					ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

					// Lo metemos en la request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedores", vendedores);

					// Redirigimos hacia VENDEDORES_LOGEADO_CLIENTE_JSP
					rs.forward(request, response);

				} else {

					// Si no es cliente es vendedor, así que lo metemos en la request
					request.setAttribute("vendedor", v);

					// RS hacia VENDEDORES_LOGEADO_VENDEDOR_JSP
					rs = getServletContext().getRequestDispatcher(VENDEDORES_LOGEADO_VENDEDOR_JSP);

					// Recogemos todas poblaciones y todos los vendedores
					ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
					ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

					// Lo introducimos en la request
					request.setAttribute("poblaciones", poblaciones);
					request.setAttribute("vendedores", vendedores);

					// Redirigimos hacia VENDEDORES_LOGEADO_VENDEDOR_JSP
					rs.forward(request, response);

				}

			} else {

				// Si no hay sesión solo cargamos poblaciones y vendedores
				ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
				ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

				// Lo introducimos en la request
				request.setAttribute("poblaciones", poblaciones);
				request.setAttribute("vendedores", vendedores);

				// REdirigimos hacia VENDEDORES_LOGEADO_VENDEDOR_JSP
				rs.forward(request, response);

			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

	/**
	 * Método doPost encargado de mostrar los vendedores filtrados por población
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		// Creamos el RequestDispatcher
		RequestDispatcher rs = getServletContext().getRequestDispatcher(VENDEDORES_NO_LOGEADO_JSP);

		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		try {
			// Intentamos obtener el usuario de la sesión
			Vendedor v = sesionVendedorEJB.vendedorLogeado(session);
			Cliente c = sesionClienteEJB.clienteLogeado(session);
			
			//Si existe algún usuario en la sesión
			if (v != null || c != null) {
				
				//Si el usuario es cliente
				if (c != null) {
					
					//Lo metemos en la request
					request.setAttribute("cliente", c);
					
					//RS hacia VENDEDORES_LOGEADO_CLIENTE_JSP
					rs = getServletContext().getRequestDispatcher(VENDEDORES_LOGEADO_CLIENTE_JSP);
					
					//Si el parámetro selectPoblacion es nulo
					if (request.getParameter("selectPoblacion") == null) {
						
						//Mostramos todos los vendedores
						ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
						ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();
						
						//Lo introducimos en la request
						request.setAttribute("poblaciones", poblaciones);
						request.setAttribute("vendedores", vendedores);
						
						//REdirigimos hacia VENDEDORES_LOGEADO_CLIENTE_JSP
						rs.forward(request, response);

					} else {
						//Si el parámetro selectPoblacion no está vacío comprobamos
						//que sea diferente a "todos"
						if (!request.getParameter("selectPoblacion").equals("todos")) {
							
							//Recogemos el id de la poblacion
							Integer id_poblacion = Integer.valueOf(request.getParameter("selectPoblacion"));
							
							//Recogemos los vendedores de esa poblacion y todas las poblaCiones
							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedoresPoblacion(id_poblacion);
							Poblacion poblacion = poblacionEJB.getPoblacionPorId(id_poblacion);
							
							//Si no existen vendedores en sa Poblacion mostramos un mensaje de error
							if (vendedores.isEmpty()) {
								String error = "No existen vendedores en esta población.";
								request.setAttribute("poblacion", poblacion);
								request.setAttribute("error", error);
							}
							
							//Lo introducimos todo en la request.
							request.setAttribute("poblacion", poblacion);
							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);
							
							//Redirigimos a VENDEDORES_LOGEADO_CLIENTE_JSP
							rs.forward(request, response);
						} else {
							
							//Si el parametro selectPoblaciones es igual a "todos"
							//CArgamos todos los vendedores y todas las poblaciones
							ArrayList<Poblacion> poblaciones = poblacionEJB.getPoblaciones();
							ArrayList<Vendedor> vendedores = vendedorEJB.getVendedores();

							request.setAttribute("poblaciones", poblaciones);
							request.setAttribute("vendedores", vendedores);

							rs.forward(request, response);
						}

					}
				} else {
					request.setAttribute("vendedor", v);

					rs = getServletContext().getRequestDispatcher(VENDEDORES_LOGEADO_VENDEDOR_JSP);
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

				}

			} else {
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

			}
		} catch (Exception e) {
			logger.setErrorLogger(e.getMessage());
		}
	}

}
