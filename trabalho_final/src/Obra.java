import java.util.ArrayList;
import java.util.List;

public abstract class Obra {
	private String titulo;
	private String autor;
	private int numCapitulos;
	private String sinopse;
	private List<Genero> generos = new ArrayList<>();

	public Obra(String titulo, String autor, int capitulos, String sinopse) {
		this.titulo = titulo;
		this.autor = autor;
		this.numCapitulos = capitulos;
		this.sinopse = sinopse;
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

	public List<Genero> getGeneros() {
		return generos;
	}

	public void adicionarGenero(Genero genero) {
		if (genero != null)
			generos.add(genero);
	}

	public abstract String getTipo();

	public String toString() {
    return titulo + " (" + getTipo() + ")";
}
}
