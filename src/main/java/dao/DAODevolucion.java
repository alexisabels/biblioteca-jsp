package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Autor;
import entidades.Devolucion;
import entidades.Ejemplar;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;
import tools.LanzadorExcepciones;

public class DAODevolucion {
	public void insert(Devolucion devolucion, Connection con) throws SQLException {


		String sql = "INSERT INTO DEVOLUCION VALUES(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, devolucion.getEjemplar().getIdejemplar());
			ps.setObject(2, devolucion.getSocio().getIdSocio());
			ps.setObject(3, devolucion.getFechaPrestamo());
			ps.setObject(4, devolucion.getFechaDevolucion());
			ps.executeUpdate();
			ps.close();
			 
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);

			e.printStackTrace();
		}
	}
	public void delete(Devolucion devolucion, Connection con) throws SQLException {

		String sql = "DELETE FROM DEVOLUCION WHERE IDEJEMPLAR=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, devolucion.getEjemplar().getIdejemplar());
			ps.executeUpdate();

			ps.close();
			 
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}
	}
	public void update(Devolucion devolucion, Connection con) throws SQLException {


		String sql = "UPDATE DEVOLUCION SET IDSOCIO=?, FECHADEVOLUCION=? WHERE IDEJEMPLAR=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(3, devolucion.getEjemplar().getIdejemplar());
			ps.setInt(1, devolucion.getSocio().getIdSocio() );
			ps.setObject(2, devolucion.getFechaDevolucion());

			ps.executeUpdate();
			ps.close();
			 
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}
	}

		
	public ArrayList<Devolucion> selectPorIdSocio(int idSocio, Connection con) {
		Devolucion devolucion = null;
		ArrayList<Devolucion> listaDevolucion = new ArrayList<Devolucion>();

		String sql = "SELECT d.idsocio, d.idEjemplar, e.isbn, e.baja, s.email, s.nombre, s.direccion, s.version, d.fechaprestamo,d.fechadevolucion FROM DEVOLUCION d JOIN SOCIO s ON s.idsocio=d.idsocio JOIN EJEMPLAR e ON e.idEjemplar=d.idEjemplar WHERE s.idSocio=?";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idSocio);

			ResultSet resultado = ps.executeQuery();
			while(resultado.next()) {
				devolucion = new Devolucion(new Ejemplar(resultado.getInt("IDEJEMPLAR"), resultado.getString("ISBN"),
						mapCharToBoolean(resultado.getString("BAJA").charAt(0))), 
						new Socio(idSocio, resultado.getString("EMAIL"), 
						resultado.getString("NOMBRE"), resultado.getString("DIRECCION"), 
						resultado.getInt("VERSION")), resultado.getTimestamp("FECHAPRESTAMO").toLocalDateTime(),
						resultado.getTimestamp("FECHADEVOLUCION").toLocalDateTime());
			listaDevolucion.add(devolucion);
			}
			ps.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaDevolucion;
	}
	public ArrayList<Devolucion> selectPorIdEjemplar(int idEjemplar, Connection con) throws SQLException {
		ArrayList<Devolucion> listaDevolucion = new ArrayList<Devolucion>();
		Devolucion devolucion = null;

		String sql = "SELECT d.idsocio, d.idEjemplar, e.isbn, e.baja, s.email, "
				+ "s.nombre, s.direccion, s.version, d.fechaprestamo, "
				+ "d.fechadevolucion FROM DEVOLUCION "
				+ "d JOIN SOCIO s ON s.idsocio=d.idsocio JOIN EJEMPLAR e "
				+ "ON e.idEjemplar=d.idEjemplar WHERE d.idEjemplar=?";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idEjemplar);

			ResultSet resultado = ps.executeQuery();
			while(resultado.next()) {
				devolucion = new Devolucion(new Ejemplar(idEjemplar, resultado.getString("ISBN"),
						mapCharToBoolean(resultado.getString("BAJA").charAt(0))), 
						new Socio(resultado.getInt("IDSOCIO"), resultado.getString("EMAIL"), 
						resultado.getString("NOMBRE"), resultado.getString("DIRECCION"), 
						resultado.getInt("VERSION")), resultado.getTimestamp("FECHAPRESTAMO").toLocalDateTime(),
						resultado.getTimestamp("FECHADEVOLUCION").toLocalDateTime());
			listaDevolucion.add(devolucion);
			}
			ps.close();
			 
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);

			e.printStackTrace();
		}
		return listaDevolucion;
	}
	public ArrayList<Devolucion> select(Connection con) throws SQLException {
		Devolucion devolucion = null;
		ArrayList<Devolucion> listaDevolucion = new ArrayList<Devolucion>();

		
		String sql = "SELECT d.idsocio, d.idEjemplar, e.isbn, e.baja, "
				+ "s.email, s.nombre, s.direccion, s.version, d.fechaprestamo,"
				+ "d.fechadevolucion FROM DEVOLUCION d JOIN SOCIO s ON s.idsocio="
				+ "d.idsocio JOIN EJEMPLAR e ON e.idEjemplar=d.idEjemplar";

		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();
			while(resultado.next()) {
				devolucion = new Devolucion(new Ejemplar(resultado.getInt("IDEJEMPLAR"), resultado.getString("ISBN"),
						mapCharToBoolean(resultado.getString("BAJA").charAt(0))), 
						new Socio(resultado.getInt("IDSOCIO"), resultado.getString("EMAIL"), 
						resultado.getString("NOMBRE"), resultado.getString("DIRECCION"), 
						resultado.getInt("VERSION")), resultado.getTimestamp("FECHAPRESTAMO").toLocalDateTime(),
						resultado.getTimestamp("FECHADEVOLUCION").toLocalDateTime());
			listaDevolucion.add(devolucion);
			}
			ps.close();
			 
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}
		return listaDevolucion;
	}
	public ArrayList<Devolucion> selectPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin, Connection con) throws SQLException {
		Devolucion devolucion = null;
		ArrayList<Devolucion> listaDevolucion = new ArrayList<Devolucion>();

		
		String sql = "SELECT d.idsocio, d.idEjemplar, e.isbn, e.baja, "
				+ "s.email, s.nombre, s.direccion, s.version, d.fechaprestamo,"
				+ "d.fechadevolucion FROM DEVOLUCION d JOIN SOCIO s ON s.idsocio="
				+ "d.idsocio JOIN EJEMPLAR e ON e.idEjemplar=d.idEjemplar where d.fechaprestamo between ? and ?";

		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, fechaInicio);
			ps.setObject(2, fechaFin);

			ResultSet resultado = ps.executeQuery();
			while(resultado.next()) {
				devolucion = new Devolucion(new Ejemplar(resultado.getInt("IDEJEMPLAR"), resultado.getString("ISBN"),
						mapCharToBoolean(resultado.getString("BAJA").charAt(0))), 
						new Socio(resultado.getInt("IDSOCIO"), resultado.getString("EMAIL"), 
						resultado.getString("NOMBRE"), resultado.getString("DIRECCION"), 
						resultado.getInt("VERSION")), fechaInicio,
						fechaFin);
			listaDevolucion.add(devolucion);
			}
			ps.close();
			 
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}
		return listaDevolucion;
	}
	public ArrayList<Prestamo> select(Socio socio, Connection con) throws SQLException {
		Prestamo prestamo = null;
		ArrayList<Prestamo> listaDevolucion = new ArrayList<Prestamo>();

		String sql = "SELECT p.idsocio, e.idEjemplar, e.isbn, e.baja, s.email, "
				+ "s.nombre, s.direccion, s.version, p.fechaprestamo, "
				+ "p.fechalimitedevolucion FROM PRESTAMO "
				+ "p JOIN SOCIO s ON s.idsocio=p.idsocio JOIN EJEMPLAR e "
				+ "ON e.idEjemplar=p.idEjemplar WHERE s.idSocio=?";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, socio.getIdSocio());

			ResultSet resultado = ps.executeQuery();
			while(resultado.next()) {
				Prestamo prestamoActual = new Prestamo(new Ejemplar(resultado.getInt("IDEJEMPLAR"), resultado.getString("ISBN"),
						mapCharToBoolean(resultado.getString("BAJA").charAt(0))), 
						new Socio(socio.getIdSocio(), resultado.getString("EMAIL"), 
						resultado.getString("NOMBRE"), resultado.getString("DIRECCION"), 
						resultado.getInt("VERSION")), resultado.getTimestamp("FECHAPRESTAMO").toLocalDateTime(),
						resultado.getTimestamp("FECHALIMITEDEVOLUCION").toLocalDateTime());
			listaDevolucion.add(prestamoActual);
			}
			ps.close();
			 
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);

			e.printStackTrace();
			
		}
		return listaDevolucion;
	}
	
	
	public ArrayList<Libro> selectRanking(Connection con) throws SQLException {
	    ArrayList<Libro> lista = new ArrayList<Libro>();

	    
	    String sql = "SELECT COUNT(*), l.isbn, l.titulo, l.idautor " +
	                 "FROM DEVOLUCION D " +
	                 "JOIN EJEMPLAR E ON d.idejemplar = e.idejemplar " +
	                 "JOIN libro l ON e.isbn = l.isbn " +
	                 "GROUP BY l.isbn, l.titulo, l.idautor ORDER BY count(*) DESC";
	    

	    PreparedStatement ps;

	    try {
	        ps = con.prepareStatement(sql);
	        
	        ResultSet resultado = ps.executeQuery();
	        while (resultado.next()) {
	            
	            Libro libroActual = new Libro(resultado.getInt("COUNT(*)"), resultado.getString("isbn"), resultado.getString("titulo"), resultado.getInt("idautor"));
	            lista.add(libroActual);
	        }
	        ps.close();
	         
	    } catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
	    	e.printStackTrace();
	    }
	    return lista;
	}

	
	
	
	
	
	
	
	
	
	
	
	public boolean mapCharToBoolean(char c) {
		if (c == 'S') {
			return true;
		} else {
			return false;
		}
	}
}
