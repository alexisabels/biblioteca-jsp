package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Socio;
import excepciones.ConstraintViolationException;
import excepciones.DatabaseConnectionException;

public class DAOSocio {
	public void insert(Socio socio, Connection con) {

		String sql = "INSERT INTO SOCIO VALUES(S_SOCIO.NEXTVAL,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, socio.getEmail());
			ps.setString(2, socio.getNombre());
			ps.setString(3, socio.getDireccion());
			ps.setInt(4, socio.getVersion());

			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Socio socio, Connection con) {

		String sql = "DELETE FROM SOCIO WHERE IDSOCIO=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, socio.getIdSocio());
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int idSocio, Connection con) {

		String sql = "DELETE FROM SOCIO WHERE IDSOCIO=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idSocio);
			ps.executeUpdate();

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Socio socio, Connection con) {

		String sql = "UPDATE SOCIO SET EMAIL=?, NOMBRE=?, DIRECCION=?, VERSION=? WHERE IDSOCIO=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(5, socio.getIdSocio());
			ps.setString(1, socio.getEmail());
			ps.setString(2, socio.getNombre());
			ps.setString(3, socio.getDireccion());
			ps.setInt(4, socio.getVersion());

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Socio select(int idSocio, Connection con) {
		Socio socio = null;

		String sql = "SELECT * FROM SOCIO WHERE IDSOCIO=?";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idSocio);

			ResultSet resultado = ps.executeQuery();
			if (resultado.next()) {
				socio = new Socio(idSocio, resultado.getString("EMAIL"), resultado.getString("NOMBRE"),
						resultado.getString("DIRECCION"), resultado.getInt("VERSION"));
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		return socio;
	}

	public ArrayList<Socio> select(Connection con) {

		ArrayList<Socio> listadoSocios = new ArrayList<Socio>();

		String sql = "SELECT * FROM SOCIO";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);

			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Socio socioActual = new Socio(resultado.getInt("IDSOCIO"), resultado.getString("EMAIL"),
						resultado.getString("NOMBRE"), resultado.getString("DIRECCION"), resultado.getInt("VERSION"));
				listadoSocios.add(socioActual);
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listadoSocios;

	}

}
