public class Manhwa extends Obra {
	private String plataforma;

	public Manhwa(String titulo, String autor, int capitulos, String sinopse, String plataforma) {
		super(titulo, autor, capitulos, sinopse);
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