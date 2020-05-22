package com.example.a3job.Classes;

public class BatFamilia {

       public String nome;
    public String idade;

    public BatFamilia(String nome, String idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public BatFamilia() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}
