public class Manhwa extends Obras {
	private String plataforma;

	public Manhwa(String titulo, String autor, int capitulos, String sinopse, double avaliacao, String plataforma) {
		super(titulo, autor, capitulos, sinopse, avaliacao);
		this.plataforma = plataforma;
	}

	public String getPlataforma() {
		return plataforma;
	}

	@Override
	public String getTipo() {
		return "Manhwa";
	}
}
