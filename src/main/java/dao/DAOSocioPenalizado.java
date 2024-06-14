package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Socio;
import entidades.SocioPenalizado;

public class DAOSocioPenalizado {
	public void insert(SocioPenalizado socioPenalizado, Connection con) {


		String sql = "INSERT INTO SOCIOPENALIZADO VALUES(?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, socioPenalizado.getSocio().getIdSocio());
			ps.setObject(2, socioPenalizado.getLimitePenalizacion());
	

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void update(SocioPenalizado socioPenalizado, Connection con) {

		String sql = "UPDATE SOCIOPENALIZADO SET LIMITEPENALIZACION=? WHERE IDSOCIO=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(2, socioPenalizado.getSocio().getIdSocio());
			ps.setObject(1, socioPenalizado.getLimitePenalizacion());
			

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(SocioPenalizado socioPenalizado, Connection con) {

		String sql = "DELETE FROM SOCIOPENALIZADO WHERE IDSOCIO=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, socioPenalizado.getSocio().getIdSocio());
			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(int idSocio, Connection con) {

		String sql = "DELETE FROM SOCIOPENALIZADO WHERE IDSOCIO=?";
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
	public SocioPenalizado select(int idSocio, Connection con) {
		SocioPenalizado socioPenalizado = null;

		String sql = "SELECT s.email, s.nombre, s.direccion, s.version, sp.limitepenalizacion FROM SOCIO s join SOCIOPENALIZADO sp ON sp.idsocio=s.idsocio WHERE s.IDSOCIO=?";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idSocio);

			ResultSet resultado = ps.executeQuery();
			if (resultado.next()) {
				socioPenalizado = new SocioPenalizado(new Socio(idSocio, resultado.getString("EMAIL"), 
					resultado.getString("NOMBRE"), resultado.getString("DIRECCION"), 
						resultado.getInt("VERSION")), resultado.getTimestamp("LIMITEPENALIZACION").toLocalDateTime());
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		return socioPenalizado;
	}
	public ArrayList<SocioPenalizado> select(Connection con) {
		SocioPenalizado socioPenalizado = null;
		ArrayList<SocioPenalizado> listaSociosPenalizados = new ArrayList<SocioPenalizado>();

		
		String sql = "SELECT s.email, s.nombre, s.direccion, s.version, sp.limitepenalizacion FROM SOCIO s join SOCIOPENALIZADO sp WHERE sp.IDSOCIO=?";

		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);

			ResultSet resultado = ps.executeQuery();
			while(resultado.next()) {
				socioPenalizado = new SocioPenalizado(new Socio(resultado.getInt("IDSOCIO"), resultado.getString("EMAIL"), 
					resultado.getString("NOMBRE"), resultado.getString("DIRECCION"), 
						resultado.getInt("VERSION")), resultado.getTimestamp("LIMITEPENALIZACION").toLocalDateTime());
			listaSociosPenalizados.add(socioPenalizado);
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		return listaSociosPenalizados;
	}
	
	
}
