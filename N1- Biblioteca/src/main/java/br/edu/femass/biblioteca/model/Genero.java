package br.edu.femass.biblioteca.model;

public enum Genero {
    Ação ("ção"),
    Aventura ("Aventura"),
    Suspense ("Suspense"),
    Terror ("Terror"),
    Ficção ("Ficção"),
    Fantasia ("Fantasia"),
    Academico ("Acadêmico"),
    Filosofia ("Filosofia");


    private String nome;
    Genero (String nome){
        this.nome = nome;
    }
}
