package org.arrecadou.View;

import org.arrecadou.Controladores.ControllerAcaoProducao;
import org.arrecadou.Controladores.ControllerCoordenador;
import org.arrecadou.Model.Colaborador;
import org.arrecadou.Model.Coordenador;
import org.arrecadou.Model.ItemEsperado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("DuplicatedCode")
public class CadastrarAcaoDeProducaoView extends JFrame {
    private JTable itensTable, colaboradoresTable;
    private DefaultTableModel itensTableModel, colaboradoresTableModel;
    private JButton addItemButton, removeItemButton, salvarButton, addColaboradorButton, removeColaboradorButton;
    private JTextField quantidadeField, valorField, nomeField, nomeAcaoField, dataFimField, dataInicioField, descricaoField, objetivoField;
    JTextField nomeColaboradorField, emailColaboradorField, telefoneColaboradorField;
    JList<Coordenador> coordenadoresList;
    private final ControllerAcaoProducao controllerAcaoProducao;

    public CadastrarAcaoDeProducaoView(ControllerCoordenador controllerCoordenador, ControllerAcaoProducao controllerAcaoProducao) {
        setTitle("Cadastro de Ação de Produção");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        this.controllerAcaoProducao = controllerAcaoProducao;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeUI(controllerCoordenador.listarTodosCoordenadores());
    }

    private void initializeUI(List<Coordenador> coordenadoresDisponiveis) {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        nomeAcaoField = new JTextField();
        descricaoField = new JTextField();
        objetivoField = new JTextField();
        dataInicioField = new JTextField("YYYY-MM-DD");
        dataFimField = new JTextField("YYYY-MM-DD");

        topPanel.add(new JLabel("Nome:"));
        topPanel.add(nomeAcaoField);
        topPanel.add(new JLabel("Descrição:"));
        topPanel.add(descricaoField);
        topPanel.add(new JLabel("Objetivo da Ação:"));
        topPanel.add(objetivoField);
        topPanel.add(new JLabel("Data Início:"));
        topPanel.add(dataInicioField);
        topPanel.add(new JLabel("Data Fim:"));
        topPanel.add(dataFimField);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(new JLabel("Selecionar Coordenadores:"), BorderLayout.NORTH);

        DefaultListModel<Coordenador> coordenadoresModel = new DefaultListModel<>();
        coordenadoresDisponiveis.forEach(coordenadoresModel::addElement);
        coordenadoresList = new JList<>(coordenadoresModel);
        coordenadoresList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane coordenadoresScroller = new JScrollPane(coordenadoresList);
        centerPanel.add(coordenadoresScroller, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.WEST);

        // Painel principal para as tabelas
        JPanel tablesPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        // Configurar a tabela de itens esperados
        JPanel itensPanel = new JPanel(new BorderLayout(10, 10));
        itensPanel.add(new JLabel("Itens Esperados:"), BorderLayout.NORTH);
        itensTableModel = new DefaultTableModel(new Object[]{"Nome", "Quantidade (kg)", "Valor do kg"}, 0);
        itensTable = new JTable(itensTableModel);
        JScrollPane itensScroller = new JScrollPane(itensTable);
        itensPanel.add(itensScroller, BorderLayout.CENTER);

        JPanel itensButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addItemButton = new JButton("Adicionar Item");
        removeItemButton = new JButton("Remover Item");
        itensButtonsPanel.add(addItemButton);
        itensButtonsPanel.add(removeItemButton);
        itensPanel.add(itensButtonsPanel, BorderLayout.SOUTH);

        tablesPanel.add(itensPanel);

        JPanel colaboradoresPanel = new JPanel(new BorderLayout(10, 10));
        colaboradoresPanel.add(new JLabel("Colaboradores:"), BorderLayout.NORTH);
        colaboradoresTableModel = new DefaultTableModel(new Object[]{"Nome", "Telefone", "Email"}, 0);
        colaboradoresTable = new JTable(colaboradoresTableModel);
        JScrollPane colaboradoresScroller = new JScrollPane(colaboradoresTable);
        colaboradoresPanel.add(colaboradoresScroller, BorderLayout.CENTER);

        JPanel colaboradoresButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addColaboradorButton = new JButton("Adicionar Colaborador");
        removeColaboradorButton = new JButton("Remover Colaborador");
        colaboradoresButtonsPanel.add(addColaboradorButton);
        colaboradoresButtonsPanel.add(removeColaboradorButton);
        colaboradoresPanel.add(colaboradoresButtonsPanel, BorderLayout.SOUTH);

        tablesPanel.add(colaboradoresPanel);
        add(tablesPanel, BorderLayout.CENTER);

        salvarButton = new JButton("Salvar Ação");
        add(salvarButton, BorderLayout.SOUTH);

        configureItemButtons();
        configureColaboradorButtons();
        configSalvarBtn();
    }

