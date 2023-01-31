
public class FiguritaTop10 extends Figurita {
	String mundialJugado;
	String balon;

	public FiguritaTop10(int valorBase, String paisJugado, String balon, int posicionJugador, int numeroID) {
		super(valorBase, numeroID, posicionJugador, "Top10");
		this.mundialJugado = paisJugado;
		this.balon = balon;

	}

	public String getMundialJugado() {
		return mundialJugado;
	}

	public String getBalon() {
		return balon;
	}

}
