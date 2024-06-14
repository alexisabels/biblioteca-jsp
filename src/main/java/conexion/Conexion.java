package conexion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import excepciones.ConstraintViolationException;
import excepciones.DataAccessException;
import excepciones.DatabaseConnectionException;
import excepciones.DuplicateEntityException;
import excepciones.QueryExecutionException;

import java.sql.DatabaseMetaData;

import oracle.jdbc.pool.OracleDataSource;
import tools.LanzadorExcepciones;

public class Conexion {
	public Conexion() {

	};

	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:S2DAWBIBLIOTECA16/S2DAWBIBLIOTECA16@80.28.158.14:1521:oradai";
		Connection con = null;
		OracleDataSource ods;

		try {
			ods = new OracleDataSource();
			ods.setURL(url);
			con = ods.getConnection();
			DatabaseMetaData meta = con.getMetaData();
			System.out.println("JDBC driver version is: " + meta.getDriverVersion());
			System.out.println("Data Source está definido y conexión establecida.");
		} catch (SQLException e) {

			try {
				LanzadorExcepciones.ElegirExcepcionALanzar(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}

		return con;
	}

}
