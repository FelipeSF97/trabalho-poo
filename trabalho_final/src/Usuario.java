import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nome;
	private String cpf;
	private List<Obra> obras = new ArrayList<>();

	public Usuario(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public List<Obra> getObras() {
		return obras;
	}

	public void favoritarObra(Obra obra)
    {
		if (obra != null) obras.add(obra);
    }
}
 