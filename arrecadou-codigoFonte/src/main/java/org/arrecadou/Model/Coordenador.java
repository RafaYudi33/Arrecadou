package org.arrecadou.Model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Coordenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String telefone;

    @ManyToMany(mappedBy = "coordenadores", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Acao> acoesCoordenadas;



    public Coordenador(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.acoesCoordenadas = new ArrayList<>();
    }

    public Coordenador() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Acao> getAcoesCoordenadas() {
        return acoesCoordenadas;
    }

    public void setAcoesCoordenadas(List<Acao> acoesCoordenadas) {
        this.acoesCoordenadas = acoesCoordenadas;
    }

    public String getTelefone() {
        return telefone;
    }

    public void addAcao(Acao acao) {
        acoesCoordenadas.add(acao);
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Coordenador{" +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordenador that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome()) && Objects.equals(getCpf(), that.getCpf()) && Objects.equals(getTelefone(), that.getTelefone()) && Objects.equals(getAcoesCoordenadas(), that.getAcoesCoordenadas());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCpf(), getTelefone(), getAcoesCoordenadas());
    }
}
