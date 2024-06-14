package entidades;

import java.time.LocalDateTime;

public class SocioPenalizado {
	private Socio socio;
	private LocalDateTime limitePenalizacion;
	public SocioPenalizado(Socio socio, LocalDateTime limitePenalizacion) {
		super();
		this.socio = socio;
		this.limitePenalizacion = limitePenalizacion;
	}
	


	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	public LocalDateTime getLimitePenalizacion() {
		return limitePenalizacion;
	}
	public void setLimitePenalizacion(LocalDateTime limitePenalizacion) {
		this.limitePenalizacion = limitePenalizacion;
	}

	@Override
	public String toString() {
		return "SocioPenalizado [socio=" + socio.toString() + ", limitePenalizacion=" + limitePenalizacion + "]";
	}
	
}

