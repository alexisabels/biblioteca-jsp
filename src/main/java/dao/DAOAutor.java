package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Autor;
import entidades.Libro;
import excepciones.ConstraintViolationException;
import excepciones.DatabaseConnectionException;
import excepciones.DuplicateEntityException;
import excepciones.QueryExecutionException;
import tools.LanzadorExcepciones;

public class DAOAutor {
	
 	public void insert (Autor autor, Connection con) throws SQLException  {
 		//Quitamos la conexión y se la pasamos como parámetro 
//		Connection con = Conexion.getConnection();

		String sql = "INSERT INTO Autor VALUES (S_AUTOR.NEXTVAL, ?,?)";
//		String sql = "INSERT INTO Autor VALUES (?,?,?)"; para probar exception3
		PreparedStatement ps;
		try {
			ps= con.prepareStatement(sql);
//			ps.setInt(1, autor.getIdAutor());
			ps.setString(1, autor.getNombre());
			//java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(autor.getFechaDeNacimiento()); // puedo quitar el java.sql y ponemos el import
			ps.setObject(2, autor.getFechaNacimiento());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
			
		}
		
	}
	
	public void delete(Autor autor, Connection con) throws SQLException{



		String sql = "DELETE FROM AUTOR WHERE IdAutor = ?";

		PreparedStatement ps;

		try {
			ps= con.prepareStatement(sql);
			ps.setInt(1, autor.getIdAutor());

			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}
	}
	

	
	
	public Autor select(int idAutor, Connection con) throws SQLException{

		Autor autor = null;

		String sql = "SELECT * from autor where IdAutor = ?";

		PreparedStatement ps;

		try {
			ps= con.prepareStatement(sql);
			ps.setInt(1, idAutor);

			ResultSet resultado = ps.executeQuery();

			if (resultado.next()) {
				autor= new Autor(idAutor ,resultado.getString("NOMBRE"),resultado.getTimestamp("FECHANACIMIENTO").toLocalDateTime());
			}


			ps.close();
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}

		return autor;
	}
	
	public void update(Autor autor, Connection con) throws SQLException{ 



		String sql = "UPDATE AUTOR SET nombre = ?, fechanacimiento = ? WHERE IDAUTOR = ?";

		PreparedStatement ps;

		try {
			ps= con.prepareStatement(sql);
			ps.setString(1, autor.getNombre());
			ps.setObject(2, autor.getFechaNacimiento());
			ps.setInt(3, autor.getIdAutor());


			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}


	}
	
//	public ArrayList<Autor> select(){
//		ArrayList<Autor> listadoAutores = new ArrayList <Autor>();
//
//		Connection con = Conexion.GetConnection();
//
//		String sql = "SELECT * from AUTOR ";
//
//		PreparedStatement ps;
//
//		try {
//			ps= con.prepareStatement(sql);
//			
//			ResultSet resultado = ps.executeQuery();
//
//		    while (resultado.next()) {
//		    	autor  = new Autor (resultado.getString("IDAUTOR"),resultado.getString("NOMBRE"),resultado.getInt("FECHANACIMIENTO"));
//		    	
//		    	listadoAutores.add(autor);
//				
//			}
//		   
//
//			ps.close();
//			con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		return listadoLibros;
//	}

	public ArrayList<Autor> select() throws SQLException{
		ArrayList<Autor> listadoautores =new ArrayList<Autor>();
		Connection con = Conexion.getConnection(); //QUITAR Y PASAR COMO PARÁMETRO (como?)
		String sql = "SELECT * FROM AUTOR";
		PreparedStatement ps;
		try {
			ps= con.prepareStatement(sql);
			ResultSet resultado=ps.executeQuery();
			while(resultado.next()) {
			Autor AutorActual= new Autor(resultado.getInt("IDAUTOR"),resultado.getString("NOMBRE"),resultado.getTimestamp("FECHANACIMIENTO").toLocalDateTime());
			listadoautores.add(AutorActual);
			}
			//cerrar la conexion
			ps.close();
			con.close(); //QUITAR ESTO 
		} catch (SQLException e) {
			e.printStackTrace();
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
		}
		return listadoautores;
	}
	


	
	
	
	
}
