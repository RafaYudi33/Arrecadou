package org.arrecadou.Model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AcaoContribuicaoDireta extends Acao{

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DoacaoDinheiro> doacoesDinheiro;

    public AcaoContribuicaoDireta(Entidade entidade, List<Coordenador> coordenadores, LocalDateTime dataFim, LocalDateTime dataInicio, String objetivoAcao, String descricao, String nome) {
        super(entidade, coordenadores, dataFim, dataInicio, objetivoAcao, descricao, nome);
        this.doacoesDinheiro = new ArrayList<>();
    }

    public AcaoContribuicaoDireta() {
        super();
    }

    public double calcularValorTotalArrecadado() {
        double total = 0;
        for (DoacaoDinheiro doacao : doacoesDinheiro) {
            total += doacao.getValor();
        }
        return total;
    }


    public List<DoacaoDinheiro> getDoacoesDinheiro() {
        return doacoesDinheiro;
    }
    public void setDoacoesDinheiro(List<DoacaoDinheiro> doacoesDinheiro) {
        this.doacoesDinheiro = doacoesDinheiro;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcaoContribuicaoDireta that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getDoacoesDinheiro(), that.getDoacoesDinheiro());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDoacoesDinheiro());
    }

    public void addDoacao(DoacaoDinheiro doacao) {
        this.doacoesDinheiro.add(doacao);
    }
}
