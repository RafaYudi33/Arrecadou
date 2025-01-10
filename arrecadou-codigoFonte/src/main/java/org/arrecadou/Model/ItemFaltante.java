package org.arrecadou.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class ItemFaltante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int quantidadeEmKg;

    public ItemFaltante(String nome, int quantidadeEmKg) {
        this.nome = nome;
        this.quantidadeEmKg = quantidadeEmKg;
    }

    public ItemFaltante() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof ItemFaltante that)) return false;
        return getQuantidadeEmKg() == that.getQuantidadeEmKg() && Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getQuantidadeEmKg());
    }
}
