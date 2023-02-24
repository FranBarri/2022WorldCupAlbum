
public class Figurita {
	private int _codigo;
	private int _numero;
	private int _valor;
	private String _nombre;
	private String _pais;

	public Figurita(int codigo, String nombre, String pais, int numero) {
		_codigo = codigo;
		_nombre = nombre;
		_pais = pais;
		_numero = numero;
	}

	public int generarValor(int valor) {
		return _valor = valor;
	}

	public int getCodigo() {
		return _codigo;
	}

	public int getValor() {
		return _valor;
	}

	public int getNumero() {
		return _numero;
	}

	public String getNombre() {
		return _nombre;
	}

	public String getPais() {
		return _pais;
	}
}
