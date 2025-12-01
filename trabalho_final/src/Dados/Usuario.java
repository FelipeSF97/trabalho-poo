package Dados;

import java.util.*;


public class Usuario {

    private String nome;
    private String cpf;
    private Set<Obra> favoritos = new HashSet<>();
    private Map<Obra, List<Avaliacao>> avaliacoes = new HashMap<>();

    public Usuario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Set<Obra> getFavoritos(){
        return favoritos;
    }

    public Map<Obra, List<Avaliacao>> getAvaliacoes() {
        Map<Obra, List<Avaliacao>> copia = new HashMap<>();

        for (Map.Entry<Obra, List<Avaliacao>> e : avaliacoes.entrySet()) {
            copia.put(e.getKey(), Collections.unmodifiableList(e.getValue()));
        }

        return Collections.unmodifiableMap(copia);
    }


    public void avaliarObra(Obra obra, int nota, String comentario) {

        if (obra == null) return;

        Avaliacao a = new Avaliacao(this, obra, nota, comentario);

        // Adiciona avaliação ao usuário
        if (!avaliacoes.containsKey(obra)) {
            avaliacoes.put(obra, new ArrayList<>());
        }
        avaliacoes.get(obra).add(a);

        // Adiciona a avaliação à obra
        obra.adicionarAvaliacao(a);
    }

    public double mediaNotasUsuario() {
        double soma = 0;
        int total = 0;

        for (List<Avaliacao> lista : avaliacoes.values()) {
            for (Avaliacao a : lista) {
                soma += a.getNota();
                total++;
            }
        }

        return (total == 0) ? 0 : soma / total;
    }

    public void favoritar(Obra obra) {
        if (obra != null) {
            favoritos.add(obra);
        }
    }

    public void desfavoritar(Obra obra) {
        if (obra != null) {
            favoritos.remove(obra);
        }
    }

    public boolean isFavorito(Obra obra) {
        return obra != null && favoritos.contains(obra);
    }

    @Override
    public String toString() {
        return nome + " (" + cpf + ")";
    }
}
