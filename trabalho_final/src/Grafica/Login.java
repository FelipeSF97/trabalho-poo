package Grafica;

import Dados.Usuario;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {
    private JButton confirmar;
    private JButton sair;

    public Login(Aplicacao aplicacao, Usuario usuario) {
        super();
        this.setLayout(new BorderLayout(8,8));

        JLabel rotulo = new JLabel("<html><h1>Bem vindo ao LerMangas!</h1></html>", SwingConstants.CENTER);
        this.add(rotulo, BorderLayout.NORTH);

        confirmar = new JButton("Entrar");
        sair = new JButton("Sair");

        JPanel centro = new JPanel(new FlowLayout());
        centro.add(confirmar);
        centro.add(sair);
        this.add(centro, BorderLayout.CENTER);

        confirmar.addActionListener(e -> aplicacao.mudaPainel(2));
        sair.addActionListener(e -> System.exit(0));
    }
}
