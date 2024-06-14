package controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import conexion.Conexion;
import dao.DAOEjemplar;
import dao.DAOSocio;
import entidades.Ejemplar;
import entidades.Socio;
import excepciones.BorrowingRequestException;
import excepciones.DevolverPrestamosTarde;
import excepciones.ReturnRequestException;
import triggers.AnotarDatosDevolucion;
import triggers.AnotarDatosPrestamos;

/**
 * Servlet implementation class ControladorTest
 */
@WebServlet("/ControladorTest")
public class ControladorTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		Connection con = (Connection) session.getAttribute("conexion");
		if (con==null) {
			try {
				con=Conexion.getConnection();
				session.setAttribute("conexion", con);
				} catch (Exception e) {
				// TODO: handle exception
			}
			session.setAttribute("conexion", con);
			
		}
		
		
		AnotarDatosPrestamos adp = new AnotarDatosPrestamos();
		DAOSocio daoSocio = new DAOSocio();
		DAOEjemplar daoEjemplar = new DAOEjemplar();
		AnotarDatosDevolucion add = new AnotarDatosDevolucion();
		
	
	
	
		
		ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
		
		ArrayList<Integer> idEjemplares = new ArrayList<Integer>();

		
		String operacion=  request.getParameter("operacion");
		
	
		
		switch (operacion) {
		
//		case "recuperarDatosUsuario":
//			ciudad = request.getParameter("ciudad");
//			break;
		case "prestarLibro":
		
			
			int idSocio = Integer.valueOf(request.getParameter("idSocio"));
			int idEjemplarPrestamo = Integer.valueOf(request.getParameter("idEjemplar"));
			idEjemplares.add(idEjemplarPrestamo);
			
			
			
			try {
				adp.SolicitudPrestamo(idSocio, idEjemplares, con);
				request.setAttribute("resultado", "Préstamo realizado correctamente");
			} catch (BorrowingRequestException | SQLException e) {
                request.setAttribute("resultadoError", e.getMessage());


			}
			request.getRequestDispatcher("/Prestamo.jsp").forward(request, response);
			break;
			
			
			
			
			
			
		case "devolverLibro":
			int idEjemplarDevolucion = Integer.valueOf(request.getParameter("idEjemplarDevolver"));
			idEjemplares.add(idEjemplarDevolucion);
			
			
																		
			try {
				add.solicitarDevolucion(idEjemplares, con);
				request.setAttribute("resultado", "Devolución realizada correctamente");

			} catch (ReturnRequestException | SQLException e) {
                request.setAttribute("resultadoError", e.getMessage());
			}catch ( DevolverPrestamosTarde e) {
                request.setAttribute("resultado", e.getMessage());
			}
			request.getRequestDispatcher("/Devolucion.jsp").forward(request, response);

			break;
																	
		case "testingJSTL": //esto es solo de prueba, no sirve para biblioteca
			String cadena = "mi cadena";
			Boolean escribirCadena = true;
			int miEntero = 5;
//			String idSocio=request.getParameter("idSocio");
			String idejemplar=request.getParameter("idEjemplar");
			request.setAttribute("cadena", miEntero);
			request.setAttribute("escribirCadena", escribirCadena);
			
			request.getRequestDispatcher("/testingJST.jsp").forward(request, response);
			break;
		default:
			break;
			
			
		
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
