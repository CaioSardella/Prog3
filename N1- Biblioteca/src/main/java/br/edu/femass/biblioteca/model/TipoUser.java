package br.edu.femass.biblioteca.model;

public enum TipoUser {
    Professor ("Professor"),
    Aluno("Aluno");


    private String nome;
    TipoUser (String nome){
        this.nome = nome;
    }
}
