package excepciones;
import java.sql.SQLException;
//ORA-00933 error de sql al final de la sentencia (SQL Command Not Properly Ended)
//ORA-01722 (Invalid Number): Se produce cuando se intenta realizar una operación matemática en una columna que no contiene datos numéricos.
//ORA-0900 (error sintaxis)
public class QueryExecutionException extends SQLException{

	private String errorCode;

	public QueryExecutionException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode=errorCode; 
	}
}
