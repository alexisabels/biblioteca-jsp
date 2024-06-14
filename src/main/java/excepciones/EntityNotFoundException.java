package excepciones;

import java.sql.SQLException;
//dice que no hemos recibido resultados de nuestra consulta
public class EntityNotFoundException extends SQLException {
private String errorCode;
public EntityNotFoundException(String message, Throwable cause, String errorCode) {
	super(message, cause);
	this.errorCode=errorCode; 

}

}
