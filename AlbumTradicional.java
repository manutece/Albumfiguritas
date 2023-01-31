import java.util.HashMap;
import java.util.HashSet;

public class AlbumTradicional extends Album {

	public AlbumTradicional() {
		super();
		sobrePromocional = false;
		super.codigoSorteo = (int) (Math.random() * 10000000);
		super.tipoDeAlbum = "Tradicional";
	}
	
	public String getPremio() {
		return "Pelota";
	}
}
