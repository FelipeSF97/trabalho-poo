package Grafica;

import Dados.Biblioteca;
import Dados.Usuario;

import javax.swing.*;
import java.awt.*;

public class PainelCadastroUsuario extends JPanel {

    private Aplicacao app;
    private Biblioteca biblioteca;

    private JTextField nomeField;
    private JTextField cpfField;
    private JButton btnSalvar;
    private JButton btnVoltar;

    public PainelCadastroUsuario(Aplicacao app, Biblioteca biblioteca) {
        super(new BorderLayout(8,8));
        this.app = app;
        this.biblioteca = biblioteca;

        JPanel form = new JPanel(new GridLayout(0,1,5,5));
        nomeField = new JTextField();
        cpfField = new JTextField();

        form.add(new JLabel("Nome:"));
        form.add(nomeField);
        form.add(new JLabel("CPF:"));
        form.add(cpfField);

        btnSalvar = new JButton("Salvar");
        btnVoltar = new JButton("Voltar");

        JPanel botoes = new JPanel(new FlowLayout());
        botoes.add(btnSalvar);
        botoes.add(btnVoltar);

        add(form, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> salvar());
        btnVoltar.addActionListener(e -> app.mudaPainel(2));
    }

    private void salvar() {
        String nome = nomeField.getText().trim();
        String cpf = cpfField.getText().trim();

        if (nome.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e CPF são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!cpf.matches("\\d{11}")) {
            JOptionPane.showMessageDialog(this,
                    "CPF deve conter apenas números e ter 11 dígitos.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (biblioteca.usuarioCadastrado(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario u = new Usuario(nome, cpf);
        biblioteca.adicionarUsuario(u);

        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        nomeField.setText("");
        cpfField.setText("");

        app.mudaPainel(2);
    }
}
