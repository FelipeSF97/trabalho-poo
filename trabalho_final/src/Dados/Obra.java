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
    private int ano;
    private List<Genero> generos = new ArrayList<>();
    private List<Avaliacao> avaliacoes = new ArrayList<>();

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
        return c;
    }

    /**
     * Adiciona um capítulo já existente (possivelmente vindo de outra fonte).
     * Se o capítulo pertence a outra obra, cria um novo capítulo com mesmo
     * título/páginas.
     * Retorna o capítulo final presente na obra.
     */
    public Capitulo adicionarCapitulo(Capitulo cap) {
        if (cap == null)
            return null;
        if (cap.getObra() != this) {
            // cria novo capítulo local com mesmo título/páginas
            return criarCapitulo(cap.getTitulo(), cap.getNumPaginas());
        } else {
            int numero = proximoNumeroCapitulo();
            capitulos.put(numero, cap);
            return cap;
        }
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

    /**
     * Retorna uma lista ordenada de capítulos (ordenada pelo número).
     */
    public List<Capitulo> listarCapitulos() {
        List<Integer> keys = new ArrayList<>(capitulos.keySet());
        Collections.sort(keys);
        List<Capitulo> lista = new ArrayList<>();
        for (Integer k : keys)
            lista.add(capitulos.get(k));
        return lista;
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
        return avaliacoes;
    }

    public double getMediaAvaliacoes() {
        if (avaliacoes.isEmpty())
            return 0;
        double soma = 0;
        for (Avaliacao a : avaliacoes)
            soma += a.getNota();
        return soma / avaliacoes.size();
    }

}
