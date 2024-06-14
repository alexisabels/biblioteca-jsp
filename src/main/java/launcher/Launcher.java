package launcher;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.Conexion;
import dao.DAOLibro;
import dao.DAOPrestamo;
import dao.DAOSocio;
import dao.DAOSocioPenalizado;
import entidades.Autor;
import entidades.Devolucion;
import entidades.Ejemplar;
import entidades.Libro;
import entidades.Prestamo;
import entidades.Socio;
import entidades.SocioPenalizado;
import excepciones.BorrowingRequestException;
import excepciones.DevolverPrestamosTarde;
import excepciones.QueryExecutionException;
import excepciones.ReturnRequestException;
import triggers.AnotarDatosDevolucion;
import triggers.AnotarDatosPrestamos;
import dao.DAOAutor;
import dao.DAODevolucion;
import dao.DAOEjemplar;
import java.time.LocalDateTime;

public class Launcher {
	public static void main(String[] args) throws SQLException, BorrowingRequestException, ReturnRequestException, DevolverPrestamosTarde {

		Connection con = Conexion.getConnection();
		DAOPrestamo daoPrestamo = new DAOPrestamo();
		DAOEjemplar daoEjemplar = new DAOEjemplar();
		DAOSocio daoSocio = new DAOSocio();
		AnotarDatosDevolucion add = new AnotarDatosDevolucion();
		AnotarDatosPrestamos adp = new AnotarDatosPrestamos();
//		Socio socio = daoSocio.select(9, con);
//		int idSocio = socio.getIdSocio();
		Ejemplar ejemplar = daoEjemplar.select(9, con);
//		Prestamo prestamo = new Prestamo(ejemplar, socio);
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
		ArrayList<Integer> idEjemplares = new ArrayList<Integer>();
		ejemplar.setIdejemplar(9);
		idEjemplares.add(ejemplar.getIdejemplar());
//		prestamo.setEjemplar(ejemplar);
//		socio.setIdSocio(551);
//		prestamos.add(prestamo);	
		
//	adp.SolicitudPrestamo(idSocio, idEjemplares, con);

		add.solicitarDevolucion(idEjemplares, con);
      
       
       
       
       
       
       
       
       
       
       
		// ANTIGUO
//		DAOAutor daoAutor = new DAOAutor();	
//		DAOLibro daoLibro = new DAOLibro();
//		DAOEjemplar daoEjemplar = new DAOEjemplar();
//		DAOSocio daoSocio = new DAOSocio();
//		DAOSocioPenalizado daoSocioPenalizado = new DAOSocioPenalizado();
//		DAOPrestamo daoPrestamo = new DAOPrestamo();
//
////		Ejemplar ejemplar = new Ejemplar(daoEjemplar.selectUltimoID()+1,"",true);
////		Ejemplar ejemplar2 = new Ejemplar("",true);
////		Socio socio = new Socio ("UPDATED2@IES-AZARQUIEL.ES", "SOCIO updated2", "C/socio nueva2", 2);
//ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
//
//AnotarDatosPrestamos adp = new AnotarDatosPrestamos();
//AnotarDatosDevolucion add = new AnotarDatosDevolucion();
////PASARLE CON
//Socio socio = daoSocio.select(10);
//Ejemplar ejemplar = daoEjemplar.select(8);
////Prestamo prestamo = new Prestamo(ejemplar, socio, LocalDateTime.of(2010, 1, 1, 0, 0), LocalDateTime.of(2010, 2, 1, 0, 0));
////daoPrestamo.insert(prestamo);
//Prestamo prestamo = new Prestamo(ejemplar, socio);
//prestamos = daoPrestamo.select(socio);
////prestamos.add(prestamo);
////prestamos.add(prestamo);
//add.solicitarDevolucion(prestamos);
////cp.controlPrestamo(prestamos);
////System.out.println(ejemplar);
//
////System.out.println(ejemplar2);
////System.out.println(daoEjemplar.selectUltimoID());
//		//		daoLibro.update(libro);
////		Libro libro = daoLibro.select("016");
////		System.out.println(libro.getISBN()+" "+libro.getTitulo()+" "+libro.getIdAutor());
//
////		ArrayList<Libro> librosDevueltos=daoLibro.select();
////		for (Libro libro : librosDevueltos) {
////			
////		}
////		
////		System.out.println(librosDevueltos.toString());
//		
////		ArrayList<Libro> librosAutor=daoLibro.selectLibrosAutor(6);
////		
////		System.out.println(librosAutor.toString());
////		DAOLibro daoLibro = new DAOLibro();
////
////		for (Libro l : daoLibro.selectLibrosPorTitulo("la")) {
////			System.out.println(l.toString());
////		}
////		System.out.println(daoLibro.selectLibrosPorNombre("MILIBRO1"));
//		//EJEMPLAR
////		Ejemplar ejemplar = new Ejemplar(63, false);
////		daoEjemplar.delete(62);
////		DAOSocio daoSocio = new DAOSocio();
////		Socio socio = new Socio ("UPDATED2@IES-AZARQUIEL.ES", "SOCIO updated2", "C/socio nueva2", 2);
////		daoSocio.insert(socio);
////		daoSocio.delete(19);
////		System.out.println(daoSocio.select(12));
////		for (Socio s : daoSocio.select()) {
////			System.out.println(s);
////		}
////		//		System.out.println(daoSocio.select());
//
////DAOSOCIOPENALIZADO
//
//
////	Socio socio= daoSocio.select(12);
////	SocioPenalizado sociopenalizado = new SocioPenalizado (socio, LocalDateTime.now().plusDays(10));
////	daoSocioPenalizado.insert(sociopenalizado);	
////	daoSocioPenalizado.update(sociopenalizado);
////	daoSocioPenalizado.delete(sociopenalizado);
////	daoSocioPenalizado.delete(18);
////System.out.println(daoSocioPenalizado.select(15));
////for (SocioPenalizado sp : daoSocioPenalizado.select()) {
////	System.out.println(sp);
////}
//////System.out.println(daoSocioPenalizado.select());
////	
//////	}
//	//PRESTAMO
//
//DAODevolucion daoDevolucion = new DAODevolucion();
////Socio socio = daoSocio.select(5);
////Ejemplar ejemplar = daoEjemplar.select(5);
////Prestamo prestamo = new Prestamo(ejemplar,socio,LocalDateTime.now(), LocalDateTime.now().plusDays(10));
////daoPrestamo.insert(prestamo);
//
////Socio socio = daoSocio.select(5);
////Ejemplar ejemplar = daoEjemplar.select(6);
////Prestamo prestamo = new Prestamo(ejemplar,socio,LocalDateTime.now(), LocalDateTime.now().plusDays(10));
////daoPrestamo.delete(6);
////daoPrestamo.update(prestamo);
////daoPrestamo.insert(prestamo);
////Socio socio = daoSocio.select(3);
////System.out.println(daoPrestamo.select(socio));
//
////for (Devolucion d : daoDevolucion.selectPorIdSocio(6)) {
////	System.out.println(d.toString());
////}
//
////for (Devolucion d : daoDevolucion.selectPorRangoFechas() {
////System.out.println(d.toString());
////}
//
////LocalDateTime fecha = LocalDateTime.now();
////for (Devolucion d : daoDevolucion.selectPorRangoFechas(fecha.minusDays(150), fecha)
////) {
////	System.out.println(d.toString());
////}
////for (Libro l : daoDevolucion.selectRanking()) {
////System.out.println(l.toString());
////}
///*
//						  CONTROL DE EXCEPCIONES EN DAOAUTOR
//*/
//
////INSERT
////Autor autor = daoAutor.select(1); //CAMBIAR EL DAOAUTOR (NEXTVAL) PARA QUE FALLE
////try {
////	daoAutor.insert(autor);
////} catch (SQLException e) {
////	e.printStackTrace();
////}
//
////DELETE
////Autor autor = daoAutor.select(1);
////	try {
////		daoAutor.delete(autor);
////	} catch (SQLException e) {
////		e.printStackTrace();
////	}
//
//////SELECT
////	try {
////		System.out.println(daoAutor.select(56));
////	} catch (SQLException e) {
////	e.printStackTrace();
////	}
//
//
////Con el update no podemos hacer nada (el unico error que puede dar (id no existe) no lo gestiona SQL)
//
//////////////////////////////////////////////////////////////////
//

	}
}
