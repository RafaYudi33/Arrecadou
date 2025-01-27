package org.arrecadou.View;

import javax.swing.*;
import java.awt.*;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.arrecadou.Controladores.ControllerCoordenador;

public class CadastroCoordenadorView extends JFrame {
    private final ControllerCoordenador controller;
    private JTextField nomeField, cpfField, telefoneField;

    public CadastroCoordenadorView(ControllerCoordenador controller) {
        this.controller = controller;
        setTitle("Cadastro de Coordenador");
        setSize(350, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initializeUI();
    }

    private void initializeUI() {
        // Painel principal com borda e espaçamento refinado
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        FlatSVGIcon img = new FlatSVGIcon("icons/coordenador2.svg", 100, 100);
        img.setColorFilter(null);
        JLabel imgLabel = new JLabel(img);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(imgLabel, BorderLayout.NORTH);


        // **Formulário central**
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Reduz espaço entre componentes
        gbc.anchor = GridBagConstraints.WEST; // Alinhamento à esquerda
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        // Labels e Campos
        JLabel nomeLabel = new JLabel("Nome:");
        nomeField = new JTextField(20);
        formPanel.add(nomeLabel, gbc);
        gbc.gridy = 1;
        formPanel.add(nomeField, gbc);

        gbc.gridy = 2;
        JLabel cpfLabel = new JLabel("CPF:");
        cpfField = new JTextField(20);
        formPanel.add(cpfLabel, gbc);
        gbc.gridy = 3;
        formPanel.add(cpfField, gbc);

        gbc.gridy = 4;
        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneField = new JTextField(20);
        formPanel.add(telefoneLabel, gbc);
        gbc.gridy = 5;
        formPanel.add(telefoneField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        JButton submitButton = new JButton("Cadastrar");
        submitButton.setPreferredSize(new Dimension(150, 30));
        UIutils.styleButton(submitButton);


        submitButton.addActionListener(e -> {
            try {
                controller.cadastrarCoordenador(nomeField.getText(), cpfField.getText(), telefoneField.getText());
                JOptionPane.showMessageDialog(this, "Coordenador cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
