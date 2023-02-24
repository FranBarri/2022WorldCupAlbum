public class Web extends Album {
	String _codigoPromocional;

	public Web(int id, String tipo) {
		super(id, tipo);
	}

	public void generarCodigoPromo(String[] codigos, int pos) {
		_codigoPromocional = codigos[pos];
	}

	public String getCodigoPromo() {
		return _codigoPromocional;
	}
}
