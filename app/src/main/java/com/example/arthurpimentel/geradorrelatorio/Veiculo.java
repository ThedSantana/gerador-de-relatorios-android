package com.example.arthurpimentel.geradorrelatorio;

/**
 * Created by arthur.pimentel on 31/05/2016.
 */
public class Veiculo {
    private String nome;
    private String cor;
    private String ano;
    private String condicao;
    private String marca;

    public Veiculo(String nome, String cor, String ano, String condicao, String marca){
        this.nome = nome;
        this.cor = cor;
        this.ano = ano;
        this.condicao = condicao;
        this.marca = marca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }



}