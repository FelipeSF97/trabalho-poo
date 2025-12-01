package Dados;
public class Genero {

	private final String nomeGenero;

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