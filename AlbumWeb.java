import java.util.HashMap;
import java.util.HashSet;

public class AlbumWeb extends Album {

	public AlbumWeb() {
		super();
		sobrePromocional = true;
		super.tipoDeAlbum = "Web";
	}
	
	public String getPremio() {
		return "Nada";
	}

}
