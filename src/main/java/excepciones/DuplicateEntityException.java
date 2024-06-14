package excepciones;

import java.sql.SQLException;
// case "ORA-00001":
//throw new ConstraintViolationException("Violación de restricción única (PK o similar)", e.getCause(), causaError);
//00069 intento de crear una restricción única en una columna que ya contiene datos duplicados
//case "ORA-00069":
//throw new DuplicateEntityException("", e.getCause(), causaError);

public class DuplicateEntityException extends SQLException{
private String errorCode;


		public DuplicateEntityException(String message, Throwable cause,String errorCode) {
			super(message, cause);
			this.errorCode=errorCode; 
		}
}
