package Dados;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nome;
	private String cpf;

	private List<Obra> obras = new ArrayList<>();
	private List<Avaliacao> avaliacoes = new ArrayList<>();

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
	public List<Obra> getObras() {
		return obras;
	}

	public void avaliarObra(Obra obra, int nota, String comentario) {

    if (obra == null) {
        System.out.println("Obra inválida.");
        return;
    }

    if (nota < 1 || nota > 10) {
        System.out.println("A nota deve ser entre 1 e 10.");
        return;
    }

    // Verifica se já existe avaliação dessa obra
    for (Avaliacao a : avaliacoes) {
        if (a.getObra().equals(obra)) {
            // Atualiza a avaliação existente
            avaliacoes.remove(a);
            break;
        }
    }

    Avaliacao novaAvaliacao = new Avaliacao(obra, nota, comentario);
    avaliacoes.add(novaAvaliacao);

    System.out.println("Avaliação registrada com sucesso!");
}


	public void favoritarObra(Obra obra)
    {
		if (obra != null) obras.add(obra);
    }
}
 