package org.arrecadou.View;

import javax.swing.*;
import java.awt.*;
import org.arrecadou.Controladores.ControllerCoordenador;

public class CadastroCoordenadorView extends JFrame {
    private final ControllerCoordenador controller;
    private JTextField nomeField, cpfField, telefoneField;


    public CadastroCoordenadorView(ControllerCoordenador controller) {
        this.controller = controller;
        setTitle("Cadastro de Coordenador");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initializeUI();
    }

    private void initializeUI() {
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        formPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField(20);
        formPanel.add(nomeField);

        formPanel.add(new JLabel("CPF:"));
        cpfField = new JTextField(20);
        formPanel.add(cpfField);

        formPanel.add(new JLabel("Telefone:"));
        telefoneField = new JTextField(20);
        formPanel.add(telefoneField);

        JButton submitButton = new JButton("Cadastrar");
        formPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            try {
                controller.cadastrarCoordenador(nomeField.getText(), cpfField.getText(), telefoneField.getText());
                JOptionPane.showMessageDialog(this, "Coordenador cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }

        });

        add(formPanel, BorderLayout.CENTER);
    }
}
