package Grafica;

import javax.swing.*;

import Dados.Usuario;
import Dados.Biblioteca;
import Dados.Obra;
import Dados.Manga;
import Dados.Manhwa;

public class Aplicacao extends JFrame {

    private Usuario usuario;
    private Biblioteca biblioteca;

    private Login login;
    private Acervo acervo;
    private ObraUnica unico;
    private PainelEstatisticas estatisticas;
    private PainelCadastroUsuario cadastroUsuario;
    private PainelBuscar buscar;

    public Aplicacao(Usuario usuario, Biblioteca biblioteca) {
        super();

        this.biblioteca = (biblioteca != null) ? biblioteca : new Biblioteca();
        this.usuario = (usuario != null) ? usuario : new Usuario("Convidado", "000");

        inicializarDados();

        login = new Login(this, this.usuario);
        acervo = new Acervo(this, this.usuario, this.biblioteca);
        unico = new ObraUnica(this);
        estatisticas = new PainelEstatisticas(this.biblioteca);
        cadastroUsuario = new PainelCadastroUsuario(this, this.biblioteca);
        buscar = new PainelBuscar(this, this.biblioteca);

        this.setContentPane(login);
        this.setTitle("LerMangas");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 520);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        criarMenuSuperior();
    }

    private void inicializarDados() {

        // --------- MANGÁS ---------
        Manga berserk = new Manga("Berserk", "Kentaro Miura", 1989, 41);
        berserk.criarCapitulo("The Black Swordsman", 48);
        berserk.criarCapitulo("Brand", 42);
        biblioteca.adicionarObra(berserk);

        Manga op = new Manga("One Piece", "Eiichiro Oda", 1997, 107);
        op.criarCapitulo("Romance Dawn", 53);
        op.criarCapitulo("The Pirate Hunter", 41);
        biblioteca.adicionarObra(op);

        Manga fma = new Manga("Fullmetal Alchemist", "Hiromu Arakawa", 2001, 27);
        fma.criarCapitulo("The Two Alchemists", 39);
        biblioteca.adicionarObra(fma);

        // --------- MANHWAS ---------
        Manhwa sl = new Manhwa("Solo Leveling", "Chugong", 2016, 179);
        sl.criarCapitulo("I'm Weak", 38);
        sl.criarCapitulo("Grind", 40);
        biblioteca.adicionarObra(sl);

        Manhwa tog = new Manhwa("Tower of God", "SIU", 2010, 600);
        tog.criarCapitulo("The 25th Night", 55);
        biblioteca.adicionarObra(tog);

        Manhwa tbat = new Manhwa("The Beginning After the End", "TurtleMe", 2018, 180);
        tbat.criarCapitulo("Early Life", 41);
        biblioteca.adicionarObra(tbat);
    }

    private void criarMenuSuperior() {
        JMenuBar barra = new JMenuBar();
        JMenu menuBiblioteca = new JMenu("Biblioteca");

        JMenuItem itemAcervo = new JMenuItem("Acervo");
        itemAcervo.addActionListener(e -> mudaPainel(2));

        JMenuItem estatisticasItem = new JMenuItem("Estatísticas");
        estatisticasItem.addActionListener(e -> mudaPainel(4));

        JMenuItem cadastrarUserItem = new JMenuItem("Cadastrar Usuário");
        cadastrarUserItem.addActionListener(e -> mudaPainel(5));

        JMenuItem buscarItem = new JMenuItem("Buscar");
        buscarItem.addActionListener(e -> mudaPainel(6));

        JMenuItem exportarItem = new JMenuItem("Exportar CSV");
        exportarItem.addActionListener(e -> CSVExporter.exportarBibliotecaParaCSV(this, biblioteca));

        JMenuItem sairItem = new JMenuItem("Sair");
        sairItem.addActionListener(e -> System.exit(0));

        menuBiblioteca.add(itemAcervo);
        menuBiblioteca.add(estatisticasItem);
        menuBiblioteca.addSeparator();
        menuBiblioteca.add(cadastrarUserItem);
        menuBiblioteca.add(buscarItem);
        menuBiblioteca.addSeparator();
        menuBiblioteca.add(exportarItem);
        menuBiblioteca.addSeparator();
        menuBiblioteca.add(sairItem);

        barra.add(menuBiblioteca);
        this.setJMenuBar(barra);
    }

    public void mudaPainel(int painel) {
        switch (painel) {
            case 1 -> this.setContentPane(login);
            case 2 -> {
                acervo.atualiza();
                this.setContentPane(acervo);
            }
            case 3 -> this.setContentPane(unico);
            case 4 -> {
                estatisticas.atualizar();
                this.setContentPane(estatisticas);
            }
            case 5 -> this.setContentPane(cadastroUsuario);
            case 6 -> {
                buscar.atualizarResultados();
                this.setContentPane(buscar);
            }
            default -> this.setContentPane(login);
        }
        this.revalidate();
        this.repaint();
    }

    public void abrirObraUnica(Obra obra) {
        unico.setObra(obra);
        unico.atualizarLista();
        mudaPainel(3);
    }
}
