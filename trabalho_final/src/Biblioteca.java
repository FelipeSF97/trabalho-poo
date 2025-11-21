import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Biblioteca {

	private List<Usuario> usuarios = new ArrayList<>();

	private Map<String, Usuario> mapaUsuariosCpfs = new HashMap<>();

	public Biblioteca(){}

	public double mediaAvaliacao() {
		return 0;
	}

	public int somaCapitulo() {
		return 0;
	}

	public boolean usuarioCadastrado(String cpf)
    {
       return mapaUsuariosCpfs.containsKey(cpf);
    }

	public void cadastrarCliente(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o CPF ou 'S' para sair: ");
		String inputCPF = scan.nextLine();

		if (inputCPF.toUpperCase() == "S")
            return;

		 if (this.usuarioCadastrado(inputCPF))
        {
            System.out.println("Usuário com esse CPF já está cadastrado.");

            return;
        }
		System.out.println("Digite o CPF ou 'S' para sair: ");
		String inputNome = scan.nextLine();

         if (inputNome.toUpperCase() == "S")
            return;

         Usuario novoUsuario = new Usuario(inputCPF, inputNome);

         usuarios.add(novoUsuario);
		 
		 System.out.println("Usuário cadastrado com sucesso!");
    }
}
