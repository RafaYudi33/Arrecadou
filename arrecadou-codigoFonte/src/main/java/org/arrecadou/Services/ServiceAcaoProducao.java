package org.arrecadou.Services;

import org.arrecadou.Model.*;
import org.arrecadou.Repositories.AcaoProducaoRepository;
import org.arrecadou.Repositories.EntidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceAcaoProducao {

    private final EntidadeRepository entidadeRepository;
    private final AcaoProducaoRepository acaoProducaoRepository;
    @Autowired
    public ServiceAcaoProducao(
            EntidadeRepository entidadeRepository,
            AcaoProducaoRepository acaoProducaoRepository
    ) {
        this.entidadeRepository = entidadeRepository;
        this.acaoProducaoRepository = acaoProducaoRepository;
    }

    public void cadastrarAcaoProducao(
            List<Coordenador> coordenadores,
            LocalDateTime dataFim,
            LocalDateTime dataInicio,
            String objetivo,
            String descricao,
            String nome,
            List<Colaborador> colaboradores,
            List<ItemEsperado> itensEsperados
    ) {
        Entidade entidade = this.entidadeRepository.findFirstEntidade()
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada!"));

        AcaoProducao acaoProducao = new AcaoProducao(
                entidade, coordenadores , dataFim, dataInicio, objetivo, descricao, nome, colaboradores, itensEsperados
        );
        coordenadores.forEach(c -> c.addAcao(acaoProducao));
        entidade.addAcao(acaoProducao);
        this.entidadeRepository.save(entidade);
    }

    public List<AcaoProducao> listarTodasAcoesProducao(){
        return this.acaoProducaoRepository.findAll();
    }

    public ItemEsperado cadastrarItemEsperado(String nome, int quantidadeEmKg, double valorKg) {
        return new ItemEsperado(nome, quantidadeEmKg, valorKg);
    }

    public Colaborador cadastrarColaborador(String nomeColaborador, String email, String telefone) {
        return new Colaborador(nomeColaborador, email, telefone);
    }

    public void cadastrarDoacaoDeItem( String telefoneDoador,
                                       String nomeDoador,
                                       boolean isAnonimo,
                                       String nome,
                                       int quantidadeEmKg,
                                       AcaoProducao acaoProducao) {
        DoacaoItem doacaoItem = new DoacaoItem(telefoneDoador, nomeDoador, isAnonimo, nome, quantidadeEmKg);
        acaoProducao.addDoacaoItem(doacaoItem);
        this.acaoProducaoRepository.save(acaoProducao);
    }

    public void cadastrarDoacaoDinheiro(
            String telefone, String nome, boolean isAnonimo, double valor, AcaoProducao acaoSelecionada
    ) {
        acaoSelecionada.addDoacaoDinheiro(new DoacaoDinheiro(telefone, nome, isAnonimo, valor));
        this.acaoProducaoRepository.save(acaoSelecionada);

    }
}
