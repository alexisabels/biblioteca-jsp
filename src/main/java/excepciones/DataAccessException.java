package excepciones;
import java.sql.SQLException;

public class DataAccessException extends SQLException{
	private String errorCode;
	
	
	public DataAccessException (String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode=errorCode;
		
	
	}
}
