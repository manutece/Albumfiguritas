import java.util.HashMap;
import java.util.HashSet;

public abstract class Album {
	protected HashMap<String, HashSet<Integer>> páginas = new HashMap<>();
	
	private int albumID;
	static private int contador = 1000000;
	protected boolean sobrePromocional;
	protected Integer codigoSorteo;
	protected String tipoDeAlbum;

	public Album() {
		
		this.albumID = ++contador;
		HashSet<Integer> SetQat = new HashSet<Integer>();
		páginas.put("Qatar", SetQat);
		HashSet<Integer> SetEcu = new HashSet<Integer>();
		páginas.put("Ecuador", SetEcu);
		HashSet<Integer> SetSen = new HashSet<Integer>();
		páginas.put("Senegal", SetSen);
		HashSet<Integer> SetHol = new HashSet<Integer>();
		páginas.put("Países Bajos", SetHol);
		HashSet<Integer> SetIng = new HashSet<Integer>();
		páginas.put("Inglaterra", SetIng);
		HashSet<Integer> SetIr = new HashSet<Integer>();
		páginas.put("Irán", SetIr);
		HashSet<Integer> SetUsa = new HashSet<Integer>();
		páginas.put("Estados Unidos", SetUsa);
		HashSet<Integer> SetGal = new HashSet<Integer>();
		páginas.put("Gales", SetGal);
		HashSet<Integer> SetArg = new HashSet<Integer>();
		páginas.put("Argentina", SetArg);
		HashSet<Integer> SetAra = new HashSet<Integer>();
		páginas.put("Arabia Saudí", SetAra);
		HashSet<Integer> SetMex = new HashSet<Integer>();
		páginas.put("México", SetMex);
		HashSet<Integer> SetPol = new HashSet<Integer>();
		páginas.put("Polonia", SetPol);
		HashSet<Integer> SetFr = new HashSet<Integer>();
		páginas.put("Francia", SetFr);
		HashSet<Integer> SetAus = new HashSet<Integer>();
		páginas.put("Australia", SetAus);
		HashSet<Integer> SetDin = new HashSet<Integer>();
		páginas.put("Dinamarca", SetDin);
		HashSet<Integer> SetTun = new HashSet<Integer>();
		páginas.put("Túnez", SetTun);
		HashSet<Integer> SetEsp = new HashSet<Integer>();
		páginas.put("España", SetEsp);
		HashSet<Integer> SetCR = new HashSet<Integer>();
		páginas.put("Costa Rica", SetCR);
		HashSet<Integer> SetAl = new HashSet<Integer>();
		páginas.put("Alemania", SetAl);
		HashSet<Integer> SetJap = new HashSet<Integer>();
		páginas.put("Japón", SetJap);
		HashSet<Integer> SetBel = new HashSet<Integer>();
		páginas.put("Bélgica", SetBel);
		HashSet<Integer> SetCan = new HashSet<Integer>();
		páginas.put("Canadá", SetCan);
		HashSet<Integer> SetMar = new HashSet<Integer>();
		páginas.put("Marruecos", SetMar);
		HashSet<Integer> SetCro = new HashSet<Integer>();
		páginas.put("Croacia", SetCro);
		HashSet<Integer> SetBr = new HashSet<Integer>();
		páginas.put("Brasil", SetBr);
		HashSet<Integer> SetSer = new HashSet<Integer>();
		páginas.put("Serbia", SetSer);
		HashSet<Integer> SetSui = new HashSet<Integer>();
		páginas.put("Suiza", SetSui);
		HashSet<Integer> SetCam = new HashSet<Integer>();
		páginas.put("Camerún", SetCam);
		HashSet<Integer> SetPor = new HashSet<Integer>();
		páginas.put("Portugal", SetPor);
		HashSet<Integer> SetGha = new HashSet<Integer>();
		páginas.put("Ghana", SetGha);
		HashSet<Integer> SetUru = new HashSet<Integer>();
		páginas.put("Uruguay", SetUru);
		HashSet<Integer> SetKor = new HashSet<Integer>();
		páginas.put("República de Corea", SetKor);

	}

	public void pegarFigurita(Figurita figu) {
		if (páginas.get(figu.getPaisJugador()) != null) {
			páginas.get(figu.getPaisJugador()).add(figu.getPosicionJugador());
			if (páginas.get(figu.getPaisJugador()).size() == 12) {
				páginas.remove(figu.getPaisJugador());
			}
			if (páginas.containsKey("Top10") && páginas.get("Top10").size() == 20) {
				páginas.remove("Top10");
			}
		}
	}

	public int getAlbumID() {
		return albumID;
	}

	public void usarCodigoPromocional() {
		this.sobrePromocional = false;
	}

	public boolean getSobrePromocional() {
		return sobrePromocional;
	}

	public <T> HashMap<String, HashSet<Integer>> getPaginas() {
		return páginas;
	}

	public Integer getCodigoSorteo() {
		return codigoSorteo;
	}



	public String getTipoDeAlbum() {
		return tipoDeAlbum;
	}

	protected abstract String getPremio();
}
