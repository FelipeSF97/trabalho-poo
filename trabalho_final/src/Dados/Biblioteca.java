package Dados;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Biblioteca {

	private List<Usuario> usuarios = new ArrayList<>();
	private Map<String, Usuario> mapaUsuariosCpfs = new HashMap<>();
	private List<Obra> obras = new ArrayList<>();

	public Biblioteca(){}

	public double mediaAvaliacao() {
		return 0;
		// TODO: implementar
	}

	public int somaCapitulo() {
		return 0;
		// TODO: implementar
	}

	public boolean usuarioCadastrado(String cpf)
    {
       return mapaUsuariosCpfs.containsKey(cpf);
    }

	public void cadastrarCliente(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o CPF ou 'S' para sair: ");
		String inputCPF = scan.nextLine();

		if (inputCPF.equalsIgnoreCase("S"))
            return;

		 if (this.usuarioCadastrado(inputCPF))
        {
            System.out.println("Usuário com esse CPF já está cadastrado.");
            return;
        }
		System.out.println("Digite o Nome ou 'S' para sair: ");
		String inputNome = scan.nextLine();

        if (inputNome.equalsIgnoreCase("S"))
            return;

         Usuario novoUsuario = new Usuario(inputNome, inputCPF);

         usuarios.add(novoUsuario);

		 mapaUsuariosCpfs.put(inputCPF, novoUsuario);
		 
		 System.out.println("Usuário cadastrado com sucesso!");
    }

	public void adicionarObra(Obra obra) {
        if (obra != null)
            obras.add(obra);
    }

    public void removerObra(Obra obra) {
        if (obra != null)
            obras.remove(obra);
    }

	public List<Obra> getObras() {
        return obras;
    }

	public void avaliarObraUsuario(Usuario usuario) {
    if (usuario == null) {
        System.out.println("Usuário inválido.");
        return;
    }

    if (obras.isEmpty()) {
        System.out.println("Nenhuma obra cadastrada.");
        return;
    }

    Scanner scan = new Scanner(System.in);

    System.out.println("Selecione a obra para avaliar:");
    for (int i = 0; i < obras.size(); i++) {
        System.out.println((i + 1) + " - " + obras.get(i).getTitulo());
    }

    System.out.print("Digite o número da obra: ");
    int opcao = scan.nextInt();
    scan.nextLine();

    if (opcao < 1 || opcao > obras.size()) {
        System.out.println("Opção inválida.");
        return;
    }

    Obra obraSelecionada = obras.get(opcao - 1);

    System.out.print("Digite a nota (1 a 10): ");
    int nota = scan.nextInt();
    scan.nextLine();

    if (nota < 1 || nota > 10) {
        System.out.println("Nota inválida.");
        return;
    }

    System.out.print("Digite um comentário: ");
    String comentario = scan.nextLine();

    usuario.avaliarObra(obraSelecionada, nota, comentario);

    System.out.println("Avaliação registrada com sucesso!");
}

}
