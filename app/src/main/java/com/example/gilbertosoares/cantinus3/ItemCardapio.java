package com.example.gilbertosoares.cantinus3;

/**
 * Created by Heraclito on 10/10/2017.
 */

public class ItemCardapio {

    String nome;
    String descricao;
    double preco;
    int tipoCardapio;


    public ItemCardapio(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public ItemCardapio(String nome, String descricao, double preco, int tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tipoCardapio=tipo;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getTipoCardapio() {
        return tipoCardapio;
    }

    public void setTipoCardapio(int tipoCardapio) {
        this.tipoCardapio = tipoCardapio;
    }
}
