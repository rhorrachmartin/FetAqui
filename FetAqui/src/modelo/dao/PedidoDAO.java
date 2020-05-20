package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.PedidosMapper;
import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;
import modelo.pojo.Vendedor;

public class PedidoDAO {

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

}
