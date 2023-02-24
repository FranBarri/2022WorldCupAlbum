import java.util.*;

public abstract class Album {
	private int _id;
	private String _tipo;
	protected HashMap<String, ArrayList<Figurita>> _secciones;

	public Album(int id, String tipo) {
		_id = id;
		_tipo = tipo;
		_secciones = new HashMap<>();
	}

	/**
	 * Genera las secciones del album, para cada pais genera una lista y le aniade
	 * las figuritas, las cuales su codigo depende de la posicion en la lista de
	 * coleccionables. Cada vez que j es igual a 12 (la cantidad de
	 * figuritas por pais), crea una nueva lista y aniade las figuritas que siguen.
	 * Basicamente, para cada pais crea una lista de sus figuritas correspondientes
	 * (estan ordenadas por paises participante)
	 * 
	 * @param paisesParticipantes
	 * @param aColeccionar
	 * @param lugares
	 */
	public void generarSecciones(String[] paisesParticipantes, Figurita[] aColeccionar, int lugares) {
		int i = 1;
		int j = 1;
		for (String pais : paisesParticipantes) {
			ArrayList<Figurita> figus = new ArrayList<>();
			while (j <= lugares) {
				figus.add(aColeccionar[i - 1]);
				i++;
				j++;
			}
			j = 1;
			_secciones.put(pais, figus);
		}
	}
	
	public void setId(int id) {
		_id = id;
	}

	public String getTipo() {
		return _tipo;
	}

	public int getCodigo() {
		return _id;
	}

	public HashMap<String, ArrayList<Figurita>> getSecciones() {
		return _secciones;
	}
}
