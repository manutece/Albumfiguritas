import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AlbumDelMundial implements InterfazPublicaAlbumDelMundial {

	private ArrayList<Integer> DNIs = new ArrayList<>(); // Necesario para poder iterar sobre el HashMap de
															// participantes para intercambiar.
	private HashMap<Integer, Participante> participantes = new HashMap<>();
	private HashSet<Integer> codigosUsados = new HashSet<>();
	private Fabrica fabrica = new Fabrica();

	@Override
	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {
		if (!this.participantes.containsKey(dni)) {

			if (tipoAlbum.equals("Tradicional")) {
				Participante participante = new Participante(dni, nombre, fabrica.crearAlbumTradicional());
				participantes.put(dni, participante);
				DNIs.add(dni);
				return participante.getCodigoAlbum();

			}
			if (tipoAlbum.equals("Web")) {
				Participante participante = new Participante(dni, nombre, fabrica.crearAlbumWeb());
				participantes.put(dni, participante);
				DNIs.add(dni);
				return participante.getCodigoAlbum();
			}
			if (tipoAlbum.equals("Extendido")) {
				Participante participante = new Participante(dni, nombre, fabrica.crearAlbumExtendido());
				participantes.put(dni, participante);
				DNIs.add(dni);
				return participante.getCodigoAlbum();
			}
			throw new RuntimeException("Debe indicar un tipo de album válido");
		} else {
			throw new RuntimeException("Participante ya registrado");
		}
	}

	@Override
	public void comprarFiguritas(int dni) {
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException ("Participante no registrado");
		}
		Participante persona = participantes.get(dni);
		ArrayList<Figurita> figus = (ArrayList<Figurita>) fabrica.generarSobre(1);
		for (Figurita figu : figus) {
			persona.agregarFigurita(figu);
		}
	}

	@Override
	public void comprarFiguritasTop10(int dni) {
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException ("Participante no registrado");
		}
		Participante participante = participantes.get(dni);
		if (participante.getAlbum().getTipoDeAlbum().equals("Extendido")) {
			ArrayList<Figurita> figus = (ArrayList<Figurita>) fabrica.generarSobreTop10(1);
			for (Figurita figu : figus) {
				participante.agregarFigurita(figu);
			}
		} else {
			throw new RuntimeException("Sólo pueden comprar sobres Top 10 los que tengan album extendido");
		}
	}

	@Override
	public void comprarFiguritasConCodigoPromocional(int dni) {
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException ("Participante no registrado");
		}
		Participante persona = participantes.get(dni);
		ArrayList<Figurita> figus = (ArrayList<Figurita>) fabrica.generarSobre(1);
		if (persona.getAlbum().getSobrePromocional()) {
			for (Figurita figu : figus) {
				persona.agregarFigurita(figu);
			}
			persona.getAlbum().usarCodigoPromocional();
		} else {
			throw new RuntimeException("Codigo promocional inexistente o ya usado");
		}
	}

	@Override
	public List<String> pegarFiguritas(int dni) {
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException ("Participante no registrado");
		} 
		ArrayList<String> pegadas = new ArrayList<>();
		Participante participante = participantes.get(dni);
		ArrayList<Figurita> figus = participante.getFiguritas();
		for (Figurita figu : figus) {
			if (participante.getContenidoAlbum().get(figu.getPaisJugador()) != null) {
				if (participante.getContenidoAlbum().get(figu.getPaisJugador()).contains(figu.getPosicionJugador())) {
					participante.agregarRepetida(figu);
				} else {
					participante.getAlbum().pegarFigurita(figu);
					pegadas.add("" + figu.getPaisJugador() + "-" + figu.getPosicionJugador());
				}
			} else {
				participante.agregarRepetida(figu);
			}
		}
		participante.terminarDePegarFiguritas();
		return pegadas;
	}

	@Override
	public boolean llenoAlbum(int dni) {
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException ("Participante no registrado");
		}
		Participante persona = participantes.get(dni);
		return persona.completoAlbum();
	}

	@Override
	public String aplicarSorteoInstantaneo(int dni) {
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException ("Participante no registrado");
		}
		Participante participante = participantes.get(dni);
		if (participante.getAlbum().getCodigoSorteo() != null) {
			if (codigosUsados.contains(participante.getAlbum().getCodigoSorteo())) {
				throw new RuntimeException("Codigo ya usado");
			} else {
				return (fabrica.getPremiosInstantaneos()[(int) (Math.random() * 2)]);
			}
		}
		throw new RuntimeException("No posee código de sorteo");
	}

	@Override
	public int buscarFiguritaRepetida(int dni) {
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException ("Participante no registrado");
		}
		Participante participante = participantes.get(dni);
		if (participante.getFiguritas().isEmpty() && participante.getRepetidas().isEmpty()) {
			return -1;
		}
		if (participante.getRepetidas().isEmpty()) {
			return participante.getFiguritas().get(0).getNumeroID();
		}
		return participante.getRepetidas().get(0).getNumeroID();
	}

	@Override
	public boolean intercambiar(int dni, int codFigurita) {
		
		boolean ret = false;
		Figurita figuritaA = null;
		Participante participanteA = participantes.get(dni);
		
		
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException("Usted no esta registrado");
		}
		
		if (participanteA.getRepetidas().isEmpty() && participanteA.getFiguritas().isEmpty()) {
			return false;
		}

		for (Figurita figu : participanteA.getRepetidas()) {
			if (figu.getNumeroID() == codFigurita) {
				figuritaA = figu;
				codFigurita = 0; // Para que no tome dos repetidas iguales
			}
		}
		for (Figurita figu : participanteA.getFiguritas()) {
			if (figu.getNumeroID() == codFigurita) {
				figuritaA = figu;
				codFigurita = 0; 
			}
		}
			if (figuritaA == null) {
				throw new RuntimeException("Usted no posee la figurita ofrecida para intercambiar");
			}

		
		for (int dniB : DNIs) {
			Participante participanteB = participantes.get(dniB);
			if (participanteB.getFiguritas().isEmpty() && participanteB.getRepetidas().isEmpty()) {
				ret = ret || false;
			} else {
			if (participanteA.getAlbum().getTipoDeAlbum().equals(participanteB.getAlbum().getTipoDeAlbum())) {

				if (participanteB.getPegadas().contains(figuritaA.getNumeroID())) {
					ret = ret || false; // usamos un acumulador para saber cuando se encuentra por lo menos un
										// participante con el cual sea posible el intercambio
					
				
				} else {
					if (!participanteB.getRepetidas().isEmpty()) {
						ArrayList<Figurita> repetidasB = new ArrayList<>();
						repetidasB.addAll(participanteB.getRepetidas());
					for (Figurita figuritaB : repetidasB) {
						if (figuritaA.getValorBase() <= figuritaB.getValorBase()) {
							if (participanteA.getPegadas().contains(figuritaB.getNumeroID())) {
								ret = ret || false;
							} else {
								if (participanteA.getFiguritas().contains(figuritaB)) {
									ret = ret || false;
								} else {
									ret = ret || true;
									participanteA.getRepetidas().remove(figuritaA);
									participanteB.agregarFigurita(figuritaA);
									participanteA.agregarFigurita(figuritaB);
									participanteB.getRepetidas().remove(figuritaB);
									
								}
							}

						}

					}
					}
					if (!participanteB.getFiguritas().isEmpty()) {
						ArrayList<Figurita> figuritasB = new ArrayList<>();
						figuritasB.addAll(participanteB.getFiguritas());
					for (Figurita figuritaB : figuritasB) {
						if (figuritaA.getValorBase() <= figuritaB.getValorBase()) {
							if (participanteA.getPegadas().contains(figuritaB.getNumeroID())) {
								ret = ret || false;
							} else {
								if (participanteA.getFiguritas().contains(figuritaB)) {
									ret = ret || false;
								} else {
									ret = ret || true;
									participanteA.getFiguritas().remove(figuritaA);
									participanteB.agregarFigurita(figuritaA);
									participanteA.agregarFigurita(figuritaB);
									participanteB.getFiguritas().remove(figuritaB);
									
								}
							}

						}
					}
				}
			}
		}
		}
		}

		return ret;
	}

	@Override
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		if (participantes.containsKey(dni)) {
			return intercambiar(dni, buscarFiguritaRepetida(dni));
		} else {
			throw new RuntimeException("Participante no registrado");
		}
	}

	@Override
	public String darNombre(int dni) {
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException ("Participante no registrado");
		}
		Participante participante = participantes.get(dni);
		return participante.getNombre();
	}

	@Override
	public String darPremio(int dni) {
		if (!participantes.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		if (!llenoAlbum(dni)) {
			throw new RuntimeException("Album sin completar");
		}
		Participante participante = participantes.get(dni);
		return participante.getAlbum().getPremio();
	}

	@Override
	public String listadoDeGanadores() {
		StringBuilder lista = new StringBuilder();
		for (Integer dni : DNIs) {
			if (llenoAlbum(dni)) {
				lista.append("-");
				lista.append(dni);
				lista.append(" ");
				lista.append(darNombre(dni));
				lista.append(":");
				lista.append(darPremio(dni));
				lista.append("\n");
			}
		}
		return lista.toString();
	}

	@Override
	public List<String> participantesQueCompletaronElPais(String nombrePais) {

		ArrayList<String> paisCompletados = new ArrayList<>();
		for (int dni : DNIs) {
			Participante participante = participantes.get(dni);
			if (!participante.getAlbum().getPaginas().containsKey(nombrePais)) {
				StringBuilder lista = new StringBuilder();
				lista.append("-");
				lista.append(dni);
				lista.append(" ");
				lista.append(darNombre(dni));
				lista.append(":");
				lista.append(participante.getAlbum().getTipoDeAlbum());
				paisCompletados.add(lista.toString());

			}
		}

		return paisCompletados;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Album del Mundial Qatar 2022");
		str.append("---");
		str.append("Panini");
		str.append("\n Cantidad de jugadores registrados:");
		str.append(participantes.size());
		str.append("\n Cantidad de códigos promocionales usados:");
		str.append(codigosUsados.size());
		str.append("\n Cantidad de Páginas de Argentina completadas:");
		str.append(participantesQueCompletaronElPais("Argentina").size());
		return str.toString();
		
	}
	
	

}
