
public class FiguTOP10 extends Figurita {
	private String _balon;
	private String _mundial;
	private int _valorFinal;
	
	public FiguTOP10(Integer codigo, String nombre, String pais, int numero) {
		super(codigo, nombre, pais, numero);
	}
	
	public void generarValorFinal(int valor, String balon) {
		if (balon == "Oro") {
			_valorFinal = (int) (valor * 0.2) + valor;
		}
		_valorFinal = (int) (valor * 0.1) + valor;
	}
	
	public void setMundial(String MundialTop10) {
		_mundial = MundialTop10;
	}

	public String get_mundial() {
		return _mundial;
	}
	
	public int getvalorFinal() {
		return _valorFinal;
	}

	public void setBalon(String balon) {
		_balon = balon;
	}

	public String getBalon() {
		return _balon;
	}
}