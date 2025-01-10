package org.arrecadou.Services;

import org.arrecadou.Model.AcaoContribuicaoDireta;
import org.arrecadou.Model.Coordenador;
import org.arrecadou.Model.DoacaoDinheiro;
import org.arrecadou.Model.Entidade;
import org.arrecadou.Repositories.AcaoContribuicaoDiretaRepository;
import org.arrecadou.Repositories.EntidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceAcaoContribuicaoDireta {

    private final AcaoContribuicaoDiretaRepository acaoContribuicaoDiretaRepository;
    private final EntidadeRepository entidadeRepository;

    @Autowired
    public ServiceAcaoContribuicaoDireta(AcaoContribuicaoDiretaRepository acaoContribuicaoDiretaRepository, EntidadeRepository entidadeRepository) {
        this.acaoContribuicaoDiretaRepository = acaoContribuicaoDiretaRepository;
        this.entidadeRepository = entidadeRepository;
    }

    public void cadastrarAcaoContribuicaoDireta(List<Coordenador> coordenadores, LocalDateTime dataFim, LocalDateTime dataInicio, String objetivoAcao, String descricao, String nome) {
        Entidade entidade = this.entidadeRepository.findById(1L).orElseThrow(()->new RuntimeException("Entidade não encontrada"));
        entidade.addAcao(new AcaoContribuicaoDireta(entidade, coordenadores, dataFim, dataInicio, objetivoAcao, descricao, nome));
        this.entidadeRepository.save(entidade);
    }

    public List<AcaoContribuicaoDireta> listarTodasAcoesContribuicaoDireta() {
        return this.acaoContribuicaoDiretaRepository.findAll();
    }

    public void addDoacao(String telefoneDoador, String nomeDoador, boolean isAnonimo, double valor, AcaoContribuicaoDireta acao) {
        acao.addDoacao(new DoacaoDinheiro(telefoneDoador, nomeDoador, isAnonimo, valor));
        this.acaoContribuicaoDiretaRepository.save(acao);
    }
}
