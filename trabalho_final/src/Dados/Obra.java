package Dados;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Obra {
	private String titulo;
	private String autor;
	private int numCapitulos;
	private String sinopse;
	private List<Genero> generos = new ArrayList<>();
	private Map<Integer, Capitulo> capitulos = new HashMap<>();

	public Obra(String titulo, String autor, String sinopse) {
		this.titulo = titulo;
		this.autor = autor;
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

	public Map<Integer, Capitulo> getCapitulosMap() {
        List<Integer> keys = new ArrayList<>(capitulos.keySet());
        Collections.sort(keys);
        Map<Integer, Capitulo> ordered = new LinkedHashMap<>();
        for (Integer k : keys) {
            ordered.put(k, capitulos.get(k));
        }
        return ordered;
    }

	public int proximoNumeroCapitulo() {
        if (capitulos.isEmpty()) return 1;
        return capitulos.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }

	public Capitulo criarCapitulo(String tituloCap, int numPaginas) {
        if (tituloCap == null || tituloCap.trim().isEmpty()) {
            System.err.println("Título do capítulo inválido.");
            return null;
        }
        if (numPaginas <= 0) {
            System.err.println("Número de páginas deve ser maior que zero.");
            return null;
        }
        Capitulo c = new Capitulo(tituloCap, numPaginas, this);
        int numero = proximoNumeroCapitulo();
        capitulos.put(numero, c);
        this.numCapitulos = capitulos.size();
        return c;
    }

	public Capitulo adicionarCapitulo(Capitulo cap) {
        if (cap == null) return null;
        if (cap.getObra() != this) {
            return criarCapitulo(cap.getTitulo(), cap.getNumPaginas());
        } else {
            int numero = proximoNumeroCapitulo();
            capitulos.put(numero, cap);
            this.numCapitulos = capitulos.size();
            return cap;
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

	public List<Capitulo> listarCapitulos() {
        List<Integer> keys = new ArrayList<>(capitulos.keySet());
        Collections.sort(keys);
        List<Capitulo> lista = new ArrayList<>();
        for (Integer k : keys) lista.add(capitulos.get(k));
        return lista;
    }
}
