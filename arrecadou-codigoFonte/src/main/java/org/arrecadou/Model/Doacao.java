package org.arrecadou.Model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Doacao {

    private String telefoneDoador;
    private String nomeDoador;
    private boolean isAnonimo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Doacao(String telefoneDoador, String nomeDoador, boolean isAnonimo) {
        this.telefoneDoador = telefoneDoador;
        this.nomeDoador = nomeDoador;
        this.isAnonimo = isAnonimo;
    }

    public Doacao() {
    }

    public String getTelefoneDoador() {
        return telefoneDoador;
    }

    public void setTelefoneDoador(String telefoneDoador) {
        this.telefoneDoador = telefoneDoador;
    }

    public String getNomeDoador() {
        return nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    public boolean isAnonimo() {
        return isAnonimo;
    }

    public void setAnonimo(boolean anonimo) {
        isAnonimo = anonimo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doacao doacao)) return false;
        return isAnonimo() == doacao.isAnonimo() && Objects.equals(getTelefoneDoador(), doacao.getTelefoneDoador()) && Objects.equals(getNomeDoador(), doacao.getNomeDoador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTelefoneDoador(), getNomeDoador(), isAnonimo());
    }

    @Override
    public String toString() {
        return "Doacao{" +
                "telefoneDoador='" + telefoneDoador + '\'' +
                ", nomeDoador='" + nomeDoador + '\'' +
                ", isAnonimo=" + isAnonimo +
                '}';
    }


    public Long getId() {
        return id;
    }
}
