package triggers;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import conexion.Conexion;
import dao.DAOEjemplar;
import dao.DAOPrestamo;
import dao.DAOSocio;
import dao.DAOSocioPenalizado;
import entidades.Ejemplar;
import entidades.Prestamo;
import entidades.Socio;
import entidades.SocioPenalizado;
import excepciones.BorrowingRequestException;
import excepciones.ReturnRequestException;

public class Comprobantes {

	public void compruebaIDSocio(int idsocio, Connection con) throws BorrowingRequestException {
		DAOSocio daoSocio = new DAOSocio();
		if (daoSocio.select(idsocio, con) == null) {
			throw new BorrowingRequestException("No existe ese socio");
		}

	}

//	public void compruebaDisponibilidadDevolucion(ArrayList<Ejemplar> ejemplares, Connection con) throws ReturnRequestException {
//		DAOEjemplar daoEjemplar = new DAOEjemplar();
//		DAOPrestamo daoPrestamo = new DAOPrestamo();
//
//		for (Ejemplar ejemplar : ejemplares) {
//			Ejemplar ejemplarSelect = daoEjemplar.select(ejemplar.getIdEjemplar(), con);
//			if (ejemplarSelect == null) {
//				throw new ReturnRequestException("No existe ese ejemplar");
//
//			}
//			if (ejemplarSelect.isBaja()) {
//				throw new ReturnRequestException("El ejemplar está de baja");
//
//			}
//			Prestamo prestamoSelect = daoPrestamo.select(ejemplarSelect.getIdEjemplar(),con);
//			if (prestamoSelect == null) {
//				throw new ReturnRequestException("Ese ejemplar no está prestado");
//			}
////			if (prestamoSelect.getSocio().getIdSocio()!=prestamo.getSocio().getIdSocio()) {
////				throw new ReturnRequestException("Ese ejemplar no es de ese socio. Es de "+prestamoSelect.getSocio().getNombre());
////			} Este comprobante creemos que no nos hace falta.
//				
//		}
//	}
	public void compruebaDisponibilidadDevolucion(ArrayList<Integer> idejemplares, Connection con) throws ReturnRequestException, SQLException {
		DAOEjemplar daoEjemplar = new DAOEjemplar();
		DAOPrestamo daoPrestamo = new DAOPrestamo();

		for (int idejemplar : idejemplares) {
			Ejemplar ejemplarSelect = daoEjemplar.select(idejemplar, con);
			if (ejemplarSelect == null) {
				throw new ReturnRequestException("No existe ese ejemplar");

			}
			if (ejemplarSelect.isBaja()) {
				throw new ReturnRequestException("El ejemplar está de baja");

			}
			Prestamo prestamoSelect = daoPrestamo.select(ejemplarSelect.getIdejemplar(),con);
			if (prestamoSelect == null) {
				throw new ReturnRequestException("Ese ejemplar no está prestado");
			}
//			if (prestamoSelect.getSocio().getIdSocio()!=prestamo.getSocio().getIdSocio()) {
//				throw new ReturnRequestException("Ese ejemplar no es de ese socio. Es de "+prestamoSelect.getSocio().getNombre());
//			} Este comprobante creemos que no nos hace falta.
				
		}
	}

//	public void compruebaDisponibilidadPrestamo(ArrayList<Prestamo> prestamos, Connection con)
//			throws BorrowingRequestException {
//		DAOEjemplar daoEjemplar = new DAOEjemplar();
//		DAOPrestamo daoPrestamo = new DAOPrestamo();
//		for (Prestamo prestamo : prestamos) {
//			Ejemplar ejemplarSelect = daoEjemplar.select(prestamo.getEjemplar().getIdEjemplar(), con);
//			if (ejemplarSelect == null) { // QUINTO HECHO MANIN
//				throw new BorrowingRequestException("No existe ese ejemplar");
//
//			} // SEXTO HECHO MANIN
//			if (ejemplarSelect.isBaja()) {
//				throw new BorrowingRequestException("El ejemplar está de baja");
//
//			} // SÉPTIMO HECHO MANIN
//			if (daoPrestamo.select(ejemplarSelect.getIdEjemplar(), con) != null) {
//				throw new BorrowingRequestException("Ese ejemplar ya está prestado");
//			}
//		}
//	}
	public void compruebaDisponibilidadPrestamo(ArrayList<Integer> idPrestamos, Connection con)
			throws BorrowingRequestException {
		DAOEjemplar daoEjemplar = new DAOEjemplar();
		DAOPrestamo daoPrestamo = new DAOPrestamo();
		for (int idejemplar : idPrestamos) {
			Ejemplar ejemplarSelect = daoEjemplar.select(idejemplar, con);
			if (ejemplarSelect == null) { // QUINTO HECHO 
				throw new BorrowingRequestException("No existe ese ejemplar");

			} // SEXTO HECHO 
			if (ejemplarSelect.isBaja()) {
				throw new BorrowingRequestException("El ejemplar está de baja");

			} // SÉPTIMO HECHO 
			if (daoPrestamo.select(ejemplarSelect.getIdejemplar(), con) != null) {
				throw new BorrowingRequestException("Ese ejemplar ya está prestado");
			}
		}
	}

