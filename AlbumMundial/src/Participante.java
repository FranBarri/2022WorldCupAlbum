import java.util.*;

public class Participante {
	private int _dni;
	private int _pegadasTam;
	private String _nombre;
	private String _premio;
	private Album _album;
	private ArrayList<Figurita> _coleccion;
	private ArrayList<Figurita> _pegadas;
	private Fabrica _fabrica;

	public Participante(Integer dni, String nombre) {
		_dni = dni;
		_nombre = nombre;
		_coleccion = new ArrayList<>();
		_pegadas = new ArrayList<>();
		_fabrica = new Fabrica();
	}

	public Album generarAlbum(String tipoAlbum) {
		if (tipoAlbum == "Tradicional") {
			_album = _fabrica.crearAlbumTradicional();
			return _album;
		}
		if (tipoAlbum == "Web") {
			_album = _fabrica.crearAlbumWeb();
			return _album;
		}

		if (tipoAlbum == "Extendido") {
			_album = _fabrica.crearAlbumExtendido();
			return _album;
		}
		throw new RuntimeException("Tipo de Album no valido");
	}

	public void anadirFiguritas(List<Figurita> sobre) {
		for (Figurita figu : sobre) {
			_coleccion.add(figu);
		}
	}

	public void anadirFiguritasTOP10(List<FiguTOP10> sobre) {
		for (Figurita figu : sobre) {
			_coleccion.add(figu);
		}
	}

	public List<Figurita> pegarFiguritas() {
		if (_coleccion.size() == 0) {
			return _pegadas;
		}
		Iterator<Figurita> it = _coleccion.iterator();
		while (it.hasNext()) {
			Figurita figurita = it.next();
			if (!_pegadas.contains(figurita)) {
				_pegadas.add(figurita);
				it.remove();
				_pegadasTam++;
			}
		}
		return _pegadas;
	}

	public void intercambio(Figurita figurita, Figurita otro) {
		_coleccion.add(otro);
		_coleccion.remove(figurita);
	}

	public List<Figurita> pegarFiguritasTOP10() {
		for (Figurita figu : _coleccion) {
			if (!_pegadas.contains(figu)) {
				_pegadas.add(figu);
				_coleccion.remove(figu);
			}
		}
		return _pegadas;
	}

	public int buscarRepetidas() {
		for (Figurita figu : _coleccion) {
			for (Figurita figu2 : _coleccion) {
				if (figu.equals(figu2)) {
					return figu.getCodigo();
				}
			}
		}
		return -1;
	}

	public Figurita buscarFigurita(int codigo) {
		Figurita ret = null;
		for (Figurita figu : _coleccion) {
			if (codigo == figu.getCodigo()) {
				ret = figu;
			}
		}
		return ret;
	}

	public boolean duenoFigu(int codigoFigu) {
		for (Figurita figu : _coleccion) {
			if (figu.getCodigo() == codigoFigu) {
				return true;
			}
		}
		return false;
	}

	public void setPremio(String premio) {
		_premio = premio;
	}

	public int getDni() {
		return _dni;
	}

	public int getAlbumId() {
		return _album.getCodigo();
	}

	public String getNombre() {
		return _nombre;
	}

	public String getPremio() {
		return _premio;
	}

	public String getTipoAlbum() {
		return _album.getTipo();
	}

	public String getCodigoPromo() {
		return ((Web) _album).getCodigoPromo(); // upcasting
	}

	public Album getAlbum() {
		return _album;
	}

	public ArrayList<Figurita> getColeccion() {
		return _coleccion;
	}

	public List<Figurita> getPegadas() {
		return _pegadas;
	}

	public int getPegadasTam() {
		return _pegadasTam;
	}
}
