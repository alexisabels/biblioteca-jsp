package entidades;

import java.time.LocalDateTime;

public class Prestamo {
private Ejemplar ejemplar;
private Socio socio;
private LocalDateTime fechaPrestamo;
private LocalDateTime fechaLimiteDevolucion;

public Ejemplar getEjemplar() {
	return ejemplar;
}
public void setEjemplar(Ejemplar ejemplar) {
	this.ejemplar = ejemplar;
}
public Socio getSocio() {
	return socio;
}
public void setSocio(Socio socio) {
	this.socio = socio;
}
public LocalDateTime getFechaPrestamo() {
	return fechaPrestamo;
}
public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
	this.fechaPrestamo = fechaPrestamo;
}
public LocalDateTime getFechaLimiteDevolucion() {
	return fechaLimiteDevolucion;
}
public void setFechaLimiteDevolucion(LocalDateTime fechaLimiteDevolucion) {
	this.fechaLimiteDevolucion = fechaLimiteDevolucion;
}
public Prestamo(Ejemplar ejemplar, Socio socio, LocalDateTime fechaPrestamo, LocalDateTime fechaLimiteDevolucion) {
	super();
	this.ejemplar = ejemplar;
	this.socio = socio;
	this.fechaPrestamo = fechaPrestamo;
	this.fechaLimiteDevolucion = fechaLimiteDevolucion;
}
public Prestamo(Ejemplar ejemplar, Socio socio) {
	this.ejemplar = ejemplar;
	this.socio = socio;
}
@Override
public String toString() {
	return "\n Prestamo [" + ejemplar.toString() + ", " + socio.toString() + ", fechaPrestamo=" + fechaPrestamo
			+ ", fechaLimiteDevolucion=" + fechaLimiteDevolucion + "]";
}

}
