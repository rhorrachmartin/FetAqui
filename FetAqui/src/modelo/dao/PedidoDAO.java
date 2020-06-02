package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.PedidosMapper;
import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;
import modelo.pojo.Vendedor;

/**
 * Clase DAO para manejar los pedidos
 * 
 * @author ramon
 *
 */
public class PedidoDAO {

	/**
	 * Método para insertar un pedido en BD
	 * 
	 * @param pedido Recibe pojo Pedido
	 */
	public void insertarPedido(Pedido pedido) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			pedidosMapper.insertarPedido(pedido);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para borrar un pedido de la BD
	 * 
	 * @param id_pedido Recibe la id del pedido
	 */
	public void borrarPedidoPorId(Integer id_pedido) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			pedidosMapper.borrarPedidoPorId(id_pedido);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para borrar un producto de la cesta (Pedido)
	 * 
	 * @param id_detalle Recibe la id del detalle del pedido
	 */
	public void borrarProductoCesta(Integer id_detalle) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			pedidosMapper.borrarProductoCesta(id_detalle);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener la cantidad de productos de un pedido
	 * 
	 * @param id_pedido Recibe la id del pedido
	 * @return Devuelve la cantidad de productos
	 */
	public int getNumeroProductos(Integer id_pedido) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			return pedidosMapper.getNumeroProductos(id_pedido);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener un pedido detallado en función de su ID
	 * 
	 * @param id_pedido
	 * @return Devuelve un arraylist de PedidoDetallado
	 */
	public ArrayList<PedidoDetallado> getPedidoDetalladoPorId(Integer id_pedido) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			return pedidosMapper.getPedidoDetalladoPorId(id_pedido);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener un pedido detallado de un pedido y vendedor concretos
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 * @param id_pedido   Recibe la id del pedido
	 * @return Devuelve un araylist de PedidoDetallado
	 */
	public ArrayList<PedidoDetallado> getPedidoDetalladoPorIdVendedorYpedido(Integer id_vendedor, Integer id_pedido) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			return pedidosMapper.getPedidoDetalladoPorIdVendedorYpedido(id_vendedor, id_pedido);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para pasar un pedido a confirmado
	 * 
	 * @param id_pedido Recibe la id del pedido
	 */
	public void updatePedidoAconfirmado(Integer id_pedido) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			pedidosMapper.updatePedidoAconfirmado(id_pedido);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para pasar un pedido a pendiente
	 * 
	 * @param id_pedido Recibe la id del pedido
	 */
	public void updatePedidoApendiente(Integer id_pedido) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			pedidosMapper.updatePedidoApendiente(id_pedido);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener los diferentes vendedores que hay en un mismo pedido
	 * 
	 * @param id_pedido Recibe la id del pedido
	 * @return Devuelve un arraylist de Vendedor
	 */
	public ArrayList<Vendedor> getVendedoresPorPedido(Integer id_pedido) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			return pedidosMapper.getVendedoresPorPedido(id_pedido);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener todos los pedidos de un cliente
	 * 
	 * @param id_cliente Recibe la id del cliente
	 * @return Devuelve un arraylist de Pedido
	 */
	public ArrayList<Pedido> getPedidosCliente(Integer id_cliente) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			return pedidosMapper.getPedidosCliente(id_cliente);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener todos los pedidos que ha recibido un vendedor
	 * 
	 * @param id_vendedor Recibe la id del vendedor
	 * @return Devuelve un arraylist de Pedido
	 */
	public ArrayList<Pedido> getPedidosVendedor(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PedidosMapper pedidosMapper = sqlSession.getMapper(PedidosMapper.class);
			// Insertamos el usuario
			return pedidosMapper.getPedidosVendedor(id_vendedor);
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
