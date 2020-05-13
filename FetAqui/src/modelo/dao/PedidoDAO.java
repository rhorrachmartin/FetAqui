package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.PedidosMapper;
import modelo.pojo.Pedido;
import modelo.pojo.PedidoDetallado;

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

}
