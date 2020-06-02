package modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import modelo.dao.mappers.PostsMapper;
import modelo.pojo.Post;

/**
 * Método DAO para manejar los POST
 * 
 * @author ramon
 *
 */
public class PostDAO {

	/**
	 * Método para insertar un Post en BD
	 * 
	 * @param post REcibe un pojo Post
	 */
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

	/**
	 * Método para editar un post ya creado
	 * 
	 * @param post Recibe un pojo Post
	 */
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

	/**
	 * Método para borrar un post
	 * 
	 * @param id_post Recibe la id del post
	 */
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

	/**
	 * Método para obtener un Post a través de su id
	 * 
	 * @param id_post Recibe la id del posrt
	 * @return Devuelve un pojo Post
	 */
	public Post getPostPorId(Integer id_post) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
			// Devolvemos el arraylist de poblacion
			return postsMapper.getPostPorId(id_post);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener todos los posts
	 * 
	 * @return Devuelve un arraylist de Post
	 */
	public ArrayList<Post> getPosts() {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
			// Devolvemos el arraylist de poblacion
			return postsMapper.getPosts();

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

	/**
	 * Método para obtener todos los Post de un vendedor a través de su id
	 * 
	 * @param id_vendedor
	 * @return Devuelve un arraylist de Post
	 */
	public ArrayList<Post> getPostsVendedor(Integer id_vendedor) {
		// Abrimos la sesión
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

		try {
			// Obtenemos el mapper
			PostsMapper postsMapper = sqlSession.getMapper(PostsMapper.class);
			// Devolvemos el arraylist de poblacion
			return postsMapper.getPostsPorVendedor(id_vendedor);

		} finally {
			// Cerramos sesión
			sqlSession.close();
		}
	}

}
