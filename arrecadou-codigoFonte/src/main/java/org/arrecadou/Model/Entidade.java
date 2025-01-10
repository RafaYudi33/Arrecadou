package org.arrecadou.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;
    private String telefone;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String numero;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "entidade")
    private List<Acao> acoes;

    public Entidade(String numero, String cep, String uf, String cidade, String bairro, String logradouro, String telefone, String cnpj, String nome) {
        this.numero = numero;
        this.cep = cep;
        this.uf = uf;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.nome = nome;
        this.acoes = new ArrayList<>();
    }




    public Entidade() {}


    public List<Acao> getAcoes() {
        return acoes;
    }

    public void setAcoes(List<Acao> acoes) {
        this.acoes = acoes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entidade entidade)) return false;
        return Objects.equals(getId(), entidade.getId()) && Objects.equals(getNome(), entidade.getNome()) && Objects.equals(getCnpj(), entidade.getCnpj()) && Objects.equals(getTelefone(), entidade.getTelefone()) && Objects.equals(getLogradouro(), entidade.getLogradouro()) && Objects.equals(getBairro(), entidade.getBairro()) && Objects.equals(getCidade(), entidade.getCidade()) && Objects.equals(getUf(), entidade.getUf()) && Objects.equals(getCep(), entidade.getCep()) && Objects.equals(getNumero(), entidade.getNumero()) && Objects.equals(acoes, entidade.acoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCnpj(), getTelefone(), getLogradouro(), getBairro(), getCidade(), getUf(), getCep(), getNumero(), acoes);
    }

    public void addAcao(Acao acao) {
        acoes.add(acao);
    }
}
