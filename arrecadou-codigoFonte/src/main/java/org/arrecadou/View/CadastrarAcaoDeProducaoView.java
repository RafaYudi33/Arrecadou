package org.arrecadou.View;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.arrecadou.Controladores.ControllerAcaoProducao;
import org.arrecadou.Controladores.ControllerCoordenador;
import org.arrecadou.Model.Colaborador;
import org.arrecadou.Model.Coordenador;
import org.arrecadou.Model.ItemEsperado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("DuplicatedCode")
public class CadastrarAcaoDeProducaoView extends JFrame {
    private JTable itensTable, colaboradoresTable;
    private DefaultTableModel itensTableModel, colaboradoresTableModel;
    private JButton addItemButton, removeItemButton, salvarButton, addColaboradorButton, removeColaboradorButton;
    private JTextField quantidadeField, valorField, nomeField, nomeAcaoField, descricaoField, objetivoField;
    private JFormattedTextField dataFimField, dataInicioField;
    JTextField nomeColaboradorField, emailColaboradorField, telefoneColaboradorField;
    JList<Coordenador> coordenadoresList;
    private final ControllerAcaoProducao controllerAcaoProducao;
    private static final FlatSVGIcon iconAdd = new FlatSVGIcon("icons/addIcon.svg", 16, 16);
    private static final FlatSVGIcon iconRemove = new FlatSVGIcon("icons/removeIcon.svg", 16, 16);

    public CadastrarAcaoDeProducaoView(ControllerCoordenador controllerCoordenador, ControllerAcaoProducao controllerAcaoProducao) throws ParseException {
        setTitle("Cadastro de Ação de Produção");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        this.controllerAcaoProducao = controllerAcaoProducao;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeUI(controllerCoordenador.listarTodosCoordenadores());
    }

    private void initializeUI(List<Coordenador> coordenadoresDisponiveis) throws ParseException {
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new GridBagLayout());
        nomeAcaoField = new JTextField();
        descricaoField = new JTextField();
        objetivoField = new JTextField();

        MaskFormatter dateMask = new MaskFormatter("##/##/####");
        dateMask.setPlaceholderCharacter('_');

        dataInicioField = new JFormattedTextField(dateMask);
        dataFimField = new JFormattedTextField(dateMask);

        descricaoField.setPreferredSize(new Dimension(460, 30));
        dataFimField.setPreferredSize(new Dimension(460, 30));
        dataInicioField.setPreferredSize(new Dimension(200, 30));
        nomeAcaoField.setPreferredSize(new Dimension(200, 30));
        objetivoField.setPreferredSize(new Dimension(727, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(2, 2, 6, 2);
        double labelWeight = 0.2;
        double fieldWeight = 0.8;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = labelWeight;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = fieldWeight;
        topPanel.add(nomeAcaoField, gbc);

        gbc.gridx = 2;
        gbc.weightx = labelWeight;
        topPanel.add(new JLabel("Descrição:"), gbc);

        gbc.gridx = 3;
        gbc.weightx = fieldWeight;
        topPanel.add(descricaoField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = labelWeight;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(new JLabel("Data Início:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = fieldWeight;
        topPanel.add(dataInicioField, gbc);

        gbc.gridx = 2;
        gbc.weightx = labelWeight;
        topPanel.add(new JLabel("Data Fim:"), gbc);

        gbc.gridx = 3;
        gbc.weightx = fieldWeight;
        topPanel.add(dataFimField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = labelWeight;
        gbc.anchor = GridBagConstraints.WEST;
        topPanel.add(new JLabel("Objetivo da Ação:"), gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 3; // 🔹 Ocupa o espaço restante
        gbc.weightx = fieldWeight;
        topPanel.add(objetivoField, gbc);

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

        JPanel tablesPanel = new JPanel(new GridLayout(2, 1, 10, 10));

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

        salvarButton = new JButton("Salvar");

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(20, 0, 20, 0);
        bottomPanel.add(salvarButton, gbc);

        add(bottomPanel, BorderLayout.SOUTH);

        configureItemButtons();
        configureColaboradorButtons();
        configSalvarBtn();
    }

    private void configureColaboradorButtons() {

        addColaboradorButton.setPreferredSize(new Dimension(180, 28));
        removeColaboradorButton.setPreferredSize(new Dimension(180, 28));
        addColaboradorButton.setIcon(iconAdd);
        removeColaboradorButton.setIcon(iconRemove);

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

        iconAdd.setColorFilter(null);
        iconRemove.setColorFilter(null);
        addItemButton.setIcon(iconAdd);
        removeItemButton.setIcon(iconRemove);
        addItemButton.setPreferredSize(new Dimension(180, 28));
        removeItemButton.setPreferredSize(new Dimension(180, 28));

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

        UIutils.styleButton(salvarButton);
        salvarButton.addActionListener(e -> {
            try {

                String nome = nomeAcaoField.getText();
                String descricao = descricaoField.getText();
                String objetivo = objetivoField.getText();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataInicio;
                LocalDate dataFim;

                try {
                    dataInicio = LocalDate.parse(dataInicioField.getText(),formatter);
                    dataFim = LocalDate.parse(dataFimField.getText(), formatter);
                } catch (DateTimeParseException ex) {
                    throw new RuntimeException("Data inválida!");
                }

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
                JOptionPane.showMessageDialog(this, "Erro ao salvar ação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        });
    }
}
