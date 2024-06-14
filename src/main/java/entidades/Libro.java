package entidades;
import entidades.Autor;
public class Libro {
	private String ISBN;
	private String titulo;
	private int idAutor;
	private Autor autor;
	private int count;
	
	public Libro(int count, String iSBN, String titulo, int idAutor) {
		super();
		this.count = count;
		this.ISBN = iSBN;
		this.titulo = titulo;
		this.idAutor = idAutor;
		
	}
	public Libro(int count, String iSBN, String titulo, Autor autor) {
		super();
		this.count = count;

		this.ISBN = iSBN;
		this.titulo = titulo;
		this.idAutor = autor.getIdAutor();
	}
	public Libro(String iSBN, String titulo, int idAutor) {
		super();
		this.ISBN = iSBN;
		this.titulo = titulo;
		this.idAutor = idAutor;
	}
	public Libro(String iSBN, String titulo, Autor autor) {
		super();
		this.ISBN = iSBN;
		this.titulo = titulo;
		this.idAutor = autor.getIdAutor();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public String getISBN() {
		return ISBN;
	}

	@Override
	public String toString() {
		return "\n Libro [Contador= "+ count + ", ISBN=" + ISBN + ", titulo= " + titulo + ", idAutor= " + idAutor+"]";
	}

}
