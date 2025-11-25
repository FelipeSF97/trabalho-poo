public class Manga extends Obra {
	private String distribuidora;

	public Manga(String titulo, String autor, int capitulos, String sinopse, double avaliacao, String distribuidora) {
		super(titulo, autor, capitulos, sinopse, avaliacao);
		this.distribuidora = distribuidora;
	}

	public String getDistribuidora() {
		return distribuidora;
	}

	@Override
	public String getTipo() {
		return "Manga";
	}
}
