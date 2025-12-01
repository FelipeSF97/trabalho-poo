import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Obra {
	private String titulo;
	private String autor;
	private int numCapitulos;
	private String sinopse;
	private List<Genero> generos = new ArrayList<>();
	private Map<Integer, Capitulo> capitulos = new HashMap<>();

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

	public Capitulo criarCapitulo(String tituloCap, int numPaginas) {
        try {
            if (tituloCap == null) {
                throw new IllegalArgumentException("Título do capítulo inválido.");
            }
            if (numPaginas <= 0) {
                throw new IllegalArgumentException("Número de páginas deve ser maior que zero.");
            }
            Capitulo c = new Capitulo(tituloCap, numPaginas, this);
            capitulos.put(capitulos.size() + 1, c);
            return c;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao criar capítulo: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao criar capítulo: " + e.getMessage());
            return null;
        }
    }

	public Capitulo getCapitulo(Integer numero) {
        if (numero == null) 
			return null;
        return capitulos.get(numero);
    }

	public Capitulo removerCapitulo(Integer numero) {
        if (numero == null) 
			return null;
        return capitulos.remove(numero);
    }
}
