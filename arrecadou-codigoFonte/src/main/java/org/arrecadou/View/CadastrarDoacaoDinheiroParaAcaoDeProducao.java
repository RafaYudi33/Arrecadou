package org.arrecadou.View;
import org.arrecadou.Controladores.ControllerAcaoProducao;
import org.arrecadou.Model.AcaoContribuicaoDireta;
import org.arrecadou.Model.AcaoProducao;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("DuplicatedCode")
public class CadastrarDoacaoDinheiroParaAcaoDeProducao extends JFrame {
    private JTextField telefoneDoadorField;
    private JTextField nomeDoadorField;
    private JCheckBox anonimidadeSimCheckBox, anonimidadeNaoCheckBox;
    private final ControllerAcaoProducao controllerAcaoProducao;

    public CadastrarDoacaoDinheiroParaAcaoDeProducao(ControllerAcaoProducao controllerAcaoProducao) {
        this.controllerAcaoProducao = controllerAcaoProducao;
        setTitle("Cadastro de Doação em Dinheiro para Ação de Produção");
        setSize(600, 300);
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
        JPanel doadorPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        telefoneDoadorField = new JTextField();
        nomeDoadorField = new JTextField();
        JTextField valorField = new JTextField();

        anonimidadeSimCheckBox = new JCheckBox("Sim");
        anonimidadeNaoCheckBox = new JCheckBox("Não");
        anonimidadeNaoCheckBox.setSelected(true);

        doadorPanel.add(new JLabel("Telefone do Doador:"));
        doadorPanel.add(telefoneDoadorField);

        doadorPanel.add(new JLabel("Nome do Doador:"));
        doadorPanel.add(nomeDoadorField);

        doadorPanel.add(new JLabel("Valor da Doação:"));
        doadorPanel.add(valorField);

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
                nomeDoadorField.setEnabled(true);
                telefoneDoadorField.setEnabled(true);
                isAnonimo.set(false);
            }
        });

        cadastrarButton.addActionListener(e -> {
            try {
                AcaoProducao acaoSelecionada = acoesList.getSelectedValue();
                controllerAcaoProducao.cadastrarDoacaoDinheiro(
                        telefoneDoadorField.getText(),
                        nomeDoadorField.getText(),
                        isAnonimo.get(),
                        Double.parseDouble(valorField.getText()),
                        acaoSelecionada
                );
                System.out.println(isAnonimo.get());
                JOptionPane.showMessageDialog(this, "Doação cadastrada com sucesso!", "Cadastro Completo", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

}
