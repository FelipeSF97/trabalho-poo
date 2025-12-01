import Dados.*;
import Grafica.*;

public class Main {
    public static void main(String[] args) {
        // Criação da minha biblioteca
        Biblioteca conta = new Biblioteca();

        //Adicionar mangas
        conta.adicionarObra(new Manga("teste4", "luca", "sinopse", "puc"));
        conta.adicionarObra(new Manga("teste1", "pedro", "sinopse", "puc"));
        conta.adicionarObra(new Manga("teste2", "renan", "sinopse", "puc"));
        conta.adicionarObra(new Manga("teste3", "felipe", "sinopse", "puc"));
        conta.adicionarObra(new Manhwa("teste", "luca", "sinopse", "puc"));

        //Adiciono um usuario antes de iniciar
        Usuario usuario = new Usuario("luca", "08532");
        //Inicia a aplicação
        Aplicacao aplicacao = new Aplicacao(usuario, conta);
    }
}
