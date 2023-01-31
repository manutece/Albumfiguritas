
public class Figurita {
	Integer numeroID;
	Integer valorBase;
	Integer posicionJugador;
	String paisJugador;

	public Figurita(int valorBase, int numeroID, int posicion, String pais) {
		this.numeroID = numeroID;
		this.posicionJugador = posicion;
		this.paisJugador = pais;
		this.valorBase = valorBase;
	}

	public Figurita() {
	}

	@Override
	public boolean equals(Object o) {
		boolean ret = false;
		if (o instanceof Figurita) {
			Figurita that = (Figurita) o;
			ret = (this.posicionJugador == that.getPosicionJugador() && this.paisJugador.equals(that.getPaisJugador())
					&& this.numeroID == that.getNumeroID());
		}
		return ret;
	}

	@Override
	public int hashCode() {
		return (41 * (41 + numeroID));
	}

	public Integer getPosicionJugador() {
		return posicionJugador;
	}

	public String getPaisJugador() {
		return paisJugador;
	}

	public Integer getNumeroID() {
		return numeroID;
	}

	public Integer getValorBase() {
		return valorBase;
	}
}
