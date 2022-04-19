package br.edu.femass.biblioteca.model;

import javafx.beans.binding.BooleanExpression;
import lombok.Data;

@Data
public class Livro {
    private String nomeLivro;
    private Genero genero;
    private String edicao;
    private String autor;
    private String ano;
    public Integer codigoLivro;
    public Boolean fixo;
    public static Integer proximoCodigo = 1;

    public Livro() {
        codigoLivro = proximoCodigo;
        proximoCodigo++;
    }
}
