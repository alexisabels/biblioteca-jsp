package excepciones;

import java.sql.SQLException;

public class DatabaseConnectionException extends SQLException {
	private String errorCode;
	public DatabaseConnectionException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode=errorCode; 
	}
}
