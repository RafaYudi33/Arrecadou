package org.arrecadou.Model;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class DoacaoDinheiro extends Doacao {


    private double valor;

    public DoacaoDinheiro(String telefoneDoador, String nomeDoador, boolean isAnonimo, double valor) {
        super(telefoneDoador, nomeDoador, isAnonimo);
        this.valor = valor;
    }

    public DoacaoDinheiro() {

    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoacaoDinheiro that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getValor(), that.getValor()) == 0;
    }

    @Override
    public String toString() {
        return super.toString()+
                "valor=" + valor +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getValor());
    }
}
