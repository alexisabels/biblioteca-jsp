package entidades;

public class Ejemplar {
private int idejemplar;
private String isbn;
private boolean baja;
private static int numeroIdEjemplar;

public int getIdejemplar() {
	return idejemplar;
}
public void setIdejemplar(int idejemplar) {
	this.idejemplar = idejemplar;
}
public String getISBN() {
	return isbn;
}
public void setISBN(String isbn) {
	isbn = this.isbn;
}
public boolean isBaja() {
	return baja;
}
public void setBaja(boolean baja) {
	this.baja = baja;
}

public static int getNumeroIdEjemplar() {
	return numeroIdEjemplar;
}
public static void setNumeroIdEjemplar(int numeroIdEjemplar) {
	Ejemplar.numeroIdEjemplar = numeroIdEjemplar;
}
public Ejemplar(int idejemplar, String isbn, boolean baja) {
	super();
	this.idejemplar = idejemplar;
	this.isbn = isbn;
	this.baja = baja;
}




@Override
public String toString() {
	return "\n Ejemplar [idejemplar=" + idejemplar + ", ISBN=" + isbn + ", baja=" + baja + "]";
}
public Ejemplar(String isbn, boolean baja) {
	super();
	this.isbn = isbn;
	this.baja = baja;
	this.idejemplar = numeroIdEjemplar;
	numeroIdEjemplar++;
}


}
