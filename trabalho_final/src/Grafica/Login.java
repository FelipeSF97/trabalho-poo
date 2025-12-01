package Grafica;

import Dados.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JPanel {
    private JButton confirmar;
    private JButton sair;

    //Construtor
    public Login(Aplicacao aplicacao, Usuario usuario) {
        super();

        //Criando os botões e layout
        GridLayout layout = new GridLayout(2,1);
        this.setLayout(layout);
        JLabel rotulo = new JLabel("Bem vindo ao LerMangas!");
        confirmar = new JButton("Entrar");
        sair = new JButton("Sair");
        JPanel pRotulo = new JPanel();
        pRotulo.add(rotulo);
        this.add(pRotulo);
        
        JPanel pUsuario = new JPanel(new GridLayout(3,2));
        pUsuario.add(confirmar);
        pUsuario.add(sair);
        this.add(pUsuario);

        //Avançar
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicacao.mudaPainel(2);
            }
        });

        //Fechar
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
