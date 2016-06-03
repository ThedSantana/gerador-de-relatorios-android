package com.example.arthurpimentel.geradorrelatorio;

/**
 * Created by arthur.pimentel on 30/05/2016.
 */
public class Usuario {
    private String nome;
    private int idade;
    private String sexo;

    public Usuario(String nome, int idade, String sexo){
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}