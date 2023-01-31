import java.util.HashMap;
import java.util.HashSet;

public class AlbumExtendido extends Album {

	public AlbumExtendido() {
		super();
		super.sobrePromocional = false;
		HashSet<Integer> SetTop10 = new HashSet<Integer>();
		páginas.put("Top10", SetTop10);
		super.codigoSorteo = null;
		super.tipoDeAlbum = "Extendido";
	}
	
	public String getPremio() {
		return "Pelota y viaje";
	}
}