	/**
	 * Este método comprueba si el socio se pasó de plazo con algun prestamo y lo
	 * penaliza
	 * 
	 *
	 * @param socio será el socio a comprobar
	 * @throws BorrowingRequestException
	 * 
	 * 
	 */
	public void compruebaTardon(Socio socio, Connection con) throws BorrowingRequestException {
		DAOPrestamo daoPrestamo = new DAOPrestamo();
		DAOSocioPenalizado daoSocioPenalizado = new DAOSocioPenalizado();
		ArrayList<Prestamo> prestamosSocio = daoPrestamo.select(socio, con);
		for (Prestamo prestamo : prestamosSocio) { // CUARTO (que el socio no tenga un ejemplar prestado que deba
													// haber sido devuelto)

			if (prestamo.getFechaLimiteDevolucion().isBefore(LocalDateTime.now())) {
				SocioPenalizado sp = new SocioPenalizado(socio, LocalDateTime.now().plusDays(15));
				if (daoSocioPenalizado.select(sp.getSocio().getIdSocio(), con) == null) {
					daoSocioPenalizado.insert(sp, con);

				} else {
					daoSocioPenalizado.update(sp, con);
				}
				throw new BorrowingRequestException("Tenías un préstamo pasado de fecha. Se te ha penalizado.");
			}

		}
	}

	public void compruebaPenalizado(Socio socio, Connection con) throws BorrowingRequestException {
		DAOSocioPenalizado daoSocioPenalizado = new DAOSocioPenalizado();
		SocioPenalizado socioPenalizado = daoSocioPenalizado.select(socio.getIdSocio(), con);
		if (socioPenalizado != null && socioPenalizado.getLimitePenalizacion().isAfter(LocalDateTime.now())) {
			throw new BorrowingRequestException("El socio está penalizado hasta el: "
					+ printLocalDateTime(socioPenalizado.getLimitePenalizacion()));
		}
	} //tanto si no está en socioPenalizado como si está penalizado todavía, lo penaliza usando getLimitePenalizacion 

	// INSERTAYCOMPRUEBA (ESTE ESTÁ EN DESUSO)
	public void compruebaAdquisicon(ArrayList<Prestamo> prestamos, Connection con) throws BorrowingRequestException {
		DAOPrestamo daoPrestamo = new DAOPrestamo();

		ArrayList<Prestamo> prestamosSocio = daoPrestamo.select(prestamos.get(0).getSocio(), con);
		AnotarDatosPrestamos adp = new AnotarDatosPrestamos();

		boolean duplicado = false;
		for (Prestamo prestamo : prestamos) {
			for (Prestamo prestamosInsertados : prestamosSocio) {
				if (prestamo.getEjemplar().getISBN().equals(prestamosInsertados.getEjemplar().getISBN())) {
					duplicado = true;
					throw new BorrowingRequestException(
							"Ya lo tienes manin, libro:" + prestamo.getEjemplar().toString());
				}
			}
			if (!duplicado) {
				AnotarDatosPrestamo(prestamo);
				daoPrestamo.insert(prestamo, con);
			}

			duplicado = false;
		}
	}
//COMPRUEBAN SI TIENES YA ESE LIBRO (AUNQUE SEA UN DISTINTO EJEMPLAR)
	// SOLO COMPRUEBA y devuelve para insertar!!!
	public Prestamo compruebaAdquisicion(Prestamo prestamo, Connection con) throws BorrowingRequestException {
		DAOPrestamo daoPrestamo = new DAOPrestamo();

		ArrayList<Prestamo> prestamosSocio = daoPrestamo.select(prestamo.getSocio(), con);

		for (Prestamo prestamosInsertados : prestamosSocio) {
			if (prestamo.getEjemplar().getISBN().equals(prestamosInsertados.getEjemplar().getISBN())) {
				throw new BorrowingRequestException("Ya lo tienes manin, libro:" + prestamo.getEjemplar().toString());

			}
		}
		return AnotarDatosPrestamo(prestamo);
	}

	public void compruebaNumeroPrestamos(ArrayList<Prestamo> prestamos, Connection con)
			throws BorrowingRequestException {
		DAOPrestamo daoPrestamo = new DAOPrestamo();
		ArrayList<Prestamo> prestamosSocio = daoPrestamo.select(prestamos.get(0).getSocio(), con);
		int sumaPrestamos = prestamos.size() + prestamosSocio.size();
		if (sumaPrestamos > 5) {
			throw new BorrowingRequestException(
					"El límite de libros que puedes tener son 5 (Deseas " + sumaPrestamos + " libros)");
		}

	}

	public static Prestamo AnotarDatosPrestamo(Prestamo prestamo) {

		if (prestamo.getFechaPrestamo() == null) {
			prestamo.setFechaPrestamo(LocalDateTime.now());
			System.out.println(printLocalDateTime(prestamo.getFechaPrestamo()));
		}
		prestamo.setFechaLimiteDevolucion(getFechaDevolucion(prestamo.getFechaPrestamo()));
		System.out.println(printLocalDateTime(prestamo.getFechaLimiteDevolucion()));

		return prestamo;
	}

	public static LocalDateTime getFechaDevolucion(LocalDateTime fecha) {

		LocalDateTime nuevaFecha = fecha.plusDays(3);

		// Verifica si la nueva fecha es sábado o domingo

		DayOfWeek diaSemana = nuevaFecha.getDayOfWeek();

		if (diaSemana == DayOfWeek.SATURDAY) {
			nuevaFecha = nuevaFecha.plusDays(2);
		} else if (diaSemana == DayOfWeek.SUNDAY) {
			nuevaFecha = nuevaFecha.plusDays(1);
		}

		return nuevaFecha;
	}

	public static String printLocalDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String formattedDateTime = localDateTime.format(formatter);
		return formattedDateTime;
	}

}
