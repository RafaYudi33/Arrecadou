package org.arrecadou.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String objetivoAcao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "acao_coordenador",
            joinColumns = @JoinColumn(name = "acao_id"),
            inverseJoinColumns = @JoinColumn(name = "coordenador_id")
    )
    private List<Coordenador> coordenadores;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "entidade_id", foreignKey = @ForeignKey(name = "FK_acao_entidade"))
    private Entidade entidade;




    public Acao(Entidade entidade, List<Coordenador> coordenadores, LocalDateTime dataFim, LocalDateTime dataInicio, String objetivoAcao, String descricao, String nome) {
        this.entidade = entidade;
        this.coordenadores = coordenadores;
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.objetivoAcao = objetivoAcao;
        this.descricao = descricao;
        this.nome = nome;
    }

    public Acao() {
    }

    public Long getId() {
        return id;
    }


    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }

    public List<Coordenador> getCoordenadores() {
        return coordenadores;
    }

    public void setCoordenadores(List<Coordenador> coordenadores) {
        this.coordenadores = coordenadores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObjetivoAcao() {
        return objetivoAcao;
    }

    public void setObjetivoAcao(String objetivoAcao) {
        this.objetivoAcao = objetivoAcao;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Acao acao)) return false;
        return Objects.equals(getId(), acao.getId()) && Objects.equals(getNome(), acao.getNome()) && Objects.equals(getDescricao(), acao.getDescricao()) && Objects.equals(getObjetivoAcao(), acao.getObjetivoAcao()) && Objects.equals(getDataInicio(), acao.getDataInicio()) && Objects.equals(getDataFim(), acao.getDataFim()) && Objects.equals(getCoordenadores(), acao.getCoordenadores()) && Objects.equals(getEntidade(), acao.getEntidade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getDescricao(), getObjetivoAcao(), getDataInicio(), getDataFim(), getCoordenadores(), getEntidade());
    }

    @Override
    public String toString() {
        return "Acao{" +
                ", nome='" + nome + '\'' +
                //", coordenadores='" + coordenadores + '\'' +
                '}';
    }
}
