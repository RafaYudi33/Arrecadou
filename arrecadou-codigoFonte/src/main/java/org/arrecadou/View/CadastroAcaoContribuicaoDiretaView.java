package org.arrecadou.View;

import org.arrecadou.Controladores.ControllerAcaoContribuicaoDireta;
import org.arrecadou.Controladores.ControllerCoordenador;
import org.arrecadou.Model.Coordenador;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class CadastroAcaoContribuicaoDiretaView extends JFrame {
    private JTextField nomeField, descricaoField, objetivoField, dataInicioField, dataFimField;
    private JList<Coordenador> coordenadorList;
    private final ControllerAcaoContribuicaoDireta controllerAcao;
    private final ControllerCoordenador controllerCoordenador;

    public CadastroAcaoContribuicaoDiretaView(ControllerAcaoContribuicaoDireta controllerAcao, ControllerCoordenador controllerCoordenador) {
        this.controllerAcao = controllerAcao;
        this.controllerCoordenador = controllerCoordenador;
        initializeUI();
        setTitle("Cadastro de Ação de Contribuição Direta");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initializeUI() {
        getContentPane().setLayout(new BorderLayout(5, 5));

        JPanel textPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        nomeField = new JTextField();
        descricaoField = new JTextField();
        objetivoField = new JTextField();
        dataInicioField = new JTextField();
        dataInicioField.setToolTipText("Formato YYYY-MM-DD");
        dataFimField = new JTextField();
        dataFimField.setToolTipText("Formato YYYY-MM-DD");

        textPanel.add(new JLabel("Nome:"));
        textPanel.add(nomeField);
        textPanel.add(new JLabel("Descrição:"));
        textPanel.add(descricaoField);
        textPanel.add(new JLabel("Objetivo da Ação:"));
        textPanel.add(objetivoField);
        textPanel.add(new JLabel("Data Início:"));
        textPanel.add(dataInicioField);
        textPanel.add(new JLabel("Data Fim:"));
        textPanel.add(dataFimField);

        getContentPane().add(textPanel, BorderLayout.NORTH);

        JPanel coordenadorPanel = new JPanel(new BorderLayout());
        coordenadorPanel.add(new JLabel("Selecionar Coordenador(es)"), BorderLayout.NORTH);
        DefaultListModel<Coordenador> coordenadorModel = new DefaultListModel<>();
        List<Coordenador> coordenadores = controllerCoordenador.listarTodosCoordenadores();
        coordenadores.forEach(coordenadorModel::addElement);
        coordenadorList = new JList<>(coordenadorModel);
        coordenadorList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane listScroller = new JScrollPane(coordenadorList);
        coordenadorPanel.add(listScroller, BorderLayout.CENTER);
        getContentPane().add(coordenadorPanel, BorderLayout.CENTER);

        JButton submitButton = new JButton("Cadastrar");
        submitButton.addActionListener(e -> cadastrarAcao());
        getContentPane().add(submitButton, BorderLayout.PAGE_END);

        pack();  // Ajusta o tamanho da janela baseado nos subcomponentes
    }

    private void cadastrarAcao() {
        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        String objetivo = objetivoField.getText();
        LocalDate dataInicio = LocalDate.parse(dataInicioField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate dataFim = LocalDate.parse(dataFimField.getText(), DateTimeFormatter.ISO_LOCAL_DATE);
        List<Coordenador> coordenadoresSelecionados = coordenadorList.getSelectedValuesList();


        try {
            controllerAcao.cadastrarAcaoContribuicaoDireta(coordenadoresSelecionados, dataFim.atStartOfDay(), dataInicio.atStartOfDay(), objetivo, descricao, nome);
            JOptionPane.showMessageDialog(this, "Ação cadastrada com sucesso!", "Cadastro Completo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.WARNING_MESSAGE);
        }

    }
}
