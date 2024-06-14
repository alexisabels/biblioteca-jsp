package entidades;

import java.time.LocalDateTime;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

public class Autor {
	private int idAutor;
	private String nombre;
	private LocalDateTime fechaNacimiento;
	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	@Override
	public String toString() {
		return "Autor [idAutor=" + idAutor + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + "]";
	}
	public Autor(int idAutor, String nombre, LocalDateTime localDateTime) {
		super();
		this.idAutor = idAutor;
		this.nombre = nombre;
		this.fechaNacimiento = localDateTime;
	}
	
	
}
