package org.arrecadou.View;

import org.arrecadou.Config.AppConfig;
import org.arrecadou.Config.JpaConfig;
import org.arrecadou.Controladores.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

@SuppressWarnings("ExtractMethodRecommender")
public class MainScreen extends JFrame {
    private static AnnotationConfigApplicationContext context;
    private static ControllerAcaoContribuicaoDireta controllerAcaoContribuicaoDireta;
    private static ControllerCoordenador controllerCoordenador;
    private static ControllerAcaoProducao controllerAcaoProducao;
    private static ControllerRelatorios controllerRelatorios;
    private static ControllerEntidade controllerEntidade;

    public MainScreen() {
        initializeUI();
        setupMenu();
    }

    private static void initializeContext() {
        context = new AnnotationConfigApplicationContext(AppConfig.class, JpaConfig.class);
        controllerAcaoContribuicaoDireta = context.getBean(ControllerAcaoContribuicaoDireta.class);
        controllerCoordenador = context.getBean(ControllerCoordenador.class);
        controllerAcaoProducao = context.getBean(ControllerAcaoProducao.class);
        controllerRelatorios = context.getBean(ControllerRelatorios.class);
        controllerEntidade = context.getBean(ControllerEntidade.class);
    }

    private void initializeUI() {
        setTitle("Arrecadou");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);


        ImageIcon originalImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/img.png")));
        Image backgroundImage = originalImage.getImage();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(panel);
        setSize(originalImage.getIconWidth(), originalImage.getIconHeight());
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                closeContext();
            }
        });
    }

    private void setupMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuCoordenador = new JMenu("Coordenador");
        JMenuItem itemCadastrarCoordenador = new JMenuItem("Cadastrar Coordenador");
        itemCadastrarCoordenador.addActionListener(actionEvent -> openCadastroCoordenadorView());
        menuCoordenador.add(itemCadastrarCoordenador);
        menuBar.add(menuCoordenador);

        JMenu menuAcaoEventoComida = new JMenu("Ação Produção Evento de Comida");
        JMenuItem itemCadastrarAcaoEvento = new JMenuItem("Cadastrar Ação");
        JMenuItem itemCadastrarDoacaoItem = new JMenuItem("Cadastrar Doação de Item");
        JMenuItem itemCadastrarDoacaoDinheiro = new JMenuItem("Cadastrar Doação em dinheiro");

        menuAcaoEventoComida.add(itemCadastrarAcaoEvento);
        menuAcaoEventoComida.add(itemCadastrarDoacaoItem);
        menuAcaoEventoComida.add(itemCadastrarDoacaoDinheiro);
        menuBar.add(menuAcaoEventoComida);
        itemCadastrarAcaoEvento.addActionListener(actionEvent -> openCadastroAcaoDeProducaoView());
        itemCadastrarDoacaoItem.addActionListener(actionEvent -> openCadastrarDoacaoItemAcaoDeProducaoView());
        itemCadastrarDoacaoDinheiro.addActionListener(actionEvent -> openCadastrarDoacaoDinheiroParaAcaoDeProducao());

        JMenu menuAcaoContribuicaoDireta = new JMenu("Ação Contribuição Direta");
        JMenuItem itemCadastrarAcaoContribuicao = new JMenuItem("Cadastrar Ação");
        itemCadastrarAcaoContribuicao.addActionListener(actionEvent -> openCadastroAcaoContribuicaoDiretaView());
        menuAcaoContribuicaoDireta.add(itemCadastrarAcaoContribuicao);
        JMenuItem itemCadastrarDoacao = new JMenuItem("Cadastrar Doação");
        itemCadastrarDoacao.addActionListener(actionEvent -> openCadastroDoacaoParaAcaoContribuicaoDiretaView());
        menuAcaoContribuicaoDireta.add(itemCadastrarDoacao);
        menuBar.add(menuAcaoContribuicaoDireta);

        JMenu menuRelatorios = new JMenu("Relatorios");

        JMenuItem itemRelatorioFinalAcao = new JMenuItem("Relatorio Final das Ações");

        menuRelatorios.add(itemRelatorioFinalAcao);
        itemRelatorioFinalAcao.addActionListener(actionEvent -> openRelatorioFinalAcaoView());
        menuBar.add(menuRelatorios);

        setJMenuBar(menuBar);
    }

    private void closeContext() {
        if (context != null) {
            context.close();
        }
    }


    private void openRelatorioFinalAcaoView() {
        SwingUtilities.invokeLater(() -> {
            RelatorioFinalAcoes screen = new RelatorioFinalAcoes(controllerRelatorios, controllerAcaoProducao, controllerAcaoContribuicaoDireta);
            screen.setVisible(true);
        });
    }

    private void openCadastroAcaoContribuicaoDiretaView() {
        SwingUtilities.invokeLater(() -> {
            CadastroAcaoContribuicaoDiretaView screen = new CadastroAcaoContribuicaoDiretaView(
                    controllerAcaoContribuicaoDireta, controllerCoordenador
            );
            screen.setVisible(true);
        });
    }

    private void openCadastrarDoacaoItemAcaoDeProducaoView () {
        SwingUtilities.invokeLater(() -> {
            CadastrarDoacaoItemAcaoDeProducaoView screen = new CadastrarDoacaoItemAcaoDeProducaoView(
                    controllerAcaoProducao
            );
            screen.setVisible(true);
        });
    }

    private void openCadastrarDoacaoDinheiroParaAcaoDeProducao () {
        SwingUtilities.invokeLater(() -> {
            CadastrarDoacaoDinheiroParaAcaoDeProducao screen = new CadastrarDoacaoDinheiroParaAcaoDeProducao(
                    controllerAcaoProducao
            );
            screen.setVisible(true);
        });
    }


    private void openCadastroCoordenadorView() {
        SwingUtilities.invokeLater(() -> {
            CadastroCoordenadorView screen = new CadastroCoordenadorView(controllerCoordenador);
            screen.setVisible(true);
        });
    }

    private void openCadastroDoacaoParaAcaoContribuicaoDiretaView() {
        SwingUtilities.invokeLater(() -> {
            CadastrarDoacaoParaAcaoContribuicaoDiretaView screen = new CadastrarDoacaoParaAcaoContribuicaoDiretaView(
                    controllerAcaoContribuicaoDireta
            );
            screen.setVisible(true);
        });
    }

    private void openCadastroAcaoDeProducaoView() {
        SwingUtilities.invokeLater(() -> {
            CadastrarAcaoDeProducaoView screen = new CadastrarAcaoDeProducaoView(
                    controllerCoordenador, controllerAcaoProducao
            );
            screen.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            initializeContext();
            if (controllerEntidade.findFirst().isEmpty()){
                CadastrarEntidade cadastrarEntidadeView = new CadastrarEntidade(controllerEntidade);
                cadastrarEntidadeView.setVisible(true);
            }else{
                MainScreen ms = new MainScreen();
                ms.setVisible(true);
            }
        });
    }
}
