package excepciones;

import java.sql.SQLException;

public class ConstraintViolationException extends SQLException {
	private String errorCode;

public ConstraintViolationException(String message, Throwable cause, String errorCode) {
	super(message,cause);
	this.errorCode=errorCode; 
	/*VIOLACION DE RESTRICCION UNICA ORA-00001
	VIOLACION DE RESTRICCION DE CLAVE EXTERNA ORA-02291
	VIOLACION DE CLAVE EXTERNA EN TABLA REFERENCIADA ORA-02292
	
	*/
	
	
}

}
