package org.arrecadou.View;

import org.arrecadou.Controladores.ControllerEntidade;

import javax.swing.*;

public class CadastrarEntidade extends JFrame {
    private final JTextField nomeField;
    private final JTextField cnpjField;
    private final JTextField telefoneField;
    private final JTextField logradouroField;
    private final JTextField bairroField;
    private final JTextField cidadeField;
    private final JTextField ufField;
    private final JTextField cepField;
    private final JTextField numeroField;
    private final JButton cadastrarButton;
    private final ControllerEntidade controllerEntidade;

    public CadastrarEntidade(ControllerEntidade controllerEntidade) {
        this.controllerEntidade = controllerEntidade;

        setTitle("Cadastrar Entidade");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        nomeField = new JTextField(20);
        cnpjField = new JTextField(20);
        telefoneField = new JTextField(20);
        logradouroField = new JTextField(20);
        bairroField = new JTextField(20);
        cidadeField = new JTextField(20);
        ufField = new JTextField(20);
        cepField = new JTextField(20);
        numeroField = new JTextField(20);
        cadastrarButton = new JButton("Cadastrar");

        cadastrarButton.addActionListener(e -> cadastrarEntidade());

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblCnpj = new JLabel("CNPJ:");
        JLabel lblTelefone = new JLabel("Telefone:");
        JLabel lblLogradouro = new JLabel("Logradouro:");
        JLabel lblBairro = new JLabel("Bairro:");
        JLabel lblCidade = new JLabel("Cidade:");
        JLabel lblUf = new JLabel("UF:");
        JLabel lblCep = new JLabel("CEP:");
        JLabel lblNumero = new JLabel("Número:");

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(lblNome)
                                .addComponent(lblCnpj)
                                .addComponent(lblTelefone)
                                .addComponent(lblLogradouro)
                                .addComponent(lblBairro)
                                .addComponent(lblCidade)
                                .addComponent(lblUf)
                                .addComponent(lblCep)
                                .addComponent(lblNumero))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(nomeField)
                                .addComponent(cnpjField)
                                .addComponent(telefoneField)
                                .addComponent(logradouroField)
                                .addComponent(bairroField)
                                .addComponent(cidadeField)
                                .addComponent(ufField)
                                .addComponent(cepField)
                                .addComponent(numeroField)
                                .addComponent(cadastrarButton))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNome)
                                .addComponent(nomeField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblCnpj)
                                .addComponent(cnpjField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTelefone)
                                .addComponent(telefoneField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblLogradouro)
                                .addComponent(logradouroField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblBairro)
                                .addComponent(bairroField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblCidade)
                                .addComponent(cidadeField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUf)
                                .addComponent(ufField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblCep)
                                .addComponent(cepField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(lblNumero)
                                .addComponent(numeroField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(cadastrarButton))
        );

        add(panel);
    }

    private void cadastrarEntidade() {
        try {
            String nome = nomeField.getText();
            String cnpj = cnpjField.getText();
            String telefone = telefoneField.getText();
            String logradouro = logradouroField.getText();
            String bairro = bairroField.getText();
            String cidade = cidadeField.getText();
            String uf = ufField.getText();
            String cep = cepField.getText();
            String numero = numeroField.getText();

            controllerEntidade.cadastrarEntidade(nome, cnpj, telefone, logradouro, bairro, cidade, uf, cep, numero);

            JOptionPane.showMessageDialog(this,
                    "Entidade cadastrada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            MainScreen ms = new MainScreen();
            ms.setVisible(true);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar entidade: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
