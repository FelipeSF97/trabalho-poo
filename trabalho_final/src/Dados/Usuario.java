package Dados;

import java.util.*;

public class Usuario {

    private String nome;
    private String cpf;

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

    public Map<Obra, List<Avaliacao>> getAvaliacoes() {
        return avaliacoes;
    }

    public void avaliarObra(Obra obra, int nota, String comentario) {
        Avaliacao nova = new Avaliacao(nota, comentario);

        // salva no mapa do usuário
        avaliacoes.computeIfAbsent(obra, o -> new ArrayList<>()).add(nova);

        // salva na própria obra também
        obra.adicionarAvaliacao(nova);

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

    @Override
    public String toString() {
        return nome + " (" + cpf + ")";
    }
}
