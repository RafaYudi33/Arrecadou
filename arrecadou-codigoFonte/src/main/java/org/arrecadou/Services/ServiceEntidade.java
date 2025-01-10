package org.arrecadou.Services;

import org.arrecadou.Model.Entidade;
import org.arrecadou.Repositories.EntidadeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceEntidade {
    private final EntidadeRepository entidadeRepository;


    public ServiceEntidade(EntidadeRepository entidadeRepository) {
        this.entidadeRepository = entidadeRepository;
    }

    public Optional<Entidade> findFirst(){
        return this.entidadeRepository.findFirstEntidade();
    }

    public void cadastrarEntidade(String nome, String cnpj, String telefone, String logradouro, String bairro, String cidade, String uf, String cep, String numero) {
        this.entidadeRepository.save(
                new Entidade(nome, cnpj, telefone, logradouro, bairro, cidade, uf, cep, numero)
        );
    }
}
