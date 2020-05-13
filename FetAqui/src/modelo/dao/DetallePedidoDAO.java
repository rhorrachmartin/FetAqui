package modelo.dao;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.DetallesPedidoMapper;
import modelo.pojo.DetallePedido;


public class DetallePedidoDAO {
	
	public void insertarDetallePedido(DetallePedido detallePedido) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			DetallesPedidoMapper detallesPedidoMapper = sqlSession.getMapper(DetallesPedidoMapper.class);
			// Insertamos el usuario
			detallesPedidoMapper.insertarDetallePedido(detallePedido);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	

}
