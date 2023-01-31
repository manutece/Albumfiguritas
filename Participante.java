import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Participante {
	private int DNI;
	private String nombre;
	private Album album;
	private ArrayList<Figurita> figuritas = new ArrayList<>();
	private ArrayList<Figurita> repetidas = new ArrayList<>();
	private HashSet<Integer> pegadas = new HashSet<>();
	
	
	public Participante(int DNI, String nombre, Album album) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.album = album;
			 
	}



	public int getDNI() {
		return DNI;
	}

	public <T> HashMap<String, HashSet<Integer>> getContenidoAlbum() {
		return album.getPaginas();
	}
	
	public boolean completoAlbum() {
		return album.getPaginas().isEmpty();
	}
	
	public void agregarRepetida(Figurita figu) {
		repetidas.add(figu);
	}
	
	public void agregarFigurita(Figurita figu) {
		if (figuritas.contains(figu)) {
			agregarRepetida(figu);
		} else {
		figuritas.add(figu);
		pegadas.add(figu.getNumeroID());
		}//El participante automaticamente se agrega la figurita a pegadas, ya que si tuvo una figurita en su poder esta si o si se pega.
	}
	

	public int getCodigoAlbum() {
		return getAlbum().getAlbumID();
	}



	public Album getAlbum() {
		return album;
	}

	public void terminarDePegarFiguritas() {
		figuritas.clear();
	}

	public ArrayList<Figurita> getFiguritas() {
	
		return figuritas;
	}
	
	public ArrayList<Figurita> getRepetidas(){
		return repetidas;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public HashSet<Integer> getPegadas(){
		return pegadas;
	}
}
