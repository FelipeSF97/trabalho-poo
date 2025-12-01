package Grafica;

import Dados.Biblioteca;
import Dados.Genero;
import Dados.Manga;
import Dados.Manhwa;
import Dados.Obra;
import Dados.Usuario;

import javax.swing.*;

import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;

public class Acervo extends JPanel {
    private Aplicacao app;
    private Usuario usuario;
    private Biblioteca biblioteca;

    private JButton voltar;
    private JButton fechar;
    private JButton ver;
    private JButton adicionar;
    private JButton remover;
    private DefaultListModel<String> listaModel;
    private JList<String> listaObras;
    private JScrollPane scroll;

    public Acervo(Aplicacao app, Usuario usuario, Biblioteca biblioteca) {
        super(new BorderLayout(8, 8));
        this.app = app;
        this.usuario = usuario;
        this.biblioteca = biblioteca;

        voltar = new JButton("Voltar");
        fechar = new JButton("Fechar");
        ver = new JButton("Ver");
        adicionar = new JButton("Adicionar");
        remover = new JButton("Remover");

        listaModel = new DefaultListModel<>();
        listaObras = new JList<>(listaModel);
        listaObras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll = new JScrollPane(listaObras);

        listaObras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { 
                    ver.doClick();            
                }
            }
        });

        JPanel topo = new JPanel(new GridLayout(1, 5, 5, 5));
        topo.add(voltar);
        topo.add(ver);
        topo.add(adicionar);
        topo.add(remover);
        topo.add(fechar);

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        voltar.addActionListener(e -> app.mudaPainel(1));
        fechar.addActionListener(e -> System.exit(0));

        ver.addActionListener(e -> {
            int idx = listaObras.getSelectedIndex();
            if (idx < 0) {
                JOptionPane.showMessageDialog(this, "Selecione uma obra para ver.", "Atenção",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            Obra obra = biblioteca.getObras().get(idx);
            app.abrirObraUnica(obra);
        });

        adicionar.addActionListener(e -> adicionarObra());
        remover.addActionListener(e -> removerObra());

        atualiza();
    }

    public void atualiza() {
        listaModel.clear();
        if (biblioteca != null) {
            List<Obra> obras = biblioteca.getObras();
            for (Obra o : obras) {
                listaModel.addElement(o.getTitulo() + " [" + o.getTipo() + "] - " + o.getGenerosComoString());

            }
        }
    }

    private void adicionarObra() {
        String[] options = new String[] { "Manga", "Manhwa" };

        JComboBox<Genero> generoCombo = new JComboBox<>(
        biblioteca.getGeneros().toArray(new Genero[0]));
        

        String tipo = (String) JOptionPane.showInputDialog(
                this,
                "Escolha o tipo de obra:",
                "Tipo de Obra",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (tipo == null)
            return;

        JTextField tituloF = new JTextField();
        JTextField autorF = new JTextField();
        JTextField anoF = new JTextField();
        JTextField unidadesF = new JTextField();

        JPanel painel = new JPanel(new GridLayout(0, 1));
        painel.add(new JLabel("Título:"));
        painel.add(tituloF);
        painel.add(new JLabel("Autor:"));
        painel.add(autorF);
        painel.add(new JLabel("Ano:"));
        painel.add(anoF);
        painel.add(new JLabel(tipo.equals("Manga") ? "Volumes:" : "Capítulos:"));
        painel.add(unidadesF);
        painel.add(new JLabel("Gênero:"));
        painel.add(generoCombo);


        int result = JOptionPane.showConfirmDialog(this, painel,
                "Adicionar " + tipo, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION)
            return;

        String titulo = tituloF.getText().trim();
        String autor = autorF.getText().trim();
        String anoS = anoF.getText().trim();
        String unidadesS = unidadesF.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Título é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int ano = 0, unidades = 0;
        try {
            ano = Integer.parseInt(anoS);
        } catch (Exception ignored) {
        }

        try {
            unidades = Integer.parseInt(unidadesS);
            if (unidades < 0)
                unidades = 0;
        } catch (Exception ignored) {
        }

        if (tipo.equals("Manga")) {
            Manga m = new Manga(titulo, autor, ano, unidades);
            biblioteca.adicionarObra(m);
            Genero genero = (Genero) generoCombo.getSelectedItem();
            m.setGenero(genero);

        } else {
            Manhwa mw = new Manhwa(titulo, autor, ano, unidades);
            biblioteca.adicionarObra(mw);
        }
        

        atualiza();
    }

    private void removerObra() {
        int idx = listaObras.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma obra para remover.", "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        Obra obra = biblioteca.getObras().get(idx);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Remover \"" + obra.getTitulo() + "\"?",
                "Confirmar remoção",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            biblioteca.removerObra(obra);
            atualiza();
        }
    }
}
