public class Tradicional extends Album {
	private Integer _codigoSorteo;

	public Tradicional(int id, String tipo) {
		super(id, tipo);

	}

	public void setCodigoSorteo(Integer codigo) {
		_codigoSorteo = codigo;
	}

	public Integer getCodigoSorteo() {
		return _codigoSorteo;
	}
}
