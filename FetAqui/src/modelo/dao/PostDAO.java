package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.PostsMapper;
import modelo.pojo.Post;

/**
 * Clase DAO con los métodos necesarios para acceder a los mapper (BD)
 * necesarios de Estadisticas
 * 
 * @author ramon
 *
 */
public class PostDAO {
	
	public void insertarPost(Post post) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
			// Insertamos el usuario
			postsMapper.insertarPost(post);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public void editarPost(Post post) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
			// Insertamos el usuario
			postsMapper.editarPost(post);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public void borrarPost(Integer id_post) {

		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			// Obtenemos el mapper
			PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
			// Insertamos el usuario
			postsMapper.borrarPost(id_post);
			sqlSession.commit();
		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public Post getPostPorId(Integer id_post) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			// Obtenemos el mapper
			PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
			//Devolvemos el arraylist de poblacion
			return postsMapper.getPostPorId(id_post);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	
	public ArrayList<Post> getPosts() {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			// Obtenemos el mapper
			PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
			//Devolvemos el arraylist de poblacion
			return postsMapper.getPosts();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}
	
	public ArrayList<Post> getPostsVendedor(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		
		try {
			// Obtenemos el mapper
			PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
			//Devolvemos el arraylist de poblacion
			return postsMapper.getPostsPorVendedor(id_vendedor);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	

}
