package Grafica;
import javax.swing.*;
import Dados.Usuario;
import Dados.Biblioteca;
import Dados.Obra;


public class Aplicacao extends JFrame {
    private Usuario usuario;
    private Login login;
    private Acervo acervo;
    private Biblioteca biblioteca;
    private ObraUnica unico;

    //Construtor puxando o usuario e biblioteca que serão usados
    public Aplicacao(Usuario usuario, Biblioteca biblioteca) {
        super();
    
        //Caso tenha uma biblioteca antiga, crie uma nova
        this.biblioteca = (biblioteca != null) ? biblioteca : new Biblioteca();
        this.usuario = usuario;

        //Todos os paineis usados
        login = new Login(this, usuario);
        acervo = new Acervo(this, this.usuario, this.biblioteca);
        unico = new ObraUnica(this);

        //Decoração
        this.setContentPane(login);
        this.setTitle("LerMangas");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800,200);
        this.setVisible(true);
    }

    //Troca de Painel
    public void mudaPainel(int painel) {
        switch(painel) {
            case 1:
                this.setContentPane(login);
                this.pack();
                this.setSize(800,200);
                break;
            case 2:
                this.setContentPane(acervo);
                this.pack();
                this.setSize(800,200);
                break;
            case 3:
                this.setContentPane(unico);
                this.pack();
                this.setSize(800,200);
                break;
        }
    }

    //Metodo para o 3 painel. Explicar
    public void abrirObraUnica(Obra obra) {
        unico.setObra(obra);
        unico.atualizarLista();
        mudaPainel(3);
    }

}