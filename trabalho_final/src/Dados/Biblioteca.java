package Dados;

import java.util.*;

public class Biblioteca {

    private List<Usuario> usuarios = new ArrayList<>();
    private Map<String, Usuario> mapaUsuariosCpfs = new HashMap<>();
    private List<Genero> generos = new ArrayList<>();
    private List<Obra> obras = new ArrayList<>();

    public Biblioteca() {}

    // ---------------- GETTERS ----------------

    public List<Obra> getObras() {
        return obras;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // ---------------- OBRAS ----------------

    public void adicionarObra(Obra obra) {
        if (obra != null) obras.add(obra);
    }

    public void removerObra(Obra obra) {
        obras.remove(obra);
    }

    // ---------------- MÉTRICAS ----------------

    // Soma capítulos ou volumes
    public int somaCapitulo() {
        int soma = 0;
        for (Obra o : obras) soma += o.getNumeroUnidades();
        return soma;
    }

    // Média geral das avaliações de TODOS os usuários
    public double mediaAvaliacao() {

        double soma = 0;
        int qtd = 0;

        for (Usuario u : usuarios) {
            for (List<Avaliacao> lista : u.getAvaliacoes().values()) {
                for (Avaliacao a : lista) {
                    soma += a.getNota();
                    qtd++;
                }
            }
        }

        return (qtd == 0) ? 0 : soma / qtd;
    }

    // ---------------- USUÁRIOS ----------------

    public boolean usuarioCadastrado(String cpf) {
        if (cpf == null) return false;

        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }


    public void adicionarUsuario(Usuario u) {
        if (u != null && !usuarioCadastrado(u.getCpf())) {
            usuarios.add(u);
            mapaUsuariosCpfs.put(u.getCpf(), u);
        }
    }

    public void adicionarGenero(Genero g) {
        if (g != null) generos.add(g);
    }

    public List<Genero> getGeneros() {
        return Collections.unmodifiableList(generos);
    }
    
    public Usuario buscarUsuarioPorCPF(String cpf) {
    for (Usuario u : usuarios) {
        if (u.getCpf().equals(cpf)) {
            return u;
        }
    }
    return null;
}
}
