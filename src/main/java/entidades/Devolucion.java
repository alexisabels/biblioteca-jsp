package entidades;

import java.time.LocalDateTime;

public class Devolucion {
private Ejemplar ejemplar;
private Socio socio;
private LocalDateTime fechaPrestamo;
private LocalDateTime fechaDevolucion;

public Devolucion(Ejemplar ejemplar, Socio socio, LocalDateTime fechaPrestamo, LocalDateTime fechaDevolucion) {
	
	this.ejemplar = ejemplar;
	this.socio = socio;
	this.fechaPrestamo = fechaPrestamo;
	this.fechaDevolucion = fechaDevolucion;
}

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
public LocalDateTime getFechaDevolucion() {
	return fechaDevolucion;
}
public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
	this.fechaDevolucion = fechaDevolucion;
}
@Override
public String toString() {
	return "Devolucion [ejemplar=" + ejemplar + ", socio=" + socio + ", fechaPrestamo=" + fechaPrestamo
			+ ", fechaDevolucion=" + fechaDevolucion + "]";
}

}
