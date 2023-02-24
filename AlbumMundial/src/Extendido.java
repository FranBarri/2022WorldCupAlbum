import java.util.*;

public class Extendido extends Album {

	public Extendido(int id, String tipo) {
		super(id, tipo);
	}

	public void generarSeccionesTOP10(FiguTOP10[] coleccionTOP10) {
		ArrayList<Figurita> oro = new ArrayList<>();
		ArrayList<Figurita> plata = new ArrayList<>();
		for (int i = 0; i <= coleccionTOP10.length - 1; i++) {
			if (i % 2 == 0) {
				oro.add(coleccionTOP10[i]);
			} else {
				plata.add(coleccionTOP10[i]);
			}
		}
		_secciones.put("Balon de Oro", oro);
		_secciones.put("Balon de Plata", plata);
	}
}
