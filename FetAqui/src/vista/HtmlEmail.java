package vista;

import java.text.DecimalFormat;
import java.util.ArrayList;

import modelo.pojo.PedidoDetallado;

/**
 * 
 * @author ramon
 *
 */
public class HtmlEmail {

	/**
	 * Método que devuelve un string para generar el mensaje del correo de
	 * confirmación de usuario
	 * 
	 * @param codigo
	 * @return string
	 */
	public String mailConfirmacion(int codigo) {
		return "<html>" + "<head>" + "<meta charset=\"UTF8\">" + "</head>" + "<body>"
				+ "<h1>Pulse sobre el link para confirmar su usuario!</h1>"
				+ "<h3>Recuerde que su email es el nombre de usuario de la aplicación</h3>"
				+ "<h2><a href='http://fetaquimallorca.com/ConfirmarUsuario?codigo=" + codigo + "'"
				+ ">Confirmar alta en la aplicación</a></h2>" + "</body>" + "</html>";

	}

	public String mailPedidoVendedor(ArrayList<PedidoDetallado> pedidoDetallado) {

		StringBuilder bld = new StringBuilder();
		
		DecimalFormat df = new DecimalFormat("####0.00");
		
		double precioTotal = 0;
		
		String mensaje = "<style type=\"text/css\">\n" + 
				"  body,\n" + 
				"  html, \n" + 
				"  .body {\n" + 
				"    background: #f3f3f3 !important;\n" + 
				"  }\n" + 
				"</style>\n" + 
				"\n" + 
				"<spacer size=\"16\"></spacer>\n" + 
				"\n" + 
				"<container>\n" + 
				"\n" + 
				"  <spacer size=\"16\"></spacer>\n" + 
				"\n" + 
				"  <row>\n" + 
				"    <columns>\n" + 
				"      <h1>¡Ha recibido un nuevo pedido!</h1>\n" + 
				"      <p>A continuación le mostramos los detalles del pedido.</p>\n" + 
				"\n" + 
				"      <spacer size=\"16\"></spacer>\n" + 
				"\n" + 
				"      <callout class=\"secondary\">\n" + 
				"        <row>\n" + 
				"          <columns large=\"6\">\n" + 
				"            <p>\n" + 
				"              <strong>Método de pago</strong><br/>\n" + 
				"              Contra reembolso\n" + 
				"            </p>\n" + 
				"            <p>\n" + 
				"              <strong>Datos de contacto</strong><br/>\n" + 
				"              Email: "+pedidoDetallado.get(0).getEmail_cliente()+"\n" +
				"              Teléfono: "+pedidoDetallado.get(0).getTelf_cliente()+"\n" + 
				"            </p>\n" + 
				"            <p>\n" + 
				"              <strong>Pedido nº</strong><br/>\n" + 
				"              "+pedidoDetallado.get(0).getId_pedido()+"\n" + 
				"            </p>\n" + 
				"          </columns>\n" + 
				"          <columns large=\"6\">\n" + 
				"            <p>\n" + 
				"              <strong>Dirección de envío</strong><br/>\n" + 
				"              "+pedidoDetallado.get(0).getDireccion()+"<br/>\n" + 
				"              "+pedidoDetallado.get(0).getPoblacion()+"<br/>\n" +
				"            </p>\n" + 
				"          </columns>\n" + 
				"        </row>\n" + 
				"      </callout>\n" + 
				"\n";
		
				bld.append(mensaje);		
		
				bld.append("<h3>Detalles del pedido</h3>\n"); 
				
				
				bld.append("<table>\n"); 
				bld.append("<tr><th>Producto</th><th>Cantidad</th><th>Precio Unitario</th><th>Precio Total</th></tr>\n");
				
				for (PedidoDetallado pd : pedidoDetallado) {

					precioTotal = precioTotal + pd.getPrecio_final();
					bld.append("<tr>");
					bld.append("<td>" + pd.getProducto() + "</td>");
					bld.append("<td>" + pd.getCantidad() + "</td>");
					bld.append("<td>" + pd.getPrecio_unidad() + "/€</td>");
					bld.append("<td>" + pd.getPrecio_final() + "/€</td>");
					bld.append("</tr>");
				}
				
				bld.append("<tr>\n");
				bld.append("<td colspan=\"3\"><b>Total:</b></td>\n"); 
				bld.append("<td>"+df.format(precioTotal)+"/€</td>\n");
				bld.append(" </tr>\n  </table>\n");
				bld.append("\n" + 
				"      <hr/>\n" + 
				"\n" + 
				"      <h1>Importante</h1>\n" + 
				"\n" + 
				"      <p>Le invitamos a ponserse en contacto con el cliente para verificar los datos del pedido.</p>\n" + 
				"    </columns>\n" + 
				"  </row>\n" + 
				"  <row class=\"footer text-center\">\n" +
				"    <columns large=\"3\">\n" + 
				"      <p>\n" +
				"        Puede ponerse en contacto con nosotros a través de correo a fetaquimallorca@gmail.com .\n" + 
				"      </p>\n" +
				"      <p>\n" +
				"        Le agradecemos la confianza depositada en nuestra plataforma." + 
				"      </p>\n" +
				"      <p>\n" +
				"        Reciba un cordial saludo." + 
				"      </p>\n" +
				"      <h4>\n" +
				"        www.fetaquimallorca.com" + 
				"      </h4>\n" + 
				"    </columns>\n" +
				"  </row>\n" + 
				"</container>");
		
		return bld.toString();

	}
	
