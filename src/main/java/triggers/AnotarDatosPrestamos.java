package triggers;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dao.DAOEjemplar;
import dao.DAOPrestamo;
import dao.DAOSocio;
import dao.DAOSocioPenalizado;
import entidades.Ejemplar;
import entidades.Prestamo;
import entidades.Socio;
import entidades.SocioPenalizado;
import excepciones.BorrowingRequestException;

public class AnotarDatosPrestamos {
	
public ArrayList<Prestamo> crearPrestamos(Socio socio, ArrayList<Ejemplar> ejemplares){
	ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
	for (Ejemplar ejemplar : ejemplares) {
		prestamos.add(new Prestamo(ejemplar, socio));
	}
	return prestamos;
}

public void SolicitudPrestamo(int idSocio, ArrayList<Integer> idejemplares, Connection con) throws BorrowingRequestException, SQLException {
	Comprobantes comprobante = new Comprobantes();
	//PRIMERO HECHO MANIN ( que exista el socio manin)
	comprobante.compruebaIDSocio(idSocio, con);
	//AQUI VA QUINTO SEXTO Y SEPTIMO
		comprobante.compruebaDisponibilidadPrestamo(idejemplares, con);
	DAOSocio daos = new DAOSocio();
	Socio socio = daos.select(idSocio, con);
	DAOEjemplar daoe = new DAOEjemplar();
	ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
	for (int i : idejemplares) {
		ejemplares.add(daoe.select(i, con));
	}
	ArrayList<Prestamo> prestamossol = crearPrestamos(socio, ejemplares);
	
	//obtenemos el socio en cuestión
	
	DAOPrestamo daoPrestamo = new DAOPrestamo();

	//COMPROBAR SI VA A TENER MÁS DE 5 PRESTAMOS
	comprobante.compruebaNumeroPrestamos(prestamossol, con);
	
    // AQUI VA EL CUARTO
  comprobante.compruebaTardon(socio, con);
   // AQUI VA EL SEGUNDO
  comprobante.compruebaPenalizado(socio, con);
  //TERCERO E INSERTAR(ANTIGUO)
 
  
  //NUEVA INSERCION
  //comprueba el isbn 
    for (Prestamo prestamo : prestamossol) {
		comprobante.compruebaAdquisicion(prestamo, con);
		daoPrestamo.insert(prestamo, con);
		System.out.println("Ejemplar prestado correctamente");
	}



}
}
