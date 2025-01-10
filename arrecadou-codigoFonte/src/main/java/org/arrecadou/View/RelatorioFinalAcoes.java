package org.arrecadou.View;

import org.arrecadou.Controladores.ControllerAcaoContribuicaoDireta;
import org.arrecadou.Controladores.ControllerAcaoProducao;
import org.arrecadou.Controladores.ControllerRelatorios;
import org.arrecadou.Model.Acao;
import org.arrecadou.Model.AcaoContribuicaoDireta;
import org.arrecadou.Model.AcaoProducao;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class RelatorioFinalAcoes extends JFrame {

    private final ControllerRelatorios controllerRelatorios;
    private final ControllerAcaoProducao controllerAcaoProducao;
    private final ControllerAcaoContribuicaoDireta controllerAcaoContribuicao;

    public RelatorioFinalAcoes(
            ControllerRelatorios controllerRelatorios,
            ControllerAcaoProducao controllerAcaoProducao,
            ControllerAcaoContribuicaoDireta controllerAcaoContribuicao
    ) {
        this.controllerRelatorios = controllerRelatorios;
        this.controllerAcaoProducao = controllerAcaoProducao;
        this.controllerAcaoContribuicao = controllerAcaoContribuicao;

        setTitle("Relatório Final de Ações");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));

        JPanel listagemPanel = new JPanel(new BorderLayout());
        listagemPanel.add(new JLabel("Selecione uma ação para gerar o relatório:"), BorderLayout.NORTH);

        DefaultListModel<Object> acoesModel = new DefaultListModel<>();

        List<AcaoContribuicaoDireta> contribuicoesDiretas = controllerAcaoContribuicao.listarTodasAcoesContribuicaoDireta();
        contribuicoesDiretas.forEach(acoesModel::addElement);

        List<AcaoProducao> acoesProducao = controllerAcaoProducao.listarTodasAcoesProducao();
        acoesProducao.forEach(acoesModel::addElement);

        JList<Object> acoesList = new JList<>(acoesModel);
        acoesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(acoesList);
        listagemPanel.add(listScroller, BorderLayout.CENTER);

        add(listagemPanel, BorderLayout.CENTER);

        // Botão para selecionar pasta
        JButton selecionarPastaButton = new JButton("Selecionar Pasta para Salvar Relatório");
        JTextField pastaField = new JTextField();
        pastaField.setEditable(false);
        JPanel pastaPanel = new JPanel(new BorderLayout());
        pastaPanel.add(pastaField, BorderLayout.CENTER);
        pastaPanel.add(selecionarPastaButton, BorderLayout.EAST);

        // Campo para o valor total das vendas
        JPanel valorTotalPanel = new JPanel(new BorderLayout());
        JLabel valorTotalLabel = new JLabel("Valor Total das Vendas: ");
        JTextField valorTotalField = new JTextField(10);
        valorTotalField.setEditable(true);
        valorTotalPanel.add(valorTotalLabel, BorderLayout.WEST);
        valorTotalPanel.add(valorTotalField, BorderLayout.CENTER);

        // Botão para gerar relatório
        JButton gerarRelatorioButton = new JButton("Gerar Relatório");
        JPanel botoesPanel = new JPanel(new BorderLayout());
        botoesPanel.add(pastaPanel, BorderLayout.NORTH);
        botoesPanel.add(valorTotalPanel, BorderLayout.CENTER);
        botoesPanel.add(gerarRelatorioButton, BorderLayout.SOUTH);

        add(botoesPanel, BorderLayout.SOUTH);

        // Ações dos botões
        selecionarPastaButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();
                pastaField.setText(selectedFolder.getAbsolutePath());
            }
        });

        gerarRelatorioButton.addActionListener(e -> {
            Acao acaoSelecionada = (Acao) acoesList.getSelectedValue();
            String caminhoPasta = pastaField.getText();
            String valorTotal = valorTotalField.getText();

            if (acaoSelecionada == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma ação para gerar o relatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (caminhoPasta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecione uma pasta para salvar o relatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (valorTotal.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o valor total das vendas!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double valorTotalVendas = Double.parseDouble(valorTotalField.getText());
                controllerRelatorios.gerarRelatorioPDF(acaoSelecionada, caminhoPasta, valorTotalVendas);
                JOptionPane.showMessageDialog(this, "Relatório gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao gerar relatório: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
