package Grafica;

import javax.swing.*;
import java.awt.*;
import Dados.Biblioteca;

public class PainelEstatisticas extends JPanel {

    private Biblioteca biblioteca;

    private JLabel lblTotalObras;
    private JLabel lblTotalMangas;
    private JLabel lblTotalManhwas;
    private JLabel lblSomaCapitulos;
    private JLabel lblMediaAvaliacoes;

    public PainelEstatisticas(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setLayout(new GridLayout(6, 1, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        lblTotalObras = new JLabel();
        lblTotalMangas = new JLabel();
        lblTotalManhwas = new JLabel();
        lblSomaCapitulos = new JLabel();
        lblMediaAvaliacoes = new JLabel();

        JButton btnAtualizar = new JButton("Atualizar Estatísticas");
        btnAtualizar.addActionListener(e -> atualizar());

        add(lblTotalObras);
        add(lblTotalMangas);
        add(lblTotalManhwas);
        add(lblSomaCapitulos);
        add(lblMediaAvaliacoes);
        add(btnAtualizar);

        atualizar();
    }

    public void atualizar() {
        long totalMangas = biblioteca.getObras().stream()
                .filter(o -> "Manga".equals(o.getTipo()))
                .count();

        long totalManhwas = biblioteca.getObras().stream()
                .filter(o -> "Manhwa".equals(o.getTipo()))
                .count();

        lblTotalObras.setText("Total de obras no acervo: " + biblioteca.getObras().size());
        lblTotalMangas.setText("Total de Mangás: " + totalMangas);
        lblTotalManhwas.setText("Total de Manhwas: " + totalManhwas);
        lblSomaCapitulos.setText("Somatório total (Capítulos/Volumes): " + biblioteca.somaCapitulo());
        lblMediaAvaliacoes.setText(String.format("Média geral das avaliações: %.2f", biblioteca.mediaAvaliacao()));
    }
}
