package org.arrecadou.Controladores;


import org.arrecadou.Model.Coordenador;
import org.arrecadou.Services.ServiceCoordenador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ControllerCoordenador {

    private final ServiceCoordenador serviceCoordenador;

    @Autowired
    public ControllerCoordenador(ServiceCoordenador serviceCoordenador) {
        this.serviceCoordenador = serviceCoordenador;
    }

    public void cadastrarCoordenador(String nome, String cpf, String telefone) {
        this.serviceCoordenador.cadastrarCoordenador(nome, cpf, telefone);
    }

    public List<Coordenador> listarTodosCoordenadores(){
        return this.serviceCoordenador.listarTodosCoordenadores();
    }
}
