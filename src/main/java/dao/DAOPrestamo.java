package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Ejemplar;
import entidades.Prestamo;
import entidades.Socio;
import entidades.SocioPenalizado;
import dao.DAOEjemplar;

public class DAOPrestamo {
	public void insert(Prestamo prestamo, Connection con) {

		String sql = "INSERT INTO PRESTAMO VALUES(?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setObject(1, prestamo.getEjemplar().getIdejemplar());
			ps.setObject(2, prestamo.getSocio().getIdSocio());
			ps.setObject(3, prestamo.getFechaPrestamo());
			ps.setObject(4, prestamo.getFechaLimiteDevolucion());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Prestamo prestamo, Connection con) {

		String sql = "DELETE FROM PRESTAMO WHERE IDEJEMPLAR=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, prestamo.getEjemplar().getIdejemplar());
			ps.executeUpdate();

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Prestamo prestamo, Connection con) {

		String sql = "UPDATE PRESTAMO SET IDSOCIO=?, FECHALIMITEDEVOLUCION=? WHERE IDEJEMPLAR=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(4, prestamo.getEjemplar().getIdejemplar());
			ps.setInt(1, prestamo.getSocio().getIdSocio());
			ps.setObject(2, prestamo.getFechaPrestamo());
			ps.setObject(3, prestamo.getFechaLimiteDevolucion());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Prestamo select(int idEjemplar, Connection con) {
		Prestamo prestamo = null;

		String sql = "SELECT p.idsocio, e.idEjemplar, e.isbn, e.baja, s.email, "
				+ "s.nombre, s.direccion, s.version, p.fechaprestamo, " + "p.fechalimitedevolucion FROM PRESTAMO "
				+ "p JOIN SOCIO s ON s.idsocio=p.idsocio JOIN EJEMPLAR e "
				+ "ON e.idEjemplar=p.idEjemplar WHERE p.idEjemplar=?";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idEjemplar);

			ResultSet resultado = ps.executeQuery();
			if (resultado.next()) {
				prestamo = new Prestamo(
						new Ejemplar(idEjemplar, resultado.getString("ISBN"),
								mapCharToBoolean(resultado.getString("BAJA").charAt(0))),
						new Socio(resultado.getInt("IDSOCIO"), resultado.getString("EMAIL"),
								resultado.getString("NOMBRE"), resultado.getString("DIRECCION"),
								resultado.getInt("VERSION")),
						resultado.getTimestamp("FECHAPRESTAMO").toLocalDateTime(),
						resultado.getTimestamp("FECHALIMITEDEVOLUCION").toLocalDateTime());
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamo;
	}

	public ArrayList<Prestamo> select(Connection con) {
		Prestamo prestamo = null;
		ArrayList<Prestamo> listaPrestamo = new ArrayList<Prestamo>();

		String sql = "SELECT p.idsocio, e.idEjemplar, e.isbn, e.baja, s.email, "
				+ "s.nombre, s.direccion, s.version, p.fechaprestamo, " + "p.fechalimitedevolucion FROM PRESTAMO "
				+ "p JOIN SOCIO s ON s.idsocio=p.idsocio JOIN EJEMPLAR e " + "ON e.idEjemplar=p.idEjemplar";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Prestamo prestamoActual = new Prestamo(
						new Ejemplar(resultado.getInt("IDEJEMPLAR"), resultado.getString("ISBN"),
								mapCharToBoolean(resultado.getString("BAJA").charAt(0))),
						new Socio(resultado.getInt("IDSOCIO"), resultado.getString("EMAIL"),
								resultado.getString("NOMBRE"), resultado.getString("DIRECCION"),
								resultado.getInt("VERSION")),
						resultado.getTimestamp("FECHAPRESTAMO").toLocalDateTime(),
						resultado.getTimestamp("FECHALIMITEDEVOLUCION").toLocalDateTime());
				listaPrestamo.add(prestamoActual);
			}
			ps.close();

		} catch (SQLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		return listaPrestamo;
	}

	public ArrayList<Prestamo> select(Socio socio, Connection con) {
		Prestamo prestamo = null;
		ArrayList<Prestamo> listaPrestamo = new ArrayList<Prestamo>();

		String sql = "SELECT p.idsocio, e.idEjemplar, e.isbn, e.baja, s.email, "
				+ "s.nombre, s.direccion, s.version, p.fechaprestamo, " + "p.fechalimitedevolucion FROM PRESTAMO "
				+ "p JOIN SOCIO s ON s.idsocio=p.idsocio JOIN EJEMPLAR e "
				+ "ON e.idEjemplar=p.idEjemplar WHERE s.idSocio=?";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, socio.getIdSocio());

			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Prestamo prestamoActual = new Prestamo(
						new Ejemplar(resultado.getInt("IDEJEMPLAR"), resultado.getString("ISBN"),
								mapCharToBoolean(resultado.getString("BAJA").charAt(0))),
						new Socio(socio.getIdSocio(), resultado.getString("EMAIL"), resultado.getString("NOMBRE"),
								resultado.getString("DIRECCION"), resultado.getInt("VERSION")),
						resultado.getTimestamp("FECHAPRESTAMO").toLocalDateTime(),
						resultado.getTimestamp("FECHALIMITEDEVOLUCION").toLocalDateTime());
				listaPrestamo.add(prestamoActual);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaPrestamo;
	}

	public boolean mapCharToBoolean(char c) {
		if (c == 'S') {
			return true;
		} else {
			return false;
		}
	}
}
