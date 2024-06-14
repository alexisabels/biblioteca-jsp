package triggers;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;


import dao.DAODevolucion;
import dao.DAOEjemplar;
import dao.DAOPrestamo;
import dao.DAOSocio;
import dao.DAOSocioPenalizado;
import entidades.Devolucion;
import entidades.Ejemplar;
import entidades.Prestamo;
import entidades.Socio;
import entidades.SocioPenalizado;
import excepciones.BorrowingRequestException;
import excepciones.DevolverPrestamosTarde;
import excepciones.ReturnRequestException;

public class AnotarDatosDevolucion {
	
    public static ArrayList<Prestamo> sacarPrestamos(ArrayList<Ejemplar> ejemplares, Connection con){
		DAOPrestamo daoPrestamo = new DAOPrestamo();
    	ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
    	for (Ejemplar ejemplar : ejemplares) {
    		Prestamo prestamoSelect = daoPrestamo.select(ejemplar.getIdejemplar(), con);
			prestamos.add(prestamoSelect);
		}
    	return prestamos;
    }
    
    			
    public void devolverPrestamos(ArrayList<Prestamo> prestamos, Connection con) throws SQLException{
		DAODevolucion daoDevolucion = new DAODevolucion();
		DAOPrestamo daoPrestamo = new DAOPrestamo();
    	for (Prestamo prestamo : prestamos) { // TERCERO
			daoDevolucion.insert(new Devolucion(prestamo.getEjemplar(), prestamo.getSocio(),
					prestamo.getFechaPrestamo(), LocalDateTime.now()), con);
			daoPrestamo.delete(prestamo, con);
			System.out.println("Préstamo devuelto correctamente");
		}
    	
    }
    
    
	public void solicitarDevolucion(ArrayList<Integer> idejemplares, Connection con) throws ReturnRequestException, SQLException, DevolverPrestamosTarde {
		Comprobantes comprobante = new Comprobantes();
	
		// EXISTENCIA DE EJEMPLAR, BAJA Y SABER QUE NO ESTA PRESTADO
		comprobante.compruebaDisponibilidadDevolucion(idejemplares, con);
		
		DAOEjemplar daoe = new DAOEjemplar();
		ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
		for (int i : idejemplares) {
			ejemplares.add(daoe.select(i, con));
		}
		// COMPROBAR FECHA PARA SABER SI EXCEDIÓ EL LIMITE
		ArrayList<Prestamo> prestamos = sacarPrestamos(ejemplares, con);
		ArrayList<Socio> sociosPenalizados = new ArrayList<Socio>();
		for (Prestamo prestamo : prestamos) {
			Socio socio = prestamo.getSocio();
			try {
				comprobante.compruebaTardon(socio, con);
			} catch (BorrowingRequestException e) {
				// TODO Auto-generated catch block
				sociosPenalizados.add(socio);
				e.printStackTrace();
			}
		}
		// ELIMINACION DE LA TABLA PRESTAMO E INSERTAR EN DEVOLUCION
        devolverPrestamos(prestamos, con);	
        
        
         if(sociosPenalizados.size()>0) {
        	 throw new DevolverPrestamosTarde("Devolución realizada correctamente. Se penalizaron: "+ sociosPenalizados.toString());
         }
		
	}
}
