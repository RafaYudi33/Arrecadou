package org.arrecadou.View;

import org.arrecadou.Controladores.ControllerAcaoProducao;
import org.arrecadou.Model.AcaoProducao;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("DuplicatedCode")
public class CadastrarDoacaoItemAcaoDeProducaoView extends JFrame {

    private JTextField telefoneDoadorField;
    private JTextField nomeDoadorField;
    private JTextField nomeItemField;
    private JTextField quantidadeField;
    private JCheckBox anonimidadeSimCheckBox, anonimidadeNaoCheckBox;
    private final ControllerAcaoProducao controllerAcaoProducao;

    public CadastrarDoacaoItemAcaoDeProducaoView(ControllerAcaoProducao controllerAcaoProducao) {
        this.controllerAcaoProducao = controllerAcaoProducao;
        setTitle("Cadastro de Doação de Item para Ação de Produção");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(5, 5));
        JPanel acoesPanel = new JPanel(new BorderLayout());
        acoesPanel.add(new JLabel("Selecione uma Ação"), BorderLayout.NORTH);

        DefaultListModel<AcaoProducao> acoesModel = new DefaultListModel<>();
        controllerAcaoProducao.listarTodasAcoesProducao().forEach(acoesModel::addElement);

        JList<AcaoProducao> acoesList = new JList<>(acoesModel);
        acoesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(acoesList);
        acoesPanel.add(listScroller, BorderLayout.CENTER);

        add(acoesPanel, BorderLayout.WEST);
        JPanel doadorPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        telefoneDoadorField = new JTextField();
        nomeDoadorField = new JTextField();
        nomeItemField = new JTextField();
        quantidadeField = new JTextField();

        anonimidadeSimCheckBox = new JCheckBox("Sim");
        anonimidadeNaoCheckBox = new JCheckBox("Não");
        anonimidadeNaoCheckBox.setSelected(true);

        doadorPanel.add(new JLabel("Telefone do Doador:"));
        doadorPanel.add(telefoneDoadorField);

        doadorPanel.add(new JLabel("Nome do Doador:"));
        doadorPanel.add(nomeDoadorField);

        doadorPanel.add(new JLabel("Nome do Item:"));
        doadorPanel.add(nomeItemField);

        doadorPanel.add(new JLabel("Quantidade (kg):"));
        doadorPanel.add(quantidadeField);

        doadorPanel.add(new JLabel("Manter Anonimato do Doador:"));
        JPanel anonimidadePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        anonimidadePanel.add(anonimidadeSimCheckBox);
        anonimidadePanel.add(anonimidadeNaoCheckBox);
        doadorPanel.add(anonimidadePanel);

        add(doadorPanel, BorderLayout.CENTER);

        JButton cadastrarButton = new JButton("Cadastrar Doação");
        add(cadastrarButton, BorderLayout.SOUTH);

        AtomicBoolean isAnonimo = new AtomicBoolean(false);

        anonimidadeSimCheckBox.addActionListener(e -> {
            if (anonimidadeSimCheckBox.isSelected()) {
                anonimidadeNaoCheckBox.setSelected(false);
                isAnonimo.set(true);
            }
        });

        anonimidadeNaoCheckBox.addActionListener(e -> {
            if (anonimidadeNaoCheckBox.isSelected()) {
                anonimidadeSimCheckBox.setSelected(false);
                isAnonimo.set(false);
            }
        });

        cadastrarButton.addActionListener(e -> {
            try {
                AcaoProducao acaoSelecionada = acoesList.getSelectedValue();
                if (acaoSelecionada == null) {
                    throw new RuntimeException("Selecione uma ação para associar a doação.");
                }

                String telefoneDoador = telefoneDoadorField.getText();
                String nomeDoador = nomeDoadorField.getText();
                String nomeItem = nomeItemField.getText();
                int quantidadeEmKg = Integer.parseInt(quantidadeField.getText());

                controllerAcaoProducao.cadastrarDoacaoDeItem(
                        telefoneDoador,
                        nomeDoador,
                        isAnonimo.get(),
                        nomeItem,
                        quantidadeEmKg,
                        acaoSelecionada
                );

                JOptionPane.showMessageDialog(this, "Doação cadastrada com sucesso!", "Cadastro Completo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
