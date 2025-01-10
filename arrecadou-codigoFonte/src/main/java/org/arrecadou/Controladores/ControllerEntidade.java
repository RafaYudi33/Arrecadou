package org.arrecadou.Controladores;

import org.arrecadou.Model.Entidade;
import org.arrecadou.Services.ServiceEntidade;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class ControllerEntidade {
    private final ServiceEntidade serviceEntidade;

    public ControllerEntidade(ServiceEntidade serviceEntidade) {
        this.serviceEntidade = serviceEntidade;
    }

    public Optional<Entidade> findFirst(){
        return this.serviceEntidade.findFirst();
    }

    public void cadastrarEntidade(String nome, String cnpj, String telefone, String logradouro, String bairro, String cidade, String uf, String cep, String numero) {
        this.serviceEntidade.cadastrarEntidade(nome, cnpj, telefone, logradouro, bairro, cidade, uf, cep, numero);
    }
}
