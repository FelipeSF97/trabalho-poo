import Dados.*;
import Grafica.*;

public class Main {
    public static void main(String[] args) {
        // Criação da minha biblioteca
        Biblioteca conta = new Biblioteca();
        

        //Adiciono um usuario antes de iniciar
        Usuario usuario = new Usuario("luca", "08532");
        //Inicia a aplicação
        Aplicacao aplicacao = new Aplicacao(usuario, conta);
    }
}
