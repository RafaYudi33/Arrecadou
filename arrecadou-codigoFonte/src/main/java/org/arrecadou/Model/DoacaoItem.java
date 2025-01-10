package org.arrecadou.Model;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class DoacaoItem extends Doacao {

    private String nome;
    private int quantidadeEmKg;

    public DoacaoItem(String telefoneDoador, String nomeDoador, boolean isAnonimo, String nome, int quantidadeEmKg) {
        super(telefoneDoador, nomeDoador, isAnonimo);
        this.nome = nome;
        this.quantidadeEmKg = quantidadeEmKg;
    }

    public DoacaoItem() {

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeEmKg() {
        return quantidadeEmKg;
    }

    public void setQuantidadeEmKg(int quantidadeEmKg) {
        this.quantidadeEmKg = quantidadeEmKg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoacaoItem that)) return false;
        if (!super.equals(o)) return false;
        return getQuantidadeEmKg() == that.getQuantidadeEmKg() && Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNome(), getQuantidadeEmKg());
    }
}
