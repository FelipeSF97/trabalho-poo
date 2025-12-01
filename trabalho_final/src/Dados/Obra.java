package Dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Obra {

    private String titulo;
    private String autor;
    private int ano;
    private List<Genero> generos = new ArrayList<>();
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    private Genero genero;

    // Mapa de capítulos: chave -> número do capítulo
    private Map<Integer, Capitulo> capitulos = new HashMap<>();

    public Obra(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    /**
     * Retorna o número total de unidades definidas pela subclasse
     * (volumes para Manga, capítulos para Manhwa, etc.).
     */
    public abstract int getNumeroUnidades();

    public abstract String getTipo();

    @Override
    public String toString() {
        return "[" + getTipo() + "] " + titulo + " - " + autor + " (" + ano + ")";
    }

    // ----------------------------
    // Gerenciamento de capítulos
    // ----------------------------

    /**
     * Retorna uma cópia ordenada do mapa de capítulos (ordenado pela chave).
     */
    public Map<Integer, Capitulo> getCapitulosMap() {
        List<Integer> keys = new ArrayList<>(capitulos.keySet());
        Collections.sort(keys);
        Map<Integer, Capitulo> ordered = new LinkedHashMap<>();
        for (Integer k : keys) {
            ordered.put(k, capitulos.get(k));
        }
        return ordered;
    }

    /**
     * Retorna o próximo número disponível para um novo capítulo (maxKey + 1).
     */
    public int proximoNumeroCapitulo() {
        if (capitulos.isEmpty())
            return 1;
        return capitulos.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }

    /**
     * Cria um novo capítulo com o título e número de páginas informados,
     * atribui o próximo número disponível e o adiciona ao mapa.
     * Retorna o capítulo criado ou null em caso de dados inválidos.
     */
    public Capitulo criarCapitulo(String titulo, int numPaginas) {

        if (titulo == null || titulo.isBlank() || numPaginas <= 0) {
            return null; // validação básica
        }

        // número automático do capítulo
        int numero = proximoNumeroCapitulo();

        // cria o capítulo associado a esta obra
        Capitulo cap = new Capitulo(numero, titulo, numPaginas, this);

        // adiciona ao mapa
        capitulos.put(numero, cap);

        return cap;
    }


    /**
     * Adiciona um capítulo já existente (possivelmente vindo de outra fonte).
     * Se o capítulo pertence a outra obra, cria um novo capítulo com mesmo
     * título/páginas.
     * Retorna o capítulo final presente na obra.
     */
    public Capitulo adicionarCapitulo(Capitulo cap) {
        if (cap == null) return null;

        // Capítulo de outra obra → criar um novo capítulo equivalente
        if (cap.getObra() != this) {
            Capitulo novo = criarCapitulo(cap.getTitulo(), cap.getNumPaginas());
            capitulos.put(novo.getNumeroCap(), novo); // <--- CORREÇÃO ESSENCIAL
            return novo;
        }

        // Capítulo já pertence a esta obra → adicionar normalmente
            capitulos.put(cap.getNumeroCap(), cap);
            return cap;
    }


    /**
     * Retorna o capítulo associado ao número, ou null se não existir.
     */
    public Capitulo getCapitulo(Integer numero) {
        if (numero == null)
            return null;
        return capitulos.get(numero);
    }

    /**
     * Remove o capítulo pelo número e retorna o capítulo removido, ou null se não
     * existir.
     */
    public Capitulo removerCapitulo(Integer numero) {
        if (numero == null)
            return null;
        return capitulos.remove(numero);
    }

    public void adicionarGenero(Genero g) {
        if (g != null)
            generos.add(g);
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void adicionarAvaliacao(Avaliacao a) {
        if (a != null)
            avaliacoes.add(a);
    }

    public List<Avaliacao> getAvaliacoes() {
        return Collections.unmodifiableList(avaliacoes);
    }

    public double getMediaAvaliacoes() {
        if (avaliacoes.isEmpty())
            return 0;
        double soma = 0;
        for (Avaliacao a : avaliacoes)
            soma += a.getNota();
        return soma / avaliacoes.size();
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
        }

    public Genero getGenero() {
        return genero;
    }
    public String getGenerosComoString() {
        if (generos.isEmpty()) return "Sem gênero";
        return generos.stream()
                    .map(Genero::getNomeGenero)
                    .collect(Collectors.joining(", "));
    }
}
