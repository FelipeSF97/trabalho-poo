package Grafica;

import Dados.Biblioteca;
import Dados.Obra;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class PainelBuscar extends JPanel {

    private Aplicacao app;
    private Biblioteca biblioteca;

    private JTextField termoField;
    private DefaultListModel<String> model;
    private JList<String> lista;
    private java.util.List<Obra> resultados;

    public PainelBuscar(Aplicacao app, Biblioteca biblioteca) {
        super(new BorderLayout(8,8));
        this.app = app;
        this.biblioteca = biblioteca;

        JPanel topo = new JPanel(new FlowLayout());
        termoField = new JTextField(30);
        JButton btnBuscar = new JButton("Buscar");
        JButton btnVoltar = new JButton("Voltar");
        topo.add(new JLabel("Termo:"));
        topo.add(termoField);
        topo.add(btnBuscar);
        topo.add(btnVoltar);

        model = new DefaultListModel<>();
        lista = new JList<>(model);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(lista);

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscar());
        btnVoltar.addActionListener(e -> app.mudaPainel(2));

        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int idx = lista.getSelectedIndex();
                    if (idx >= 0 && resultados != null && idx < resultados.size()) {
                        app.abrirObraUnica(resultados.get(idx));
                    }
                }
            }
        });
    }

    public void buscar() {
        String termo = termoField.getText().trim().toLowerCase();
        model.clear();
        resultados = biblioteca.getObras().stream()
                .filter(o -> o.getTitulo().toLowerCase().contains(termo) ||
                             o.getAutor().toLowerCase().contains(termo) ||
                             o.getTipo().toLowerCase().contains(termo))
                .collect(Collectors.toList());

        for (Obra o : resultados) {
            model.addElement(o.getTitulo() + " [" + o.getTipo() + "] - " + o.getGenerosComoString());
        }

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum resultado encontrado.", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void atualizarResultados() {
        termoField.setText("");
        model.clear();
    }
}
