package Dados;
public class Manga extends Obra {
	private String distribuidora;

	public Manga(String titulo, String autor, String sinopse, String distribuidora) {
		super(titulo, autor, sinopse);
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