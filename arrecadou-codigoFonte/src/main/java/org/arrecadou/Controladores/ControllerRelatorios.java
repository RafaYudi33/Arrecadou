package org.arrecadou.Controladores;

import org.arrecadou.Model.Acao;
import org.arrecadou.Services.ServiceGeradorRelatoriosPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerRelatorios {
    private final ServiceGeradorRelatoriosPDF serviceGeradorRelatoriosPDF;

    @Autowired
    public ControllerRelatorios(ServiceGeradorRelatoriosPDF serviceGeradorRelatoriosPDF) {
        this.serviceGeradorRelatoriosPDF = serviceGeradorRelatoriosPDF;
    }

    public void gerarRelatorioPDF(Acao acao, String caminho, double valorTotalVendas){
        serviceGeradorRelatoriosPDF.gerarRelatorioPDF(acao, caminho, valorTotalVendas);
    }
}