	public String mailPedidoCliente(ArrayList<PedidoDetallado> pedidoDetallado) {
		
		StringBuilder bld = new StringBuilder();
		
		DecimalFormat df = new DecimalFormat("####0.00");
		
		double precioTotal = 0;
		
		String mensaje = "<style type=\"text/css\">\n" + 
				"  body,\n" + 
				"  html, \n" + 
				"  .body {\n" + 
				"    background: #f3f3f3 !important;\n" + 
				"  }\n" + 
				"</style>\n" + 
				"<!-- move the above styles into your custom stylesheet -->\n" + 
				"\n" + 
				"<spacer size=\"16\"></spacer>\n" + 
				"\n" + 
				"<container>\n" + 
				"\n" + 
				"  <spacer size=\"16\"></spacer>\n" + 
				"\n" + 
				"  <row>\n" + 
				"    <columns>\n" + 
				"      <h1>¡Gracias por su pedido!</h1>\n" + 
				"      <p>Desde www.fetaquimallorca.com le queremos agradecer su confianza depositada en nosotros</p>\n" + 
				"\n" + 
				"      <spacer size=\"16\"></spacer>\n" + 
				"\n" + 
				"      <callout class=\"secondary\">\n" + 
				"        <row>\n" + 
				"          <columns large=\"6\">\n" + 
				"            <p>\n" + 
				"              <strong>Método de pago</strong><br/>\n" + 
				"              Contra reembolso\n" + 
				"            </p>\n" + 
				"            <p>\n" + 
				"              <strong>Email</strong><br/>\n" + 
				"              "+pedidoDetallado.get(0).getEmail_cliente()+"\n" + 
				"            </p>\n" + 
				"            <p>\n" + 
				"              <strong>Pedido nº</strong><br/>\n" + 
				"              "+pedidoDetallado.get(0).getId_pedido()+"\n" + 
				"            </p>\n" + 
				"          </columns>\n" + 
				"          <columns large=\"6\">\n" + 
				"            <p>\n" + 
				"              <strong>Dirección de envío</strong><br/>\n" + 
				"              "+pedidoDetallado.get(0).getDireccion()+"<br/>\n" + 
				"              "+pedidoDetallado.get(0).getPoblacion()+"<br/>\n" +
				"            </p>\n" + 
				"          </columns>\n" + 
				"        </row>\n" + 
				"      </callout>\n" + 
				"\n";
		
				bld.append(mensaje);		
		
				bld.append("<h4>Detalles de su pedido</h4>\n"); 
				
				
				bld.append("<table>\n"); 
				bld.append("<tr><th>Producto</th><th>Cantidad</th><th>Precio Unitario</th><th>Precio Total</th></tr>\n");
				
				for (PedidoDetallado pd : pedidoDetallado) {

					precioTotal = precioTotal + pd.getPrecio_final();
					bld.append("<tr>");
					bld.append("<td>" + pd.getProducto() + "</td>");
					bld.append("<td>" + pd.getCantidad() + "</td>");
					bld.append("<td>" + pd.getPrecio_unidad() + "/€</td>");
					bld.append("<td>" + pd.getPrecio_final() + "/€</td>");
					bld.append("</tr>");
				}
				
				bld.append("<tr>\n");
				bld.append("<td colspan=\"3\"><b>Total:</b></td>\n"); 
				bld.append("<td>"+df.format(precioTotal)+"/€</td>\n");
				bld.append(" </tr>\n  </table>\n");
				bld.append("\n" + 
				"      <hr/>\n" + 
				"\n" + 
				"      <h4>Importante</h4>\n" + 
				"\n" + 
				"      <p>El vendedor o los vendedores se pondrán en contacto con usted para confirmar los datos de su pedido.</p>\n" + 
				"    </columns>\n" + 
				"  </row>\n" + 
				"  <row class=\"footer text-center\">\n" +
				"    <columns large=\"3\">\n" + 
				"      <p>\n" +
				"        Contacte con nosotros en fetaquimallorca@gmail.com\n" + 
				"      </p>\n" + 
				"    </columns>\n" +
				"  </row>\n" + 
				"</container>");
		
		return bld.toString();
		
	}

}
