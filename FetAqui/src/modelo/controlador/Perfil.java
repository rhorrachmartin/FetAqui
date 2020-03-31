package modelo.controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ejb.ClienteEJB;
import modelo.ejb.CodigoClienteEJB;
import modelo.ejb.CodigoVendedorEJB;
import modelo.ejb.ImagenesEJB;
import modelo.ejb.LoggersEJB;
import modelo.ejb.PoblacionEJB;
import modelo.ejb.SesionClienteEJB;
import modelo.ejb.SesionVendedorEJB;
import modelo.ejb.VendedorEJB;
import modelo.pojo.Cliente;
import modelo.pojo.Poblacion;
import modelo.pojo.Vendedor;

/**
 * Servlet implementation class Perfil
 */
@WebServlet("/Perfil")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * EJB para trabajar con Vendedores
	 */
	@EJB
	VendedorEJB vendedorEJB;

	/**
	 * EJB para trabajar con Clientes
	 */
	@EJB
	ClienteEJB clienteEJB;
	
	@EJB
	PoblacionEJB poblacionEJB;

	/**
	 * EJB para trabajar con sesiones de vendedor
	 */
	@EJB
	SesionVendedorEJB sesionVendedorEJB;

	/**
	 * EJB para trabajar con sesiones de cliente
	 */
	@EJB
	SesionClienteEJB sesionClienteEJB;

	/**
	 * EJB para tratar las imágenes
	 */
	@EJB
	ImagenesEJB imagenesEJB;

	/**
	 * EJB para trabajar con los logger
	 */
	@EJB
	LoggersEJB logger;

	static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String PERFIL_CLIENTE_JSP = "/PerfilCliente.jsp";
	static final String PERFIL_VENDEDOR_JSP = "/PerfilVendedor.jsp";
	static final String HOME_LOGEADO_JSP = "/HomeLogeado.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Creamos el RequestDispatcher por defecto hacia Registro.jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(PERFIL_CLIENTE_JSP);
		response.setContentType(CONTENT_TYPE);
		// Recogemos la sesión en caso de que la haya, si no hay no la creamos
		HttpSession session = request.getSession(false);

		Cliente cliente = null;
		Vendedor vendedor = null;
		ArrayList<Poblacion> poblaciones = null;
		if(session == null && session.getAttribute("cliente") == null && session.getAttribute("vendedor") == null) {
			response.sendRedirect("Principal");
		}else {
			if(session.getAttribute("cliente") != null) {
				cliente = (Cliente) session.getAttribute("cliente");
				request.setAttribute("cliente", cliente);
				poblaciones = poblacionEJB.getPoblaciones();
				request.setAttribute("poblaciones", poblaciones);
				rs.forward(request, response);
			}else {
				vendedor = (Vendedor) session.getAttribute("vendedor");
				request.setAttribute("vendedor", vendedor);
				rs = getServletContext().getRequestDispatcher(PERFIL_VENDEDOR_JSP);
				rs.forward(request, response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}