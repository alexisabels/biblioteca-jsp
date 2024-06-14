package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Ejemplar;
import entidades.Libro;
import tools.LanzadorExcepciones;

public class DAOEjemplar {

	public void insert(Ejemplar ejemplar, Connection con) throws SQLException {



		String sql = "INSERT INTO EJEMPLAR VALUES(S_EJEMPLAR.NEXTVAL,?,?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ejemplar.getISBN());
			ps.setString(2, mapBooleanToChar(ejemplar.isBaja()) + "");

			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}
	}

	public void delete(Ejemplar ejemplar, Connection con) throws SQLException{


		String sql = "DELETE FROM EJEMPLAR WHERE IDEJEMPLAR=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, ejemplar.getIdejemplar());
			ps.executeUpdate();

			ps.close();
			
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}
	}

	public void delete(int idEjemplar, Connection con) throws SQLException{ 


		String sql = "DELETE FROM EJEMPLAR WHERE IDEJEMPLAR=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idEjemplar);
			ps.executeUpdate();

			ps.close();
			
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);

			e.printStackTrace();
		}
	}

	public Ejemplar select(int idEjemplar, Connection con){
		Ejemplar ejemplar = null;


		String sql = "SELECT * FROM EJEMPLAR WHERE IDEJEMPLAR=?";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idEjemplar);

			ResultSet resultado = ps.executeQuery();
			if (resultado.next()) {
				ejemplar = new Ejemplar(idEjemplar, resultado.getString("ISBN"),
						mapCharToBoolean(resultado.getString("BAJA").charAt(0)));
			}
			ps.close();
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return ejemplar;
	}

	public ArrayList<Ejemplar> select(Connection con) throws SQLException{

		ArrayList<Ejemplar> listadoEjemplares = new ArrayList<Ejemplar>();


		String sql = "SELECT * FROM EJEMPLAR";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);

			ResultSet resultado = ps.executeQuery();
			while (resultado.next()) {
				Ejemplar ejemplarActual = new Ejemplar(resultado.getInt("IDEJEMPLAR"), resultado.getString("ISBN"),
						mapCharToBoolean(resultado.getString("BAJA").charAt(0)));
				listadoEjemplares.add(ejemplarActual);
			}
			ps.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			LanzadorExcepciones.ElegirExcepcionALanzar(e);

		}
		return listadoEjemplares;
		
	}
	//este no vale pero no viene mal tenerlo
	public int selectUltimoID(Connection con) throws SQLException{
int ultimoID = 0;

		String sql = "SELECT MAX(IDEJEMPLAR) FROM EJEMPLAR";
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(sql);

			ResultSet resultado = ps.executeQuery();
			if (resultado.next()) {
				ultimoID = resultado.getInt(1);
			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
		}
		return ultimoID;
	}
	public void update(Ejemplar ejemplar, Connection con) throws SQLException{



		String sql = "UPDATE EJEMPLAR SET ISBN=?, BAJA=? WHERE IDEJEMPLAR=?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ejemplar.getISBN());
			ps.setString(2, String.valueOf(mapBooleanToChar(ejemplar.isBaja())));
			ps.setInt(3, ejemplar.getIdejemplar());

			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			LanzadorExcepciones.ElegirExcepcionALanzar(e);
			e.printStackTrace();
		}
	}
	public char mapBooleanToChar(boolean baja) {
		if (baja) {
			return 'S';
		}
		return 'N';
	}

	public boolean mapCharToBoolean(char c) {
		if (c == 'S') {
			return true;
		} else {
			return false;
		}
	}
	
}
