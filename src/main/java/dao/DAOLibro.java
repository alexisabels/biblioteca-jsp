package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Libro;
import excepciones.DatabaseConnectionException;
import excepciones.QueryExecutionException;
import entidades.Autor;

public class DAOLibro {

	public void insert(Libro libro, Connection con) {



		String sql = "INSERT INTO LIBRO VALUES(?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, libro.getISBN());
			ps.setString(2, libro.getTitulo());
			ps.setInt(3, libro.getIdAutor());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String isbn, Connection con) {
		// tambien se puede hacer pasando el libro entero

		String sql = "DELETE FROM LIBRO WHERE ISBN =?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, isbn);
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Libro libro, Connection con) {


		String sql = "UPDATE LIBRO SET TITULO=?, IDAUTOR=? WHERE ISBN=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(3, libro.getISBN());
			ps.setString(1, libro.getTitulo());
			ps.setInt(2, libro.getIdAutor());

			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Libro> select(Connection con){

		ArrayList<Libro> listadoLibros = new ArrayList<Libro>();

		String sql = "SELECT l.isbn, l.titulo, l.idautor, a.nombre, a.fechanacimiento FROM libro l"+
				"JOIN autor a ON a.idAutor=l.idAutor";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);

			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Libro libroActual = new Libro(resultado.getString("ISBN"),
						resultado.getString("TITULO"), new Autor(resultado.getInt("IDAUTOR")
								,resultado.getString("NOMBRE")
								,resultado.getTimestamp("FECHANACIMIENTO").toLocalDateTime()));
			listadoLibros.add(libroActual);
			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listadoLibros;
		
	}
	public ArrayList<Libro> selectLibrosAutor(int idAutor, Connection con){
		ArrayList<Libro> listadoLibrosAutor = new ArrayList<Libro>();

		
		String sql = "SELECT * FROM LIBRO WHERE IDAUTOR=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idAutor);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Libro libroActual = new Libro(resultado.getString(1), 
								  resultado.getString(2),
								  idAutor);
				listadoLibrosAutor.add(libroActual);
			}
			ps.close();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listadoLibrosAutor;
		
	}
	public Libro select(String ISBN, Connection con) {
		Libro libro =null;
		String sql = "SQL l.isbn, l.titulo, l.idautor, a.nombre, a.fechanacimiento FROM libro l"+
		"JOIN autor a ON a.idAutor=l.idAutor where isbn =?";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ISBN);
			ResultSet resultado = ps.executeQuery();
			if(resultado.next()) {
				libro = new Libro(resultado.getString("ISBN"),
					resultado.getString("TITULO"), new Autor(resultado.getInt("IDAUTOR")
							,resultado.getString("NOMBRE")
							,resultado.getTimestamp("FECHANACIMIENTO").toLocalDateTime()));	
			}
			ps.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace(); 
			e.getErrorCode();
		}
		
		return libro;
	}
	public ArrayList<Libro> selectLibrosPorAutor(int idAutor, Connection con){
		ArrayList<Libro> listadoLibrosAutor = new ArrayList<Libro>();

		String sql = "SELECT l.isbn, l.titulo, a.nombre, a.fechanacimiento FROM libro l"+ "JOIN autor a ON a.idAutor?l.idAutor where l.idAutor=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idAutor);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Libro libroActual = new Libro(resultado.getString("ISBN"),
						resultado.getString("TITULO"), new Autor(idAutor, resultado.getString("NOMBRE")
								,resultado.getTimestamp("FECHANACIMIENTO").toLocalDateTime()));
				
				listadoLibrosAutor.add(libroActual);

			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listadoLibrosAutor;
		
	}
	public ArrayList<Libro> selectLibrosPorTitulo(String titulo, Connection con){
		ArrayList<Libro> listadoLibros = new ArrayList<Libro>();

		String sql = "SELECT l.isbn, l.titulo, a.nombre, a.idAutor, a.fechanacimiento FROM libro l " + "JOIN autor a ON a.idAutor=l.idAutor where l.titulo LIKE (UPPER(?))";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+titulo+"%");
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Libro libroActual = new Libro(resultado.getString("ISBN"),
						resultado.getString("TITULO"), new Autor(resultado.getInt("IDAUTOR"), resultado.getString("NOMBRE")
								,resultado.getTimestamp("FECHANACIMIENTO").toLocalDateTime()));
				
				listadoLibros.add(libroActual);

			}
			ps.close();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listadoLibros;
		
	}
	
}