    private void configureColaboradorButtons() {
        addColaboradorButton.addActionListener(e -> {
            nomeColaboradorField = new JTextField();
            emailColaboradorField = new JTextField();
            telefoneColaboradorField = new JTextField();

            Object[] message = {
                    "Nome do Colaborador:", nomeColaboradorField,
                    "email:", emailColaboradorField,
                    "telefone", telefoneColaboradorField
            };

            int option = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Adicionar Colaborador",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                try {
                    String nome = nomeColaboradorField.getText();
                    String email = emailColaboradorField.getText();
                    String telefone = telefoneColaboradorField.getText();

                    if (nome.isEmpty() || email.isEmpty()|| telefone.isEmpty()) {
                        throw new IllegalArgumentException("Todos os campos devem ser preenchidos corretamente.");
                    }

                    colaboradoresTableModel.addRow(new Object[]{nome, email, telefone});
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar colaborador: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeColaboradorButton.addActionListener(e -> {
            int selectedRow = colaboradoresTable.getSelectedRow();
            if (selectedRow != -1) {
                colaboradoresTableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover.", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    private void configureItemButtons() {
        addItemButton.addActionListener(e -> {

             nomeField = new JTextField();
             quantidadeField = new JTextField();
             valorField = new JTextField();

            Object[] message = {
                    "Nome do Item:", nomeField,
                    "Quantidade (kg):", quantidadeField,
                    "Valor do kg:", valorField
            };

            int option = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Adicionar Item",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                try {
                    String nomeItem = nomeField.getText().trim();
                    double quantidade = Double.parseDouble(quantidadeField.getText().trim());
                    double valor = Double.parseDouble(valorField.getText().trim());

                    if (nomeItem.isEmpty() || quantidade <= 0 || valor <= 0) {
                        throw new IllegalArgumentException("Todos os campos devem ser preenchidos corretamente.");
                    }

                    itensTableModel.addRow(new Object[]{nomeItem, quantidade, valor});
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao adicionar item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeItemButton.addActionListener(e -> {
            int selectedRow = itensTable.getSelectedRow();
            if (selectedRow != -1) {
                itensTableModel.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma linha para remover.", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public void configSalvarBtn(){
        salvarButton.addActionListener(e -> {
            try {
                // Coletar os dados dos campos
                String nome = nomeAcaoField.getText();
                String descricao = descricaoField.getText();
                String objetivo = objetivoField.getText();

                LocalDate dataInicio = LocalDate.parse(dataInicioField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate dataFim = LocalDate.parse(dataFimField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);

                List<Coordenador> coordenadoresSelecionados = coordenadoresList.getSelectedValuesList();


                if (coordenadoresSelecionados.isEmpty()) {
                    throw new IllegalArgumentException("Selecione ao menos um coordenador.");
                }

                int rowCount = itensTableModel.getRowCount();
                if (rowCount == 0) {
                    throw new IllegalArgumentException("Adicione ao menos um item esperado.");
                }

                List<ItemEsperado> itensEsperados = new ArrayList<>();
                for (int i = 0; i < rowCount; i++) {
                    String nomeItem = (String) itensTableModel.getValueAt(i, 0);
                    int quantidade = Double.valueOf((double)itensTableModel.getValueAt(i, 1)).intValue();
                    double valor = (Double) itensTableModel.getValueAt(i, 2);
                    itensEsperados.add(controllerAcaoProducao.cadastrarItemEsperado(nomeItem, quantidade, valor));
                }

                List<Colaborador> colaboradores = new ArrayList<>();
                for (int i = 0; i < colaboradoresTableModel.getRowCount(); i++) {
                    String nomeColaborador= String.valueOf(colaboradoresTableModel.getValueAt(i, 0));
                    String email = String.valueOf(colaboradoresTableModel.getValueAt(i, 1)) ;
                    String telefone = String.valueOf(colaboradoresTableModel.getValueAt(i, 2));
                    colaboradores.add(controllerAcaoProducao.cadastrarColaborador(nomeColaborador, email, telefone));
                }

                controllerAcaoProducao.cadastrarAcaoProducao(
                        coordenadoresSelecionados,
                        dataFim.atStartOfDay(),
                        dataInicio.atStartOfDay(),
                        objetivo,
                        descricao,
                        nome,
                        colaboradores,
                        itensEsperados
                );

                JOptionPane.showMessageDialog(this, "Ação de Produção cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao salvar ação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        });
    }
}
