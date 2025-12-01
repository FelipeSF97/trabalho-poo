package Dados;
public class Manhwa extends Obra {
	private String plataforma;

	public Manhwa(String titulo, String autor, String sinopse, String plataforma) {
		super(titulo, autor, sinopse);
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