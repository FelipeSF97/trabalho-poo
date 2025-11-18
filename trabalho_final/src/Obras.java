import java.util.ArrayList;
import java.util.List;

public abstract class Obras {
	private String titulo;
	private String autor;
	private int numCapitulos;
	private String sinopse;
	private double avaliacao;
	private List<Genero> generos = new ArrayList<>();

	public Obras(String titulo, String autor, int capitulos, String sinopse, double avaliacao) {
		this.titulo = titulo;
		this.autor = autor;
		this.numCapitulos = capitulos;
		this.sinopse = sinopse;
		this.avaliacao = avaliacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public int getNumCapitulos() {
		return numCapitulos;
	}

	public String getSinopse() {
		return sinopse;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void adicionarGenero(Genero genero) {
		if (genero != null)
			generos.add(genero);
	}

	public abstract String getTipo();
}
