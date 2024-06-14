package tools;

import java.sql.SQLException;


import excepciones.ConstraintViolationException;
import excepciones.DataAccessException;
import excepciones.DatabaseConnectionException;
import excepciones.DuplicateEntityException;
import excepciones.QueryExecutionException;

public class LanzadorExcepciones {
public static void ElegirExcepcionALanzar(SQLException e) throws SQLException {
		
		String causaError=e.getMessage().substring(0,9);
	//DatabaseConnectionException
		switch(causaError) {
		case "ORA-12514":
			throw new DatabaseConnectionException("Puerto incorrecto", e.getCause(), causaError);

		case "ORA-01017":
			throw new DatabaseConnectionException("No se puede establecer la conexion", e.getCause(), causaError);
		
	//ConstraintViolationException
		
		case "ORA-02291":
			throw new ConstraintViolationException("Violacion de restriccion de clave externa", e.getCause(), causaError);
		case "ORA-02292":
			throw new ConstraintViolationException("Violacion de clave externa en tabla referenciada", e.getCause(), causaError);
	//QueryExecutionException
		case "ORA-00933":
			throw new QueryExecutionException("Error de sql al final de la sentencia", e.getCause(), causaError);
		case "ORA-01722":
			throw new QueryExecutionException("Intento de operación matemática en una columna sin datos numéricos", e.getCause(), causaError);
		case "ORA-00900":
			throw new QueryExecutionException("Error de sintaxis", e.getCause(), causaError);
	//DuplicateEntityException
		case "ORA-00001":
			throw new DuplicateEntityException("Violacion de restriccion unica", e.getCause(), causaError);
		case "ORA-00069":
			throw new DuplicateEntityException("Error creando una restricción única en una columna que ya contiene datos duplicados", e.getCause(), causaError);
	//DataAccessException
		case "ORA-00936":
		case "ORA-00942":
			throw new DataAccessException("Error acceso a datos", e.getCause(), causaError);
		default:
			break;
			 
		}
		
	}
}
