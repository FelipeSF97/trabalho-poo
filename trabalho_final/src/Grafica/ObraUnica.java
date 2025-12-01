package Grafica;

import Dados.Obra;
import Dados.Usuario;
import Dados.Capitulo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObraUnica extends JPanel {

    private final Aplicacao app;
    private Obra obra;
    private final DefaultListModel<String> modelo;
    private final JList<String> lista;
    private final List<Integer> chaves;

    private final JLabel Titulo;
    private final JLabel Autor;

    private JButton favoritar;

    public ObraUnica(Aplicacao app) {
        super(new BorderLayout(8,8));
        this.app = app;
        this.modelo = new DefaultListModel<>();
        this.lista = new JList<>(modelo);
        this.chaves = new ArrayList<>();

        this.Titulo = new JLabel("");
        this.Autor = new JLabel("");

        iniciar();
    }

    private void iniciar() {
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(lista);

        JButton adicionar = new JButton("Adicionar");
        JButton remover = new JButton("Remover");
        JButton voltar = new JButton("Voltar");
        JButton ler = new JButton("Ler");
        favoritar = new JButton("Favoritar");

        JPanel header = new JPanel(new BorderLayout(6,6));
        JPanel labels = new JPanel(new GridLayout(2,1));
        Titulo.setFont(Titulo.getFont().deriveFont(Font.BOLD, 14f));
        labels.add(Titulo);
        labels.add(Autor);
        header.add(labels, BorderLayout.NORTH);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        botoes.add(ler);
        botoes.add(adicionar);
        botoes.add(remover);
        botoes.add(voltar);
        botoes.add(favoritar);

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        ler.addActionListener(e -> ler());
        adicionar.addActionListener(e -> Adicionar());
        remover.addActionListener(e -> Remover());
        voltar.addActionListener(e -> app.mudaPainel(2));
        favoritar.addActionListener(e -> toggleFavorito());
    }

    public void setObra(Obra obra) {
        this.obra = obra;
        if (obra != null) {
            Titulo.setText("Título: " + obra.getTitulo());
            Autor.setText("Autor: " + obra.getAutor());
        } else {
            Titulo.setText("");
            Autor.setText("");
        }
        if (obra != null && app.getUsuario().isFavorito(obra)) {
            favoritar.setText("Desfavoritar");
        } else {
            favoritar.setText("Favoritar");
        }
    }

    public void atualizarLista() {
        modelo.clear();
        chaves.clear();
        if (obra == null) return;
        Map<Integer, Capitulo> mapa = obra.getCapitulosMap();
        for (Map.Entry<Integer, Capitulo> en : mapa.entrySet()) {
            Integer num = en.getKey();
            Capitulo c = en.getValue();
            chaves.add(num);
            modelo.addElement(num + " - " + c.getTitulo() + " (" + c.getNumPaginas() + " páginas)");
        }
    }

    private void Adicionar() {
        if (obra == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma obra selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextField tituloF = new JTextField();
        JTextField paginasF = new JTextField();
        JPanel p = new JPanel(new GridLayout(0,1));
        p.add(new JLabel("Título do capítulo:"));
        p.add(tituloF);
        p.add(new JLabel("Número de páginas:"));
        p.add(paginasF);

        int res = JOptionPane.showConfirmDialog(this, p, "Novo Capítulo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res != JOptionPane.OK_OPTION) return;

        String titulo = tituloF.getText().trim();
        String numPag = paginasF.getText().trim();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Título obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int paginas;
        try {
            paginas = Integer.parseInt(numPag);
            if (paginas <= 0) throw new NumberFormatException();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Número de páginas inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Capitulo criado = obra.criarCapitulo(titulo, paginas);
        if (criado == null) {
            JOptionPane.showMessageDialog(this, "Não foi possível criar o capítulo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        atualizarLista();
    }

    private void Remover() {
        if (obra == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma obra selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int idx = lista.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um capítulo para remover.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int numero = chaves.get(idx);
        Capitulo cap = obra.getCapitulo(numero);
        int ctz = JOptionPane.showConfirmDialog(this, "Remover capítulo \"" + (cap != null ? cap.getTitulo() : numero) + "\"?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ctz != JOptionPane.YES_OPTION) return;

        Capitulo removido = obra.removerCapitulo(numero);
        if (removido == null) {
            JOptionPane.showMessageDialog(this, "Erro ao remover capítulo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        atualizarLista();
    }

    public void ler(){
        JOptionPane.showMessageDialog(this, "Você (não) está lendo o capitulo!", "Capitulo", JOptionPane.WARNING_MESSAGE);
    }

    private void toggleFavorito() {
    if (obra == null) return;

    Usuario u = app.getUsuario(); // Crie um getter em Aplicacao
    if (u.isFavorito(obra)) {
        u.desfavoritar(obra);
        JOptionPane.showMessageDialog(this, "Obra removida dos favoritos!");
        favoritar.setText("Favoritar");
    } else {
        u.favoritar(obra);
        JOptionPane.showMessageDialog(this, "Obra adicionada aos favoritos!");
        favoritar.setText("Desfavoritar");
    }
}

}
