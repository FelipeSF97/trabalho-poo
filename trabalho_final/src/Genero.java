public class Genero {

	private String nomeGenero;

	public Genero(String nome) {
		this.nomeGenero = nome;
	}

	public String getNomeGenero() {
		return nomeGenero;
	}

	@Override
	public String toString() {
		return nomeGenero;
	}
}