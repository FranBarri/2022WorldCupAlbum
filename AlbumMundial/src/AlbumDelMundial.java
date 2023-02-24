import java.util.*;

public class AlbumDelMundial implements IAlbumDelMundial {
	private int cantParticipantes = 0;
	private int cantIntercambios = 0;
	private int cantAlbumesLlenos = 0;
	private int cantSobresComprados = 0;
	private HashMap<Integer, Participante> registrados;
	private HashMap<Integer, Participante> albumCompleto;
	private List<Integer> codigosSorteo;
	private Fabrica fabrica;

	public AlbumDelMundial() {
		codigosSorteo = new ArrayList<>();
		registrados = new HashMap<>();
		albumCompleto = new HashMap<>();
		fabrica = new Fabrica();
	}

	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {
		if (registrados.containsKey(dni)) {
			throw new RuntimeException("Participante ya registrado");
		} else {
			Participante p = new Participante(dni, nombre);
			p.generarAlbum(tipoAlbum);
			registrados.put(dni, p);
			cantParticipantes++;
			return p.getAlbumId();
		}
	}

	public void comprarFiguritas(int dni) {
		if (!registrados.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		List<Figurita> nuevoSobre = fabrica.generarSobre(4);
		registrados.get(dni).anadirFiguritas(nuevoSobre);
		cantSobresComprados++;
	}

	public void comprarFiguritasTop10(int dni) {
		if (!registrados.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		if (registrados.get(dni).getTipoAlbum() != "Extendido") {
			throw new RuntimeException("Tipo de album invalido");
		}
		List<FiguTOP10> nuevoSobre = fabrica.generarSobreTOP10(4);
		registrados.get(dni).anadirFiguritasTOP10(nuevoSobre);
		cantSobresComprados++;
	}

	public void comprarFiguritasConCodigoPromocional(int dni) {
		if (!registrados.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		if (registrados.get(dni).getTipoAlbum() != "Web") {
			throw new RuntimeException("Tipo de album invalido");
		}
		if (registrados.containsKey(dni)) {
			List<Figurita> nuevoSobre = fabrica.generarSobrePromo(4, registrados.get(dni).getCodigoPromo());
			registrados.get(dni).anadirFiguritas(nuevoSobre);
			cantSobresComprados++;
		}
	}

	public List<String> pegarFiguritas(int dni) {
		List<String> pegadas = new ArrayList<>();
		Participante participante = registrados.get(dni);
		List<Figurita> pegadasParticipante = participante.pegarFiguritas();
		for (Figurita figu : pegadasParticipante) {
			StringBuilder ret = new StringBuilder();
			ret.append(figu.getPais());
			ret.append(", ");
			ret.append(figu.getCodigo());
			pegadas.add(ret.toString());
		}
		return pegadas;
	}

	public boolean llenoAlbum(int dni) {
		boolean ret = false;
		if (registrados.get(dni).getPegadasTam() == registrados.get(dni).getAlbum().getSecciones().size() * 12) {
			ret = true;
		}
		return ret;
	}

	public String aplicarSorteoInstantaneo(int dni) {
		if (!registrados.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		if (registrados.get(dni).getTipoAlbum() == "Tradicional") {
			generarCodigosDeSorteo();
			for (int i = 0; i < codigosSorteo.size(); i++) {
				if (((Tradicional) registrados.get(dni).getAlbum()).getCodigoSorteo().equals(codigosSorteo.get(i))) {
					String ret = fabrica.generarSorteo();
					return ret;
				}
			}
		}
		throw new RuntimeException("El tipo de album debe ser tradicional");
	}

	public int buscarFiguritaRepetida(int dni) {
		return registrados.get(dni).buscarRepetidas();
	}

	public boolean intercambiar(int dni, int codFigurita) {
		if (!registrados.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		Participante part = registrados.get(dni);
		Figurita figurita = part.buscarFigurita(codFigurita);
		for (Participante otro : registrados.values()) {
			if (part.getDni() != otro.getDni() && part.getTipoAlbum() == otro.getTipoAlbum()) {
				ArrayList<Figurita> coleccionOtro = otro.getColeccion();
				for (int i = 0; i < coleccionOtro.size(); i++) {
					Figurita repetidaOtro = coleccionOtro.get(i);
					if (figurita.getValor() >= repetidaOtro.getValor()) {
						part.intercambio(figurita, repetidaOtro);
						otro.intercambio(repetidaOtro, figurita);
						cantIntercambios++;
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		if (!registrados.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		if (registrados.get(dni).buscarRepetidas() != -1) {
			return intercambiar(dni, registrados.get(dni).buscarRepetidas());
		} else {
			return false;
		}
	}

	public String darNombre(int dni) {
		if (!registrados.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		return registrados.get(dni).getNombre();
	}

	public String darPremio(int dni) {
		if (!registrados.containsKey(dni)) {
			throw new RuntimeException("Participante no registrado");
		}
		if (!llenoAlbum(dni)) {
			throw new RuntimeException("Album Incompleto");
		} else {
			return fabrica.premios(registrados.get(dni).getTipoAlbum());
		}
	}

	public String listadoDeGanadores() { // Por el momento no anda porque no funciona pegarFigus
		StringBuilder ret = new StringBuilder();
		albumCompleto();
		for (Participante ganador : albumCompleto.values()) {
			ret.append(ganador.getDni());
			ret.append(" - ");
			ret.append(ganador.getNombre());
			ret.append(" - ");
			ganador.setPremio(fabrica.premios(ganador.getTipoAlbum()));
			ret.append(ganador.getPremio());
			ret.append("\n");
		}
		return ret.toString();

	}

	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		List<String> lista = new ArrayList<String>();
		StringBuilder ret = new StringBuilder();
		for (int dni : registrados.keySet()) {
			if (llenoPais(dni, nombrePais)) {
				ret.append("(");
				ret.append(dni);
				ret.append(")");
				ret.append(registrados.get(dni).getNombre());
				ret.append(": ");
				ret.append(registrados.get(dni).getTipoAlbum());
				ret.append(" --- ");
				lista.add(ret.toString());
			}
		}
		return lista;
	}

	// Metodos propios
	private boolean llenoPais(int dni, String pais) {
		boolean ret = false;
		int cont = 0;
		for (Figurita figu : registrados.get(dni).getPegadas()) {
			if (figu.getPais() == pais) {
				cont++;
			}
		}
		if (cont == 12) {
			ret = true;
		}
		return ret;
	}

	private void albumCompleto() {
		for (int dni : registrados.keySet()) {
			if (llenoAlbum(dni)) {
				albumCompleto.put(dni, registrados.get(dni));
				cantAlbumesLlenos++;
			}
		}
	}

	private void generarCodigosDeSorteo() {
		for (Integer dni : registrados.keySet()) {
			if (registrados.get(dni).getTipoAlbum() == "Tradicional") {
				((Tradicional) registrados.get(dni).getAlbum()).setCodigoSorteo(registrados.get(dni).getAlbumId());
				codigosSorteo.add(registrados.get(dni).getAlbumId());
			}
		}
	}

	public String toString() {
		StringBuilder ret = new StringBuilder();
		ret.append("Participantes registrados: " + cantParticipantes + "\n");
		ret.append("Intercambios realizados: " + cantIntercambios + "\n");
		ret.append("Albumes completados: " + cantAlbumesLlenos + "\n");
		ret.append("Total de sobres comprados: " + cantSobresComprados + "\n");
		return ret.toString();
	}
}