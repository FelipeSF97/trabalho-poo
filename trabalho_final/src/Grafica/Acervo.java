package Grafica;

import Dados.Biblioteca;
import Dados.Manga;
import Dados.Manhwa;
import Dados.Obra;
import Dados.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

// ...existing code...
public class Acervo extends JPanel {
    //Pegamos nossa aplicação, usuario e biblioteca
    private Aplicacao app;
    private Usuario usuario;
    private Biblioteca biblioteca;

    //Botões, listas e o scroll do mouse
    private JButton voltar;
    private JButton fechar;
    private JButton ver;
    private JButton adicionar;
    private JButton remover;
    private DefaultListModel<String> lista;
    private JList<String> listaObras;
    private JScrollPane scroll;

    //Construtor
    public Acervo(Aplicacao app, Usuario usuario, Biblioteca biblioteca) {
        super();
        this.app = app;
        this.usuario = usuario;
        this.biblioteca = biblioteca;

        //Criação dos botões
        voltar = new JButton("Volta");
        fechar = new JButton("Fechar");
        ver = new JButton("Ver");
        adicionar = new JButton("Adicionar");
        remover = new JButton("Remover");

        //Criação das listas que serão usadas
        //Lista para add, remove e ver
        //ListaObras para decoração
        lista = new DefaultListModel<>();
        listaObras = new JList<>(lista);
        listaObras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll = new JScrollPane(listaObras);

        //Layout
        setLayout(new BorderLayout(8,8));
        JPanel topo = new JPanel(new GridLayout(1, 5, 5, 5));
        topo.add(voltar);
        topo.add(ver);
        topo.add(adicionar);
        topo.add(remover);
        topo.add(fechar);

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        //Eventos para cada botão
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mudaPainel(1);
            }
        });

        fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = listaObras.getSelectedIndex();
                if (idx < 0) {
                    JOptionPane.showMessageDialog(Acervo.this, "Selecione uma obra para ver.", "Atenção", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Obra obra = biblioteca.getObras().get(idx);
                app.abrirObraUnica(obra); 
            }
        });

        adicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Adicionar();
            }
        });

        remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove();
            }
        });

        atualiza();
    }

    //Atualiza a biblioteca para caso tenha removido ou adicionado uma obra
    public void atualiza() {
        lista.clear();
        if (biblioteca != null) {
            List<Obra> obras = biblioteca.getObras();
            for (Obra o : obras) {
                lista.addElement(o.getTitulo());
            }
        }
    }

    //Mostre os detalhes de uma obra
    private void Detalhes() {
        int index = listaObras.getSelectedIndex();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma obra na lista.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Obra obra = biblioteca.getObras().get(index);
        StringBuilder sb = new StringBuilder();

        
        try {
            sb.append("Título: ").append(obra.getTitulo()).append("\n");
        } catch (Exception ignored) {}

        // Se for Manga, mostra automaticamente seus atributos
        if (obra instanceof Manga) {
            Manga m = (Manga) obra;
            
            try {
                String autor = m.getAutor();
                if (autor != null && !autor.isEmpty()) 
                    sb.append("Autor: ").append(autor).append("\n");
            } catch (Exception ignored) {}
            
            try {
                String sinopse = m.getSinopse();
                if (sinopse != null && !sinopse.isEmpty()) 
                    sb.append("Sinopse: ").append(sinopse).append("\n");
            } catch (Exception ignored) {}
            
            try {
                String distribuidora = m.getDistribuidora();
                if (distribuidora != null && !distribuidora.isEmpty()) 
                    sb.append("Editora: ").append(distribuidora).append("\n");
            } catch (Exception ignored) {}
        } else {
            
            try {
                Manhwa p = (Manhwa) obra;
                String plataforma  = p.getPlataforma();
                if (plataforma != null && !plataforma.isEmpty()) 
                    sb.append("Editora: ").append(plataforma).append("\n");
            } catch (Exception ignored) {}
            
            try {
                String desc = obra.toString();
                if (desc != null && !desc.isEmpty()) 
                    sb.append(desc).append("\n");
            } catch (Exception ignored) {}
        }

        String detalhes = sb.length() > 0 ? sb.toString() : "Sem detalhes disponíveis.";
        JOptionPane.showMessageDialog(this, detalhes, "Detalhes da Obra", JOptionPane.INFORMATION_MESSAGE);
    }

    //Painel para adicionar um novo manga
    //Problema: Não conseguir adicionar Manhwa
    private void Adicionar() {
        JTextField tituloF = new JTextField();
        JTextField autorF = new JTextField();
        JTextField sinopseF = new JTextField();
        JTextField editoraF = new JTextField();

        JPanel painel = new JPanel(new GridLayout(0,1));
        painel.add(new JLabel("Título:"));
        painel.add(tituloF);
        painel.add(new JLabel("Autor:"));
        painel.add(autorF);
        painel.add(new JLabel("Sinopse:"));
        painel.add(sinopseF);
        painel.add(new JLabel("Editora:"));
        painel.add(editoraF);

        int result = JOptionPane.showConfirmDialog(this, painel, "Adicionar Manga", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloF.getText();
            String autor = autorF.getText();
            String sinopse = sinopseF.getText();
            String editora = editoraF.getText();

            if (titulo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Título é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // cria um Manga e adiciona na biblioteca
            Manga m = new Manga(titulo, autor, sinopse, editora);
            biblioteca.adicionarObra(m);
            atualiza();
        }
    }

    private void remove() {
        int idx = listaObras.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma obra para remover.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Obra obra = biblioteca.getObras().get(idx);
        int confirm = JOptionPane.showConfirmDialog(this, "Remover \"" + obra.getTitulo() + "\"?", "Confirmar remoção", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            biblioteca.removerObra(obra);
            atualiza();
        }
    }
}